package com.huajiao.parkingsystem.ui;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
import com.huajiao.parkingsystem.MainActivity;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.base.BaseActivity;
import com.huajiao.parkingsystem.tools.CheckUtil;

public class ForgetPasswordActivty extends BaseActivity implements View.OnClickListener{
    private EditText mMobileEt;
    private EditText mVerificationEt;
    private EditText mNewPasswordEt;
    private EditText mConfirmPasswordEt;
    private Button mSendVerificationBt;
    private Button mConfirmBt;

    private String mobile;
    private String verification;
    private String password;
    private String confirmPassword;
    /**
     * @return {int} {当前布局的layoutid}
     * 使用方式 直接返回需要setContentView的LayoutId
     */
    @Override
    protected int getViewContentId() {
        return R.layout.forget_password;
    }

    /**
     * 在这里初始化你的数据 ps:总在initView之前调用
     */
    @Override
    protected void initData() {

    }

    /**
     * 在这里初始化你的控件引用
     */
    @Override
    protected void initView() {
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white));
          mMobileEt=findViewById(R.id.edit_mobile_phone);
          mVerificationEt=findViewById(R.id.edit_verification_code);
          mNewPasswordEt=findViewById(R.id.edit_input_news_password);
          mConfirmPasswordEt=findViewById(R.id.edit_confirm_password);
          mSendVerificationBt=findViewById(R.id.send_verificaton_code);
          mConfirmBt=findViewById(R.id.confirm_btn);
    }


    /***
     * 在这里添加你的控件的任何事件绑定
     */
    @Override
    protected void bindEvent() {
        mMobileEt.addTextChangedListener(new ClassOfTextWatcher(mMobileEt));
        mVerificationEt.addTextChangedListener(new ClassOfTextWatcher(mVerificationEt));
        mNewPasswordEt.addTextChangedListener(new ClassOfTextWatcher(mNewPasswordEt));
        mConfirmPasswordEt.addTextChangedListener(new ClassOfTextWatcher(mConfirmPasswordEt));
        setCursorToEnd(mMobileEt);
        setCursorToEnd(mVerificationEt);
        setCursorToEnd(mNewPasswordEt);
        setCursorToEnd(mConfirmPasswordEt);
        mConfirmBt.setOnClickListener(this);
        mSendVerificationBt.setOnClickListener(this);
    }

    /**
     * 提供给需要获得网络数据
     */
    @Override
    protected void getInternetData() {

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.send_verificaton_code:
                if(isNext()){
                    
                }
                break;
            case R.id.confirm_btn:
                openActivity(MainActivity.class);
                break;
        }
    }

    public boolean isNext(){
        if(!CheckUtil.checkString(mobile)){
            showToast("账号不能为空");
            return false;
        }
        if(!CheckUtil.checkPhone(mobile)){
            showToast("账号不符合规则请填写手机号码");
            return false;
        }
        if (!CheckUtil.checkString(verification)){
            showToast("验证码不能为空");
            return false;

        }
        if (!CheckUtil.checkString(password)){
            showToast("密码不能为空");
            return false;

        }
        if (!CheckUtil.checkString(confirmPassword)){
            showToast("再次密码不能为空");
            return false;
        }
        if (!CheckUtil.checkStringContent(password,confirmPassword)){
            showToast("两次输入密码不相同");
            return false;
        }
        return true;
    }

    private class ClassOfTextWatcher implements TextWatcher
    {
        private TextView view;

        public ClassOfTextWatcher(View view) {

            if (view instanceof TextView)
                this.view = (TextView) view;
            else
                throw new ClassCastException(
                        "view must be an instance Of TextView");
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() >= 0) {
                switch (this.view.getId())
                {
                    case R.id.edit_mobile_phone:
                        mobile=s.toString();
                        break;
                    case R.id.edit_verification_code:
                        verification=s.toString();
                        break;
                    case R.id.edit_input_news_password:
                        password=s.toString();
                        break;
                    case R.id.edit_confirm_password:
                        confirmPassword=s.toString();
                        break;
                }
            }
        }
    }
}

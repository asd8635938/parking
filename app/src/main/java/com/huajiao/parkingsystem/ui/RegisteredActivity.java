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

public class RegisteredActivity extends BaseActivity implements View.OnClickListener{
    private EditText mMobleEt;
    private EditText mVerificationEt;
    private EditText mNewPasswordEt;
    private EditText mConfirmPasswordEt;
    private EditText mInviterEt;

    private Button mSendVerificationBt;
    private Button mRegisteredBt;
    private Button mGoToLoginBt;

    private String mobile;
    private String verification;
    private String password;
    private String confirmPassword;
    private String invitecode;
    /**
     * 在这里初始化你的数据 ps:总在initView之前调用
     */
    @Override
    protected void initData() {

    }
    /**
     * @return {int} {当前布局的layoutid}
     * 使用方式 直接返回需要setContentView的LayoutId
     */
    @Override
    protected int getViewContentId() {
        return R.layout.registered;
    }

    /**
     * 在这里初始化你的控件引用
     */
    @Override
    protected void initView() {
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white));
          mMobleEt=findViewById(R.id.edit_mobile_phone);
          mVerificationEt=findViewById(R.id.edit_verification_code);
          mNewPasswordEt=findViewById(R.id.edit_input_news_password);
          mConfirmPasswordEt=findViewById(R.id.edit_confirm_password);
          mInviterEt=findViewById(R.id.edit_invite_code);

          mSendVerificationBt=findViewById(R.id.send_verificaton_code);
          mRegisteredBt=findViewById(R.id.registered_btn);
          mGoToLoginBt=findViewById(R.id.go_login);
    }


    /***
     * 在这里添加你的控件的任何事件绑定
     */
    @Override
    protected void bindEvent() {
        mSendVerificationBt.setOnClickListener(this);
        mRegisteredBt.setOnClickListener(this);
        mGoToLoginBt.setOnClickListener(this);
        mMobleEt.addTextChangedListener(new ClassOfTextWatcher(mMobleEt));
        mVerificationEt.addTextChangedListener(new ClassOfTextWatcher(mVerificationEt));
        mNewPasswordEt.addTextChangedListener(new ClassOfTextWatcher(mNewPasswordEt));
        mConfirmPasswordEt.addTextChangedListener(new ClassOfTextWatcher(mConfirmPasswordEt));
        mInviterEt.addTextChangedListener(new ClassOfTextWatcher(mInviterEt));
        setCursorToEnd(mMobleEt);
        setCursorToEnd(mVerificationEt);
        setCursorToEnd(mNewPasswordEt);
        setCursorToEnd(mConfirmPasswordEt);
        setCursorToEnd(mInviterEt);
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
                break;
            case R.id.registered_btn:
                break;
            case R.id.go_login:
                openActivity(MainActivity.class);
                break;
        }
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
                    case R.id.edit_invite_code:
                        invitecode=s.toString();
                        break;
                }
            }
        }
    }
}

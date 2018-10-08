package com.huajiao.parkingsystem;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.huajiao.parkingsystem.base.BaseActivity;
import com.huajiao.parkingsystem.ui.ForgetPasswordActivty;
import com.huajiao.parkingsystem.ui.RegisteredActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private EditText mMobileEt;
    private EditText mPasswordEt;
    private Button mForgetPasswordBt;
    private Button mLoginBt;
    private Button mRegisteredBt;
    private ImageView mWeChatLoginIv;
    private ImageView mQQLoginIv;
    private ImageView mWeiBoLoginIv;

    private String mobile;
    private String password;
    /**
     * @return {int} {当前布局的layoutid}
     * 使用方式 直接返回需要setContentView的LayoutId
     */
    @Override
    protected int getViewContentId() {
        return R.layout.activity_main;
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
        mMobileEt=findViewById(R.id.edit_mobile_phone);
        mPasswordEt=findViewById(R.id.edit_input_password);
        mForgetPasswordBt=findViewById(R.id.forget_password);
        mLoginBt=findViewById(R.id.login_btn);
        mRegisteredBt=findViewById(R.id.registered_btn);
        mWeChatLoginIv=findViewById(R.id.wechat_login_btn);
        mQQLoginIv=findViewById(R.id.qq_login_btn);
        mWeiBoLoginIv=findViewById(R.id.weibo_login_btn);
    }

    /***
     * 在这里添加你的控件的任何事件绑定
     */
    @Override
    protected void bindEvent() {
        mForgetPasswordBt.setOnClickListener(this);
        mLoginBt.setOnClickListener(this);
        mRegisteredBt.setOnClickListener(this);
        mWeChatLoginIv.setOnClickListener(this);
        mQQLoginIv.setOnClickListener(this);
        mWeiBoLoginIv.setOnClickListener(this);
        mMobileEt.addTextChangedListener(new ClassOfTextWatcher(mMobileEt));
        setCursorToEnd(mMobileEt);
        mPasswordEt.addTextChangedListener(new ClassOfTextWatcher(mPasswordEt));
        setCursorToEnd(mPasswordEt);
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
            case R.id.forget_password:
                openActivity(ForgetPasswordActivty.class);
                break;
            case R.id.login_btn:
                break;
            case R.id.registered_btn:
                openActivity(RegisteredActivity.class);
                break;
            case R.id.wechat_login_btn:
                showToast("暂为实现");
                break;
            case R.id.qq_login_btn:
                showToast("暂为实现");
                break;
            case R.id.weibo_login_btn:
                showToast("暂为实现");
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
                    case R.id.edit_input_password:
                        password=s.toString();
                        break;
                }
            }
        }
    }
}

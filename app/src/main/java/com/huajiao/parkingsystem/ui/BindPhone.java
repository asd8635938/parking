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

public class BindPhone extends BaseActivity implements View.OnClickListener{
    private EditText mMobileEt;
    private EditText mVerificationEt;
    private Button  mSendVerificationBt;
    private Button mConfirmBt;

    private String mobile;
    private String verification;
    /**
     * @return {int} {当前布局的layoutid}
     * 使用方式 直接返回需要setContentView的LayoutId
     */
    @Override
    protected int getViewContentId() {
        return R.layout.bind_phone;
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
        setCursorToEnd(mMobileEt);
        setCursorToEnd(mVerificationEt);
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
                break;
            case R.id.confirm_btn:
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
                }
            }
        }
    }
}

package com.huajiao.parkingsystem.ui;



import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.base.BaseActivity;
import com.huajiao.parkingsystem.tools.CheckUtil;

import butterknife.ButterKnife;

public class PayActivity extends BaseActivity implements View.OnClickListener{
    private EditText alipay_account_et;
    private EditText input_alipay_account_et;
    private EditText name_et;
    private EditText phone_et;
    private EditText verification_code_et;
    private TextView get_verification_code;
    private TextView   confirm_btn;

    private String alipayAccount;
    private String inputAccount;
    private String name;
    private String iphone;
    private String verificationCode;

    @Override
    protected int getViewContentId() {
        return R.layout.activity_pay;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.title));
        setTitleText("支付宝提现");
        alipay_account_et=findViewById(R.id.alipay_account_et);
        input_alipay_account_et=findViewById(R.id.input_alipay_account_et);
        name_et=findViewById(R.id.name_et);
        phone_et=findViewById(R.id.phone_et);
        verification_code_et=findViewById(R.id.verification_code_et);
        get_verification_code=findViewById(R.id.get_verification_code);
        confirm_btn=findViewById(R.id.confirm_btn);
    }


    @Override
    protected void bindEvent() {
        get_verification_code.setOnClickListener(this);
        confirm_btn.setOnClickListener(this);
        alipay_account_et.addTextChangedListener(new ClassOfTextWatcher(alipay_account_et));
        input_alipay_account_et.addTextChangedListener(new ClassOfTextWatcher(input_alipay_account_et));
        name_et.addTextChangedListener(new ClassOfTextWatcher(name_et));
        phone_et.addTextChangedListener(new ClassOfTextWatcher(phone_et));
        verification_code_et.addTextChangedListener(new ClassOfTextWatcher(verification_code_et));
    }

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
        switch (v.getId()){
            case R.id.get_verification_code:
                if (!CheckUtil.checkString(iphone)){
                    showToast("请输入手机号");
                    return;
                }
                sendGetVerification(iphone);
                break;
            case R.id.confirm_btn:
                if (!CheckUtil.checkString(alipayAccount)||
                        !CheckUtil.checkString(inputAccount)
                        ||!CheckUtil.checkString(name)||
                        !CheckUtil.checkString(iphone)||!CheckUtil.checkString(verificationCode)){
                    showToast("请完善资料");
                    return;
                }
                // 网络请求成功后跳转提交页面
                break;
        }
    }

    private void sendGetVerification(String iphone) {

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
                    case R.id.alipay_account_et:
                        alipayAccount=s.toString();
                        break;
                    case R.id.input_alipay_account_et:
                        inputAccount=s.toString();
                        break;
                    case R.id.name_et:
                        name=s.toString();
                        break;
                    case R.id.phone_et:
                        iphone=s.toString();
                        break;
                    case R.id.verification_code_et:
                        verificationCode=s.toString();
                        break;
                }
            }
        }
    }
}

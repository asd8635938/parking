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

public class BindCarNumber extends BaseActivity {
    private EditText mMobileEt;
    private String mobile;
    private Button submit_btn;

    /**
     * @return {int} {当前布局的layoutid}
     * 使用方式 直接返回需要setContentView的LayoutId
     */
    @Override
    protected int getViewContentId() {
        return R.layout.bind_car_number;
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
        submit_btn=findViewById(R.id.submit_btn);
    }

    /***
     * 在这里添加你的控件的任何事件绑定
     */
    @Override
    protected void bindEvent() {
        mMobileEt.addTextChangedListener(new ClassOfTextWatcher(mMobileEt));
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!CheckUtil.checkString(mobile)){
                    showToast("手机号不能为空");
                    return;
                }
                if (!CheckUtil.checkPhone(mobile)){
                    showToast("手机号不符合规则");
                    return;
                }

            }
        });
    }

    /**
     * 提供给需要获得网络数据
     */
    @Override
    protected void getInternetData() {

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
                }
            }
        }
    }
}

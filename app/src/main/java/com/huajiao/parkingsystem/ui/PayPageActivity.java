package com.huajiao.parkingsystem.ui;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.base.BaseActivity;

public class PayPageActivity extends BaseActivity implements View.OnClickListener{
    private Button pay_btn;
    private LinearLayout pay_confirm;
    private EditText input_pay_coin;
    private ImageView close;
    /**
     * @return {int} {当前布局的layoutid}
     * 使用方式 直接返回需要setContentView的LayoutId
     */
    @Override
    protected int getViewContentId() {
        return R.layout.pay_page;
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
        pay_btn=findViewById(R.id.pay_btn);
        pay_confirm=findViewById(R.id.pay_confirm);
        input_pay_coin=findViewById(R.id.input_pay_coin);
        close=findViewById(R.id.close);
    }

    /***
     * 在这里添加你的控件的任何事件绑定
     */
    @Override
    protected void bindEvent() {
        pay_btn.setOnClickListener(this);
        close.setOnClickListener(this);

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
        switch (v.getId()){
            case R.id.pay_btn:
                pay_confirm.setVisibility(View.VISIBLE);
                break;
            case R.id.close:
                pay_confirm.setVisibility(View.GONE);
                break;
        }
    }
}

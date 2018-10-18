package com.huajiao.parkingsystem.ui;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.base.BaseActivity;

public class PayPageActivity extends BaseActivity implements View.OnClickListener{
    private Button pay_btn;
    private LinearLayout pay_confirm;
    private EditText input_pay_coin;
    private ImageView close;
    private String coin;
    private ImageView wechat_btn;
    private ImageView alipay_btn;
    private Button confirm_btn;
    private TextView pay_coin;
    private int payType=-1; // 1代表微信支付 2代表支付宝支付
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
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.title));
        setTitleText("充值");
        pay_btn=findViewById(R.id.pay_btn);
        pay_confirm=findViewById(R.id.pay_confirm);
        input_pay_coin=findViewById(R.id.input_pay_coin);

        wechat_btn=findViewById(R.id.wechat_btn);
        alipay_btn=findViewById(R.id.alipay_btn);
        confirm_btn=findViewById(R.id.confirm_btn);
        pay_coin=findViewById(R.id.pay_coin);

        close=findViewById(R.id.close);
        pay_confirm.setVisibility(View.GONE);
    }

    /***
     * 在这里添加你的控件的任何事件绑定
     */
    @Override
    protected void bindEvent() {
        pay_btn.setOnClickListener(this);
        close.setOnClickListener(this);
        wechat_btn.setOnClickListener(this);
        alipay_btn.setOnClickListener(this);
        confirm_btn.setOnClickListener(this);
        input_pay_coin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                coin=s.toString();
            }
        });

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
                if (coin!=null&&!coin.equals("")){
                    confirm_btn.setText("确认支付"+coin+"元");
                    pay_confirm.setVisibility(View.VISIBLE);
                    pay_coin.setText(coin);
                }else {
                    showToast("请输入充值金额");
                }
                break;
            case R.id.close:
                confirm_btn.setText("");
                pay_coin.setText("");
                alipay_btn.setSelected(false);
                wechat_btn.setSelected(false);
                pay_confirm.setVisibility(View.GONE);
                break;
            case R.id.wechat_btn:
                payType=1;
                wechat_btn.setSelected(true);
                alipay_btn.setSelected(false);
                break;
            case R.id.alipay_btn:
                payType=2;
                alipay_btn.setSelected(true);
                wechat_btn.setSelected(false);
                break;
            case R.id.confirm_btn:
                if(payType!=-1){

                }else {
                    showToast("请选择支付类型");
                }
                break;
        }
    }
}

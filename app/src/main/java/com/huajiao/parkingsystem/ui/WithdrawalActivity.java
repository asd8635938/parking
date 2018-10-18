package com.huajiao.parkingsystem.ui;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.base.BaseActivity;

public class WithdrawalActivity extends BaseActivity implements View.OnClickListener{
    private TextView coin_text;
    private EditText coin_ed;
    private ImageView wechat_btn;
    private ImageView alipay_btn;
    private Button next;
    private int payType=-1; // 1是微信支付 2 是支付宝支付
    private String coin;
    /**
     * @return {int} {当前布局的layoutid}
     * 使用方式 直接返回需要setContentView的LayoutId
     */
    @Override
    protected int getViewContentId() {
        return R.layout.withdrawal;
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
        setTitleText("提现");
        coin_text=findViewById(R.id.coin_text);
        coin_ed=findViewById(R.id.coin_ed);
        wechat_btn=findViewById(R.id.wechat_btn);
        alipay_btn=findViewById(R.id.alipay_btn);
        next=findViewById(R.id.next);
        coin_text.setText("2541");
    }

    /***
     * 在这里添加你的控件的任何事件绑定
     */
    @Override
    protected void bindEvent() {
        wechat_btn.setOnClickListener(this);
        alipay_btn.setOnClickListener(this);
        next.setOnClickListener(this);
        coin_ed.addTextChangedListener(new TextWatcher() {
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
                case R.id.next:
                    if(coin==null||coin.equals("")){
                        showToast("请输入充值金额");
                        return;
                    }
                    if(payType==-1){
                        showToast("请选择支付类型");
                        return;
                    }
                    switch (payType){
                        case 1:
                            openActivity(SubmitApply.class);
                            break;
                        case 2:
                            openActivity(PayActivity.class);
                            break;
                    }
                    break;
            }
    }
}

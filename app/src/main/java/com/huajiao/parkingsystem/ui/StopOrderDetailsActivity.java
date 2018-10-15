package com.huajiao.parkingsystem.ui;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.githang.statusbar.StatusBarCompat;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.base.BaseActivity;
import com.huajiao.parkingsystem.dialog.TimeKeepingPayDialog;

public class StopOrderDetailsActivity extends BaseActivity implements TimeKeepingPayDialog.ClickListenerInterface {
    private boolean isUse;
    private LinearLayout no_use_layout;
    private LinearLayout use_layout;
    private Button pay_btn;
    private TimeKeepingPayDialog dialog;
    /**
     * @return {int} {当前布局的layoutid}
     * 使用方式 直接返回需要setContentView的LayoutId
     */
    @Override
    protected int getViewContentId() {
        return R.layout.stop_order_details;
    }

    /**
     * 在这里初始化你的数据 ps:总在initView之前调用
     */
    @Override
    protected void initData() {
        isUse= getIntent().getBooleanExtra("state",true);
    }

    /**
     * 在这里初始化你的控件引用
     */
    @Override
    protected void initView() {
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.title));
        setTitleText("订单详情");
        no_use_layout=findViewById(R.id.no_use_layout);
        use_layout=findViewById(R.id.use_layout);
        pay_btn=findViewById(R.id.pay_btn);
        dialog=new TimeKeepingPayDialog(this);
        dialog.setClicklistener(this);
        if (isUse){
            no_use_layout.setVisibility(View.GONE);
            use_layout.setVisibility(View.VISIBLE);
        }else{
            use_layout.setVisibility(View.GONE);
            no_use_layout.setVisibility(View.VISIBLE);
        }
    }

    /***
     * 在这里添加你的控件的任何事件绑定
     */
    @Override
    protected void bindEvent() {
        pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }

    /**
     * 提供给需要获得网络数据
     */
    @Override
    protected void getInternetData() {

    }

    @Override
    public void doConfirm() {
        dialog.dismiss();
    }

    @Override
    public void openSelcetDiscountActivity() {
        openActivity(SelectDiscountActivity.class);
    }
}

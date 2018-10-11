package com.huajiao.parkingsystem.ui;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.base.BaseActivity;

public class WeCenter extends BaseActivity implements View.OnClickListener{

    private ImageView center_image; // 基本资料
    private TextView name;
    private TextView id;
    private ImageView my_money_package; // 我的钱包
    private ImageView my_record; // 我的记录
    private ImageView my_coowner; // 我是车主
    private ImageView share_btn; // 分享
    private ImageView account_btn; // 账户安全
    private ImageView query_btn; // 违章查询
    private ImageView setting_btn; // 设置
    /**
     * @return {int} {当前布局的layoutid}
     * 使用方式 直接返回需要setContentView的LayoutId
     */
    @Override
    protected int getViewContentId() {
        return R.layout.we_center;
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
        center_image=findViewById(R.id.center_image);
    }

    /***
     * 在这里添加你的控件的任何事件绑定
     */
    @Override
    protected void bindEvent() {
        center_image.setOnClickListener(this);
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
            case R.id.center_image:
                openActivity(BasicDataActivity.class);
                break;
        }
    }
}

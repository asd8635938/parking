package com.huajiao.parkingsystem.ui;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.base.BaseActivity;

public class WeCenterActivity extends BaseActivity implements View.OnClickListener{

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
    private TextView  click_share;
    private TextView  click_account;
    private TextView  click_query;
    private TextView  click_setting;
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
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.title_bg));
        center_image=findViewById(R.id.center_image);
        my_money_package=findViewById(R.id.my_money_package);
        my_record=findViewById(R.id.my_record);
        share_btn=findViewById(R.id.share_btn);
        account_btn=findViewById(R.id.account_btn);
        query_btn=findViewById(R.id.query_btn);
        setting_btn=findViewById(R.id.setting_btn);
        my_coowner=findViewById(R.id.my_coowner);
        click_share=findViewById(R.id.click_share);
        click_account=findViewById(R.id.click_account);
        click_query=findViewById(R.id.click_query);
        click_setting=findViewById(R.id.click_setting);
    }

    /***
     * 在这里添加你的控件的任何事件绑定
     */
    @Override
    protected void bindEvent() {
        center_image.setOnClickListener(this);
        my_money_package.setOnClickListener(this);
        my_record.setOnClickListener(this);
        share_btn.setOnClickListener(this);
        account_btn.setOnClickListener(this);
        query_btn.setOnClickListener(this);
        setting_btn.setOnClickListener(this);
        my_coowner.setOnClickListener(this);
        click_setting.setOnClickListener(this);
        click_share.setOnClickListener(this);
        click_account.setOnClickListener(this);
        click_query.setOnClickListener(this);
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
            case R.id.my_money_package:
                openActivity(MyMoneyPackageActivity.class);
                break;
            case R.id.my_record:
                openActivity(StopParkingRecordActivity.class);
                break;
            case R.id.share_btn:
                openActivity(ShareFriendActivity.class);
                break;
            case R.id.click_share:
                openActivity(ShareFriendActivity.class);
                break;
            case R.id.account_btn:
                openActivity(AlterPasswordActivty.class);
                break;
            case R.id.click_account:
                openActivity(AlterPasswordActivty.class);
                break;
            case R.id.query_btn:
                showToast("暂时没有第三方");
                break;
            case R.id.click_query:
                showToast("暂时没有第三方");
                break;
            case R.id.setting_btn:
               openActivity(SettingActivity.class);
                break;
            case R.id.click_setting:
               openActivity(SettingActivity.class);
                break;
            case R.id.my_coowner:
               openActivity(MyParkingMain.class);
                break;
        }
    }
}

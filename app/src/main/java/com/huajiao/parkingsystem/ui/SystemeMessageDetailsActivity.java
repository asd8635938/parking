package com.huajiao.parkingsystem.ui;

import android.content.Intent;
import android.widget.TextView;

import com.huajiao.parkingsystem.Ben.SystemeMessageData;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.base.BaseActivity;

public class SystemeMessageDetailsActivity extends BaseActivity {

    private TextView name;
    private TextView time;
    private TextView textContent;
    private SystemeMessageData data;
    /**
     * @return {int} {当前布局的layoutid}
     * 使用方式 直接返回需要setContentView的LayoutId
     */
    @Override
    protected int getViewContentId() {
        return R.layout.systeme_message_details;
    }

    /**
     * 在这里初始化你的数据 ps:总在initView之前调用
     */
    @Override
    protected void initData() {
        data = (SystemeMessageData) getIntent().getSerializableExtra("SystemeMessageData");
    }

    /**
     * 在这里初始化你的控件引用
     */
    @Override
    protected void initView() {
        name=findViewById(R.id.name);
        time=findViewById(R.id.time);
        textContent=findViewById(R.id.text_content);
        name.setText(data.getName());
        time.setText(data.getTime());
        textContent.setText(data.getContent());
    }

    /***
     * 在这里添加你的控件的任何事件绑定
     */
    @Override
    protected void bindEvent() {

    }

    /**
     * 提供给需要获得网络数据
     */
    @Override
    protected void getInternetData() {

    }
}

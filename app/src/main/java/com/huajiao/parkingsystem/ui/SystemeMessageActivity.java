package com.huajiao.parkingsystem.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.huajiao.parkingsystem.Ben.SystemeMessageData;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.adapter.SystemeMessageAdapter;
import com.huajiao.parkingsystem.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class SystemeMessageActivity extends BaseActivity {

    private ListView mListView;
    private List<SystemeMessageData> mList = new ArrayList<>();
    private SystemeMessageAdapter mAdapter;
    /**
     * @return {int} {当前布局的layoutid}
     * 使用方式 直接返回需要setContentView的LayoutId
     */
    @Override
    protected int getViewContentId() {
        return R.layout.system_message;
    }

    /**
     * 在这里初始化你的数据 ps:总在initView之前调用
     */
    @Override
    protected void initData() {
        for (int i=0;i<5;i++){
            SystemeMessageData data=new SystemeMessageData();
            data.setTime("2018/10/05");
            data.setName("消息标题");
            data.setContent("哈哈哈哈哈哈哈哈哈哈哈");
            mList.add(data);
        }
    }

    /**
     * 在这里初始化你的控件引用
     */
    @Override
    protected void initView() {
        mListView=findViewById(R.id.list_view);
        mAdapter=new SystemeMessageAdapter(this);
        mAdapter.setDate(mList);
        mListView.setAdapter(mAdapter);
    }

    /***
     * 在这里添加你的控件的任何事件绑定
     */
    @Override
    protected void bindEvent() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(SystemeMessageActivity.this,SystemeMessageDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("SystemeMessageData", mList.get(position));
                intent.putExtras(bundle);
                openActivity(intent);
            }
        });
    }

    /**
     * 提供给需要获得网络数据
     */
    @Override
    protected void getInternetData() {

    }
}

package com.huajiao.parkingsystem.ui;

import android.widget.ListView;

import com.huajiao.parkingsystem.Ben.RunningRecordData;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.adapter.RunningRecordAdapter;
import com.huajiao.parkingsystem.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class RunningRecordActivity extends BaseActivity {

    private RunningRecordAdapter mAdapter;
    private List<RunningRecordData> mList = new ArrayList<>();
    private ListView list_view;
    /**
     * @return {int} {当前布局的layoutid}
     * 使用方式 直接返回需要setContentView的LayoutId
     */
    @Override
    protected int getViewContentId() {
        return R.layout.running_record;
    }

    /**
     * 在这里初始化你的数据 ps:总在initView之前调用
     */
    @Override
    protected void initData() {
        for (int i=0;i<10;i++){
            RunningRecordData data=new RunningRecordData();
            boolean win=(i+1)%2==0?true:false;
            data.setWin(win);
            data.setCoin("12654"+i);
            data.setMatters("支付");
            data.setTime("2018/03/05 20:22");
            mList.add(data);
        }
    }

    /**
     * 在这里初始化你的控件引用
     */
    @Override
    protected void initView() {
        list_view=findViewById(R.id.list_view);
        mAdapter=new RunningRecordAdapter(this);
        mAdapter.setDate(mList);
        list_view.setAdapter(mAdapter);
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

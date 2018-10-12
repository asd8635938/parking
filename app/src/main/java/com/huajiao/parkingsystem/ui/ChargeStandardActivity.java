package com.huajiao.parkingsystem.ui;

import android.view.View;
import android.widget.ListView;

import com.huajiao.parkingsystem.Ben.ChargeStandardData;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.adapter.ChargeStandardAdapter;
import com.huajiao.parkingsystem.base.BaseActivity;
import com.huajiao.parkingsystem.interfaceback.SelectChargeClick;

import java.util.ArrayList;
import java.util.List;

public class ChargeStandardActivity extends BaseActivity implements SelectChargeClick {

    private ListView list_view;
    private ChargeStandardAdapter mAdapter;
    private List<ChargeStandardData> mList = new ArrayList<>();

    /**
     * @return {int} {当前布局的layoutid}
     * 使用方式 直接返回需要setContentView的LayoutId
     */
    @Override
    protected int getViewContentId() {
        return R.layout.charge_standard;
    }

    /**
     * 在这里初始化你的数据 ps:总在initView之前调用
     */
    @Override
    protected void initData() {
        for (int i=0;i<5;i++){
            ChargeStandardData data=new ChargeStandardData();
            data.setCotent("没有收费规则免费哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈啊哈哈哈哈哈哈哈哈哈哈哈");
            data.setName("A");
            mList.add(data);
        }
    }

    /**
     * 在这里初始化你的控件引用
     */
    @Override
    protected void initView() {
        isShowSaveBtn(View.VISIBLE);
        list_view=findViewById(R.id.list_view);
        mAdapter=new ChargeStandardAdapter(this,this);
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

    @Override
    protected void callbackSaveBtn() {
        //关闭当前activity 并且传递数据
        finish();
        super.callbackSaveBtn();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isShowSaveBtn(View.GONE);
    }

    @Override
    public void clickBack(int couponId, ChargeStandardData data, boolean isSelect) {

    }
}

package com.huajiao.parkingsystem.ui;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.huajiao.parkingsystem.Ben.SearchData;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.adapter.SearchAdapter;
import com.huajiao.parkingsystem.base.BaseActivity;
import com.huajiao.parkingsystem.interfaceback.NavigationClick;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SearchActivity extends BaseActivity implements NavigationClick{
    private EditText searchEt;
    private ListView mListView;
    private SearchAdapter mAdapter;
    private List<SearchData> mList =new ArrayList<>();
    /**
     * @return {int} {当前布局的layoutid}
     * 使用方式 直接返回需要setContentView的LayoutId
     */
    @Override
    protected int getViewContentId() {
        return R.layout.search;
    }

    /**
     * 在这里初始化你的数据 ps:总在initView之前调用
     */
    @Override
    protected void initData() {
        for (int i=0;i<5;i++){
            SearchData data=new SearchData();
            data.setName("我的停车场");
            data.setDataType(0);
            data.setDistanceNumber(300);
            data.setFreeDurationContent("12分钟内免费");
            data.setResidueContent("30位");
            data.setResidueNoCommonContent("53位");
            data.setCanCommonNumber(true);
            mList.add(data);
        }
    }

    /**
     * 提供给需要获得网络数据
     */
    @Override
    protected void getInternetData() {

    }

    /**
     * 在这里初始化你的控件引用
     */
    @Override
    protected void initView() {
        setTitleText("搜索");
        searchEt=findViewById(R.id.edit_search);
        mListView=findViewById(R.id.list_view);
        mAdapter=new SearchAdapter(this,this);
        setAdapterData(mList);
        mListView.setAdapter(mAdapter);
    }
    private void setAdapterData(List<SearchData> list){
        mAdapter.setDate(list);
    }

    /***
     * 在这里添加你的控件的任何事件绑定
     */
    @Override
    protected void bindEvent() {
        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public void clickBack(int position, int dataType) {

    }
}

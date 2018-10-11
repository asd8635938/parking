package com.huajiao.parkingsystem.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.huajiao.parkingsystem.Ben.SearchData;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.adapter.SearchAdapter;
import com.huajiao.parkingsystem.base.AbsFragment;
import com.huajiao.parkingsystem.interfaceback.NavigationClick;

import java.util.ArrayList;
import java.util.List;

public class ParkingSpaceFragment extends AbsFragment implements View.OnClickListener,NavigationClick {
    private Button all;
    private Button charging;
    private ListView listView;

    private SearchAdapter mAdapter;
    private List<SearchData> mList =new ArrayList<>(); // 充电桩

    private List<SearchData> mListTwo =new ArrayList<>(); // 普通

    @Override
    public int getLayoutId() {
        return R.layout.parking_space_fragment;
    }

    @Override
    public void onViewLoad()
    {
        super.onViewLoad();
    }

    @Override
    public void onDataLoad()
    {
        super.onDataLoad();
    }

    @Override
    public void onViewCreated()
    {
        setAdapterData();
        initView();
        setOnClick();
    }

    private void setAdapterData() {
        for (int i=0;i<5;i++){
            SearchData data=new SearchData();
            data.setName("我的停车场");
            data.setDistanceNumber(300);
            data.setFreeDurationContent("12分钟内免费");
            data.setResidueContent("30位");
            data.setResidueNoCommonContent("53位");
            data.setCanCommonNumber(true);
            mList.add(data);
        }
        for (int i=0;i<5;i++){
            SearchData data=new SearchData();
            data.setName("我的停车场");
            data.setDistanceNumber(300);
            data.setFreeDurationContent("12分钟内免费");
            data.setResidueContent("30位");
            data.setResidueNoCommonContent("53位");
            data.setCanCommonNumber(false);
            mListTwo.add(data);
        }
    }

    private void setOnClick() {
        all.setOnClickListener(this);
        charging.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),ParkingDetails.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("SearchData", mList.get(position));
                intent.putExtras(bundle);
                getActivity().startActivity(intent);
            }
        });
    }

    private void initView() {
        all=mView.findViewById(R.id.all);
        charging=mView.findViewById(R.id.charging);
        listView=mView.findViewById(R.id.list_view);
        mAdapter=new SearchAdapter(getActivity(),this);
        mAdapter.setDate(mList);
        listView.setAdapter(mAdapter);
        all.setBackgroundResource(R.drawable.greenbtn);
        charging.setBackgroundResource(0);
        all.setTextColor(Color.parseColor("#ffffff"));
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.all:
                all.setBackgroundResource(R.drawable.greenbtn);
                charging.setBackgroundResource(0);
                mAdapter.setDate(mList);
                all.setTextColor(Color.parseColor("#ffffff"));
                charging.setTextColor(Color.parseColor("#898989"));
                break;
            case R.id.charging:
                charging.setBackgroundResource(R.drawable.greenbtn);
                all.setBackgroundResource(0);
                mAdapter.setDate(mListTwo);
                charging.setTextColor(Color.parseColor("#ffffff"));
                all.setTextColor(Color.parseColor("#898989"));
                break;
        }
    }

    @Override
    public void clickBack(int position, int dataType) {

    }
}

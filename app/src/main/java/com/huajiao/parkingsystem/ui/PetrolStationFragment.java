package com.huajiao.parkingsystem.ui;

import android.widget.ListView;

import com.huajiao.parkingsystem.Ben.SearchData;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.adapter.SearchAdapter;
import com.huajiao.parkingsystem.base.AbsFragment;
import com.huajiao.parkingsystem.interfaceback.NavigationClick;

import java.util.ArrayList;
import java.util.List;

public class PetrolStationFragment extends AbsFragment implements NavigationClick{

    private ListView listView;

    private SearchAdapter mAdapter;
    private List<SearchData> mList =new ArrayList<>(); // 充电桩
    @Override
    public int getLayoutId() {
        return R.layout.petrol_station_fragment;
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
            data.setDataType(1);
            data.setDistanceNumber(300);
            data.setFreeDurationContent("12分钟内免费");
            data.setResidueContent("30位");
            data.setResidueNoCommonContent("53位");
            data.setCanCommonNumber(true);
            mList.add(data);
        }
    }

    private void setOnClick() {

    }

    private void initView() {
        listView=mView.findViewById(R.id.list_view);
        mAdapter=new SearchAdapter(getActivity(),this);
        mAdapter.setDate(mList);
        listView.setAdapter(mAdapter);
    }

    @Override
    public void clickBack(int position, int dataType) {

    }
}

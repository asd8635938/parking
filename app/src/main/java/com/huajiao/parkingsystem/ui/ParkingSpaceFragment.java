package com.huajiao.parkingsystem.ui;

import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.base.AbsFragment;

public class ParkingSpaceFragment extends AbsFragment {
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

        initView();
        setOnClick();
        setAdapterData();
    }

    private void setAdapterData() {

    }

    private void setOnClick() {

    }

    private void initView() {

    }
}

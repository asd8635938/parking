package com.huajiao.parkingsystem.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.base.BaseActivity;

public class ParkingMessageActivity extends BaseActivity implements View.OnClickListener{
   private TextView parkingText;
   private TextView petrolStatio;
   private ParkingSpaceFragment parkingSpaceFragment;
   private PetrolStationFragment petrolStationFragment;
   private FragmentTransaction transaction;
   private FragmentManager frgmentManager;
   private  View parking_text_cross;
   private  View petrol_station_text_cross;
    /**
     * @return {int} {当前布局的layoutid}
     * 使用方式 直接返回需要setContentView的LayoutId
     */
    @Override
    protected int getViewContentId() {
        return R.layout.parking_message;
    }

    /**
     * 在这里初始化你的数据 ps:总在initView之前调用
     */
    @Override
    protected void initData() {
        parkingSpaceFragment=new ParkingSpaceFragment();
        petrolStationFragment=new PetrolStationFragment();
    }

    /**
     * 在这里初始化你的控件引用
     */
    @Override
    protected void initView() {
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.title));
        parkingText=findViewById(R.id.parking_text);
        parking_text_cross=findViewById(R.id.parking_text_cross);

        petrolStatio=findViewById(R.id.petrol_station_text);
        petrol_station_text_cross=findViewById(R.id.petrol_station_text_cross);

        frgmentManager = getFragmentManager();
        transaction = frgmentManager.beginTransaction();
        transaction.add(R.id.fragment,parkingSpaceFragment).commit();
    }

    /***
     * 在这里添加你的控件的任何事件绑定
     */
    @Override
    protected void bindEvent() {
        parkingText.setOnClickListener(this);
        petrolStatio.setOnClickListener(this);
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
        transaction = frgmentManager.beginTransaction();
        switch (v.getId()){
            case R.id.parking_text:
                parkingText.setTextColor(Color.parseColor("#27c38a"));
                petrolStatio.setTextColor(Color.parseColor("#898989"));
                parkingSpaceFragment=new ParkingSpaceFragment();
                transaction.replace(R.id.fragment,parkingSpaceFragment).commit();
                parking_text_cross.setVisibility(View.VISIBLE);
                petrol_station_text_cross.setVisibility(View.GONE);
                break;
            case R.id.petrol_station_text:
                petrolStatio.setTextColor(Color.parseColor("#27c38a"));
                parkingText.setTextColor(Color.parseColor("#898989"));
                petrolStationFragment=new PetrolStationFragment();
                transaction.replace(R.id.fragment,petrolStationFragment).commit();
                parking_text_cross.setVisibility(View.GONE);
                petrol_station_text_cross.setVisibility(View.VISIBLE);
                break;
        }
    }
}

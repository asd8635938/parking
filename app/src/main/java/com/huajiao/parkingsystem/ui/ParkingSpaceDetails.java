package com.huajiao.parkingsystem.ui;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.Toast;

import com.githang.statusbar.StatusBarCompat;
import com.huajiao.parkingsystem.Ben.ParkingDetailsData;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.adapter.ParkingDetailsAdapter;
import com.huajiao.parkingsystem.base.BaseActivity;
import com.huajiao.parkingsystem.dialog.ParkingDetailsDialog;
import com.huajiao.parkingsystem.dialog.TimeKeepingPayDialog;
import com.huajiao.parkingsystem.interfaceback.ParkingDetailsClick;

import java.util.ArrayList;
import java.util.List;

public class ParkingSpaceDetails extends BaseActivity implements ParkingDetailsClick {
    private int buttonType;
    private ListView list_view;
    private ParkingDetailsAdapter mAdapter;
    private List<ParkingDetailsData> mList = new ArrayList<>();
    private ParkingDetailsDialog dialog;
    private TimeKeepingPayDialog tDialog;

    private List<String> whenData =new ArrayList<>();
    private List<String> secondsData=new ArrayList<>();
    private List<String> timeData=new ArrayList<>();

    /**
     * @return {int} {当前布局的layoutid}
     * 使用方式 直接返回需要setContentView的LayoutId
     */
    @Override
    protected int getViewContentId() {
        return R.layout.parking_space_details;
    }

    /**
     * 在这里初始化你的数据 ps:总在initView之前调用
     */
    @Override
    protected void initData() {
        buttonType= getIntent().getIntExtra("buttonType",0);
        for (int i=0;i<10;i++){
            ParkingDetailsData data= new ParkingDetailsData();
            data.setSerial(i+"");
            data.setParkingSerial("48423"+i);
            boolean isReservation=(i+1)%2==0?true:false;
            data.setCanReservation(isReservation);
            mList.add(data);
        }
        for (int i =0; i<24;i++){
            whenData.add((i+1)+"");
        }
        for (int i = 0; i < 6; i++) {
            secondsData.add(((i+1)*10)+"");
        }

        for (int i = 0; i <4 ; i++) {

            if(i==3){
                timeData.add("1");
                break;
            }
            timeData.add(((i+1)*10)+"");
        }

    }

    /**
     * 在这里初始化你的控件引用
     */
    @Override
    protected void initView() {
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.title));
        setTitleText("车位详情");
        list_view=findViewById(R.id.list_view);
        mAdapter=new ParkingDetailsAdapter(this,this);
        mAdapter.setDate(mList);
        list_view.setAdapter(mAdapter);
        dialog=new ParkingDetailsDialog(this);
        tDialog=new TimeKeepingPayDialog(this);
        tDialog.setCoin(50);
        dialog.setWhenData(whenData);
        dialog.setSecondsData(secondsData);
        dialog.setTimeData(timeData);
    }

    /***
     * 在这里添加你的控件的任何事件绑定
     */
    @Override
    protected void bindEvent() {
        dialog.setClicklistener(new ParkingDetailsDialog.ClickListenerInterface() {
            @Override
            public void doConfirm() {
                dialog.dismiss();
                tDialog.show();
            }
        });
        tDialog.setClicklistener(new TimeKeepingPayDialog.ClickListenerInterface() {
            @Override
            public void doConfirm() {
                tDialog.dismiss();
            }

            @Override
            public void openSelcetDiscountActivity() {
                Intent intent = new Intent();
                intent.setClass(ParkingSpaceDetails.this,SelectDiscountActivity.class);
                openForResultActivity(intent,1);
            }
        });
    }

    /**
     * 提供给需要获得网络数据
     */
    @Override
    protected void getInternetData() {

    }

    @Override
    public void clickBack(ParkingDetailsData data) {
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       tDialog.setDiscountCouponCoin(data.getExtras().getInt("payCoin"));
    }
}

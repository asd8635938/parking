package com.huajiao.parkingsystem.ui;

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
                openActivity(SelectDiscountActivity.class);
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
}

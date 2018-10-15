package com.huajiao.parkingsystem.ui;

import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.githang.statusbar.StatusBarCompat;
import com.huajiao.parkingsystem.Ben.MyParkingMainData;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.adapter.MyParkingMainAdapter;
import com.huajiao.parkingsystem.base.BaseActivity;
import com.huajiao.parkingsystem.dialog.DialogUtils;

import java.util.ArrayList;
import java.util.List;

public class MyParkingMain extends BaseActivity implements View.OnClickListener{

    private Button withdrawal_btn;
    private ImageButton switch_btn;
    private LinearLayout charge_btn;
    private LinearLayout parking_space_type;
    private LinearLayout parking_space_serial_number;
    private LinearLayout parking_space_location;
    private ListView list_view;
    private MyParkingMainAdapter mAdapter;
    private List<MyParkingMainData> mList = new ArrayList<>();


    /**
     * @return {int} {当前布局的layoutid}
     * 使用方式 直接返回需要setContentView的LayoutId
     */
    @Override
    protected int getViewContentId() {
        return R.layout.my_parking_main;
    }

    /**
     * 在这里初始化你的数据 ps:总在initView之前调用
     */
    @Override
    protected void initData() {
        for (int i=0;i<10;i++){
            MyParkingMainData data =new MyParkingMainData();
            data.setName("麻花藤"+i);
            data.setCost("2"+i+"分钟");
            data.setState("￥3"+i);
            data.setTime("使用中");
            mList.add(data);
        }
    }

    /**
     * 在这里初始化你的控件引用
     */
    @Override
    protected void initView() {
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.title));
        isShowSaveBtn(View.VISIBLE);
        setShowSaveBtnText("注销车位");
        setTitleText("我是车位主");

        View mView = this.getLayoutInflater().inflate(R.layout.inclue_layout, null);

        withdrawal_btn=mView.findViewById(R.id.withdrawal_btn);
        switch_btn=mView.findViewById(R.id.switch_btn);
        charge_btn=mView.findViewById(R.id.charge_btn);
        parking_space_type= mView.findViewById(R.id.parking_space_type);
        parking_space_serial_number=mView.findViewById(R.id.parking_space_serial_number);
        parking_space_location=mView.findViewById(R.id.parking_space_location);

        list_view=findViewById(R.id.list_view);
        mAdapter=new MyParkingMainAdapter(this);
        mAdapter.setDate(mList);

        list_view.setAdapter(mAdapter);
        list_view.addHeaderView(mView);
    }

    /***
     * 在这里添加你的控件的任何事件绑定
     */
    @Override
    protected void bindEvent() {
        withdrawal_btn.setOnClickListener(this);
        switch_btn.setOnClickListener(this);
        charge_btn.setOnClickListener(this);
        parking_space_type.setOnClickListener(this);
        parking_space_serial_number.setOnClickListener(this);
        parking_space_location.setOnClickListener(this);
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
        switch(v.getId()){
            case R.id.withdrawal_btn:
                openActivity(PayActivity.class);
                break;
            case R.id.switch_btn:

                break;
            case R.id.charge_btn:
                openActivity(ChargeStandardActivity.class);
                break;
            case R.id.parking_space_type:
                DialogUtils.showMyParkingDialog(true, this, getWindow().getWindowManager().getDefaultDisplay().getWidth() - DialogUtils.dip2px(this, 40), null, new DialogUtils.ShowDialogCallBack() {
                    @Override
                    public void LeftClick(Dialog dialog) {
                        dialog.dismiss();
                    }

                    @Override
                    public void RightClick(Dialog dialog) {
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.parking_space_serial_number:
                DialogUtils.showDialog(false, this, getWindow().getWindowManager().getDefaultDisplay().getWidth() - DialogUtils.dip2px(this, 40), "提示", "确定要注销车位吗?", new DialogUtils.ShowDialogCallBack() {
                    @Override
                    public void LeftClick(Dialog dialog) {
                        dialog.dismiss();
                    }

                    @Override
                    public void RightClick(Dialog dialog) {
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.parking_space_location:
                    showToast("暂时没有定位数据");
                break;
        }
    }

    /**
     *
     */
    @Override
    protected void callbackSaveBtn() {
        DialogUtils.showDialog(false, this, getWindow().getWindowManager().getDefaultDisplay().getWidth() - DialogUtils.dip2px(this, 40), "提示", "确定要注销车位吗？", new DialogUtils.ShowDialogCallBack() {
            @Override
            public void LeftClick(Dialog dialog) {
                dialog.dismiss();
            }

            @Override
            public void RightClick(Dialog dialog) {
                dialog.dismiss();
            }
        });
        super.callbackSaveBtn();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isShowSaveBtn(View.GONE);
    }
}

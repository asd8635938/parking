package com.huajiao.parkingsystem.ui;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.provider.ContactsContract;
import android.support.constraint.ConstraintLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.base.BaseActivity;

public class HomePageActivity extends BaseActivity implements View.OnClickListener {
    private TextView mLocationText; // 用作按钮功能 ps：作用是列表选择按钮

    private TextView mStateText;
    private TextView mStateContent;
    private TextView mStateTime;
    private ConstraintLayout mStateLayout; // 状态的布局可能不需要显示

    private ImageView mImageMessage;
    private ImageView mGotoDetails;
    private ImageView mBannerView; // 广告位
    private ImageView mImageCloseBanner;
    private ImageView mRefreshBtn; // 百度地图相关
    private ImageView mLocationbBtn; // 百度地图相关
    private ImageView mListBtn;
    private ImageView mPersonaBtn;
    private ImageView mScanning;

    private MapView mMapView;
    private BaiduMap mBaiduMap;

    private EditText mEditSearch;
    /**
     * @return {int} {当前布局的layoutid}
     * 使用方式 直接返回需要setContentView的LayoutId
     */
    @Override
    protected int getViewContentId() {
        return R.layout.home_page;
    }

    /**
     * 在这里初始化你的数据 ps:总在initView之前调用
     */
    @Override
    protected void initData() {

    }

    /**
     * 在这里初始化你的控件引用
     */
    @Override
    protected void initView() {
        mStateText=findViewById(R.id.state_text);
        mStateContent=findViewById(R.id.state_content);
        mStateTime=findViewById(R.id.state_time);
        mStateLayout=findViewById(R.id.state_layout);
        mBannerView=findViewById(R.id.banner_view);
        // 以下有用户交互
        mLocationText=findViewById(R.id.location_text);
        mImageMessage=findViewById(R.id.image_message);
        mGotoDetails=findViewById(R.id.goto_details);
        mImageCloseBanner=findViewById(R.id.image_close_banner);
        mRefreshBtn=findViewById(R.id.refresh_btn);
        mLocationbBtn=findViewById(R.id.location_btn);
        mListBtn=findViewById(R.id.list_btn);
        mPersonaBtn=findViewById(R.id.persona_btn);
        mScanning=findViewById(R.id.scanning);
        mEditSearch=findViewById(R.id.edit_search);
        // 百度地图使用形式比较特殊 不设置点击事件 ps相当于一个布局容器
        mMapView=findViewById(R.id.baidu_map);
        mBaiduMap=mMapView.getMap();
    }

    /***
     * 在这里添加你的控件的任何事件绑定
     */
    @Override
    protected void bindEvent() {
        mLocationText.setOnClickListener(this);
        mImageMessage.setOnClickListener(this);
        mGotoDetails.setOnClickListener(this);
        mImageCloseBanner.setOnClickListener(this);
        mRefreshBtn.setOnClickListener(this);
        mLocationbBtn.setOnClickListener(this);
        mListBtn.setOnClickListener(this);
        mPersonaBtn.setOnClickListener(this);
        mScanning.setOnClickListener(this);
        mEditSearch.addTextChangedListener(new TextWatcher() {
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
        switch (v.getId())
        {
            case R.id.location_text:
                // 弹出选择列表框
                showSelectDialog();
                break;
            case R.id.image_message:
                // 跳转消息页面
                break;
            case R.id.goto_details:
                // 跳转详情页面 要做区分
                break;
            case R.id.image_close_banner:
                // 关闭banner图
                closeBanner();
                break;
            case R.id.refresh_btn:
                // 刷新当前自己的位置信息
                refreshLocation();
                break;
            case R.id.location_btn:
                // 跟随自己的位置
                followOneself();
                break;
            case R.id.list_btn:
                // 跳转到停车场信息页面
                break;
            case R.id.persona_btn:
                // 跳转到个人信息页面
                break;
            case R.id.scanning:
                // 跳转到自定义的二维码扫描页面
                break;
        }

    }
    // 百度地图移动到自己当前的位置
    private void followOneself() {
    }

    // 刷新百度地图自己的位置
    private void refreshLocation() {

    }

    // 关闭banner图
    private void closeBanner() {
        mBannerView.setVisibility(View.GONE);
    }

    //  显示地区选择框
    private void showSelectDialog() {
    }




    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
        //为系统的方向传感器注册监听器
//        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
//                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onStop() {
        //取消注册传感器监听
//        mSensorManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        // 退出时销毁定位
//        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }

}

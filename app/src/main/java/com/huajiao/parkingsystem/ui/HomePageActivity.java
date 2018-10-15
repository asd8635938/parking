package com.huajiao.parkingsystem.ui;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.githang.statusbar.StatusBarCompat;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.base.BaseActivity;

public class HomePageActivity extends BaseActivity implements View.OnClickListener,SensorEventListener {

    // 定位相关
    LocationClient mLocClient; // 定位客户端
    public MyLocationListenner myListener = new MyLocationListenner();
    private MyLocationConfiguration.LocationMode mCurrentMode;
    BitmapDescriptor mCurrentMarker;

    private static final int accuracyCircleFillColor = 0xAAFFFF88;
    private static final int accuracyCircleStrokeColor = 0xAA00FF00;
    private SensorManager mSensorManager; // 重力传感器相关
    private Double lastX = 0.0;
    private int mCurrentDirection = 0;
    private double mCurrentLat = 0.0;
    private double mCurrentLon = 0.0;
    private float mCurrentAccracy;
    private boolean isFirstLoc = true; // 是否第一次定位
    private MyLocationData locData;
    private LatLng oneSelf;


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
    private View roundBg;
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
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.title));
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
        roundBg=findViewById(R.id.round_bg);
//         百度地图使用形式比较特殊 不设置点击事件 ps相当于一个布局容器
        mMapView=findViewById(R.id.baidu_map);
        mBaiduMap=mMapView.getMap();
        mBaiduMap=mMapView.getMap();

        mCurrentMarker = null;
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);//获取传感器管理服务
        showMapView();
        showOneselfLocation();
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
        roundBg.setOnClickListener(this);
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
                // 跳转切换城市页面
                openActivity(SwitchOverCity.class);
                break;
            case R.id.image_message:
                // 跳转消息页面
                openActivity(SystemeMessageActivity.class);
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
                openActivity(ParkingMessageActivity.class);
                break;
            case R.id.persona_btn:
                // 跳转到个人信息页面
                openActivity(WeCenterActivity.class);
                break;
            case R.id.scanning:
                // 跳转到车位锁页面
                openActivity(ParkingSpaceLock.class);
                break;
            case R.id.round_bg:
                // 跳转到搜索页面
                openActivity(SearchActivity.class);
                break;
        }

    }

    private void showMapView() {
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();

    }

    private void showOneselfLocation() {
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(
                mCurrentMode, true, mCurrentMarker,
                accuracyCircleFillColor, accuracyCircleStrokeColor));
    }
    // 百度地图移动到自己当前的位置
    private void followOneself() {
        mCurrentMode = MyLocationConfiguration.LocationMode.FOLLOWING;
        mBaiduMap.setMyLocationConfigeration
                (new MyLocationConfiguration(mCurrentMode, true, null));
    }

    // 刷新百度地图自己的位置
    private void refreshLocation() {
        mBaiduMap.setMyLocationEnabled(true);
        mLocClient.start();
    }

    // 关闭banner图
    private void closeBanner() {
        mBannerView.setVisibility(View.GONE);
        mImageCloseBanner.setVisibility(View.GONE);
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
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onStop() {
        //取消注册传感器监听
        mSensorManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        // 退出时销毁定位
        mLocClient.stop();
//         关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        double x = event.values[SensorManager.DATA_X];
        if (Math.abs(x - lastX) > 1.0) {
            mCurrentDirection = (int) x;
            locData = new MyLocationData.Builder()
                    .accuracy(mCurrentAccracy)
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection).latitude(mCurrentLat)
                    .longitude(mCurrentLon).build();
            mBaiduMap.setMyLocationData(locData);
        }
        lastX = x;
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            mCurrentLat = location.getLatitude();
            mCurrentLon = location.getLongitude();
            mCurrentAccracy = location.getRadius();
            locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            oneSelf = new LatLng(location.getLatitude(),
                    location.getLongitude());
            if (isFirstLoc) {
                isFirstLoc = false;
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(oneSelf).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }

        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }

}

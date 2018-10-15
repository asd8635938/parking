package com.huajiao.parkingsystem.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
import com.huajiao.parkingsystem.Ben.CityData;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.adapter.SwitchOverCityAdapter;
import com.huajiao.parkingsystem.base.BaseActivity;
import com.huajiao.parkingsystem.tools.CharacterParser;
import com.huajiao.parkingsystem.tools.PinyinComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SwitchOverCity extends BaseActivity {

    private TextView presentCity;
    private ListView mListView;
    private SwitchOverCityAdapter mAdapter;
    private List<CityData> mList =new ArrayList<>();
    private PinyinComparator pinyinComparator;
    private String city;
    /**
     * @return {int} {当前布局的layoutid}
     * 使用方式 直接返回需要setContentView的LayoutId
     */
    @Override
    protected int getViewContentId() {
        return R.layout.switchover_city;
    }

    /**
     * 在这里初始化你的数据 ps:总在initView之前调用
     */
    @Override
    protected void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String[] date=getResources().getStringArray(R.array.date);
                for (int i=0;i<date.length;i++){
                    CityData cityData = new CityData();
                    cityData.setName(date[i]);
                    // 汉字转换成拼音
                    String pinyin = CharacterParser.getInstance().getSelling(date[i]);
                    String sortString = pinyin.substring(0, 1).toUpperCase();

                    // 正则表达式，判断首字母是否是英文字母
                    if (sortString.matches("[A-Z]")) {
                        cityData.setFristA(sortString.toUpperCase());
                    } else {
                        cityData.setFristA("#");
                    }
                    mList.add(cityData);
                }
                Message message = new Message();
                message.what = 1;
                myHandler.sendMessage(message);
            }
        }).start();

        city= getIntent().getStringExtra("city");
    }

    Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                 mAdapter.setDate(mList);
                    break;
            }
            super.handleMessage(msg);
        }
    };
    /**
     * 在这里初始化你的控件引用
     */
    @Override
    protected void initView() {
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.title));
        setTitleText("切换城市");
        presentCity=findViewById(R.id.present_city);
        mListView=findViewById(R.id.list_view);
        mAdapter=new SwitchOverCityAdapter(this);
        mListView.setAdapter(mAdapter);
        pinyinComparator = new PinyinComparator();
        presentCity.setText(city);
    }

    /***
     * 在这里添加你的控件的任何事件绑定
     */
    @Override
    protected void bindEvent() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                //把返回数据存入Intent
                intent.putExtra("city", mList.get(position).getName().toString());
                //设置返回数据
                SwitchOverCity.this.setResult(RESULT_OK, intent);
                //关闭Activity
                SwitchOverCity.this.finish();
            }
        });
    }

    /**
     * 提供给需要获得网络数据
     */
    @Override
    protected void getInternetData() {

    }

    public List<CityData> filledData(String[] date) {
        List<CityData> mSortList = new ArrayList<CityData>();

        for (int i = 0; i < date.length; i++) {
            CityData cityData = new CityData();
            cityData.setName(date[i]);
            // 汉字转换成拼音
            String pinyin = CharacterParser.getInstance().getSelling(date[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                cityData.setFristA(sortString.toUpperCase());
            } else {
                cityData.setFristA("#");
            }
            mSortList.add(cityData);
        }
        return mSortList;
    }
}

package com.huajiao.parkingsystem.ui;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.githang.statusbar.StatusBarCompat;
import com.huajiao.parkingsystem.Ben.StopParkingOrderData;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StopItActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private ListAdapter mListAdapter;
    private List<StopParkingOrderData> mList = new ArrayList<>();

    @Override
    protected int getViewContentId() {
        return R.layout.activity_stop_it;
    }

    @Override
    protected void initData() {
       StopParkingOrderData data1 = new StopParkingOrderData();
        data1.setStateType(0);
        data1.setTime("20108/03/01");
        data1.setState("预约中");
       StopParkingOrderData data2 = new StopParkingOrderData();
        data2.setStateType(1);
        data2.setTime("20108/03/02");
        data2.setState("已到达");
       StopParkingOrderData data3 = new StopParkingOrderData();
        data3.setStateType(2);
        data3.setTime("20108/03/03");
        data3.setState("已取消");
       StopParkingOrderData data4 = new StopParkingOrderData();
        data4.setStateType(3);
        data4.setTime("20108/03/04");
        data4.setState("已超时");
        mList.add(data1);
        mList.add(data2);
        mList.add(data3);
        mList.add(data4);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.title));
        setTitleText("预约停车订单");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mListAdapter = new ListAdapter(this);
        recyclerView.setAdapter(mListAdapter);
        mListAdapter.setNewData(mList);

        initOnClick();
    }

    private class ListAdapter extends BaseQuickAdapter<StopParkingOrderData, BaseViewHolder> {
        private Context mContext;
        public ListAdapter(Context mContext) {
            super(R.layout.stop_list_adapter);
            this.mContext = mContext;
        }

        @Override
        protected void convert(BaseViewHolder helper, final StopParkingOrderData item) {
            final TextView time = helper.getView(R.id.time);
            final TextView state =helper.getView(R.id.state);
            final TextView details=helper.getView(R.id.details);
            time.setText(item.getTime());
            if (item.getStateType()==0){
                state.setTextColor(Color.parseColor("#27c38a"));
            }else{
                state.setTextColor(Color.parseColor("#898989"));
            }
            state.setText(item.getState());
            details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(StopItActivity.this,AppointmentActivity.class);
                    intent.putExtra("type",item.getStateType());
                    openActivity(intent);
                }
            });
        }
    }

    private void initOnClick() {

    }

    @Override
    protected void bindEvent() {

    }

    @Override
    protected void getInternetData() {

    }
}

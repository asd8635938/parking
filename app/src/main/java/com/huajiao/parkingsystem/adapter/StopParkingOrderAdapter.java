package com.huajiao.parkingsystem.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.huajiao.parkingsystem.Ben.RunningRecordData;
import com.huajiao.parkingsystem.Ben.StopParkingOrderData;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.interfaceback.ParkingDetailsClick;
import com.huajiao.parkingsystem.interfaceback.StopParkingOrderClick;

import java.util.ArrayList;
import java.util.List;

public class StopParkingOrderAdapter extends BaseAdapter {
    private Context context;
    private List<StopParkingOrderData> mList = new ArrayList<>();
    private StopParkingOrderData mData;
    private StopParkingOrderClick click;

    public StopParkingOrderAdapter(Context context){
        this.context=context;
    } public StopParkingOrderAdapter(Context context, StopParkingOrderClick click){
        this.context=context;
        this.click=click;
    }

    public void setDate(List<StopParkingOrderData> list)
    {
        this.mList = list;
        notifyDataSetChanged();
    }
    private class ViewHolder {
        TextView state;
        TextView time;
        TextView coin;

    }


    @Override
    public int getCount() {
        return mList.size();
    }


    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder parkingView;
        if (convertView==null){
            parkingView=new ViewHolder();
            convertView=LayoutInflater.from(context).inflate(R.layout.running_record_item,null);
            parkingView.time=convertView.findViewById(R.id.matters);
            parkingView.state=convertView.findViewById(R.id.time);
            parkingView.coin=convertView.findViewById(R.id.coin);
            convertView.setTag(parkingView);
        }else {
            parkingView = (ViewHolder) convertView.getTag();
        }
        mData=mList.get(position);
        parkingView.time.setText(mData.getTime()); //这儿相当于时间
        parkingView.coin.setText("详细");
        if (mData.isUse()){
            parkingView.state.setText("使用中"); // 这儿是使用状态
            parkingView.state.setTextColor(Color.parseColor("#27c38a"));
            parkingView.coin.setTextColor(Color.parseColor("#27c38a"));
        }else{
            parkingView.state.setText("已完成"); // 这儿是使用状态
            parkingView.state.setTextColor(Color.parseColor("#898989"));
        }
        parkingView.coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.clickBack(mList.get(position));
            }
        });

        return convertView;
    }
}

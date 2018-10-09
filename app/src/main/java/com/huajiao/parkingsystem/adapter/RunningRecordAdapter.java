package com.huajiao.parkingsystem.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.huajiao.parkingsystem.Ben.ParkingDetailsData;
import com.huajiao.parkingsystem.Ben.RunningRecordData;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.interfaceback.ParkingDetailsClick;

import java.util.ArrayList;
import java.util.List;

public class RunningRecordAdapter extends BaseAdapter {
    private Context context;
    private List<RunningRecordData> mList = new ArrayList<>();
    private RunningRecordData mData;

    public RunningRecordAdapter(Context context){
        this.context=context;
    } public RunningRecordAdapter(Context context, ParkingDetailsClick click){
        this.context=context;
    }

    public void setDate(List<RunningRecordData> list)
    {
        this.mList = list;
        notifyDataSetChanged();
    }
    private class ViewHolder {
        TextView matter;
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
            parkingView.matter=convertView.findViewById(R.id.matters);
            parkingView.time=convertView.findViewById(R.id.time);
            parkingView.coin=convertView.findViewById(R.id.state);
            convertView.setTag(parkingView);
        }else {
            parkingView = (ViewHolder) convertView.getTag();
        }
        mData=mList.get(position);
        parkingView.matter.setText(mData.getMatters());
        parkingView.time.setText(mData.getTime());
        String coins=mData.isWin()?"+":"-";
        parkingView.coin.setText(coins+mData.getCoin());

        return convertView;
    }
}

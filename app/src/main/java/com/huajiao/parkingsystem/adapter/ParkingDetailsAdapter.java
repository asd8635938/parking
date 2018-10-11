package com.huajiao.parkingsystem.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.huajiao.parkingsystem.Ben.ParkingDetailsData;
import com.huajiao.parkingsystem.Ben.SearchData;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.interfaceback.NavigationClick;
import com.huajiao.parkingsystem.interfaceback.ParkingDetailsClick;

import java.util.ArrayList;
import java.util.List;

public class ParkingDetailsAdapter extends BaseAdapter {
    private Context context;
    private List<ParkingDetailsData> mList = new ArrayList<>();
    private ParkingDetailsData mData;
    private ParkingDetailsClick click;

    public ParkingDetailsAdapter(Context context){
        this.context=context;
    } public ParkingDetailsAdapter(Context context, ParkingDetailsClick click){
        this.context=context;
        this.click=click;
    }

    public void setDate(List<ParkingDetailsData> list)
    {
        this.mList = list;
        notifyDataSetChanged();
    }
    private class ParkingView {
        TextView serial;
        TextView parkingSerial;
        TextView state;

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
        ParkingView parkingView;
        if (convertView==null){
            parkingView=new ParkingView();
            convertView=LayoutInflater.from(context).inflate(R.layout.parking_space_details_item,null);
            parkingView.serial=convertView.findViewById(R.id.serial);
            parkingView.parkingSerial=convertView.findViewById(R.id.parking_space_serial);
            parkingView.state=convertView.findViewById(R.id.state);
            convertView.setTag(parkingView);
        }else {
            parkingView = (ParkingView) convertView.getTag();
        }
        mData=mList.get(position);
        parkingView.serial.setText(mData.getSerial());
        parkingView.parkingSerial.setText(mData.getParkingSerial());
        if(mData.isCanReservation()){
            parkingView.state.setText("已预约");
            parkingView.state.setTextColor(Color.parseColor("#898989"));
            parkingView.state.setClickable(false);
        }else{
            parkingView.state.setText("预约");
            parkingView.state.setTextColor(Color.parseColor("#27c38a"));
            parkingView.state.setClickable(true);
        }

        parkingView.state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.clickBack(mList.get(position));
            }
        });

        return convertView;
    }
}

package com.huajiao.parkingsystem.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.huajiao.parkingsystem.Ben.CityData;
import com.huajiao.parkingsystem.Ben.ParkingDetailsData;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.interfaceback.ParkingDetailsClick;

import java.util.ArrayList;
import java.util.List;

public class SwitchOverCityAdapter extends BaseAdapter {
    private Context context;
    private List<CityData> mList = new ArrayList<>();
    private CityData mData;
    private ParkingDetailsClick click;

    public SwitchOverCityAdapter(Context context) {
        this.context = context;
    }

    public SwitchOverCityAdapter(Context context, ParkingDetailsClick click) {
        this.context = context;
        this.click = click;
    }

    public void setDate(List<CityData> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    private class ParkingView {
        TextView title;
        TextView content;
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
        if (convertView == null) {
            parkingView = new ParkingView();
            convertView = LayoutInflater.from(context).inflate(R.layout.switch_city_item, null);
            parkingView.title = convertView.findViewById(R.id.item_title);
            parkingView.content = convertView.findViewById(R.id.item_content);
            convertView.setTag(parkingView);
        } else {
            parkingView = (ParkingView) convertView.getTag();
        }
        mData = mList.get(position);
//        根据position获取首字母作为目录catalog
          String catalog = mList.get(position).getFirstLetter();
//         如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
         if(position == getPositionForSection(catalog)){
             parkingView.title.setVisibility(View.VISIBLE);
             parkingView.title.setText(mData.getFirstLetter().toUpperCase());
          }
         else{
             parkingView.title.setVisibility(View.GONE);
          }
        parkingView.content.setText(mList.get(position).getName());

        return convertView;
    }

    /**
     * 获取catalog首次出现位置
     */
    public int getPositionForSection(String catalog) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = mList.get(i).getFirstLetter();
            if (catalog.equalsIgnoreCase(sortStr)) {
                return i;
            }
        }
        return -1;
    }
}

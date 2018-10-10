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
//          String catalog = mList.get(position).getFirstLetter();
        int section = getSectionForPosition(position);

        //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if(position == getPositionForSection(section)){
            parkingView.title.setVisibility(View.VISIBLE);
            parkingView.title.setText(mData.getFristA());
        }else{
            parkingView.title.setVisibility(View.GONE);
        }
        parkingView.content.setText(mList.get(position).getName());

        return convertView;
    }


    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */
    public int getSectionForPosition(int position) {
        return mList.get(position).getFristA().charAt(0);
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = mList.get(i).getFristA();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 提取英文的首字母，非英文字母用#代替。
     *
     * @param str
     * @return
     */
    private String getAlpha(String str) {
        String  sortStr = str.trim().substring(0, 1).toUpperCase();
        // 正则表达式，判断首字母是否是英文字母
        if (sortStr.matches("[A-Z]")) {
            return sortStr;
        } else {
            return "#";
        }
    }

    public Object[] getSections() {
        return null;
    }
}

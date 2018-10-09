package com.huajiao.parkingsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.huajiao.parkingsystem.Ben.ChargeStandardData;
import com.huajiao.parkingsystem.Ben.DiscountCouponData;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.interfaceback.SelectChargeClick;
import com.huajiao.parkingsystem.interfaceback.SelectDiscoutClick;

import java.util.ArrayList;
import java.util.List;

public class ChargeStandardAdapter extends BaseAdapter {
    private Context context;
    private List<ChargeStandardData> mList = new ArrayList<>();
    private ChargeStandardData mData;
    private SelectChargeClick clik;

    public ChargeStandardAdapter(Context context){
        this.context=context;
    }
    public ChargeStandardAdapter(Context context, SelectChargeClick clik){
        this.context=context;
        this.clik=clik;
    }

    public void setDate(List<ChargeStandardData> list)
    {
        this.mList = list;
        notifyDataSetChanged();
    }
    private class DiscountView {
        TextView name;
        TextView content;
        ImageButton isUse;
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
        final DiscountView discountView;
        if(convertView==null){
            discountView= new DiscountView();
            convertView= LayoutInflater.from(context).inflate(R.layout.charge_standard_item,null);
            discountView.name=convertView.findViewById(R.id.name);
            discountView.content=convertView.findViewById(R.id.content);
            discountView.isUse=convertView.findViewById(R.id.charge_btn);
            convertView.setTag(discountView);
        }else{
            discountView= (DiscountView) convertView.getTag();
        }

        mData=mList.get(position);
        discountView.name.setText(mData.getName());
        discountView.content.setText(mData.getCotent());
        discountView.isUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            clik.clickBack(position,mData,discountView.isUse.isSelected());
            // 如果是单选那么就不需要用这个做监听了
            }
        });
        return convertView;
    }
}

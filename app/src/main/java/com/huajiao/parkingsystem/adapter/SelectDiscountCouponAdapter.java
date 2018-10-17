package com.huajiao.parkingsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.huajiao.parkingsystem.Ben.DiscountCouponData;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.interfaceback.SelectDiscoutClick;

import java.util.ArrayList;
import java.util.List;

public class SelectDiscountCouponAdapter extends BaseAdapter {
    private Context context;
    private List<DiscountCouponData> mList = new ArrayList<>();
    private DiscountCouponData mData;
    private SelectDiscoutClick clik;
    private int temp = -1;

    public SelectDiscountCouponAdapter(Context context){
        this.context=context;
    }
    public SelectDiscountCouponAdapter(Context context,SelectDiscoutClick clik){
        this.context=context;
        this.clik=clik;
    }

    public void setDate(List<DiscountCouponData> list)
    {
        this.mList = list;
        notifyDataSetChanged();
    }
    private class DiscountView {
        TextView money;
        TextView time;
        ImageButton isUse;
        View bg;
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
            convertView= LayoutInflater.from(context).inflate(R.layout.select_discount_item,null);
            discountView.bg=convertView.findViewById(R.id.discount_bg);
            discountView.money=convertView.findViewById(R.id.money);
            discountView.time=convertView.findViewById(R.id.period_validity_content);
            discountView.isUse=convertView.findViewById(R.id.discount_btn);
            convertView.setTag(discountView);
        }else{
            discountView= (DiscountView) convertView.getTag();
        }
        mData=mList.get(position);
        discountView.bg.setBackgroundResource(mData.isCanUse() ? R.mipmap.discountcouponuncheck : R.mipmap.discountcouponcheck);
        discountView.money.setText(mData.getMoney());
        discountView.time.setText(mData.getTime());
        discountView.isUse.setSelected(mList.get(position).isClick());
        discountView.isUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp= position;
                mList.get(position).setClick(true);
                discountView.isUse.setSelected(mList.get(position).isClick());
                notifyDataSetChanged();
                clik.clickBack(position,mData.getCouponId(),mList.get(position),discountView.isUse.isSelected());
            }
        });
        if (position == temp) {//比对position和当前的temp是否一致
            discountView.isUse.setSelected(mList.get(position).isClick());
        }else {
            mList.get(position).setClick(false);
            discountView.isUse.setSelected(mList.get(position).isClick());
        }
        return convertView;
    }
}

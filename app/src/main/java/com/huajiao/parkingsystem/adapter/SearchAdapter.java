package com.huajiao.parkingsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.huajiao.parkingsystem.Ben.SearchData;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.interfaceback.NavigationClick;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends BaseAdapter {
    private Context context;
    private List<SearchData> mList = new ArrayList<>();
    private SearchData mData;
    private NavigationClick click;

    public SearchAdapter(Context context){
        this.context=context;
    } public SearchAdapter(Context context, NavigationClick click){
        this.context=context;
        this.click=click;
    }

    public void setDate(List<SearchData> list)
    {
        this.mList = list;
        notifyDataSetChanged();
    }
    private class SearchView {
        TextView name;
        TextView navigationNumber;
        TextView residueContent;
        TextView residueNoCommon;
        TextView residueNoCommonContent;
        TextView freeDurationContent;
        ImageView navigationBtn;

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
        SearchView searchView;
        if(convertView==null){
            searchView=new SearchView();
            if (mList.get(position).getDataType()==0){
                convertView=LayoutInflater.from(context).inflate(R.layout.parking_item,null);
                searchView.name=convertView.findViewById(R.id.parking_item_name);
                searchView.freeDurationContent=convertView.findViewById(R.id.free_duration_content);
                searchView.navigationNumber=convertView.findViewById(R.id.distance_number);
                searchView.residueContent=convertView.findViewById(R.id.residue_content);
                searchView.residueNoCommon=convertView.findViewById(R.id.residue_no_common);
                searchView.residueNoCommonContent=convertView.findViewById(R.id.residue_no_common_content);
                searchView.navigationBtn=convertView.findViewById(R.id.image_navigation);
            }else {
                convertView = LayoutInflater.from(context).inflate(R.layout.petrol_station_item, null);
                searchView.name = convertView.findViewById(R.id.petrol_item_name);
                searchView.navigationNumber = convertView.findViewById(R.id.distance_number);
                searchView.navigationBtn=convertView.findViewById(R.id.image_navigation);
            }

            convertView.setTag(searchView);
        }else{
            searchView= (SearchView) convertView.getTag();
        }

        mData=mList.get(position);
        if(mData.getDataType()==0){
            if (mData.isCanCommonNumber()==false) {
                searchView.residueNoCommon.setVisibility(View.GONE);
                searchView.residueNoCommonContent.setVisibility(View.GONE);
            }
            searchView.name.setText(mData.getName());
            searchView.freeDurationContent.setText(mData.getFreeDurationContent());
            searchView.navigationNumber.setText(mData.getDistanceNumber()+"");
            searchView.residueContent.setText(mData.getResidueContent());
            searchView.residueNoCommonContent.setText(mData.getResidueNoCommonContent());
        }else{
            searchView.name.setText(mData.getName());
            searchView.navigationNumber.setText(mData.getDistanceNumber()+"");
        }

        searchView.navigationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.clickBack(position,mData.getDataType());
            }
        });

        return convertView;
    }
}

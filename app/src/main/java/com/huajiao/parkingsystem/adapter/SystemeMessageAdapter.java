package com.huajiao.parkingsystem.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.huajiao.parkingsystem.Ben.SystemeMessageData;
import com.huajiao.parkingsystem.R;

import java.util.ArrayList;
import java.util.List;

public class SystemeMessageAdapter extends BaseAdapter {
    private Context context;
    private List<SystemeMessageData> mList = new ArrayList<>();
    private SystemeMessageData mData;

    public SystemeMessageAdapter(Context context){
        this.context=context;
    }

    public void setDate(List<SystemeMessageData> list)
    {
        this.mList = list;
        notifyDataSetChanged();
    }
    private class MessageView {
        TextView name;
        TextView time;
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
        MessageView messageView;
        if(convertView==null){
            messageView= new MessageView();
            convertView= LayoutInflater.from(context).inflate(R.layout.systeme_message_item,null);
            messageView.name=convertView.findViewById(R.id.name);
            messageView.time=convertView.findViewById(R.id.time);
            messageView.content=convertView.findViewById(R.id.content);
            convertView.setTag(messageView);
        }else{
            messageView= (MessageView) convertView.getTag();
        }

        mData=mList.get(position);
        messageView.name.setText(mData.getName());
        messageView.time.setText(mData.getTime());
        messageView.content.setText(mData.getContent());

        return convertView;
    }
}

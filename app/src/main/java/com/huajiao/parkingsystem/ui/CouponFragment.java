package com.huajiao.parkingsystem.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huajiao.parkingsystem.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by DELL on 2018/10/9.
 */

public class CouponFragment extends Fragment {

    public static String COITEM = "COITEM";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private ListAdapter mListAdapter;
    private List<String> textList = new ArrayList<>();
    private int itemType = 0;

    public static CouponFragment newInstance(int item) {
        CouponFragment fragment = new CouponFragment();
        Bundle args = new Bundle();
        args.putInt(COITEM, item);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.coupon_fragment, container, false);
        ButterKnife.bind(this, view);

        Bundle arguments = getArguments();
        itemType = arguments.getInt(COITEM);

        textList.add("1");
        textList.add("1");
        textList.add("1");
        textList.add("1");
        textList.add("1");
        textList.add("1");
        textList.add("1");
        textList.add("1");

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListAdapter = new ListAdapter(getActivity());
        recyclerView.setAdapter(mListAdapter);
        mListAdapter.setNewData(textList);

        return view;
    }

    private class ListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        private Context mContext;

        public ListAdapter(Context mContext) {
            super(R.layout.list_adapter);
            this.mContext = mContext;
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            RelativeLayout relativeLayoutBackGround = helper.getView(R.id.relativeLayoutBackGround);
            if (itemType == 2) {
                relativeLayoutBackGround.setBackgroundResource(R.mipmap.discountcouponuncheck);
            } else {
                relativeLayoutBackGround.setBackgroundResource(R.mipmap.discountcouponcheck);
            }
        }
    }
}

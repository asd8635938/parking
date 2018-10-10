package com.huajiao.parkingsystem.ui;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.githang.statusbar.StatusBarCompat;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TryCashActivity extends BaseActivity {

    @BindView(R.id.titleName)
    TextView titleName;
    @BindView(R.id.titleLife)
    ImageView titleLife;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private ListAdapter mListAdapter;
    private List<String> textList = new ArrayList<>();

    @Override
    protected int getViewContentId() {
        return R.layout.activity_try_cash;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.title));
        relativeLayout.setBackgroundColor(getResources().getColor(R.color.title));
        titleName.setText("异常停车");
        titleLife.setImageResource(R.mipmap.left);

        textList.add("1");
        textList.add("1");
        textList.add("1");
        textList.add("1");
        textList.add("1");
        textList.add("1");
        textList.add("1");
        textList.add("1");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mListAdapter = new ListAdapter(this);
        recyclerView.setAdapter(mListAdapter);
        mListAdapter.setNewData(textList);

        initOnClick();
    }

    private class ListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        private Context mContext;

        public ListAdapter(Context mContext) {
            super(R.layout.try_cash_adapter);
            this.mContext = mContext;
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }

    private void initOnClick() {
        titleName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(InvoiceListActivity.class);
            }
        });
        titleLife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    protected void getInternetData() {

    }
}

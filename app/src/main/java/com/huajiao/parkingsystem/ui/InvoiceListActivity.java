package com.huajiao.parkingsystem.ui;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.githang.statusbar.StatusBarCompat;
import com.huajiao.parkingsystem.Ben.InvoiceBean;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.base.BaseActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class InvoiceListActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private ImageView imageViewClick;
    private TextView textViewNumb;

    private ListAdapter mListAdapter;
    private List<InvoiceBean> beanList = new ArrayList<>();
    private ArrayList<InvoiceBean> checkList=new ArrayList<>();
    private TextView next;
    private boolean isClick = false;

    @Override
    protected int getViewContentId() {
        return R.layout.activity_invoice_list;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.title));
        setTitleText("选择开具发票订单");
        View titleTop = findViewById(R.id.titleTop);
        recyclerView = findViewById(R.id.recyclerView);
        imageViewClick = findViewById(R.id.imageViewClick);
        textViewNumb = findViewById(R.id.textViewNumb);
        next=findViewById(R.id.next);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.title));
        titleTop.setBackgroundColor(getResources().getColor(R.color.title));

        for (int i = 0; i < 10; i++) {
            InvoiceBean invoiceBean = new InvoiceBean();
            invoiceBean.setCheck(false);
            invoiceBean.setId(i);
            invoiceBean.setAddress("棠东花园B区");
            invoiceBean.setPayCoin("30"+i);
            invoiceBean.setTime("2018-10-18");
            invoiceBean.setUseDuration("123"+i);
            beanList.add(invoiceBean);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mListAdapter = new ListAdapter(this);
        recyclerView.setAdapter(mListAdapter);
        mListAdapter.setNewData(beanList);

        initOnClick();

    }

    private class ListAdapter extends BaseQuickAdapter<InvoiceBean, BaseViewHolder> {
        private Context mContext;

        public ListAdapter(Context mContext) {
            super(R.layout.invoice_list_adapter);
            this.mContext = mContext;
        }

        @Override
        protected void convert(final BaseViewHolder helper, final InvoiceBean item) {
            final ImageView imageView = helper.getView(R.id.imageView);
            TextView time_text= helper.getView(R.id.time_text);
            TextView address_text=helper.getView(R.id.address_text);
            TextView use_duration=helper.getView(R.id.use_duration);
            TextView coin_text=helper.getView(R.id.coin_text);
            time_text.setText(item.getTime());
            address_text.setText(item.getAddress());
            use_duration.setText(item.getUseDuration()+"分钟");
            coin_text.setText(item.getPayCoin()+"元");


            if (item.getCheck()) {
                imageView.setImageResource(R.mipmap.circlecheck);
            } else {
                imageView.setImageResource(R.mipmap.circleuncheck);
            }

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (item.getCheck()) {
                        mListAdapter.getData().get(helper.getPosition()).setCheck(false);
                        isClick = true;
                        imageViewClick.setImageResource(R.mipmap.circleuncheck);
                        initCount();
                    } else {
                        mListAdapter.getData().get(helper.getPosition()).setCheck(true);
                        initCount();
                    }
                    mListAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    private void initCount() {
        checkList.clear();
        if (mListAdapter.getData() != null && mListAdapter.getData().size() != 0) {
            int count = 0;
            for (int i = 0; i < mListAdapter.getData().size(); i++) {
                if (mListAdapter.getData().get(i).getCheck()) {
                    count = count + 1;
                    checkList.add(beanList.get(i));
                }
            }
            textViewNumb.setText("共选" + count + "次停车记录");

        }
    }

    private void initOnClick() {
        imageViewClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListAdapter.getData() != null && mListAdapter.getData().size() != 0) {
                    if (isClick) {
                        isClick = false;
                        imageViewClick.setImageResource(R.mipmap.circlecheck);
                        for (int i = 0; i < mListAdapter.getData().size(); i++) {
                            mListAdapter.getData().get(i).setCheck(true);
                        }
                    } else {
                        isClick = true;
                        imageViewClick.setImageResource(R.mipmap.circleuncheck);
                        for (int i = 0; i < mListAdapter.getData().size(); i++) {
                            mListAdapter.getData().get(i).setCheck(false);
                        }
                    }
                    initCount();
                    mListAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void bindEvent() {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkList.size()>0){
                    Intent intent = new Intent();
                    intent.putExtra("list",checkList);
                    intent.setClass(InvoiceListActivity.this,InvoiceActivity.class);
                    openActivity(intent);
                }else {
                    showToast("请选择需要开具的发票");
                }
            }
        });
    }

    @Override
    protected void getInternetData() {

    }
}

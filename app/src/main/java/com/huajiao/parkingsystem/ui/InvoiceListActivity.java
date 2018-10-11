package com.huajiao.parkingsystem.ui;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.githang.statusbar.StatusBarCompat;
import com.huajiao.parkingsystem.Ben.TestBean;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InvoiceListActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private ImageView imageViewClick;
    private TextView textViewNumb;

    private ListAdapter mListAdapter;
    private List<TestBean> beanList = new ArrayList<>();
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
        View titleTop = findViewById(R.id.titleTop);
        recyclerView = findViewById(R.id.recyclerView);
        imageViewClick = findViewById(R.id.imageViewClick);
        textViewNumb = findViewById(R.id.textViewNumb);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.title));
        titleTop.setBackgroundColor(getResources().getColor(R.color.title));

        for (int i = 0; i < 10; i++) {
            TestBean testBean = new TestBean();
            testBean.setCheck(false);
            beanList.add(testBean);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mListAdapter = new ListAdapter(this);
        recyclerView.setAdapter(mListAdapter);
        mListAdapter.setNewData(beanList);

        initOnClick();

    }

    private class ListAdapter extends BaseQuickAdapter<TestBean, BaseViewHolder> {
        private Context mContext;

        public ListAdapter(Context mContext) {
            super(R.layout.invoice_list_adapter);
            this.mContext = mContext;
        }

        @Override
        protected void convert(final BaseViewHolder helper, final TestBean item) {
            final ImageView imageView = helper.getView(R.id.imageView);
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
        if (mListAdapter.getData() != null && mListAdapter.getData().size() != 0) {
            int count = 0;
            for (int i = 0; i < mListAdapter.getData().size(); i++) {
                if (mListAdapter.getData().get(i).getCheck()) {
                    count = count + 1;
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

    }

    @Override
    protected void getInternetData() {

    }
}

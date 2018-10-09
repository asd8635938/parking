package com.huajiao.parkingsystem.ui;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.base.BaseActivity;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.DrawableBar;
import com.shizhefei.view.indicator.slidebar.ScrollBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CouponActivity extends BaseActivity {

    @BindView(R.id.moretab_indicator)
    ScrollIndicatorView scrollIndicatorView;
    @BindView(R.id.moretab_viewPager)
    ViewPager viewPager;
    @BindView(R.id.titleName)
    TextView titleName;
    @BindView(R.id.titleLife)
    ImageView titleLife;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;

    private IndicatorViewPager indicatorViewPager;
    private LayoutInflater inflate;
    private String[] names = {"全部", "未使用", "已过期"};
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected int getViewContentId() {
        return R.layout.activity_coupon;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        inflate = LayoutInflater.from(this);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.title));
        relativeLayout.setBackgroundColor(getResources().getColor(R.color.title));
        titleName.setText("优惠券");
        titleLife.setImageResource(R.mipmap.left);

        fragments.add(CouponFragment.newInstance(0));
        fragments.add(CouponFragment.newInstance(1));
        fragments.add(CouponFragment.newInstance(2));
        scrollIndicatorView.setBackgroundColor(Color.WHITE);
        scrollIndicatorView.setScrollBar(new DrawableBar(this, R.drawable.shape_top, ScrollBar.Gravity.CENTENT_BACKGROUND) {
            @Override
            public int getHeight(int tabHeight) {
                return tabHeight - dipToPix(12);
            }

            @Override
            public int getWidth(int tabWidth) {
                return tabWidth - dipToPix(30);
            }
        });
        scrollIndicatorView.setOnTransitionListener(new OnTransitionTextListener());
        scrollIndicatorView.setOnTransitionListener(new OnTransitionTextListener().setColor(getResources().getColor(R.color.white), getResources().getColor(R.color.text898989)));
        indicatorViewPager = new IndicatorViewPager(scrollIndicatorView, viewPager);
        indicatorViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(3);

        initOnClick();
    }

    private void initOnClick() {
        titleLife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        titleName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(AppointmentActivity.class);
            }
        });
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    protected void getInternetData() {

    }

    private class MyAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = inflate.inflate(R.layout.tab_top, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(names[position % names.length]);
            int padding = dipToPix(10);
            textView.setPadding(padding, 0, padding, 0);
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            return fragments.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            //这是ViewPager适配器的特点,有两个值 POSITION_NONE，POSITION_UNCHANGED，默认就是POSITION_UNCHANGED,
            // 表示数据没变化不用更新.notifyDataChange的时候重新调用getViewForPage
            return PagerAdapter.POSITION_NONE;
        }

    }

    /**
     * 根据dip值转化成px值
     *
     * @param dip
     * @return
     */
    private int dipToPix(float dip) {
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
        return size;
    }
}

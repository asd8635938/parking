package com.huajiao.parkingsystem.ui;

import android.view.View;
import android.widget.Button;

import com.githang.statusbar.StatusBarCompat;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.base.BaseActivity;

public class SubmitApply extends BaseActivity {
    private Button back_money_package;
    /**
     * @return {int} {当前布局的layoutid}
     * 使用方式 直接返回需要setContentView的LayoutId
     */
    @Override
    protected int getViewContentId() {
        return R.layout.submit_apply;
    }

    /**
     * 在这里初始化你的数据 ps:总在initView之前调用
     */
    @Override
    protected void initData() {

    }

    /**
     * 在这里初始化你的控件引用
     */
    @Override
    protected void initView() {
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.title));
        setTitleText("提交申请");
        back_money_package=findViewById(R.id.back_money_package);

    }

    /***
     * 在这里添加你的控件的任何事件绑定
     */
    @Override
    protected void bindEvent() {
        back_money_package.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(MyMoneyPackageActivity.class);
            }
        });
    }
    /**
     * 提供给需要获得网络数据
     */
    @Override
    protected void getInternetData() {

    }
}

package com.huajiao.parkingsystem.ui;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.huajiao.parkingsystem.MainActivity;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.base.BaseActivity;
import com.huajiao.parkingsystem.dialog.SettingDialog;

public class SettingActivity extends BaseActivity implements View.OnClickListener{
    private ImageButton switch_btn;
    private LinearLayout check_update;
    private LinearLayout about_my;
    private LinearLayout feedback;
    private Button out_login;
    private SettingDialog dialog;
    /**
     * @return {int} {当前布局的layoutid}
     * 使用方式 直接返回需要setContentView的LayoutId
     */
    @Override
    protected int getViewContentId() {
        return R.layout.setting;
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
        switch_btn=findViewById(R.id.switch_btn);
        check_update=findViewById(R.id.check_update);
        about_my=findViewById(R.id.about_my);
        feedback=findViewById(R.id.feedback);
        out_login=findViewById(R.id.out_login);
        dialog=new SettingDialog(this);
    }

    /***
     * 在这里添加你的控件的任何事件绑定
     */
    @Override
    protected void bindEvent() {
        switch_btn.setOnClickListener(this);
        check_update.setOnClickListener(this);
        about_my.setOnClickListener(this);
        feedback.setOnClickListener(this);
        out_login.setOnClickListener(this);
        dialog.setClicklistener(new SettingDialog.ClickListenerInterface() {
            @Override
            public void doConfirm() {
                dialog.dismiss();
            }

            @Override
            public void doCancel() {
                dialog.dismiss();
            }
        });
    }

    /**
     * 提供给需要获得网络数据
     */
    @Override
    protected void getInternetData() {

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.switch_btn:
                switch_btn.setClickable(!switch_btn.isClickable());
                break;
            case R.id.check_update:
                dialog.show();
                break;
            case R.id.about_my:
                openActivity(MyAboutActivity.class);
                break;
            case R.id.feedback:
                openActivity(FeedbackAndAdvice.class);
                break;
            case R.id.out_login:
                openActivity(MainActivity.class);
                break;

        }
    }
}

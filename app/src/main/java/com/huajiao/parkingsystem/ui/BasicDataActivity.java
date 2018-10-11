package com.huajiao.parkingsystem.ui;

import android.app.Dialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.base.BaseActivity;
import com.huajiao.parkingsystem.dialog.DialogUtils;

public class BasicDataActivity extends BaseActivity implements View.OnClickListener{

    private ImageView replace_btn; // 头像
    private ImageView gender_btn; // 男女
    private TextView name_content; // 昵称
    /**
     * @return {int} {当前布局的layoutid}
     * 使用方式 直接返回需要setContentView的LayoutId
     */
    @Override
    protected int getViewContentId() {
        return R.layout.basic_data;
    }

    /**
     * 在这里初始化你的数据 ps:总在initView之前调用
     */
    @Override
    protected void initData(){

    }

    /**
     * 在这里初始化你的控件引用
     */
    @Override
    protected void initView() {
        replace_btn=findViewById(R.id.replace_btn);
        gender_btn=findViewById(R.id.gender_btn);
        name_content=findViewById(R.id.name_content);
    }

    /***
     * 在这里添加你的控件的任何事件绑定
     */
    @Override
    protected void bindEvent() {
        replace_btn.setOnClickListener(this);
        gender_btn.setOnClickListener(this);
        name_content.setOnClickListener(this);
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
            case  R.id.replace_btn:
                DialogUtils.showSelectCameraDialog(this, getWindow().getWindowManager().getDefaultDisplay().getWidth() - DialogUtils.dip2px(this, 40), new DialogUtils.ShowCameraCallBack() {
                            @Override
                            public void TopClick(Dialog dialog) {
                                dialog.dismiss();
                            }

                            @Override
                            public void CenterClick(Dialog dialog) {
                                dialog.dismiss();
                            }

                            @Override
                            public void CancelClick(Dialog dialog) {
                                dialog.dismiss();
                            }
                        }
                );
                break;
             case  R.id.gender_btn:
                 DialogUtils.showSelectGenderDialog(this, getWindow().getWindowManager().getDefaultDisplay().getWidth() - DialogUtils.dip2px(this, 40), new DialogUtils.ShowGenderCallBack() {
                             @Override
                             public void ManClick(Dialog dialog) {
                                 dialog.dismiss();
                             }

                             @Override
                             public void femaleClick(Dialog dialog) {
                                 dialog.dismiss();
                             }
                         }
                 );
                break;
            case R.id.name_content:
                DialogUtils.showBasicDialog(this, getWindow().getWindowManager().getDefaultDisplay().getWidth() - DialogUtils.dip2px(this, 40), new DialogUtils.ShowDialogCallBack() {
                    @Override
                    public void LeftClick(Dialog dialog) {
                        dialog.dismiss();
                    }

                    @Override
                    public void RightClick(Dialog dialog) {

                    }
                });
                break;

        }
    }
}

package com.huajiao.parkingsystem.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.huajiao.parkingsystem.R;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 把自己添加进去统一进行管理
        ActivityStack.getIns().push(this);
        initData();
        setContentView(getViewContentId());
        initView();
        bindEvent();
        bindBackButton();
        getInternetData();
    }
    /**
     * @return {int} {当前布局的layoutid}
     * 使用方式 直接返回需要setContentView的LayoutId
     * */
    protected abstract int getViewContentId();
    /**
     * 在这里初始化你的数据 ps:总在initView之前调用
     * */
    protected abstract void initData();
    /**
     * 在这里初始化你的控件引用
     * */
    protected abstract void initView();

    /***
     * 在这里添加你的控件的任何事件绑定
     */
    protected abstract void bindEvent();

    /**
     * 提供给需要获得网络数据
     * */
    protected abstract void getInternetData();
    /**
     * 自动绑定你的返回按钮
     * */
    protected void bindBackButton(){
        bindBackButton(R.id.back);
    }
    protected  void bindBackButton(int btnId) {
        View backView = findViewById(btnId);
        if (null != backView) {
            backView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBack();
                }
            });
        }
    }
    /**
     * 用于设置title  ps  有些页面不需要 也没有
     * */
    protected  void setTitleText(String title){
        TextView titleView=findViewById(R.id.title_text);
        if (titleView!=null){
            titleView.setText(title);
        }
    }

    /**
     *
     * @param viewType {控制是否显示右边的按钮}
     *  使用示例  isShowSaveBtn(View.GONE)
     */
    protected  void isShowSaveBtn(int viewType){

        Button btn=findViewById(R.id.save);
        if(btn!=null){
            btn.setVisibility(viewType);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callbackSaveBtn();
                }
            });
        }
    }

    /**
     *
     * @param s{当前按钮的文字}
     */
    protected  void setShowSaveBtnText(String s){

        Button btn=findViewById(R.id.save);
        if(btn!=null){
            btn.setText(s);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callbackSaveBtn();
                }
            });
        }
    }

    /**
     *
     * 用于保存按钮的回调你可以在里面处理保存按钮的事件
     */
    protected void callbackSaveBtn(){}

    protected void onBack()
    {
        ActivityStack.getIns().popup(this);
    }
    @Override
    protected void onDestroy() {
        ActivityStack.getIns().popup(this);
        super.onDestroy();
    }
    /**
     * @param resId {当前需要显示的String 在系统中的引用地址   }
     *  使用示例   showToast(R.string.regsitered)
     * */
    public void showToast(int resId)
    {
        Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show();
    }
    /**
     * @param text {当前需要显示String的文字 }
     *  使用示例   showToast("我要显示")
     * */
    public void showToast(String text)
    {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
    protected void openActivity(){
        Intent intent =new Intent(this,this.getClass());
        this.openActivity(intent);
    }
    protected void openActivity(Class<?> cls){
        Intent intent =new Intent(this,cls);
        this.openActivity(intent);
    }
    protected void openActivity(Class<?> cls,int launchModel){
        Intent intent =new Intent(this,cls);
        intent.addFlags(launchModel);
        this.openActivity(intent);
    }
    protected void openActivity(Intent intent){
        this.startActivity(intent);
    }

    /***
     *
     * 将编辑框的光标移动到末尾
     * @param text {这个是你需要控制的控件}
     */

    protected void setCursorToEnd(EditText text){
        String content = text.getText().toString();
        text.setSelection(content.length());
    }

}

package com.huajiao.parkingsystem.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.tools.CheckUtil;

import java.util.List;

public class DelayedDialog extends Dialog {
    private Context context;


    private TextView text_hour;
    private TextView text_min;
    private TextView cancel_tv;
    private TextView confirm_tv;

    private String whenStr;
    private String secondsStr;

    private ListView whenList;
    private ListView secondsList;
    private List<String> whenData;
    private List<String> secondsData;

    public DelayedDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

     private ClickListenerInterface clickListenerInterface;

             public interface ClickListenerInterface {

                 void doConfirm(String when,String seconds);
   }

   public void setWhenData(List<String> data){
       whenData=data;
   }
   public void setSecondsData(List<String> data){
       secondsData=data;
   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
                     // TODO Auto-generated method stub
                     super.onCreate(savedInstanceState);

                    init();
                }

            public void init() {
                    LayoutInflater inflater = LayoutInflater.from(context);
                    View view = inflater.inflate(R.layout.dialog_delayed, null);
                    setContentView(view);

                Window win = getWindow();
                WindowManager.LayoutParams lp = win.getAttributes();

                lp.gravity = Gravity.CENTER;
                lp.width = win.getWindowManager().getDefaultDisplay().getWidth() - DialogUtils.dip2px(context,40);
                win.setAttributes(lp);
                text_hour=view.findViewById(R.id.text_hour);
                text_min=view.findViewById(R.id.text_min);
                cancel_tv=view.findViewById(R.id.cancel_tv);
                confirm_tv=view.findViewById(R.id.confirm_tv);
                text_hour.setOnClickListener(new clickListener());
                text_min.setOnClickListener(new clickListener());
                cancel_tv.setOnClickListener(new clickListener());
                confirm_tv.setOnClickListener(new clickListener());

                }

             public void setClicklistener(ClickListenerInterface clickListenerInterface) {
                     this.clickListenerInterface = clickListenerInterface;
                 }

            private class clickListener implements View.OnClickListener {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    int id = v.getId();
                    switch (id)
                    {
                        case R.id.text_hour:
                            showWhenPop();
                            break;
                        case R.id.text_min:
                            showSecondsPop();
                            break;
                        case R.id.cancel_tv:
                            dismiss();
                            break;
                        case R.id.confirm_tv:
                            if(!CheckUtil.checkString(whenStr)&&!CheckUtil.checkString(secondsStr)){
                                Toast.makeText(context,"请选择时间",Toast.LENGTH_LONG).show();
                                return;
                            }
                            clickListenerInterface.doConfirm(whenStr,secondsStr);
                            break;
                    }
                }

            }

    private void showSecondsPop() {
        DisplayMetrics metrics=new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenHeight=metrics.heightPixels;
        View contentView = LayoutInflater.from(context).inflate(R.layout.popup_list, null);

        final PopupWindow popupWindow = new PopupWindow(contentView,
                text_min.getWidth(), (int)(screenHeight*0.3), true);

        secondsList=(ListView) contentView.findViewById(R.id.data_list);
        secondsList.setAdapter(new ArrayAdapter<String>(context, R.layout.item_popup_list, secondsData));
        secondsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                secondsStr=secondsData.get(position);
                text_min.setText(secondsStr);
                popupWindow.dismiss();
            }
        });



        popupWindow.setTouchable(true);
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        // 设置好参数之后再show
        popupWindow.showAsDropDown(text_min);
    }

    private void showWhenPop() {
        DisplayMetrics metrics=new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenHeight=metrics.heightPixels;
        View contentView = LayoutInflater.from(context).inflate(R.layout.popup_list, null);

        final PopupWindow popupWindow = new PopupWindow(contentView,
                text_hour.getWidth(), (int)(screenHeight*0.3), true);

        whenList=(ListView) contentView.findViewById(R.id.data_list);
        whenList.setAdapter(new ArrayAdapter<String>(context, R.layout.item_popup_list, whenData));
        whenList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                whenStr=whenData.get(position);
                text_hour.setText(whenStr);
                popupWindow.dismiss();
            }
        });

        popupWindow.setTouchable(true);
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        // 这里单独写一篇文章来分析
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        // 设置好参数之后再show
        popupWindow.showAsDropDown(text_hour);
    }
}


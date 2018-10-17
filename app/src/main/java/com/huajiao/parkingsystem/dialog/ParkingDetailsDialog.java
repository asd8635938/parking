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
import android.view.ViewGroup;
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
import com.huajiao.parkingsystem.ui.AppointmentActivity;

import java.util.List;

public class ParkingDetailsDialog extends Dialog {
    private Context context;
    private ImageView closeBt;
    private ImageView whenBt;
    private ImageView secondsBt;
    private ImageView isEndHintBt;
    private ImageView timeBt;

    private TextView whenTt;
    private TextView secondsTt;
    private TextView reservationTt;
    private TextView timeTt;

    private String whenStr;
    private String secondsStr;
    private String timeStr;


    private Button payCost;
    private boolean isEbdHint=false;

    private ListView whenList;
    private ListView secondsList;
    private ListView timeList;
    private List<String> whenData;
    private List<String> secondsData;
    private List<String> timeData;

    private LinearLayout pop_layout;
    private LinearLayout seconds;
    private LinearLayout when_layout;

    public ParkingDetailsDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

     private ClickListenerInterface clickListenerInterface;

             public interface ClickListenerInterface {

                public void doConfirm();
   }

   public void setWhenData(List<String> data){
       whenData=data;
   }
   public void setSecondsData(List<String> data){
       secondsData=data;
   }
   public void setTimeData(List<String> data){
       timeData=data;
   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
                     // TODO Auto-generated method stub
                     super.onCreate(savedInstanceState);

                    init();
                }

            public void init() {
                    LayoutInflater inflater = LayoutInflater.from(context);
                    View view = inflater.inflate(R.layout.parking_details_dialog, null);
                    setContentView(view);

                Window win = getWindow();
                WindowManager.LayoutParams lp = win.getAttributes();

                lp.gravity = Gravity.CENTER;
                lp.width = win.getWindowManager().getDefaultDisplay().getWidth() - DialogUtils.dip2px(context,40);
                win.setAttributes(lp);

                closeBt=view.findViewById(R.id.close);
                whenBt=view.findViewById(R.id.when_btn);
                secondsBt=view.findViewById(R.id.seconds_btn);
                isEndHintBt=view.findViewById(R.id.is_end_hint_btn);
                timeBt=view.findViewById(R.id.time_btn);

                whenTt=view.findViewById(R.id.when_text);
                secondsTt=view.findViewById(R.id.seconds_text);
                reservationTt=view.findViewById(R.id.reservation);
                timeTt=view.findViewById(R.id.time_text);
                payCost=view.findViewById(R.id.pay_cost);

                pop_layout=view.findViewById(R.id.pop_layout);
                seconds=view.findViewById(R.id.seconds);
                when_layout=view.findViewById(R.id.when_layout);

                closeBt.setOnClickListener(new clickListener());
                whenBt.setOnClickListener(new clickListener());
                secondsBt.setOnClickListener(new clickListener());
                isEndHintBt.setOnClickListener(new clickListener());
                isEndHintBt.setSelected(false);
                pop_layout.setVisibility(View.GONE);
                timeBt.setOnClickListener(new clickListener());
                payCost.setOnClickListener(new clickListener());

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
                        case R.id.close:
                            dismiss();
                            break;
                        case R.id.when_btn:
                            showWhenPop();
                            break;
                        case R.id.seconds_btn:
                            showSecondsPop();
                            break;
                        case R.id.is_end_hint_btn:
                            isEbdHint=!isEndHintBt.isSelected();
                            isEndHintBt.setSelected(isEbdHint);
                            if(isEbdHint){
                                pop_layout.setVisibility(View.VISIBLE);
                            }else{
                                pop_layout.setVisibility(View.GONE);
                            }
                            break;
                        case R.id.time_btn:
                            showTimePop();
                            break;
                        case R.id.pay_cost:
                            if(secondsStr==null&&secondsStr==""&&whenStr==null&&whenStr==""){
                                Toast.makeText(context,"请选择到达时间",Toast.LENGTH_LONG).show();
                                return;
                            }
                            clickListenerInterface.doConfirm();
                            break;

                    }
                }

            }

    private void showTimePop() {
        DisplayMetrics metrics=new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenHeight=metrics.heightPixels;
        View contentView = LayoutInflater.from(context).inflate(R.layout.popup_list, null);

        final PopupWindow popupWindow = new PopupWindow(contentView,
                pop_layout.getWidth(), (int)(screenHeight*0.2), true);

        timeList=(ListView) contentView.findViewById(R.id.data_list);
        timeList.setAdapter(new ArrayAdapter<String>(context, R.layout.item_popup_list, timeData));

        timeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                timeStr=timeData.get(position);
                timeTt.setText(timeStr);
                popupWindow.dismiss();
            }
        });

        popupWindow.setTouchable(true);
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        // 这里单独写一篇文章来分析
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        // 设置好参数之后再show
        popupWindow.showAsDropDown(pop_layout);
    }

    private void showSecondsPop() {
        DisplayMetrics metrics=new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenHeight=metrics.heightPixels;
        View contentView = LayoutInflater.from(context).inflate(R.layout.popup_list, null);

        final PopupWindow popupWindow = new PopupWindow(contentView,
                seconds.getWidth(), (int)(screenHeight*0.3), true);

        secondsList=(ListView) contentView.findViewById(R.id.data_list);
        secondsList.setAdapter(new ArrayAdapter<String>(context, R.layout.item_popup_list, secondsData));
        secondsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                secondsStr=secondsData.get(position);
                secondsTt.setText(secondsStr);
                reservationTt.setText(secondsStr+"分钟");
                if(whenStr!=null&&!whenStr.equals("")&&secondsStr!=null&&!secondsStr.equals("")){
                    reservationTt.setText(whenStr+"小时"+secondsStr+"分钟");
                }
                popupWindow.dismiss();
            }
        });



        popupWindow.setTouchable(true);
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        // 这里单独写一篇文章来分析
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        // 设置好参数之后再show
        popupWindow.showAsDropDown(seconds);
    }

    private void showWhenPop() {
        DisplayMetrics metrics=new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenHeight=metrics.heightPixels;
        View contentView = LayoutInflater.from(context).inflate(R.layout.popup_list, null);

        final PopupWindow popupWindow = new PopupWindow(contentView,
                when_layout.getWidth(), (int)(screenHeight*0.3), true);

        whenList=(ListView) contentView.findViewById(R.id.data_list);
        whenList.setAdapter(new ArrayAdapter<String>(context, R.layout.item_popup_list, whenData));
        whenList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                whenStr=whenData.get(position);
                whenTt.setText(whenStr);
                reservationTt.setText(whenStr+"小时");
                if(whenStr!=null&&!whenStr.equals("")&&secondsStr!=null&&!secondsStr.equals("")){
                    reservationTt.setText(whenStr+"小时"+secondsStr+"分钟");
                }
                popupWindow.dismiss();
            }
        });

        popupWindow.setTouchable(true);
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        // 这里单独写一篇文章来分析
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        // 设置好参数之后再show
        popupWindow.showAsDropDown(when_layout);
    }
}


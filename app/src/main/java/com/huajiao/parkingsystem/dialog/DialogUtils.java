package com.huajiao.parkingsystem.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huajiao.parkingsystem.R;

import org.w3c.dom.Text;

/**
 * Created by DELL on 2018/10/9.
 */

public class DialogUtils {

    public static void showDialog(boolean commit,Context context,int width ,String title, String content, final ShowDialogCallBack listener) {
        View dialogview = LayoutInflater.from(context).inflate(R.layout.dialog_common, null);
        final Dialog dialog = new Dialog(context, R.style.dialog_bg_style);
        //设置view
        dialog.setContentView(dialogview);
        dialog.setCanceledOnTouchOutside(false);
        //dialog默认是环绕内容的
        //通过window来设置位置、高宽
        Window window = dialog.getWindow();
        WindowManager.LayoutParams windowparams = window.getAttributes();
        windowparams.width = width;

        TextView confirm_tv = (TextView) dialogview.findViewById(R.id.confirm_tv);
        TextView cancel_tv = (TextView) dialogview.findViewById(R.id.cancel_tv);
        TextView tvTitle = (TextView) dialogview.findViewById(R.id.title);
        TextView tvContent = (TextView) dialogview.findViewById(R.id.tv_content);
        RelativeLayout relativeLayout = (RelativeLayout) dialogview.findViewById(R.id.relativeLayout);
        TextView whenText=dialogview.findViewById(R.id.text_hour);
        TextView minText=dialogview.findViewById(R.id.text_min);

        if (commit) {
            relativeLayout.setVisibility(View.VISIBLE);
        } else {
            relativeLayout.setVisibility(View.GONE);
        }

        if (title != null) {
            tvTitle.setText(title);
        }
        if (content != null) {
            tvContent.setText(content);
        }

        cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.LeftClick(dialog);
            }
        });

        confirm_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.RightClick(dialog);
            }
        });
        dialog.show();
    }

    /**
     *
     * @param isShowType {用来判断是否显示车位类型}
     * @param context
     * @param width
     * @param title
     * @param listener
     */
    public static void showMyParkingDialog(boolean isShowType,Context context,int width ,String title, final ShowDialogCallBack listener) {
        View dialogview = LayoutInflater.from(context).inflate(R.layout.my_parking_dialog, null);
        final Dialog dialog = new Dialog(context, R.style.dialog_bg_style);
        //设置view
        dialog.setContentView(dialogview);
        dialog.setCanceledOnTouchOutside(false);
        //dialog默认是环绕内容的
        //通过window来设置位置、高宽
        Window window = dialog.getWindow();
        WindowManager.LayoutParams windowparams = window.getAttributes();
        windowparams.width = width;

        TextView confirm_tv = (TextView) dialogview.findViewById(R.id.confirm_tv);
        TextView cancel_tv = (TextView) dialogview.findViewById(R.id.cancel_tv);
        TextView tvTitle = (TextView) dialogview.findViewById(R.id.title);

        EditText edit_parking_type =dialogview.findViewById(R.id.edit_parking_type);

        EditText edit_parking_number =dialogview.findViewById(R.id.edit_parking_number);

        if (isShowType) {
            edit_parking_type.setVisibility(View.VISIBLE);
            edit_parking_number.setVisibility(View.GONE);
        } else {
            edit_parking_type.setVisibility(View.GONE);
            edit_parking_number.setVisibility(View.VISIBLE);
        }

        if (title != null) {
            tvTitle.setText(title);
        }

        cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.LeftClick(dialog);
            }
        });

        confirm_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.RightClick(dialog);
            }
        });
        dialog.show();
    }

    public interface ShowDialogCallBack {
        void LeftClick(Dialog dialog);

        void RightClick(Dialog dialog);
    }
    public interface ShowEditCallBack {
        void LeftClick(Dialog dialog);

        void RightClick(Dialog dialog,TextView textView,String s);
    }

    public interface ShowCameraCallBack {
        void TopClick(Dialog dialog);

        void CenterClick(Dialog dialog);
        void CancelClick(Dialog dialog);
    }

    public interface ShowGenderCallBack {
        void ManClick(Dialog dialog);

        void femaleClick(Dialog dialog);
    }

    public static int dip2px(Context ctx, int dpValue) {
        float scale = ctx.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context ctx, int pxValue) {
        float scale = ctx.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }



    public static void showBasicDialog(Context context, int width , final ShowEditCallBack listener, final TextView textView) {
        View dialogview = LayoutInflater.from(context).inflate(R.layout.basic_dialog, null);
        final Dialog dialog = new Dialog(context, R.style.dialog_bg_style);
        final StringBuilder text = new StringBuilder();
        //设置view
        dialog.setContentView(dialogview);
        dialog.setCanceledOnTouchOutside(false);
        //dialog默认是环绕内容的
        //通过window来设置位置、高宽
        Window window = dialog.getWindow();
        WindowManager.LayoutParams windowparams = window.getAttributes();
        windowparams.width = width;
        windowparams.gravity= Gravity.CENTER;
        EditText edit_text = dialogview.findViewById(R.id.edit_text);
        edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                text.delete(0,text.length());
                text.append(s.toString());
            }
        });
        Button confirm_tv =  dialogview.findViewById(R.id.confirm_btn);
        Button cancel_tv =  dialogview.findViewById(R.id.cancel_btn);

        cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.LeftClick(dialog);
            }
        });

        confirm_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.RightClick(dialog,textView,text.toString());
            }
        });
        dialog.show();
    }

    public static void showSelectCameraDialog(Context context,int width , final ShowCameraCallBack listener) {
        View dialogview = LayoutInflater.from(context).inflate(R.layout.header_select_dialog, null);
        final Dialog dialog = new Dialog(context, R.style.dialog_bg_style);
        //设置view
        dialog.setContentView(dialogview);
        dialog.setCanceledOnTouchOutside(false);
        //dialog默认是环绕内容的
        //通过window来设置位置、高宽
        Window window = dialog.getWindow();
        WindowManager.LayoutParams windowparams = window.getAttributes();
        windowparams.width = width;
        windowparams.gravity= Gravity.BOTTOM;

        TextView photograph=dialogview.findViewById(R.id.photograph);
        TextView select_camera=dialogview.findViewById(R.id.select_camera);
        TextView cancel=dialogview.findViewById(R.id.cancel);
        photograph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            listener.TopClick(dialog);
            }
        });
        select_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            listener.CenterClick(dialog);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                listener.CancelClick(dialog);
            }
        });
        dialog.show();
    }

    public static void showSelectGenderDialog(Context context,int width , final ShowGenderCallBack listener) {
        View dialogview = LayoutInflater.from(context).inflate(R.layout.gender_select_dialog, null);
        final Dialog dialog = new Dialog(context, R.style.dialog_bg_style);
        //设置view
        dialog.setContentView(dialogview);
        dialog.setCanceledOnTouchOutside(false);
        //dialog默认是环绕内容的
        //通过window来设置位置、高宽
        Window window = dialog.getWindow();
        WindowManager.LayoutParams windowparams = window.getAttributes();
        windowparams.width = width;
        windowparams.gravity= Gravity.BOTTOM;
        TextView man=dialogview.findViewById(R.id.man);
        TextView female=dialogview.findViewById(R.id.female);
        man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.ManClick(dialog);
            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.femaleClick(dialog);
            }
        });
        dialog.show();
    }
}

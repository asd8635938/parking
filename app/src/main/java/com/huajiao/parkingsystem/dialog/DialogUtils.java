package com.huajiao.parkingsystem.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huajiao.parkingsystem.R;

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

    public interface ShowDialogCallBack {
        void LeftClick(Dialog dialog);

        void RightClick(Dialog dialog);
    }

    public static int dip2px(Context ctx, int dpValue) {
        float scale = ctx.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context ctx, int pxValue) {
        float scale = ctx.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}

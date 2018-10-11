package com.huajiao.parkingsystem.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.huajiao.parkingsystem.R;

public class SettingDialog extends Dialog {
    private Context context;
    private Button bindBtn;
    private Button cancelBtn;
    private TextView updateTx;
    private TextView titleTx;
    public SettingDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

     private ClickListenerInterface clickListenerInterface;

             public interface ClickListenerInterface {

                public void doConfirm();

                 public void doCancel();
   }
    public void setClicklistener(ClickListenerInterface clickListenerInterface) {
        this.clickListenerInterface = clickListenerInterface;
    }
   public void setTitle(String s){
    this.titleTx.setText(s);
   }

    public void setUpdateTx(String s) {
        this.updateTx.setText(s);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
                     // TODO Auto-generated method stub
                     super.onCreate(savedInstanceState);

                    init();
                }

            public void init() {
                    LayoutInflater inflater = LayoutInflater.from(context);
                    View view = inflater.inflate(R.layout.setting_dialog, null);
                    setContentView(view);

                Window win = getWindow();
                WindowManager.LayoutParams lp = win.getAttributes();

                lp.gravity = Gravity.CENTER;
                lp.width = win.getWindowManager().getDefaultDisplay().getWidth() - DialogUtils.dip2px(context,40);
                win.setAttributes(lp);

                bindBtn=view.findViewById(R.id.confirm_btn);
                cancelBtn=view.findViewById(R.id.cancel_btn);
                updateTx=view.findViewById(R.id.content);
                titleTx=view.findViewById(R.id.title);

                bindBtn.setOnClickListener(new clickListener());
                cancelBtn.setOnClickListener(new clickListener());

                }

             private class clickListener implements View.OnClickListener {
                        @Override
                        public void onClick(View v) {
                             // TODO Auto-generated method stub
                                int id = v.getId();
                                switch (id)
                                {
                                    case R.id.bind_btn:
                                            clickListenerInterface.doConfirm();
                                            break;
                                    case R.id.cancel_btn:
                                            clickListenerInterface.doCancel();
                                            break;
                                }
                            }

             };

}


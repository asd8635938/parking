package com.huajiao.parkingsystem.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.huajiao.parkingsystem.R;

public class HintDialog extends Dialog {
    private Context context;
    private Button bindBtn;
    private Button cancelBtn;
    public HintDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

     private ClickListenerInterface clickListenerInterface;

             public interface ClickListenerInterface {

                public void doConfirm();

                 public void doCancel();
   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
                     // TODO Auto-generated method stub
                     super.onCreate(savedInstanceState);

                    init();
                }

            public void init() {
                    LayoutInflater inflater = LayoutInflater.from(context);
                    View view = inflater.inflate(R.layout.hint_dialog, null);
                    setContentView(view);

                bindBtn=view.findViewById(R.id.bind_btn);
                cancelBtn=view.findViewById(R.id.cancel_btn);

                bindBtn.setOnClickListener(new clickListener());
                cancelBtn.setOnClickListener(new clickListener());

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


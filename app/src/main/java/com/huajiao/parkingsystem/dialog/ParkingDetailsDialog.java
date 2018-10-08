package com.huajiao.parkingsystem.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.huajiao.parkingsystem.R;

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

    private Button payCost;
    private boolean isEbdHint=false;

    public ParkingDetailsDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

     private ClickListenerInterface clickListenerInterface;

             public interface ClickListenerInterface {

                public void doConfirm();
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

                closeBt.setOnClickListener(new clickListener());
                whenBt.setOnClickListener(new clickListener());
                secondsBt.setOnClickListener(new clickListener());
                isEndHintBt.setOnClickListener(new clickListener());
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
                            isEbdHint=isEndHintBt.isClickable();
                            break;
                        case R.id.time_btn:
                            showTimePop();
                            break;
                        case R.id.pay_cost:
                            clickListenerInterface.doConfirm();
                            break;

                    }
                }

            }

    private void showTimePop() {
    }

    private void showSecondsPop() {
    }

    private void showWhenPop() {
    }

    ;

}


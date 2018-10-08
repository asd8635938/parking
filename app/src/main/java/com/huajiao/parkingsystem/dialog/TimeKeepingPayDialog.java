package com.huajiao.parkingsystem.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huajiao.parkingsystem.R;

public class TimeKeepingPayDialog extends Dialog {
    private Context context;
    private ImageView closeBt;
    private ImageView selectDiscountCouponBt;


    private TextView payCoinTt;
    private TextView discountCouponCoinTt;
    private TextView payCoinUltimatelyTt;

    private LinearLayout confirmPay;

    int payCoin=0;
    int discountCouponCoin=0;
    int payCoinUltimatelyCoin=0;


    public TimeKeepingPayDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

     private ClickListenerInterface clickListenerInterface;

             public interface ClickListenerInterface {
                public void doConfirm();
                public void openSelcetDiscountActivity();
   }

    public void setCoin(int payCoin) {
        this.payCoin = payCoin;
    }
    public void setDiscountCouponCoin(int discountCouponCoin) {
        this.discountCouponCoin = discountCouponCoin;
        this.refreshDiscountCoin();
    }
    public void refreshDiscountCoin(){
        if(discountCouponCoinTt!=null) {
            discountCouponCoinTt.setText(this.discountCouponCoin);
        }
        refreshPayCoinUltimately();

    }
    public void refreshPayCoinUltimately(){
        this.payCoinUltimatelyCoin=this.payCoin-this.discountCouponCoin;
        if(payCoinUltimatelyTt!=null)
        {
            payCoinUltimatelyTt.setText(this.payCoinUltimatelyCoin+"");
        }
    }

    public void setClicklistener(ClickListenerInterface clickListenerInterface) {
        this.clickListenerInterface = clickListenerInterface;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
         // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        init();
     }

            public void init() {
                    LayoutInflater inflater = LayoutInflater.from(context);
                    View view = inflater.inflate(R.layout.time_keeping_pay_dialog, null);
                    setContentView(view);

                closeBt=view.findViewById(R.id.close);
                selectDiscountCouponBt=view.findViewById(R.id.select_discount_coupon);
                payCoinTt=view.findViewById(R.id.pay_coin);
                confirmPay=view.findViewById(R.id.confirm_pay);

                payCoinTt.setText(this.payCoin+"");

                discountCouponCoinTt=view.findViewById(R.id.discount_coupon_coin);
                discountCouponCoinTt.setText(this.discountCouponCoin+"");

                payCoinUltimatelyTt=view.findViewById(R.id.pay_coin_ultimately);
                this.payCoinUltimatelyCoin=this.payCoin-this.discountCouponCoin;
                payCoinUltimatelyTt.setText(this.payCoinUltimatelyCoin+"");

                closeBt.setOnClickListener(new clickListener());
                selectDiscountCouponBt.setOnClickListener(new clickListener());
                confirmPay.setOnClickListener(new clickListener());
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
                        case R.id.select_discount_coupon:
                            // 让Activity去获得回调数据
                            clickListenerInterface.openSelcetDiscountActivity();
                            break;
                        case R.id.confirm_pay:
                            clickListenerInterface.doConfirm();
                            break;

                    }
                }

            }


}


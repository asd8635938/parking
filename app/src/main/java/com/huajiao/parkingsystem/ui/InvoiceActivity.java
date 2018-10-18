package com.huajiao.parkingsystem.ui;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.githang.statusbar.StatusBarCompat;
import com.google.gson.Gson;
import com.huajiao.parkingsystem.Ben.InvoiceBean;
import com.huajiao.parkingsystem.Ben.JsonBean;
import com.huajiao.parkingsystem.R;
import com.huajiao.parkingsystem.base.BaseActivity;
import com.huajiao.parkingsystem.dialog.DialogUtils;
import com.huajiao.parkingsystem.tools.CheckUtil;
import com.huajiao.parkingsystem.tools.GetJsonDataUtil;

import org.json.JSONArray;

import java.util.ArrayList;

public class InvoiceActivity extends BaseActivity implements View.OnClickListener{

    private LinearLayout paper_layout;
    private LinearLayout email_layout;

    private Button electronic_invoice;
    private Button paper_invoice;
    private Button submit_btn;


    private TextView select_city;
    private TextView invoice_coin;
    private TextView iphone_text;

    private int coin;
    private int type=-1; // 1代表电子发票  2 代表纸质发票

    private EditText invoice_look_up_et;
    private EditText enterprise_et;
    private EditText invoice_content;
    private EditText email_et;
    private EditText recipient_et;
    private EditText address_et;

    private  String invoiceLookUp;
    private  String enterprise;
    private  String invoiceContent;
    private  String email;
    private  String recipient;
    private  String address;
    private  String iphone="2568";
    private  String city;

    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;

    boolean isLoaded=false;
    /**
     * @return {int} {当前布局的layoutid}
     * 使用方式 直接返回需要setContentView的LayoutId
     */
    @Override
    protected int getViewContentId() {
        return R.layout.invoice;
    }

    /**
     * 在这里初始化你的数据 ps:总在initView之前调用
     */
    @Override
    protected void initData() {
        ArrayList<InvoiceBean> listObj =  (ArrayList<InvoiceBean>) getIntent().getSerializableExtra("list");
        for (InvoiceBean data:listObj) {
            coin+=Integer.valueOf(data.getPayCoin());
        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 写子线程中的操作,解析省市区数据
                initJsonData();
            }
        });
        thread.start();
    }

    /**
     * 在这里初始化你的控件引用
     */
    @Override
    protected void initView() {
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.title));
        setTitleText("开具发票");
        electronic_invoice=findViewById(R.id.electronic_invoice);
        paper_invoice=findViewById(R.id.paper_invoice);
        submit_btn=findViewById(R.id.submit_btn);

        paper_layout=findViewById(R.id.paper_layout);
        email_layout=findViewById(R.id.email_layout);
        select_city=findViewById(R.id.select_city);
        select_city.setText("点我选择城市地区");

        invoice_coin=findViewById(R.id.invoice_coin);
        invoice_coin.setText(coin+"");
        iphone_text=findViewById(R.id.iphone_text);
        iphone_text.setText(iphone);

        invoice_look_up_et=findViewById(R.id.invoice_look_up_et);
        enterprise_et=findViewById(R.id.enterprise);
        invoice_content=findViewById(R.id.invoice_content);
        email_et=findViewById(R.id.email_et);
        recipient_et=findViewById(R.id.recipient_et);
        address_et=findViewById(R.id.address_et);

        // 初始页面调整
        email_layout.setVisibility(View.VISIBLE);
        paper_layout.setVisibility(View.GONE);
        type=1;
        paper_invoice.setTextColor(Color.parseColor("#222222"));
        paper_invoice.setBackgroundResource(R.drawable.registered_btn_no_border);
        electronic_invoice.setTextColor(Color.parseColor("#27c38a"));
        electronic_invoice.setBackgroundResource(R.drawable.registered_btn);


    }

    /***
     * 在这里添加你的控件的任何事件绑定
     */
    @Override
    protected void bindEvent() {
        electronic_invoice.setOnClickListener(this);
        paper_invoice.setOnClickListener(this);
        select_city.setOnClickListener(this);
        submit_btn.setOnClickListener(this);

        invoice_look_up_et.addTextChangedListener(new ClassOfTextWatcher(invoice_look_up_et));
        enterprise_et.addTextChangedListener(new ClassOfTextWatcher(enterprise_et));
        invoice_content.addTextChangedListener(new ClassOfTextWatcher(invoice_content));
        email_et.addTextChangedListener(new ClassOfTextWatcher(email_et));
        recipient_et.addTextChangedListener(new ClassOfTextWatcher(recipient_et));
        address_et.addTextChangedListener(new ClassOfTextWatcher(address_et));
    }

    /**
     * 提供给需要获得网络数据
     */
    @Override
    protected void getInternetData() {

    }
    private void initJsonData() {
        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String jsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseJsonData(jsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {

                    for (int d = 0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }
        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_SUCCESS:
                    isLoaded = true;
                    break;
                case MSG_LOAD_FAILED:
                    showToast("json未能加载成功");
                    break;
            }
        }
    };

    private ArrayList<JsonBean> parseJsonData(String result) {
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }

    //省市县弹框
    private void showPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                city = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2);
//                tvBankProvince.setText(tx+"");
                select_city.setText(city);
            }
        }).setTitleText("")
          .setCancelText("取消")
          .setSubmitText("确定")
          .setDividerColor(Color.BLACK)
          .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
          .setContentTextSize(20)
          .build();

        //pvOptions.setPicker(options1Items);//一级选择器
//        pvOptions.setPicker(options1Items, options2Items);//二级选择器
        pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器
        pvOptions.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.select_city:
                if (isLoaded) {
                    showPickerView();
                } else {
                    showToast("暂时无法选择城市请稍后再试");
                }
                break;
            case R.id.electronic_invoice:
                type=1;
                email_layout.setVisibility(View.VISIBLE);
                paper_layout.setVisibility(View.GONE);
                paper_invoice.setTextColor(Color.parseColor("#222222"));
                paper_invoice.setBackgroundResource(R.drawable.registered_btn_no_border);
                electronic_invoice.setTextColor(Color.parseColor("#27c38a"));
                electronic_invoice.setBackgroundResource(R.drawable.registered_btn);

                break;
            case R.id.paper_invoice:
                type=2;
                paper_layout.setVisibility(View.VISIBLE);
                email_layout.setVisibility(View.GONE);

                electronic_invoice.setTextColor(Color.parseColor("#222222"));
                electronic_invoice.setBackgroundResource(R.drawable.registered_btn_no_border);

                paper_invoice.setTextColor(Color.parseColor("#27c38a"));
                paper_invoice.setBackgroundResource(R.drawable.registered_btn);
                break;
            case R.id.submit_btn:

               if(type==1){
                   if(!CheckUtil.checkString(email)
                           ||!CheckUtil.checkString(invoiceLookUp)
                           ||!CheckUtil.checkString(enterprise)
                           ||!CheckUtil.checkString(invoiceContent)){
                       showToast("请完善发票信息");
                       return;
                   }
               }else {
                   if (!CheckUtil.checkString(invoiceLookUp)
                           ||!CheckUtil.checkString(enterprise)
                           ||!CheckUtil.checkString(invoiceContent)
                           ||!CheckUtil.checkString(recipient)
                           ||!CheckUtil.checkString(iphone)
                           ||!CheckUtil.checkString(address)
                           ||!CheckUtil.checkString(city)){
                       showToast("请完善发票信息");
                       return;
                   }
                   if (city.equals("点我选择城市地区")){
                       showToast("请选择城市地区");
                       return;
                   }
               }
                    DialogUtils.showDialog(false, this, getWindow().getWindowManager().getDefaultDisplay().getWidth() - DialogUtils.dip2px(this, 40),
                        "提示", "已经提交成功请等待后台审核", new DialogUtils.ShowDialogCallBack() {
                            @Override
                            public void LeftClick(Dialog dialog) {

                            }

                            @Override
                            public void RightClick(Dialog dialog) {
                                openActivity(WeCenterActivity.class);
                                finish();
                            }
                        },false);
                break;
        }
    }

    private class ClassOfTextWatcher implements TextWatcher
    {
        private TextView view;

        public ClassOfTextWatcher(View view) {

            if (view instanceof TextView)
                this.view = (TextView) view;
            else
                throw new ClassCastException(
                        "view must be an instance Of TextView");
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() >= 0) {
                switch (this.view.getId())
                {
                    case R.id.invoice_look_up_et:
                        invoiceLookUp=s.toString();
                        break;
                    case R.id.enterprise:
                        enterprise=s.toString();
                        break;
                    case R.id.invoice_content:
                        invoiceContent=s.toString();
                        break;
                    case R.id.email_et:
                         email=s.toString();
                        break;
                    case R.id.recipient_et:
                        recipient=s.toString();
                        break;
                    case R.id.address_et:
                        address=s.toString();
                        break;
                }
            }
        }
    }
}

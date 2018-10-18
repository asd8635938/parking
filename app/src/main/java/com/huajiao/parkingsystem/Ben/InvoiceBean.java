package com.huajiao.parkingsystem.Ben;


import java.io.Serializable;

/**
 * Created by DELL on 2018/10/10.
 */

public class InvoiceBean implements Serializable{

    private Boolean isCheck;
    private int id;

    private String time;
    private String address;
    private String useDuration;
    private String payCoin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getCheck() {
        return isCheck;
    }

    public void setCheck(Boolean check) {
        isCheck = check;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUseDuration() {
        return useDuration;
    }

    public void setUseDuration(String useDuration) {
        this.useDuration = useDuration;
    }

    public String getPayCoin() {
        return payCoin;
    }

    public void setPayCoin(String payCoin) {
        this.payCoin = payCoin;
    }

}

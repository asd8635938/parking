package com.huajiao.parkingsystem.Ben;

import java.io.Serializable;

public class StopParkingOrderData implements Serializable{
      private String time;
      private boolean isUse;
      private int dataType; // 0是停车订单 1是预约停车订单
      private int stateType; // 0是预约中 1是已到达 2是已取消 3已超时
      private String state;

 public String getTime() {
  return time;
 }

 public void setTime(String time) {
  this.time = time;
 }

    public boolean isUse() {
        return isUse;
    }

    public void setUse(boolean use) {
        isUse = use;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public int getStateType() {
        return stateType;
    }

    public void setStateType(int stateType) {
        this.stateType = stateType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}



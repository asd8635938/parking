package com.huajiao.parkingsystem.Ben;

import java.io.Serializable;

public class StopParkingOrderData implements Serializable{
      private String time;
      private boolean isUse;

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
}



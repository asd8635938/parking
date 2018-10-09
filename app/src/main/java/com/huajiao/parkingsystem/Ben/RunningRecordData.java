package com.huajiao.parkingsystem.Ben;

public class RunningRecordData {

      private String matters;
      private String time;
      private String coin;
      private boolean isWin;

    public String getMatters(){return matters;}

    public void setMatters(String matters) {
     this.matters = matters;
    }

 public String getTime() {
  return time;
 }

 public void setTime(String time) {
  this.time = time;
 }

 public String getCoin() {
  return coin;
 }

 public void setCoin(String coin) {
  this.coin = coin;
 }

    public boolean isWin() {
        return isWin;
    }

    public void setWin(boolean win) {
        isWin = win;
    }
}



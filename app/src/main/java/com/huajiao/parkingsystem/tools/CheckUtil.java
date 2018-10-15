package com.huajiao.parkingsystem.tools;

import android.support.annotation.Nullable;
import android.text.TextUtils;

public class CheckUtil {
    public static boolean checkPhone( @Nullable String phone){
        // "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        String telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";
        if (TextUtils.isEmpty(phone)) {
            return false;
        }
        else {
            return phone.matches(telRegex);
        }
    }

    public static boolean checkString(String s){

        if(s==null&&TextUtils.isEmpty(s)){
            return false;
        }
        return true;
    }
    public static boolean checkStringContent(String s,String t){

        if(!TextUtils.equals(s,t)){
            return false;
        }
        return true;
    }
}

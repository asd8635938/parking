package com.huajiao.parkingsystem.tools;

import com.huajiao.parkingsystem.Ben.CityData;

import java.util.Comparator;

public class PinyinComparator implements Comparator<CityData> {
    public int compare(CityData o1, CityData o2) {
        if (o1.getFristA().equals("@")
                || o2.getFristA().equals("#")) {
            return -1;
        } else if (o1.getFristA().equals("#")
                || o2.getFristA().equals("@")) {
            return 1;
        } else {
            return o1.getFristA().compareTo(o2.getFristA());
        }
    }
}

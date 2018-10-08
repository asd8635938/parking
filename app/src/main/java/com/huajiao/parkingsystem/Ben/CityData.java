package com.huajiao.parkingsystem.Ben;

import com.huajiao.parkingsystem.tools.CharacterParser;

public class CityData implements Comparable<CityData>{
        private String name; // 姓名
         private String pinyin; // 姓名对应的拼音
         private String firstLetter; // 拼音的首字母
         public CityData() {    }
         public CityData(String name) {
               this.name = name;
         pinyin = CharacterParser.getInstance().convert(name); // 根据姓名获取拼音
         firstLetter = pinyin.substring(0, 1).toUpperCase(); // 获取拼音首字母并转成大写
         if (!firstLetter.matches("[A-Z]")) { // 如果不在A-Z中则默认为“#”
         firstLetter = "#";
            }
         }
         public String getName() {        return name;    }
         public String getPinyin() {        return pinyin;    }
         public String getFirstLetter() {        return firstLetter;    }
         @Override
         public int compareTo(CityData another) {
         if (firstLetter.equals("#") && !another.getFirstLetter().equals("#")) {
         return 1;
          } else if (!firstLetter.equals("#") && another.getFirstLetter().equals("#")){
              return -1;
           } else {            return pinyin.compareToIgnoreCase(another.getPinyin());        }
          }}


package com.huajiao.parkingsystem.Ben;

public class SearchData {
    private int dataType; // 数据类型
    private String name;  // 名称
    private int distanceNumber; // 距离
    private String freeDurationContent; // 免费时长
    private String residueContent; // 剩余普通车位数
    private String residueNoCommonContent; // 剩余非普通的车位数
    private boolean isCanCommonNumber; // 是否有充电桩车位

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistanceNumber() {
        return distanceNumber;
    }

    public void setDistanceNumber(int distanceNumber) {
        this.distanceNumber = distanceNumber;
    }

    public String getFreeDurationContent() {
        return freeDurationContent;
    }

    public void setFreeDurationContent(String freeDurationContent) {
        this.freeDurationContent = freeDurationContent;
    }

    public String getResidueContent() {
        return residueContent;
    }

    public void setResidueContent(String residueContent) {
        this.residueContent = residueContent;
    }

    public String getResidueNoCommonContent() {
        return residueNoCommonContent;
    }

    public void setResidueNoCommonContent(String residueNoCommonContent) {
        this.residueNoCommonContent = residueNoCommonContent;
    }

    public boolean isCanCommonNumber() {
        return isCanCommonNumber;
    }

    public void setCanCommonNumber(boolean canCommonNumber) {
        isCanCommonNumber = canCommonNumber;
    }
}

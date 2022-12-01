package com.example.myapplication;

public class WeatherList {
    String desc, dateTime;
    double tmp;

    public WeatherList() {
        this.desc = desc;
        this.tmp = tmp;
        this.dateTime = dateTime;
    }

    public String getDesc() {
        return desc;
    }

    public String getDateTime() {
        return dateTime;
    }

    public double getTmp() {
        return tmp;
    }

    public void setDesc(String desc) {
        this.desc = desc;

    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setTmp(double tmp) {
        this.tmp = tmp;
    }
}

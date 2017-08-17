package com.jdyy.ytwp.bean;

/**
 * Created by Administrator on 2016/8/29 0029.
 */
public class Silver {

    private String time;
    private String week;
    private String sum;
    private String state;
    private String source;

    public Silver() {
    }

    public Silver(String time, String week, String sum, String state, String source) {
        this.time = time;
        this.week = week;
        this.sum = sum;
        this.state = state;
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

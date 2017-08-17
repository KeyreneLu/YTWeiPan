package com.jdyy.ytwp.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/27 0027.
 */
public class Bank implements Serializable {
    private int logo;
    private String name;
    private String cost;

    public Bank(int logo, String name, String cost) {
        this.logo = logo;
        this.name = name;
        this.cost = cost;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "logo=" + logo +
                ", name='" + name + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }
}

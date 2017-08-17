package com.jdyy.ytwp.bean;

/**
 * Created by Administrator on 2016/8/30 0030.
 */
public class Crew {
    private String name;
    private String phone;
    private String balance;

    public Crew() {
    }

    public Crew(String name, String phone, String balance) {
        this.name = name;
        this.phone = phone;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}

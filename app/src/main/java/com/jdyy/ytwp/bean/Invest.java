package com.jdyy.ytwp.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/8/22 0022.
 */
public class Invest implements Parcelable {

    private String name;
    private String rose;
    private String fall;
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRose() {
        return rose;
    }

    public void setRose(String rose) {
        this.rose = rose;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFall() {
        return fall;
    }

    public void setFall(String fall) {
        this.fall = fall;
    }

    public Invest(String name, String state, String fall, String rose) {
        this.name = name;
        this.state = state;
        this.fall = fall;
        this.rose = rose;
    }

    public Invest() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.rose);
        dest.writeString(this.fall);
        dest.writeString(this.state);
    }

    protected Invest(Parcel in) {
        this.name = in.readString();
        this.rose = in.readString();
        this.fall = in.readString();
        this.state = in.readString();
    }

    public static final Parcelable.Creator<Invest> CREATOR = new Parcelable.Creator<Invest>() {
        @Override
        public Invest createFromParcel(Parcel source) {
            return new Invest(source);
        }

        @Override
        public Invest[] newArray(int size) {
            return new Invest[size];
        }
    };
}

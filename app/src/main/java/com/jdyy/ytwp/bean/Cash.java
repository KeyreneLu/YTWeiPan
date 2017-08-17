package com.jdyy.ytwp.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/8/26 0026.
 */
public class Cash implements Parcelable {
    private String size;
    private String Date;
    private String Valid;

    public Cash(String size, String date, String valid) {
        this.size = size;
        Date = date;
        Valid = valid;
    }

    public String getValid() {
        return Valid;
    }

    public void setValid(String valid) {
        Valid = valid;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getsize() {
        return size;
    }

    public void setsize(String size) {
        this.size = size;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.size);
        dest.writeString(this.Date);
        dest.writeString(this.Valid);
    }

    protected Cash(Parcel in) {
        this.size = in.readString();
        this.Date = in.readString();
        this.Valid = in.readString();
    }

    public static final Parcelable.Creator<Cash> CREATOR = new Parcelable.Creator<Cash>() {
        @Override
        public Cash createFromParcel(Parcel source) {
            return new Cash(source);
        }

        @Override
        public Cash[] newArray(int size) {
            return new Cash[size];
        }
    };
}

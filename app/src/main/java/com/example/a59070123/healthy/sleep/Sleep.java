package com.example.a59070123.healthy.sleep;

import android.os.Parcel;
import android.os.Parcelable;

public class Sleep implements Parcelable {
    String date;
    String time_slp;
    String time_awake;
    String time_total;
    String id;

    public Sleep(){}

    public Sleep(String id, String date, String time_slp, String time_awake, String time_total){
        this.id = id;
        this.date = date;
        this.time_slp = time_slp;
        this.time_awake = time_awake;
        this.time_total = time_total;

    }

    public Sleep(String date, String time_slp, String time_awake, String time_total){
        this.date = date;
        this.time_slp = time_slp;
        this.time_awake = time_awake;
        this.time_total = time_total;

    }

    protected Sleep(Parcel in) {
        date = in.readString();
        time_slp = in.readString();
        time_awake = in.readString();
        time_total = in.readString();
        id = in.readString();
    }

    public static final Creator<Sleep> CREATOR = new Creator<Sleep>() {
        @Override
        public Sleep createFromParcel(Parcel in) {
            return new Sleep(in);
        }

        @Override
        public Sleep[] newArray(int size) {
            return new Sleep[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime_total() {
        return time_total;
    }

    public void setTime_total(String time_total) {
        this.time_total = time_total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime_slp() {
        return time_slp;
    }

    public void setTime_slp(String time_slp) {
        this.time_slp = time_slp;
    }

    public String getTime_awake() {
        return time_awake;
    }

    public void setTime_awake(String time_awake) {
        this.time_awake = time_awake;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(time_slp);
        dest.writeString(time_awake);
        dest.writeString(time_total);
        dest.writeString(id);
    }
}

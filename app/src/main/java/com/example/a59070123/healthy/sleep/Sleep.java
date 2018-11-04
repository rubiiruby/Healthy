package com.example.a59070123.healthy.sleep;

public class Sleep {
    String date;
    String time_slp;
    String time_awake;
    String time_total;

    public Sleep(){}

    public Sleep(String date, String time_slp, String time_awake, String time_total){
        this.date = date;
        this.time_slp = time_slp;
        this.time_awake = time_awake;
        this.time_total = time_total;

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
}

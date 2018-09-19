package com.example.a59070123.healthy.weight;

/**
 * Created by Penporn Pettammarot 59070123 IT KMITL
 */

public class Weight {
     String date;
     int weight;
     String status;

    public Weight(){}

    public Weight(String date, int weight, String status){
        this.date = date;
        this.weight = weight;
        this.status = status;
    }

    public Weight(String date, int weight){
        this.date = date;
        this.weight = weight;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

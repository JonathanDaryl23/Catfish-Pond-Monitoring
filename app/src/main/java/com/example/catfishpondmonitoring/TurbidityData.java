package com.example.catfishpondmonitoring;

public class TurbidityData {
    private String date;  // Store the date as a String
    private String time;  // Store the time as a String
    private double value; // The turbidity value

    // Default constructor required for calls to DataSnapshot.getValue(TurbidityData.class)
    public TurbidityData() {
    }

    public TurbidityData(String date, String time, double value) {
        this.date = date;
        this.time = time;
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public double getValue() {
        return value;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setValue(double value) {
        this.value = value;
    }
}

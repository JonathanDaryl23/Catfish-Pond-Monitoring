package com.example.catfishpondmonitoring;

public class WaterLevelData {
    private String date;
    private String time;
    private float value;

    // Default constructor required for calls to DataSnapshot.getValue(WaterLevel.class)
    public WaterLevelData() {
    }

    public WaterLevelData(String date, String time, float value) {
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

    public float getValue() {
        return value;
    }
}
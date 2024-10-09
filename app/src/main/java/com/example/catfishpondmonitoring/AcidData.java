package com.example.catfishpondmonitoring;

public class AcidData {
    private String id; // Optional for identification
    private String date; // Date of the reading
    private String time; // Time of the reading
    private double acidLevel; // Value of acid level

    public AcidData() {
        // Default constructor required for calls to DataSnapshot.getValue(AcidData.class)
    }

    public AcidData(String date, String time, double acidLevel) {
        this.date = date;
        this.time = time;
        this.acidLevel = acidLevel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getAcidLevel() {
        return acidLevel;
    }

    public void setAcidLevel(double acidLevel) {
        this.acidLevel = acidLevel;
    }
}

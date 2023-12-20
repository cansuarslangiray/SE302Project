package org.example;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class workLoad {

    public String activity;
    public int count;
    public int hour;
    public int workloud;


    public void setActivity(String activity) {
        this.activity = activity;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setWorkloud(int workloud) {
        this.workloud = workloud;
    }

    public workLoad(String activity, int count, int hour, int workloud) {
        this.activity = activity;
        this.count = count;
        this.hour = hour;
        this.workloud = workloud;
    }
}
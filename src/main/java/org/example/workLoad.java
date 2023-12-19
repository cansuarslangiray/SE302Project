package org.example;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class workLoad {

    public StringProperty activity;
    public IntegerProperty count;
    public IntegerProperty hour;
    public IntegerProperty workloud;

    public String getActivity() {
        return activity.get();
    }

    public StringProperty activityProperty() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity.set(activity);
    }

    public int getCount() {
        return count.get();
    }

    public IntegerProperty countProperty() {
        return count;
    }

    public void setCount(int count) {
        this.count.set(count);
    }

    public int getHour() {
        return hour.get();
    }

    public IntegerProperty hourProperty() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour.set(hour);
    }

    public int getWorkloud() {
        return workloud.get();
    }

    public IntegerProperty workloudProperty() {
        return workloud;
    }

    public void setWorkloud(int workloud) {
        this.workloud.set(workloud);
    }


    public workLoad(String activity, int count, int hour , int workloud) {
        this.activity = new SimpleStringProperty(activity);
        this.count = new SimpleIntegerProperty(count);
        this.hour = new SimpleIntegerProperty(hour);
        this.workloud = new SimpleIntegerProperty(workloud);
    }
}
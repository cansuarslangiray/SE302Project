package org.example;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Activity {
    public String name;
    public int count;
    public int percentage;
    public int lo1;
    public int lo2;
    public int lo3;
    public int lo4;


    public Activity(String name, int count, int percentage, int lo1, int lo2, int lo3, int lo4) {
        this.name = name;
        this.count = count;
        this.percentage = percentage;
        this.lo1 = lo1;
        this.lo2 = lo2;
        this.lo3 = lo3;
        this.lo4 = lo4;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public void setLo1(int lo1) {
        this.lo1 = lo1;
    }

    public void setLo2(int lo2) {
        this.lo2 = lo2;
    }

    public void setLo3(int lo3) {
        this.lo3 = lo3;
    }

    public void setLo4(int lo4) {
        this.lo4 = lo4;
    }

}
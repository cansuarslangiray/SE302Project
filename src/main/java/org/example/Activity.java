package org.example;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Activity {
    public StringProperty name;
    public IntegerProperty count;
    public IntegerProperty percentage;
    public IntegerProperty lo1;
    public IntegerProperty lo2;
    public IntegerProperty lo3;
    public IntegerProperty lo4;
    public IntegerProperty lo5;
    public IntegerProperty lo6;



    public Activity(String name, int count, int percentage, int lo1,int lo2,int lo3,int lo4,int lo5,int lo6) {
        this.name = new SimpleStringProperty(name);
        this.count = new SimpleIntegerProperty(count);
        this.percentage = new SimpleIntegerProperty(percentage);
        this.lo1 = new SimpleIntegerProperty(lo1);
        this.lo2 = new SimpleIntegerProperty(lo2);
        this.lo3 = new SimpleIntegerProperty(lo3);
        this.lo4 = new SimpleIntegerProperty(lo4);
        this.lo5 = new SimpleIntegerProperty(lo5);
        this.lo6 = new SimpleIntegerProperty(lo6);
    }
    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
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

    public int getPercentage() {
        return percentage.get();
    }

    public IntegerProperty percentageProperty() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage.set(percentage);
    }

    public int getLo1() {
        return lo1.get();
    }

    public IntegerProperty lo1Property() {
        return lo1;
    }

    public void setLo1(int lo1) {
        this.lo1.set(lo1);
    }

    public int getLo2() {
        return lo2.get();
    }

    public IntegerProperty lo2Property() {
        return lo2;
    }

    public void setLo2(int lo2) {
        this.lo2.set(lo2);
    }

    public int getLo3() {
        return lo3.get();
    }

    public IntegerProperty lo3Property() {
        return lo3;
    }

    public void setLo3(int lo3) {
        this.lo3.set(lo3);
    }

    public int getLo4() {
        return lo4.get();
    }

    public IntegerProperty lo4Property() {
        return lo4;
    }

    public void setLo4(int lo4) {
        this.lo4.set(lo4);
    }

    public int getLo5() {
        return lo5.get();
    }

    public IntegerProperty lo5Property() {
        return lo5;
    }

    public void setLo5(int lo5) {
        this.lo5.set(lo5);
    }

    public int getLo6() {
        return lo6.get();
    }

    public IntegerProperty lo6Property() {
        return lo6;
    }

    public void setLo6(int lo6) {
        this.lo6.set(lo6);
    }

}
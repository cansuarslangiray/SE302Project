package org.example;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Competency {
    public StringProperty description;
    public IntegerProperty level1;
    public IntegerProperty level2;

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public int getLevel1() {
        return level1.get();
    }

    public IntegerProperty level1Property() {
        return level1;
    }

    public void setLevel1(int level1) {
        this.level1.set(level1);
    }

    public int getLevel2() {
        return level2.get();
    }

    public IntegerProperty level2Property() {
        return level2;
    }

    public void setLevel2(int level2) {
        this.level2.set(level2);
    }

    public int getLevel3() {
        return level3.get();
    }

    public IntegerProperty level3Property() {
        return level3;
    }

    public void setLevel3(int level3) {
        this.level3.set(level3);
    }

    public int getLevel4() {
        return level4.get();
    }

    public IntegerProperty level4Property() {
        return level4;
    }

    public void setLevel4(int level4) {
        this.level4.set(level4);
    }

    public int getLevel5() {
        return level5.get();
    }

    public IntegerProperty level5Property() {
        return level5;
    }

    public void setLevel5(int level5) {
        this.level5.set(level5);
    }

    public String getLo() {
        return lo.get();
    }

    public StringProperty loProperty() {
        return lo;
    }

    public void setLo(String lo) {
        this.lo.set(lo);
    }

    public IntegerProperty level3;
    public IntegerProperty level4;
    public IntegerProperty level5;
    public StringProperty lo;

    public Competency(String description, Integer level1, Integer level2, Integer level3, Integer level4, Integer level5, String lo) {
        this.description = new SimpleStringProperty(description);
        this.level1 = new SimpleIntegerProperty(level1);
        this.level2 = new SimpleIntegerProperty(level2);
        this.level3 = new SimpleIntegerProperty(level3);
        this.level4 = new SimpleIntegerProperty(level4);
        this.level5 = new SimpleIntegerProperty(level5);
        this.lo = new SimpleStringProperty(lo);
    }
}

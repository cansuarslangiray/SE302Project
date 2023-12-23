package org.example;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Competency {
    public String description;
    public int level1;
    public int level2;

    public String getDescription() {
        return description;
    }

    public int getLevel1() {
        return level1;
    }

    public int getLevel2() {
        return level2;
    }

    public int getLevel3() {
        return level3;
    }

    public int getLevel4() {
        return level4;
    }

    public int getLevel5() {
        return level5;
    }

    public String getLo() {
        return lo;
    }

    public int level3;
    public int level4;
    public int level5;
    public String lo;

    public Competency(String description, int level1, int level2, int level3, int level4, int level5) {
        this.description = description;
        this.level1 = level1;
        this.level2 = level2;
        this.level3 = level3;
        this.level4 = level4;
        this.level5 = level5;

    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLevel1(int level1) {
        this.level1 = level1;
    }

    public void setLevel2(int level2) {
        this.level2 = level2;
    }

    public void setLevel3(int level3) {
        this.level3 = level3;
    }

    public void setLevel4(int level4) {
        this.level4 = level4;
    }

    public void setLevel5(int level5) {
        this.level5 = level5;
    }

    public void setLo(String lo) {
        this.lo = lo;
    }
}

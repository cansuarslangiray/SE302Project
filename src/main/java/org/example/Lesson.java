package org.example;

import javafx.beans.property.SimpleStringProperty;

public class Lesson {
    public String week;
    public String topics;
    public String preparation;

    public void setWeek(String week) {
        this.week = week;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public String getWeek() {
        return week;
    }

    public String getTopics() {
        return topics;
    }

    public String getPreparation() {
        return preparation;
    }

    public Lesson() {

    }

    public Lesson(String week, String topics, String preparation) {
        this.week = week;
        this.topics = topics;
        this.preparation = preparation;
    }
}


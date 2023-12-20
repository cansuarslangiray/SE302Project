package org.example;

import javafx.beans.property.SimpleStringProperty;

public class Lesson {
    public final SimpleStringProperty week;
    public final SimpleStringProperty topics;
    public final SimpleStringProperty preparation;

    public void setWeek(String week) {
        this.week.set(week);
    }

    public void setTopics(String topics) {
        this.topics.set(topics);
    }

    public void setPreparation(String preparation) {
        this.preparation.set(preparation);
    }



    public Lesson(String week, String topics, String preparation) {
        this.week = new SimpleStringProperty(week);
        this.topics = new SimpleStringProperty(topics);
        this.preparation = new SimpleStringProperty(preparation);
    }

    public String getWeek() {
        return week.get();
    }

    public String getTopics() {
        return topics.get();
    }

    public String getPreparation() {
        return preparation.get();
    }
}

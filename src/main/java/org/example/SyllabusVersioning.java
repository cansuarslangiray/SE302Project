package org.example;

public class SyllabusVersioning {
    String name;
    String reason;
    Lecture lecture;
    String time;

    public SyllabusVersioning(String name, String reason, String time, Lecture lecture) {
        this.name = name;
        this.reason = reason;
        this.time=time;
        this.lecture = lecture;
    }
}

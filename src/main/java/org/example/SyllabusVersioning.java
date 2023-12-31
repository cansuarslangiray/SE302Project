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

    @Override
    public String toString() {
        return "SyllabusVersioning{" +
                "name='" + name + '\'' +
                ", reason='" + reason + '\'' +
                ", lecture=" + lecture +
                ", time='" + time + '\'' +
                '}';
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public String getReason() {
        return reason;
    }

    public Lecture getLecture() {
        return lecture;
    }
}
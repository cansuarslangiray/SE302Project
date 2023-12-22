package org.example;


import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class Lecture {

    public String courseName;
    public String courseCode;
    public String term;
    public String theoryHours;
    public String applicationHours;
    public String localCredit;
    public String ects;
    public String prerequisites;
    public String language;
    public String type;
    public String courseLevel;
    public String deliveryMode;
    public String teachingMethods;
    public String coordinator;
    public String lecturer;
    public String assistants;
    public List<String> courseCategory;
    public List<Lesson> weeklySubject;
    public String book;
    public String materials;

    public List<Activity> assessmentTable;
    public List<workLoad> workloadTable;
    public List<Competency> outcomeTable;

    @Override
    public String toString() {
        return "Lecture{" +
                "courseName='" + courseName + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", term='" + term + '\'' +
                ", theoryHours='" + theoryHours + '\'' +
                ", applicationHours='" + applicationHours + '\'' +
                ", localCredit='" + localCredit + '\'' +
                ", ects='" + ects + '\'' +
                ", prerequisites='" + prerequisites + '\'' +
                ", language='" + language + '\'' +
                ", type='" + type + '\'' +
                ", courseLevel='" + courseLevel + '\'' +
                ", deliveryMode='" + deliveryMode + '\'' +
                ", teachingMethods='" + teachingMethods + '\'' +
                ", coordinator='" + coordinator + '\'' +
                ", lecturer='" + lecturer + '\'' +
                ", assistants='" + assistants + '\'' +
                ", courseCategory=" + courseCategory +
                ", weeklySubject=" + weeklySubject +
                ", book='" + book + '\'' +
                ", materials='" + materials + '\'' +
                ", assessmentTable=" + assessmentTable +
                ", workloadTable=" + workloadTable +
                ", outcomeTable=" + outcomeTable +
                '}';
    }

    public Lecture(String courseName, String courseCode, String term, String theoryHours, String applicationHours, String localCredit, String ects, String prerequisites, String language, String type, String courseLevel, String deliveryMode, String teachingMethods, String coordinator, String lecturer, String assistants, List<String> courseCategory, List<Lesson> weeklySubject, String book, String materials, List<Activity> assessmentTable, List<workLoad> workloadTable, List<Competency> outcomeTable) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.term = term;
        this.theoryHours = theoryHours;
        this.applicationHours = applicationHours;
        this.localCredit = localCredit;
        this.ects = ects;
        this.prerequisites = prerequisites;
        this.language = language;
        this.type = type;
        this.courseLevel = courseLevel;
        this.deliveryMode = deliveryMode;
        this.teachingMethods = teachingMethods;
        this.coordinator = coordinator;
        this.lecturer = lecturer;
        this.assistants = assistants;
        this.courseCategory = courseCategory;
        this.weeklySubject = weeklySubject;
        this.book = book;
        this.materials = materials;
        this.assessmentTable = assessmentTable;
        this.workloadTable = workloadTable;
        this.outcomeTable = outcomeTable;
    }
    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTerm() {
        return term;
    }

    public String getTheoryHours() {
        return theoryHours;
    }

    public String getApplicationHours() {
        return applicationHours;
    }

    public String getLocalCredit() {
        return localCredit;
    }

    public String getEcts() {
        return ects;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public String getLanguage() {
        return language;
    }

    public String getType() {
        return type;
    }

    public String getCourseLevel() {
        return courseLevel;
    }

    public String getDeliveryMode() {
        return deliveryMode;
    }

    public String getTeachingMethods() {
        return teachingMethods;
    }

    public String getCoordinator() {
        return coordinator;
    }

    public String getLecturer() {
        return lecturer;
    }

    public String getAssistants() {
        return assistants;
    }

    public String getCourseCategory() {
        return courseCategory;
    }

    public List<Lesson> getWeeklySubject() {
        return weeklySubject;
    }

    public String getBook() {
        return book;
    }

    public String getMaterials() {
        return materials;
    }

    public List<Activity> getAssessmentTable() {
        return assessmentTable;
    }

    public List<workLoad> getWorkloadTable() {
        return workloadTable;
    }

    public List<Competency> getOutcomeTable() {
        return outcomeTable;
    }
}

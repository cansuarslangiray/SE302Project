package org.example;


public class Lecture {
     String type;
     String courseLevel;
     String mode;
     String courseCategory;
    String language;
    String courseName;
    String code;
    String term;
    String theory;
    String application;
    String localCredits;
    String ects;
    String prerequisites;
    String teachingMethod;
    String courseCoordinator;
    String courseLecturer;
    String assistant;


    public Lecture(String courseName, String language, String type, String courseLevel, String mode, String courseCategory, String code, String term, String theory, String application, String localCredits, String ects, String prerequisites, String teachingMethod, String courseCoordinator, String courseLecturer, String assistant) {
        this.language = language;
        this.type = type;
        this.courseLevel = courseLevel;
        this.mode = mode;
        this.courseCategory = courseCategory;
        this.code = code;
        this.term = term;
        this.theory = theory;
        this.application = application;
        this.localCredits = localCredits;
        this.ects = ects;
        this.prerequisites = prerequisites;
        this.teachingMethod = teachingMethod;
        this.courseCoordinator = courseCoordinator;
        this.courseLecturer = courseLecturer;
        this.assistant = assistant;
        this.courseName = courseName;
    }
}

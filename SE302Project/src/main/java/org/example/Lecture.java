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

    @Override
    public String toString() {
        return "Lecture{" +
                "type='" + type + '\'' +
                ", courseLevel='" + courseLevel + '\'' +
                ", mode='" + mode + '\'' +
                ", courseCategory='" + courseCategory + '\'' +
                ", language='" + language + '\'' +
                ", courseName='" + courseName + '\'' +
                ", code='" + code + '\'' +
                ", term='" + term + '\'' +
                ", theory='" + theory + '\'' +
                ", application='" + application + '\'' +
                ", localCredits='" + localCredits + '\'' +
                ", ects='" + ects + '\'' +
                ", prerequisites='" + prerequisites + '\'' +
                ", teachingMethod='" + teachingMethod + '\'' +
                ", courseCoordinator='" + courseCoordinator + '\'' +
                ", courseLecturer='" + courseLecturer + '\'' +
                ", assistant='" + assistant + '\'' +
                '}';
    }
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

    public String getType() {
        return type;
    }

    public String getCourseLevel() {
        return courseLevel;
    }

    public String getMode() {
        return mode;
    }

    public String getCourseCategory() {
        return courseCategory;
    }

    public String getLanguage() {
        return language;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCode() {
        return code;
    }

    public String getTerm() {
        return term;
    }

    public String getTheory() {
        return theory;
    }

    public String getApplication() {
        return application;
    }

    public String getLocalCredits() {
        return localCredits;
    }

    public String getEcts() {
        return ects;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public String getTeachingMethod() {
        return teachingMethod;
    }

    public String getCourseCoordinator() {
        return courseCoordinator;
    }

    public String getCourseLecturer() {
        return courseLecturer;
    }

    public String getAssistant() {
        return assistant;
    }
}

package org.example;

import com.google.gson.Gson;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.Scanner;

public class ViewVersion {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the course code: ");
        String courseCode = scanner.nextLine();
        System.out.println("Please enter the version number that you want to view: ");
        int counter = scanner.nextInt();
        String path = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + File.separator + "Syllabus" + File.separator + courseCode+"/"+courseCode+counter+".json" ;
        try {

            File file = new File(path);

            if (!file.exists()) {
                System.out.println("File does not exist: " + file.getAbsolutePath());
                return;
            }

        } finally {

        }
        BufferedReader reader = new BufferedReader(new FileReader(path));
        Gson gson = new Gson();
        SyllabusVersioning syllabus = gson.fromJson(reader, SyllabusVersioning.class);
        printSyllabus(syllabus);
    }


    private static void printSyllabus(SyllabusVersioning syllabus) {
        System.out.println("Details of Version:");
        System.out.println("Lecturer's name: " + syllabus.getName());
        System.out.println("Reason: " + syllabus.getReason());
        System.out.println("Time: " + syllabus.getTime());

        Lecture lecture = syllabus.getLecture();
        if (lecture != null) {
            System.out.println("Details of the Lecture:");
            System.out.println("Type: " + lecture.getType());
            System.out.println("Course Level: " + lecture.getCourseLevel());
            System.out.println("Mode: " + lecture.getMode());
            System.out.println("Course Category: " + lecture.getCourseCategory());
            System.out.println("Language: " + lecture.getLanguage());
            System.out.println("Course Name: " + lecture.getCourseName());
            System.out.println("Code: " + lecture.getCode());
            System.out.println("Term: " + lecture.getTerm());
            System.out.println("Theory: " + lecture.getTheory());
            System.out.println("Application: " + lecture.getApplication());
            System.out.println("Local Credits: " + lecture.getLocalCredits());
            System.out.println("ECTS: " + lecture.getEcts());
            System.out.println("Prerequisites: " + lecture.getPrerequisites());
            System.out.println("Teaching Method: " + lecture.getTeachingMethod());
            System.out.println("Course Coordinator: " + lecture.getCourseCoordinator());
            System.out.println("Course Lecturer: " + lecture.getCourseLecturer());
            System.out.println("Assistant: " + lecture.getAssistant());
        }
    }

    private static void showError(String errorMessage) {
        System.err.println(errorMessage);
    }
}


package org.example;

import com.google.gson.Gson;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.Scanner;

public class ViewVersion {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the course code: ");
        String courseCode = scanner.nextLine();
        System.out.println("Please enter the version number that you want to view: ");
        int counter = scanner.nextInt();
        String path = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + File.separator + "Syllabus" + File.separator + courseCode + "/" + courseCode + counter + ".json";
        try {
            File file = new File(path);

            if (!file.exists()) {
                System.out.println("File does not exist: " + file.getAbsolutePath());
                return;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                Gson gson = new Gson();
                SyllabusVersioning syllabus = gson.fromJson(reader, SyllabusVersioning.class);
                if (syllabus != null) {
                    printSyllabus(syllabus);
                } else {
                    showError("Failed to parse syllabus.");
                }
            } catch (IOException e) {
                showError("Error reading file: " + e.getMessage());
            }
        } catch (Exception e) {
            showError("An unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void printSyllabus(SyllabusVersioning syllabus) {
        System.out.println("Details of Version/ Versiyon Detayları:");
        System.out.println("Lecturer's name/ Öğretmen Adı: " + syllabus.getName());
        System.out.println("Reason/ Sebep: " + syllabus.getReason());
        System.out.println("Time/ Zaman: " + syllabus.getTime());

        Lecture lecture = syllabus.getLecture();
        if (lecture != null) {
            System.out.println("Details of the Lecture/ Dersin Detayları:");
            System.out.println("Type/ Tür: " + lecture.getType());
            System.out.println("Course Level/ Ders Seviyesi: " + lecture.getCourseLevel());
            System.out.println("Mode/ Mod: " + lecture.getDeliveryMode());
            System.out.println("Course Category/ Ders Kategorisi: " + lecture.getCourseCategory());
            System.out.println("Language/ Dil: " + lecture.getLanguage());
            System.out.println("Course Name/ Ders Adı: " + lecture.getCourseName());
            System.out.println("Code/ Ders Kodu: " + lecture.getCourseCode());
            System.out.println("Term/ Dönem: " + lecture.getTerm());
            System.out.println("Theory/ Teori: " + lecture.getTheoryHours());
            System.out.println("Application/ Uygulama: " + lecture.getApplicationHours());
            System.out.println("Local Credits/ Yerel Krediler: " + lecture.getLocalCredit());
            System.out.println("ECTS/ AKTS: " + lecture.getEcts());
            System.out.println("Prerequisites/ Önkoşullar: " + lecture.getPrerequisites());
            System.out.println("Teaching Method/ Öğretme Yöntemi: " + lecture.getTeachingMethods());
            System.out.println("Course Coordinator/ Ders Koordinatörü: " + lecture.getCoordinator());
            System.out.println("Course Lecturer/ Ders Öğretmeni: " + lecture.getLecturer());
            System.out.println("Assistant/ Asistan: " + lecture.getAssistants());
            System.out.println("Weekly Subject/ Haftalık Plan: " + lecture.getWeeklySubject());
            System.out.println("Book/ Kitap: " + lecture.getBook());
            System.out.println("Materials/ Materyaller:" + lecture.getMaterials());
            System.out.println("Assessments/ Değerlendirmeler: " + lecture.getAssessmentTable());
            System.out.println("Workload/ İş yükü " + lecture.getWorkloadTable());
            System.out.println("Outcomes/ Kazanımlar: " + lecture.getOutcomeTable());
        }
    }

    private static void showError(String errorMessage) {
        System.err.println(errorMessage);
    }
}
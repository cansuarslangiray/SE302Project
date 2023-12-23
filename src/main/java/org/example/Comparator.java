package org.example;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.filechooser.FileSystemView;
import java.io.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Comparator extends Application implements java.util.Comparator<Lecture> {

    @Override
    public int compare(Lecture o1, Lecture o2) {

        return 0;
    }

    public List<Difference> findDifferences(Lecture o1, Lecture o2) {
        List<Difference> differences = new ArrayList<>();
        // Implement detailed comparison logic here, comparing all aspects of o1 and o2.

        if (!o1.getCourseName().equals(o2.getCourseName())) {
            differences.add(new Difference("Course Name", o1.getCourseName(), o2.getCourseName()));
        }
        if (!o1.getCourseCode().equals(o2.getCourseCode())) {
            differences.add(new Difference("Course Code", o1.getCourseCode(), o2.getCourseCode()));
        }
        if (!o1.getTerm().equals(o2.getTerm())) {
            differences.add(new Difference("Term", o1.getTerm(), o2.getTerm()));
        }
        if (!o1.getTheoryHours().equals(o2.getTheoryHours())) {
            differences.add(new Difference("Theory Hours", o1.getTheoryHours(), o2.getTheoryHours()));
        }
        if (!o1.getApplicationHours().equals(o2.getApplicationHours())) {
            differences.add(new Difference("Application Hours", o1.getApplicationHours(), o2.getApplicationHours()));
        }
        if (!o1.getLocalCredit().equals(o2.getLocalCredit())) {
            differences.add(new Difference("Local Credit", o1.getLocalCredit(), o2.getLocalCredit()));
        }
        if (!o1.getEcts().equals(o2.getEcts())) {
            differences.add(new Difference("Ects", o1.getEcts(), o2.getEcts()));
        }
        if (!o1.getPrerequisites().equals(o2.getPrerequisites())) {
            differences.add(new Difference("Prerequisites", o1.getPrerequisites(), o2.getPrerequisites()));
        }
        if (!o1.getLanguage().equals(o2.getLanguage())) {
            differences.add(new Difference("Language", o1.getLanguage(), o2.getLanguage()));
        }
        if (!o1.getType().equals(o2.getType())) {
            differences.add(new Difference("Type", o1.getType(), o2.getType()));
        }
        if (!o1.getCourseLevel().equals(o2.getCourseLevel())) {
            differences.add(new Difference("CourseLevel", o1.getCourseLevel(), o2.getCourseLevel()));
        }
        if (!o1.getDeliveryMode().equals(o2.getDeliveryMode())) {
            differences.add(new Difference("Delivery Mode", o1.getDeliveryMode(), o2.getDeliveryMode()));
        }
        if (!o1.getTeachingMethods().equals(o2.getTeachingMethods())) {
            differences.add(new Difference("Teaching Methods", o1.getTeachingMethods(), o2.getTeachingMethods()));
        }
        if (!o1.getCoordinator().equals(o2.getCoordinator())) {
            differences.add(new Difference("Coordinator", o1.getCoordinator(), o2.getCoordinator()));
        }
        if (!o1.getLecturer().equals(o2.getLecturer())) {
            differences.add(new Difference("Lecturer", o1.getLecturer(), o2.getLecturer()));
        }
        if (!o1.getAssistants().equals(o2.getAssistants())) {
            differences.add(new Difference("Assistants", o1.getAssistants(), o2.getAssistants()));
        }
        if (!o1.getCourseCategory().equals(o2.getCourseCategory())) {
            differences.add(new Difference("Course Category", o1.getCourseCategory(), o2.getCourseCategory()));
        }
        if (!o1.getWeeklySubject().equals(o2.getWeeklySubject())) {
            differences.add(new Difference("Weekly Subject", o1.getWeeklySubject(), o2.getWeeklySubject()));
        }
        if (!o1.getBook().equals(o2.getBook())) {
            differences.add(new Difference("Book", o1.getBook(), o2.getBook()));
        }
        if (!o1.getMaterials().equals(o2.getMaterials())) {
            differences.add(new Difference("Materials", o1.getMaterials(), o2.getMaterials()));
        }

        //Tables

        if (!o1.getAssessmentTable().equals(o2.getAssessmentTable())) {
            differences.add(new Difference("Assessment Table", o1.getAssessmentTable(), o2.getAssessmentTable()));
        }

        if (!o1.getWorkloadTable().equals(o2.getWorkloadTable())) {
            differences.add(new Difference("Workload Table", o1.getWorkloadTable(), o2.getWorkloadTable()));
        }

        if (!o1.getOutcomeTable().equals(o2.getOutcomeTable())) {
            differences.add(new Difference("Outcome Table", o1.getOutcomeTable(), o2.getOutcomeTable()));
        }



        return differences;
    }

    public static void main(String[] args) {
        // Paths to the JSON files
       launch(args);
    }





    Stage primaryStage;
    TextField courseCodeField1, courseCodeField2;
    TextField versionNumberField1, versionNumberField2;
    static TextArea outputArea;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("View Syllabus Version");

        VBox vBox = createVBox();
        vBox.setStyle("-fx-background-color: lavender;");

        Scene scene = new Scene(vBox, 500, 600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private VBox createVBox() {
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(20, 20, 20, 20));
        vBox.setSpacing(10);

        GridPane grid = createGridPane();
        Button backButton = new Button("Back");
        backButton.setAlignment(Pos.BOTTOM_RIGHT);
        backButton.setOnAction(e -> goBackToMainScene());
        vBox.getChildren().addAll(grid, backButton);

        return vBox;
    }

    private GridPane createGridPane() {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);

        // Add labels and text fields for the first syllabus
        Label courseCodeLabel1 = new Label("Course Code 1:");
        grid.add(courseCodeLabel1, 0, 0);
        courseCodeField1 = new TextField();
        grid.add(courseCodeField1, 1, 0);

        Label versionNumberLabel1 = new Label("Version Number 1:");
        grid.add(versionNumberLabel1, 0, 1);
        versionNumberField1 = new TextField();
        grid.add(versionNumberField1, 1, 1);

        // Add labels and text fields for the second syllabus
        Label courseCodeLabel2 = new Label("Course Code 2:");
        grid.add(courseCodeLabel2, 0, 2);
        courseCodeField2 = new TextField();
        grid.add(courseCodeField2, 1, 2);

        Label versionNumberLabel2 = new Label("Version Number 2:");
        grid.add(versionNumberLabel2, 0, 3);
        versionNumberField2 = new TextField();
        grid.add(versionNumberField2, 1, 3);

        Button viewButton = new Button("Compare Syllabus");
        grid.add(viewButton, 1, 4);
        viewButton.setOnAction(e -> onViewButtonClicked());

        outputArea = new TextArea();
        outputArea.setEditable(false);
        grid.add(outputArea, 0, 5, 2, 1);

        return grid;
    }
    private void onViewButtonClicked() {
        String courseCodeForSyllabus1 = courseCodeField1.getText();
        String versionNumberForSyllabus1Text = versionNumberField1.getText();
        String courseCodeForSyllabus2 = courseCodeField2.getText();
        String versionNumberForSyllabus2Text = versionNumberField2.getText();

        try {
            int versionNumber1 = Integer.parseInt(versionNumberForSyllabus1Text);
            int versionNumber2 = Integer.parseInt(versionNumberForSyllabus2Text);
            String pathToSyllabus1 = buildSyllabusFilePath(courseCodeForSyllabus1, versionNumber1);
            String pathToSyllabus2 = buildSyllabusFilePath(courseCodeForSyllabus2, versionNumber2);

            File file1 = new File(pathToSyllabus1);
            File file2 = new File(pathToSyllabus2);

            // Verify both files exist before continuing
            if (!file1.exists() || !file2.exists()) {
                String notFoundFiles = (file1.exists() ? "" : file1.getAbsolutePath()) +
                        (file2.exists() ? "" : (file1.exists() ? "\n" : "") + file2.getAbsolutePath());
                showErrorAlert("File not found", "The following file(s) do not exist: " + notFoundFiles);
                return;
            }

            Gson gson = new Gson();
            SyllabusVersioning syllabus1, syllabus2;

            // Read the first file
            try (BufferedReader reader1 = new BufferedReader(new FileReader(file1))) {
                syllabus1 = gson.fromJson(reader1, SyllabusVersioning.class);
            }

            // Read the second file
            try (BufferedReader reader2 = new BufferedReader(new FileReader(file2))) {
                syllabus2 = gson.fromJson(reader2, SyllabusVersioning.class);
            }

            // Check if the syllabi are null
            if (syllabus1 == null || syllabus1.getLecture() == null || syllabus2 == null || syllabus2.getLecture() == null) {
                showErrorAlert("Invalid Syllabus", "Invalid or null syllabus data.");
                return;
            }

            // Compare the syllabi
            List<Difference> differences = findDifferences(syllabus1.getLecture(), syllabus2.getLecture());

            // Highlight differences
            highlightDifferences(differences);

        } catch (NumberFormatException e) {
            showErrorAlert("Invalid Number", "Invalid version number. Please enter a valid number.");
        } catch (IOException e) {
            showErrorAlert("IO Error", "Error reading file: " + e.getMessage());
        }
    }

    private String buildSyllabusFilePath(String courseCode, int versionNumber) {
        return FileSystemView.getFileSystemView().getDefaultDirectory().getPath() +
                File.separator + "Syllabus" + File.separator + courseCode + File.separator + courseCode + versionNumber + ".json";
    }


    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private static void highlightDifferences(List<Difference> differences) {
        StringBuilder sb = new StringBuilder();
        for (Difference diff : differences) {
            sb.append(diff.getField())
                    .append(": ")
                    .append(diff.getOldValue())
                    .append(" / ")
                    .append(diff.getNewValue())
                    .append("\n");
        }
        outputArea.setText(sb.toString()); // Assuming 'outputArea' is accessible
    }
    private void goBackToMainScene() {
        FirstScene firstScene = new FirstScene();
        firstScene.start(primaryStage);
    }
}
/*public class SyllabusComparatorForTurkishSyllabus implements java.util.Comparator<TurkishSyllabus> {
    @Override
    public int compare(TurkishSyllabus o1, TurkishSyllabus o2) {

        return 0;
    }

}

 */

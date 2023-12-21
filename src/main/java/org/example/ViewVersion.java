package org.example;


import com.google.gson.Gson;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.filechooser.FileSystemView;
import java.io.*;

public class ViewVersion extends Application {
    private Stage primaryStage;
    private TextField courseCodeField;
    private TextField versionNumberField;
    private TextArea outputArea;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("View Syllabus Version");


        VBox vBox = createVBox();
        vBox.setStyle("-fx-background-color: lavender;");

        Scene scene = new Scene(vBox, 400, 300);
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

        Label courseCodeLabel = new Label("Course Code:");
        GridPane.setConstraints(courseCodeLabel, 0, 0);
        courseCodeField = new TextField();
        GridPane.setConstraints(courseCodeField, 1, 0);

        Label versionNumberLabel = new Label("Version Number:");
        GridPane.setConstraints(versionNumberLabel, 0, 1);
        versionNumberField = new TextField();
        GridPane.setConstraints(versionNumberField, 1, 1);

        Button viewButton = new Button("View Syllabus");
        GridPane.setConstraints(viewButton, 1, 2);
        viewButton.setOnAction(e -> onViewButtonClicked());

        outputArea = new TextArea();
        outputArea.setEditable(false);
        GridPane.setConstraints(outputArea, 0, 3, 2, 1);

        grid.getChildren().addAll(courseCodeLabel, courseCodeField, versionNumberLabel, versionNumberField, viewButton, outputArea);

        return grid;
    }

    private void onViewButtonClicked() {
        String courseCode = courseCodeField.getText();
        String versionNumberText = versionNumberField.getText();

        try {
            int versionNumber = Integer.parseInt(versionNumberText);

            String path = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() +
                    File.separator + "Syllabus" + File.separator + courseCode + "/" + courseCode + versionNumber + ".json";
            File file = new File(path);

            if (!file.exists()) {
                showErrorAlert("File not found", "File does not exist: " + file.getAbsolutePath());
                return;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                Gson gson = new Gson();
                SyllabusVersioning syllabus = gson.fromJson(reader, SyllabusVersioning.class);
                if (syllabus != null) {
                    printSyllabus(syllabus);
                } else {
                    showErrorAlert("Parse Error", "Failed to parse syllabus.");
                }
            } catch (IOException e) {
                showErrorAlert("IO Error", "Error reading file: " + e.getMessage());
            }
        } catch (NumberFormatException e) {
            showErrorAlert("Invalid Number", "Invalid version number. Please enter a valid number.");
        }
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void printSyllabus(SyllabusVersioning syllabus) {
        Platform.runLater(() -> {

            outputArea.setText("Details of Version/ Versiyon Detayları:\n" +
                    "Lecturer's name/ Öğretmen Adı: " + syllabus.getName() + "\n" +
                    "Reason/ Sebep: " + syllabus.getReason() + "\n" +
                    "Time/ Zaman: " + syllabus.getTime() + "\n\n" +
                    "Details of the Lecture/ Dersin Detayları::\n" +
                    "Type/ Tür: " + syllabus.getLecture().getType() + "\n" +
                    "Course Level/ Ders Seviyesi: " + syllabus.getLecture().getCourseLevel() + "\n" +
                    "Mode/ Mod: " + syllabus.getLecture().getDeliveryMode() + "\n" +
                    "Course Category/ Ders Kategorisi: " + syllabus.getLecture().getCourseCategory() + "\n" +
                    "Language/ Dil: " + syllabus.getLecture().getLanguage() + "\n" +
                    "Course Name/ Ders Adı: " + syllabus.getLecture().getCourseName() + "\n" +
                    "Code/ Ders Kodu: " + syllabus.getLecture().getCourseCode() + "\n" +
                    "Term/ Dönem: " + syllabus.getLecture().getTerm() + "\n" +
                    "Theory/ Teori: " + syllabus.getLecture().getTheoryHours() + "\n" +
                    "Application/ Uygulama: " + syllabus.getLecture().getApplicationHours() + "\n" +
                    "Local Credits/ Yerel Krediler: " + syllabus.getLecture().getLocalCredit() + "\n" +
                    "ECTS/ AKTS: " + syllabus.getLecture().getEcts() + "\n" +
                    "Prerequisites/ Önkoşullar: " + syllabus.getLecture().getPrerequisites() + "\n" +
                    "Teaching Method/ Öğretme Yöntemi: " + syllabus.getLecture().getTeachingMethods() + "\n" +
                    "Course Coordinator/ Ders Koordinatörü: " + syllabus.getLecture().getCoordinator() + "\n" +
                    "Course Lecturer/ Ders Öğretmeni: " + syllabus.getLecture().getLecturer() + "\n" +
                    "Assistant/ Asistan: " + syllabus.getLecture().getAssistants() + "\n" +
                    "Weekly Subject/ Haftalık Plan: " + syllabus.getLecture().getWeeklySubject() + "\n" +
                    "Book/ Kitap: " + syllabus.getLecture().getBook() + "\n" +
                    "Materials/ Materyaller: " + syllabus.getLecture().getMaterials() + "\n" +
                    "Assessments/ Değerlendirmeler: " + syllabus.getLecture().getAssessmentTable() + "\n" +
                    "Workload/ İş yükü: " + syllabus.getLecture().getWorkloadTable() + "\n" +
                    "Outcomes/ Kazanımlar: " + syllabus.getLecture().getOutcomeTable());
        });
    }

    private void showError(String errorMessage) {
        Platform.runLater(() -> {
            outputArea.setText(errorMessage);
        });
    }


    private void updateOutput(String message) {
        Platform.runLater(() -> {
            outputArea.setText(message);
        });
    }
    private void goBackToMainScene() {

        FirstScene firstScene = new FirstScene();
        firstScene.start(primaryStage);
    }
}
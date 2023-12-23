
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
import org.example.FirstScene;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.List;

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

                // Check if syllabus or lecture is null
                if (syllabus != null && syllabus.getLecture() != null) {
                    printSyllabus(syllabus);
                    if (syllabus != null) {
                        // printSyllabus(syllabus);

                    } else {
                        showErrorAlert("Invalid Syllabus", "Invalid or null syllabus data.");
                    }
                }
            }catch(IOException e){
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
        try {
            if (syllabus != null) {
                Platform.runLater(() -> {
                    try {
                        Lecture lecture = syllabus.getLecture();
                        String weeklySubjectDetails = "";
                        String assessmentTableDetails = "";
                        String workloadTableDetails="";
                        String outcomeTableDetails="";
                        for (int j = 0; j < lecture.weeklySubject.size(); j++) {
                            weeklySubjectDetails += "Week " + lecture.weeklySubject.get(j).getWeek() + "\n";
                            weeklySubjectDetails += "Preparation: " + lecture.weeklySubject.get(j).getPreparation() + "\n";
                            weeklySubjectDetails += "Topics: " + lecture.weeklySubject.get(j).getTopics() + "\n";
                        }

                        for (int j = 0; j < lecture.assessmentTable.size(); j++) {
                            System.out.println(j);
                            assessmentTableDetails += lecture.assessmentTable.get(j).getName()+"\n";;
                            assessmentTableDetails+="Number: " + lecture.assessmentTable.get(j).getCount() + "\n";
                            assessmentTableDetails += "Weighting: " + lecture.assessmentTable.get(j).getPercentage() + "\n";
                            assessmentTableDetails +="LO1: " + lecture.assessmentTable.get(j).getLo1() + "\n";
                            assessmentTableDetails += "LO2: " + lecture.assessmentTable.get(j).getLo2() + "\n";
                            assessmentTableDetails += "LO3: " + lecture.assessmentTable.get(j).getLo3() + "\n";
                            assessmentTableDetails += "LO4: " + lecture.assessmentTable.get(j).getLo4() + "\n";
                        }
                        for (int j = 0; j < lecture.workloadTable.size(); j++) {
                            workloadTableDetails += lecture.workloadTable.get(j).getActivity()+"\n";
                            workloadTableDetails+="Number:  " + lecture.workloadTable.get(j).getCount()+"\n" ;
                            workloadTableDetails+= "Duration(Hours): " + lecture.workloadTable.get(j).getHour()+ "\n";
                            workloadTableDetails += "Workload: " + lecture.workloadTable.get(j).getWorkloud() + "\n";

                        }
                        for (int j = 0; j < lecture.outcomeTable.size(); j++) {
                            outcomeTableDetails+=j;
                           outcomeTableDetails += "Program Competencies/Outcomes: "+ lecture.outcomeTable.get(j).getDescription() + "\n";
                            outcomeTableDetails +="1: " +lecture.outcomeTable.get(j).getLevel1()+ "\n";
                            outcomeTableDetails += "2: "+lecture.outcomeTable.get(j).getLevel2()+ "\n";
                            outcomeTableDetails += "3: "+lecture.outcomeTable.get(j).getLevel3()+ "\n";
                            outcomeTableDetails += "4: "+lecture.outcomeTable.get(j).getLevel4()+ "\n";
                            outcomeTableDetails +="5: " +lecture.outcomeTable.get(j).getLevel5()+ "\n";

                        }
                        List<String> courseCategories = lecture.getCourseCategory();
                        outputArea.setText("Details of Version/ Versiyon Detayları:\n" +
                                "Lecturer's name/ Öğretmen Adı: " + syllabus.getName() + "\n" +
                                "Reason/ Sebep: " + syllabus.getReason() + "\n" +
                                "Time/ Zaman: " + syllabus.getTime() + "\n\n" +
                                "Details of the Lecture/ Dersin Detayları:\n" +
                                "Type/ Tür: " + lecture.getType() + "\n" +
                                "Course Level/ Ders Seviyesi: " + lecture.getCourseLevel() + "\n" +
                                "Mode/ Mod: " + lecture.getDeliveryMode() + "\n" +
                                "Course Category/ Ders Kategorisi: " + printCourseCategories(courseCategories) + "\n" +
                                "Language/ Dil: " + lecture.getLanguage() + "\n" +
                                "Course Name/ Ders Adı: " + lecture.getCourseName() + "\n" +
                                "Code/ Ders Kodu: " + lecture.getCourseCode() + "\n" +
                                "Term/ Dönem: " + lecture.getTerm() + "\n" +
                                "Theory/ Teori: " + lecture.getTheoryHours() + "\n" +
                                "Application/ Uygulama: " + lecture.getApplicationHours() + "\n" +
                                "Local Credits/ Yerel Krediler: " + lecture.getLocalCredit() + "\n" +
                                "ECTS/ AKTS: " + lecture.getEcts() + "\n" +
                                "Prerequisites/ Önkoşullar: " + lecture.getPrerequisites() + "\n" +
                                "Teaching Method/ Öğretme Yöntemi: " + lecture.getTeachingMethods() + "\n" +
                                "Course Coordinator/ Ders Koordinatörü: " + lecture.getCoordinator() + "\n" +
                                "Course Lecturer/ Ders Öğretmeni: " + lecture.getLecturer() + "\n" +
                                "Assistant/ Asistan: " + lecture.getAssistants() + "\n" +
                                "Weekly Subject/ Haftalık Plan:\n" + weeklySubjectDetails +
                                "Book/ Kitap: " + lecture.getBook() + "\n" +
                                "Materials/ Materyaller: " + lecture.getMaterials() + "\n" +
                                "Assessments/ Değerlendirmeler: " + assessmentTableDetails + "\n" +
                                "Workload/ İş yükü: " + workloadTableDetails + "\n" +
                                "Outcomes/ Kazanımlar: " + outcomeTableDetails);

                    } catch (Exception e) {
                        showErrorAlert("Error", "An error occurred while displaying syllabus: " + e.getMessage());
                    }
                });
            } else {
                showError("Syllabus is null");
            }
        } catch (Exception e) {
            showErrorAlert("Error", "An error occurred in printSyllabus: " + e.getMessage());
        }
    }
    private String printCourseCategories(List<String> courseCategories) {
        StringBuilder categoriesString = new StringBuilder();

        if (courseCategories != null && !courseCategories.isEmpty()) {
            for (String category : courseCategories) {
                categoriesString.append("- ").append(category).append("\n");
            }
        } else {
            categoriesString.append("No Course Category selected.\n");
        }

        return categoriesString.toString();
    }
    private void showError(String errorMessage) {
        Platform.runLater(() -> {
            outputArea.setText(errorMessage);
        });
    }

    private void goBackToMainScene() {
        FirstScene firstScene = new FirstScene();
        firstScene.start(primaryStage);
    }
}
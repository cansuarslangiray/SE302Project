package org.example;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EnglishSyllabus extends Application {
    Lecture lecture;
    boolean isSaved;
    private Stage primaryStage;

    public EnglishSyllabus(Lecture lecture, boolean isSaved) {
        this.lecture = lecture;
        this.isSaved = isSaved;
    }

    String changedBy;
    String changeReason;
    String changeDate;
    private List<Activity> activities = new ArrayList<>();
    private List<workLoad> workLoads = new ArrayList<>();
    private List<Competency> competencies = new ArrayList<>();
    private List<Lesson> lessons = new ArrayList<>();
    List<String> selectedCategories = new ArrayList<>();

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        stage.setTitle("Course Form");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        GridPane gridPane1 = new GridPane();
        gridPane1.setPadding(new Insets(20, 20, 20, 20));
        gridPane1.setVgap(10);
        gridPane1.setHgap(10);

        Label titleLabel = new Label("           IZMIR UNIVERSITY OF ECONOMICS\n" +
                "                COURSE OUTLINE FORM");
        titleLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20;");
        titleLabel.setAlignment(Pos.CENTER);

        GridPane.setConstraints(titleLabel, 0, 0, 2, 1);
        gridPane.getChildren().add(titleLabel);
        GridPane.setHalignment(titleLabel, HPos.CENTER);

        Label orangeLabel = new Label("1. GENERAL INFORMATION");
        orangeLabel.setStyle("-fx-background-color: orange; -fx-padding: 5px;-fx-font-weight: bold;");
        GridPane.setConstraints(orangeLabel, 0, 1, 2, 1);
        gridPane.getChildren().add(orangeLabel);

        gridPane.add(new Label(""), 0, 1);

        gridPane.add(new Label("Course Name:"), 0, 2);
        TextField courseNameTextField = new TextField();
        gridPane.add(courseNameTextField, 1, 2);

        gridPane.add(new Label("Course Code:"), 0, 3);
        TextField courseCodeTextField = new TextField();
        gridPane.add(courseCodeTextField, 1, 3);

        gridPane.add(new Label("Term:"), 0, 4);
        ChoiceBox<String> termChoiceBox = new ChoiceBox<>();
        termChoiceBox.getItems().addAll("Fall", "Spring");
        gridPane.add(termChoiceBox, 1, 4);

        gridPane.add(new Label("Theory (hours/week):"), 0, 5);
        TextField theoryHoursTextField = new TextField();
        gridPane.add(theoryHoursTextField, 1, 5);

        gridPane.add(new Label("Application/Lab (hours/week):"), 0, 6);
        TextField applicationHoursTextField = new TextField();
        gridPane.add(applicationHoursTextField, 1, 6);

        gridPane.add(new Label("Local\n" +
                "Credits"), 0, 7);
        TextField localCreditTextField = new TextField();
        gridPane.add(localCreditTextField, 1, 7);

        gridPane.add(new Label("ECTS:"), 0, 8);
        TextField ectsTextField = new TextField();
        gridPane.add(ectsTextField, 1, 8);

        gridPane.add(new Label("Prerequisites:"), 0, 9);
        TextField preTextField = new TextField();
        gridPane.add(preTextField, 1, 9);

        gridPane.add(new Label("Course Language:"), 0, 10);
        ToggleGroup languageToggleGroup = new ToggleGroup();
        RadioButton languageTurkishRadioButton = new RadioButton("Turkish");
        languageTurkishRadioButton.setToggleGroup(languageToggleGroup);
        RadioButton languageEnglishRadioButton = new RadioButton("English");
        languageEnglishRadioButton.setToggleGroup(languageToggleGroup);
        RadioButton languageOtherRadioButton = new RadioButton("Second Foreign Language");
        languageOtherRadioButton.setToggleGroup(languageToggleGroup);
        HBox languageBox = new HBox(10, languageTurkishRadioButton, languageEnglishRadioButton, languageOtherRadioButton);
        gridPane.add(languageBox, 1, 10);

        gridPane.add(new Label("Course Type"), 0, 11);
        ToggleGroup typeToggleGroup = new ToggleGroup();
        RadioButton typeMandatoryRadioButton = new RadioButton("Required");
        typeMandatoryRadioButton.setToggleGroup(typeToggleGroup);
        RadioButton typeElectiveRadioButton = new RadioButton("Elective");
        typeElectiveRadioButton.setToggleGroup(typeToggleGroup);
        HBox typeBox = new HBox(10, typeMandatoryRadioButton, typeElectiveRadioButton);
        gridPane.add(typeBox, 1, 11);

        gridPane.add(new Label("Course Level:"), 0, 12);
        ToggleGroup levelToggleGroup = new ToggleGroup();
        RadioButton levelUndergraduateRadioButton = new RadioButton("Associate Degree");
        levelUndergraduateRadioButton.setToggleGroup(levelToggleGroup);
        RadioButton levelGraduateRadioButton = new RadioButton("Undergraduate");
        levelGraduateRadioButton.setToggleGroup(levelToggleGroup);
        HBox levelBox = new HBox(10, levelUndergraduateRadioButton, levelGraduateRadioButton);
        gridPane.add(levelBox, 1, 12);

        gridPane.add(new Label("Mode of Delivery "), 0, 13);
        ToggleGroup deliveryToggleGroup = new ToggleGroup();
        RadioButton deliveryFaceToFaceRadioButton = new RadioButton(" Face-to-Face");
        deliveryFaceToFaceRadioButton.setToggleGroup(deliveryToggleGroup);
        RadioButton deliveryOnlineRadioButton = new RadioButton("Online");
        deliveryOnlineRadioButton.setToggleGroup(deliveryToggleGroup);
        HBox deliveryBox = new HBox(10, deliveryFaceToFaceRadioButton, deliveryOnlineRadioButton);
        gridPane.add(deliveryBox, 1, 13);

        gridPane.add(new Label("Teaching Methods and Techniques"), 0, 14);
        TextArea teachingMethodsTextArea = new TextArea();
        gridPane.add(teachingMethodsTextArea, 1, 14);

        gridPane.add(new Label("Course Coordinator"), 0, 15);
        TextField coordinatorTextField = new TextField();
        gridPane.add(coordinatorTextField, 1, 15);

        gridPane.add(new Label("Course Lecturer(s)\n"), 0, 16);
        TextField instructionalStaffTextField = new TextField();
        gridPane.add(instructionalStaffTextField, 1, 16);

        gridPane.add(new Label("Assistant(s)"), 0, 17);
        TextField assistantsTextField = new TextField();
        gridPane.add(assistantsTextField, 1, 17);

        gridPane.add(new Label("Course Category"), 0, 18);

        CheckBox coreCourseCheckBox = new CheckBox("Core Course");
        CheckBox majorAreaCourseCheckBox = new CheckBox("Major Area Course");
        CheckBox supportiveCourseCheckBox = new CheckBox("Supportive Course");
        CheckBox communicationCourseCheckBox = new CheckBox("Communication and Management Skills Course");
        CheckBox transferableSkillCheckBox = new CheckBox("Transferable Skill Course");

        VBox categoryBox = new VBox(10, coreCourseCheckBox, majorAreaCourseCheckBox, supportiveCourseCheckBox, communicationCourseCheckBox, transferableSkillCheckBox);
        gridPane.add(categoryBox, 1, 18);

        coreCourseCheckBox.setOnAction(event -> handleCheckBoxSelection(coreCourseCheckBox, selectedCategories));
        majorAreaCourseCheckBox.setOnAction(event -> handleCheckBoxSelection(majorAreaCourseCheckBox, selectedCategories));
        supportiveCourseCheckBox.setOnAction(event -> handleCheckBoxSelection(supportiveCourseCheckBox, selectedCategories));
        communicationCourseCheckBox.setOnAction(event -> handleCheckBoxSelection(communicationCourseCheckBox, selectedCategories));
        transferableSkillCheckBox.setOnAction(event -> handleCheckBoxSelection(transferableSkillCheckBox, selectedCategories));


        Label orangeLabel1 = new Label("2. WEEKLY SUBJECTS AND REQUIRED MATERIALS");
        orangeLabel1.setStyle("-fx-background-color: orange; -fx-padding: 5px;-fx-font-weight: bold;");
        GridPane.setConstraints(orangeLabel1, 0, 19, 2, 1);
        gridPane.getChildren().add(orangeLabel1);

        Label spaceLabel1 = new Label("        ");
        Label orangeLabel3 = new Label("4. ECTS / WORKLOAD TABLE");
        orangeLabel3.setStyle("-fx-background-color: orange; -fx-padding: 5px;-fx-font-weight: bold;");
        Label spaceLabel = new Label("        ");


        VBox vBox1 = new VBox(spaceLabel1, orangeLabel3, spaceLabel);


        Label spaceLabe = new Label("        ");
        Label orangeLabel4 = new Label("5. COURSE/PROGRAM OUTCOME MATRIX");
        orangeLabel4.setStyle("-fx-background-color: orange; -fx-padding: 5px;-fx-font-weight: bold;");
        Label spaceLabe1 = new Label("        ");

        VBox vBox2 = new VBox(spaceLabe1, orangeLabel4, spaceLabe);

        gridPane1.add(new Label("Course Notes/Textbooks"), 0, 1);
        TextArea Book = new TextArea();
        gridPane1.add(Book, 1, 1);

        gridPane1.add(new Label("Suggested Readings/Materials"), 0, 2);
        TextArea Material = new TextArea();
        gridPane1.add(Material, 1, 2);

        Label orangeLabel2 = new Label("3. ASSESSMENT\n");
        orangeLabel2.setStyle("-fx-background-color: orange; -fx-padding: 5px;-fx-font-weight: bold;");
        gridPane1.setConstraints(orangeLabel2, 0, 3, 2, 2);
        gridPane1.getChildren().add(orangeLabel2);
        Button backButton = new Button("Back to Main Scene");

        backButton.setOnAction(e -> goBackToMainScene());
        GridPane backButongrid = new GridPane();
        backButongrid.setHgap(10);
        backButongrid.setVgap(10);
        backButongrid.add(backButton, 10, 19, 2, 1);
        gridPane.add(backButongrid, 10, 18, 2, 1);

        Button submitButton = new Button("Submit");
        gridPane.add(submitButton, 0, 18, 2, 1);

        GridPane gridPane2 = new GridPane();
        gridPane2.setHgap(0);
        gridPane2.setVgap(0);
        gridPane2.setPadding(new Insets(10, 10, 10, 10));
        List<TextField> lessonsTextField = new ArrayList<>();
        for (int row = 0; row < 17; row++) {
            TextField week = null;
            TextField preparation = null;
            TextField topics = null;
            for (int col = 0; col < 3; col++) {
                if ((col == 0) && (row == 0)) {
                    week = new TextField();
                    week.setEditable(false);
                    week.setText("Week");
                    week.setStyle("-fx-font-family: Arial;");
                    gridPane2.add(week, col, row);
                } else if (col == 1 && row == 0) {
                    preparation = new TextField();
                    preparation.setEditable(false);
                    preparation.setText("Required Materials");
                    preparation.setStyle("-fx-font-family: Arial;");
                    gridPane2.add(preparation, col, row);
                } else if (col == 2 && row == 0) {
                    topics = new TextField();
                    topics.setEditable(false);
                    topics.setText("Subjects");
                    topics.setStyle("-fx-font-family: Arial;");
                    gridPane2.add(topics, col, row);
                } else {
                    if (col == 0) {
                        week = new TextField();
                        week.setStyle("-fx-border-width: 2px; -fx-border-color: grey;");
                        week.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                        lessonsTextField.add(week);
                        gridPane2.add(week, col, row);
                    } else if (col == 1) {
                        preparation = new TextField();
                        preparation.setStyle("-fx-border-width: 2px; -fx-border-color: grey;");
                        preparation.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                        lessonsTextField.add(preparation);
                        gridPane2.add(preparation, col, row);
                    } else {
                        topics = new TextField();
                        topics.setStyle("-fx-border-width: 2px; -fx-border-color: grey;");
                        topics.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                        lessonsTextField.add(topics);
                        gridPane2.add(topics, col, row);
                    }
                }
                if (col == 0 && row > 0) {
                    week.setText(String.valueOf(row));
                }
            }
        }

        GridPane gridPane3 = new GridPane();
        gridPane3.setHgap(0);
        gridPane3.setVgap(0);
        gridPane3.setPadding(new Insets(10, 10, 10, 10));

        int rowCount = 14;
        int colCount = 7;
        int narrowWidth = 5;

        List<TextField> activitiesTextField = new ArrayList<>();

        for (int row = 0; row < rowCount; row++) {

            for (int col = 0; col < colCount; col++) {
                TextField textField = new TextField();
                textField.setEditable(false);
                TextField textField1 = new TextField();
                textField1.setEditable(true);


                // Özel durumlar için kontrol ekle
                if (col == 0 && row == 0) {
                    textField.setText("Semester Activities");
                } else if (col == 1 && row == 0) {
                    textField.setText("Number");
                } else if (col == 2 && row == 0) {
                    textField.setText("Weighting");
                } else if (col == 3 && row == 0) {
                    textField.setText("LO1");
                } else if (col == 4 && row == 0) {
                    textField.setText("LO2");
                } else if (col == 5 && row == 0) {
                    textField.setText("LO3");
                } else if (col == 6 && row == 0) {
                    textField.setText("LO4");
                } else if (col == 0 && row == 1) {
                    textField.setText("Participation\n");
                    activitiesTextField.add(textField);
                } else if (col == 0 && row == 2) {
                    textField.setText("Laboratory/Application");
                    activitiesTextField.add(textField);
                } else if (col == 0 && row == 3) {
                    textField.setText("Field Work");
                    activitiesTextField.add(textField);
                } else if (col == 0 && row == 4) {
                    textField.setText("Quiz/Studio Critique\n");
                    activitiesTextField.add(textField);
                } else if (col == 0 && row == 5) {
                    textField.setText("Homework/Assignment");
                    activitiesTextField.add(textField);
                } else if (col == 0 && row == 6) {
                    textField.setText("Presentation/Jury");
                    activitiesTextField.add(textField);
                } else if (col == 0 && row == 7) {
                    textField.setText("Project");
                    activitiesTextField.add(textField);
                } else if (col == 0 && row == 8) {
                    textField.setText("Portfolio");
                    activitiesTextField.add(textField);
                } else if (col == 0 && row == 9) {
                    textField.setText("Seminar/Workshop");
                    activitiesTextField.add(textField);
                } else if (col == 0 && row == 10) {
                    textField.setText("Oral Exam");
                    activitiesTextField.add(textField);
                } else if (col == 0 && row == 11) {
                    textField.setText("Midterm");
                    activitiesTextField.add(textField);
                } else if (col == 0 && row == 12) {
                    textField.setText("Final Exam");
                    activitiesTextField.add(textField);
                } else if (col == 0 && row == 13) {
                    textField.setText("Total");
                    activitiesTextField.add(textField);
                } else {
                    gridPane3.add(textField1, col, row);
                    activitiesTextField.add(textField1);
                }
                textField.setStyle("-fx-border-width: 1px; -fx-border-color: grey;");
                textField1.setStyle("-fx-border-width: 1px; -fx-border-color: grey;");
                if (col >= 3 && col <= 8) {
                    textField.setPrefColumnCount(narrowWidth);

                }
                if (col >= 0 && col <= 8 && row == 0) {
                    textField.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                } else {
                    textField.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                }


                textField.setStyle("-fx-border-width: 2px; -fx-border-color: grey;");
                textField1.setStyle("-fx-border-width: 2px; -fx-border-color: grey;");

                if (row == 0 || col == 0) {
                    gridPane3.add(textField, col, row);
                }


            }
        }

        int rowCount1 = 14;
        int colCount1 = 4;
        int narrowWidth1 = 5;

        GridPane gridPane4 = new GridPane();
        gridPane4.setHgap(0);
        gridPane4.setVgap(0);
        gridPane4.setPadding(new Insets(10, 10, 10, 10));

        List<TextField> workloadTextFields = new ArrayList<>();
        for (int row = 0; row < rowCount1; row++) {
            for (int col = 0; col < colCount1; col++) {
                TextField textField2 = new TextField();
                textField2.setEditable(false);
                TextField textField3 = new TextField();
                textField3.setEditable(true);


                if (col == 0 && row == 0) {
                    textField2.setText("Semester Activities");
                    gridPane4.add(textField2, col, row);
                } else if (col == 1 && row == 0) {
                    textField2.setText("Number");
                    gridPane4.add(textField2, col, row);
                } else if (col == 2 && row == 0) {
                    textField2.setText("Duration(Hours)");
                    gridPane4.add(textField2, col, row);
                } else if (col == 3 && row == 0) {
                    textField2.setText("Workload");
                    gridPane4.add(textField2, col, row);
                } else if (col == 0 && row == 1) {
                    textField2.setText("Participation\n");
                    workloadTextFields.add(textField2);
                    gridPane4.add(textField2, col, row);

                } else if (col == 0 && row == 2) {
                    textField2.setText("Laboratory/Application");
                    workloadTextFields.add(textField2);
                    gridPane4.add(textField2, col, row);
                } else if (col == 0 && row == 3) {
                    textField2.setText("Field Work");
                    workloadTextFields.add(textField2);
                    gridPane4.add(textField2, col, row);
                } else if (col == 0 && row == 4) {
                    textField2.setText("Quiz/Studio Critique\n");
                    workloadTextFields.add(textField2);
                    gridPane4.add(textField2, col, row);
                } else if (col == 0 && row == 5) {
                    textField2.setText("Homework/Assignment");
                    workloadTextFields.add(textField2);
                    gridPane4.add(textField2, col, row);
                } else if (col == 0 && row == 6) {
                    textField2.setText("Presentation/Jury");
                    workloadTextFields.add(textField2);
                    gridPane4.add(textField2, col, row);
                } else if (col == 0 && row == 7) {
                    textField2.setText("Project");
                    workloadTextFields.add(textField2);
                    gridPane4.add(textField2, col, row);
                } else if (col == 0 && row == 8) {
                    textField2.setText("Portfolio");
                    workloadTextFields.add(textField2);
                    gridPane4.add(textField2, col, row);
                } else if (col == 0 && row == 9) {
                    textField2.setText("Seminar/Workshop");
                    workloadTextFields.add(textField2);
                    gridPane4.add(textField2, col, row);
                } else if (col == 0 && row == 10) {
                    textField2.setText("Oral Exam");
                    workloadTextFields.add(textField2);
                    gridPane4.add(textField2, col, row);
                } else if (col == 0 && row == 11) {
                    textField2.setText("Midterm");
                    workloadTextFields.add(textField2);
                    gridPane4.add(textField2, col, row);
                } else if (col == 0 && row == 12) {
                    textField2.setText("Final Exam");
                    workloadTextFields.add(textField2);
                    gridPane4.add(textField2, col, row);
                } else if (col == 0 && row == 13) {
                    textField2.setText("Total");
                    workloadTextFields.add(textField2);
                    gridPane4.add(textField2, col, row);

                } else {
                    workloadTextFields.add(textField3);
                    gridPane4.add(textField3, col, row);
                }
                textField2.setStyle("-fx-border-width: 1px; -fx-border-color: grey;");
                if (col >= 3 && col <= 5) {
                    textField2.setPrefColumnCount(narrowWidth1);

                }
                if (col >= 0 && col <= 8 && row == 0) {
                    textField2.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                    ;
                } else {
                    textField2.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                }

                textField2.setStyle("-fx-border-width: 2px; -fx-border-color: grey;");
                textField3.setStyle("-fx-border-width: 2px; -fx-border-color: grey;");

            }
        }
        int rowCount2 = 14;
        int colCount2 = 7;
        int narrowWidth2 = 5;

        GridPane gridPane5 = new GridPane();
        gridPane5.setHgap(0);
        gridPane5.setVgap(0);
        gridPane5.setPadding(new Insets(10, 10, 10, 10));

        List<TextField> outcomeTextField = new ArrayList<>();
        for (int row = 0; row < rowCount2; row++) {
            for (int col = 0; col < colCount2; col++) {
                TextField textField2 = new TextField();
                textField2.setEditable(false);
                TextField textField3 = new TextField();
                textField3.setEditable(true);
                if (col == 0 && row == 0) {
                    textField2.setText("#");
                    gridPane5.add(textField2, col, row);
                } else if (col == 1 && row == 0) {
                    textField2.setText("Program Yeterlilikleri/Çıktıları");
                    gridPane5.add(textField2, col, row);
                } else if (col == 2 && row == 0) {
                    textField2.setText("1");
                    gridPane5.add(textField2, col, row);
                } else if (col == 3 && row == 0) {
                    textField2.setText("2");
                    gridPane5.add(textField2, col, row);
                } else if (col == 4 && row == 0) {
                    textField2.setText("3");
                    gridPane5.add(textField2, col, row);
                } else if (col == 5 && row == 0) {
                    textField2.setText("4");
                    gridPane5.add(textField2, col, row);
                } else if (col == 6 && row == 0) {
                    textField2.setText("5");
                    gridPane5.add(textField2, col, row);
                } else if (col == 0 && row > 0) {
                    textField2.setText(String.valueOf(row));
                    gridPane5.add(textField2, col, row);
                } else {
                    gridPane5.add(textField3, col, row);
                    outcomeTextField.add(textField3);

                }

                textField2.setStyle("-fx-border-width: 1px; -fx-border-color: grey;");
                textField3.setStyle("-fx-border-width: 1px; -fx-border-color: grey;");
                if (col >= 2 && col <= 8) {
                    textField2.setPrefColumnCount(narrowWidth2);

                }
                if (col == 0 && row > 0) {
                    textField2.setText(String.valueOf(row));
                }
                if (col == 0) {
                    textField2.setPrefColumnCount(narrowWidth2);

                }
                if (col == 1) {
                    textField2.setPrefWidth(400);
                }
                if (col >= 0 && col <= 8 && row == 0) {
                    textField2.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                    ;
                } else {
                    textField2.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                }

                textField2.setStyle("-fx-border-width: 2px; -fx-border-color: grey;");
                textField3.setStyle("-fx-border-width: 2px; -fx-border-color: grey;");


            }
        }
        VBox vBox = new VBox(gridPane, gridPane2, gridPane1, gridPane3, vBox1, gridPane4, vBox2, gridPane5, submitButton);

        int numberOfLines = 3;

        for (int i = 0; i < numberOfLines; i++) {

            Separator separator = new Separator();
            separator.setHalignment(HPos.CENTER);

            gridPane.add(separator, 0, 10 + i, 2, 2);
        }

        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        if (isSaved) {
            courseNameTextField.setText(lecture.courseName);
            courseCodeTextField.setText(lecture.courseCode);
            if (lecture.term.equals("Fall")) {
                termChoiceBox.setValue("Fall");
            } else {
                termChoiceBox.setValue("Spring");
            }
            theoryHoursTextField.setText(lecture.theoryHours);
            applicationHoursTextField.setText(lecture.applicationHours);
            localCreditTextField.setText(lecture.localCredit);
            ectsTextField.setText(lecture.ects);
            preTextField.setText(lecture.prerequisites);
            String lectureLanguage = lecture.language;
            switch (lectureLanguage) {
                case "Turkish":
                    languageToggleGroup.selectToggle(languageTurkishRadioButton);
                    break;
                case "English":
                    languageToggleGroup.selectToggle(languageEnglishRadioButton);
                    break;
                case "Second Foreign Language\n":
                    languageToggleGroup.selectToggle(languageOtherRadioButton);
                    break;
            }
            if (lecture.type.equals("Required")) {
                typeToggleGroup.selectToggle(typeMandatoryRadioButton);
            } else {
                typeToggleGroup.selectToggle(typeElectiveRadioButton);
            }

            if (lecture.courseLevel.equals("Associate Degree")) {
                levelToggleGroup.selectToggle(levelGraduateRadioButton);
            } else {
                levelToggleGroup.selectToggle(levelUndergraduateRadioButton);
            }

            if (lecture.deliveryMode.equals("Face-to-Face")) {
                deliveryToggleGroup.selectToggle(deliveryFaceToFaceRadioButton);
            } else {
                deliveryToggleGroup.selectToggle(deliveryOnlineRadioButton);
            }
            teachingMethodsTextArea.setText(lecture.teachingMethods);
            coordinatorTextField.setText(lecture.coordinator);
            instructionalStaffTextField.setText(lecture.lecturer);
            assistantsTextField.setText(lecture.assistants);
            for (int i = 0; i < lecture.courseCategory.size(); i++) {
                System.out.println(lecture.courseCategory.get(i));
                switch (lecture.courseCategory.get(i)) {
                    case "Core Course":
                        coreCourseCheckBox.setSelected(true);
                        coreCourseCheckBox.setOnAction(event -> handleCheckBoxSelection(coreCourseCheckBox, selectedCategories));
                        break;
                    case "Major Area Course":
                        majorAreaCourseCheckBox.setSelected(true);
                        majorAreaCourseCheckBox.setOnAction(event -> handleCheckBoxSelection(majorAreaCourseCheckBox, selectedCategories));
                        break;
                    case "Supportive Course":
                        supportiveCourseCheckBox.setSelected(true);
                        supportiveCourseCheckBox.setOnAction(event -> handleCheckBoxSelection(supportiveCourseCheckBox, selectedCategories));
                        break;
                    case "Communication and Management Skills Course":
                        communicationCourseCheckBox.setSelected(true);
                        communicationCourseCheckBox.setOnAction(event -> handleCheckBoxSelection(communicationCourseCheckBox, selectedCategories));
                        break;
                    default:
                        transferableSkillCheckBox.setSelected(true);
                        transferableSkillCheckBox.setOnAction(event -> handleCheckBoxSelection(transferableSkillCheckBox, selectedCategories));
                        break;
                }
            }
            for (int j = 0; j < lecture.weeklySubject.size(); j++) {
                int i=j*3;
                if(i==lessonsTextField.size()-3){
                    break;
                }
                    lessonsTextField.get(i).setText(lecture.weeklySubject.get(j).getWeek());
                System.out.println(lecture.weeklySubject.get(j).getWeek().toString());
                    lessonsTextField.get(i+1).setText(lecture.weeklySubject.get(j).getPreparation());
                System.out.println(lecture.weeklySubject.get(j).getPreparation().toString());
                    lessonsTextField.get(i + 2).setText(lecture.weeklySubject.get(j).getTopics());
                System.out.println(lecture.weeklySubject.get(j).getTopics().toString());


            }

            lessons.addAll(lecture.weeklySubject);
            Book.setText(lecture.book);
            Material.setText(lecture.materials);
            activities.addAll(lecture.assessmentTable);
            workLoads.addAll(lecture.workloadTable);
            competencies.addAll(lecture.outcomeTable);


        }
        submitButton.setOnAction(event -> {
            for (int j = 0; j < lessonsTextField.size() - 3; j += 3) {
                lessons.add(new Lesson(lessonsTextField.get(j).getText(),
                        lessonsTextField.get(j + 1).getText(),
                        lessonsTextField.get(j + 2).getText()));
            }

            for (int i = 0; i < activitiesTextField.size() - 7; i += 7) {
                try {
                    activities.add(new Activity(
                            activitiesTextField.get(i).getText(),
                            Integer.parseInt(activitiesTextField.get(i + 1).getText()),
                            Integer.parseInt(activitiesTextField.get(i + 2).getText()),
                            Integer.parseInt(activitiesTextField.get(i + 3).getText()),
                            Integer.parseInt(activitiesTextField.get(i + 4).getText()),
                            Integer.parseInt(activitiesTextField.get(i + 5).getText()),
                            Integer.parseInt(activitiesTextField.get(i + 6).getText())
                    ));
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setTitle("Uyarı");
                    alert.setContentText("Lütfen tüm alanları doldurun!");
                    Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
                    alertStage.setOnCloseRequest(event1 -> {
                        alert.close();
                    });
                    alert.showAndWait();
                }
            }
            for (int k = 0; k < workloadTextFields.size() - 4; k += 4) {
                try {
                    workLoads.add(new workLoad(
                            workloadTextFields.get(k).getText(),
                            Integer.parseInt(workloadTextFields.get(k + 1).getText()),
                            Integer.parseInt(workloadTextFields.get(k + 2).getText()),
                            Integer.parseInt(workloadTextFields.get(k + 3).getText())
                    ));
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setTitle("Uyarı");
                    alert.setContentText("Lütfen tüm alanları doldurun!");
                    Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
                    alertStage.setOnCloseRequest(event1 -> {
                        alert.close();
                    });
                    alert.showAndWait();
                }
            }
            for (int k = 0; k < outcomeTextField.size() - 6; k += 6) {
                try {

                    competencies.add(new Competency(
                            outcomeTextField.get(k).getText(),
                            Integer.parseInt(outcomeTextField.get(k + 1).getText()),
                            Integer.parseInt(outcomeTextField.get(k + 2).getText()),
                            Integer.parseInt(outcomeTextField.get(k + 3).getText()),
                            Integer.parseInt(outcomeTextField.get(k + 4).getText()),
                            Integer.parseInt(outcomeTextField.get(k + 5).getText())
                    ));
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setTitle("Uyarı");
                    alert.setContentText("Lütfen tüm alanları doldurun!");
                    Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
                    alertStage.setOnCloseRequest(event1 -> {
                        alert.close();
                    });
                    alert.showAndWait();

                }
            }
            if (courseNameTextField.getText().isEmpty() || courseCodeTextField.getText().isEmpty() || termChoiceBox.getValue() == null || theoryHoursTextField.getText().isEmpty() ||
                    applicationHoursTextField.getText().isEmpty() || localCreditTextField.getText().isEmpty() || ectsTextField.getText().isEmpty() || preTextField.getText().isEmpty() ||
                    languageToggleGroup.getSelectedToggle() == null ||
                    typeToggleGroup.getSelectedToggle() == null || levelToggleGroup.getSelectedToggle() == null ||
                    deliveryToggleGroup.getSelectedToggle() == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setTitle("Uyarı");
                alert.setContentText("Lütfen tüm alanları doldurun!");
                Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
                alertStage.setOnCloseRequest(event1 -> {
                    alert.close();
                });
                alert.showAndWait();


            } else {
                showInputDialog();

                RadioButton selectedLanguage = (RadioButton) languageToggleGroup.getSelectedToggle();
                RadioButton selectedType = (RadioButton) typeToggleGroup.getSelectedToggle();
                RadioButton selectedLevel = (RadioButton) levelToggleGroup.getSelectedToggle();
                RadioButton selectedDelivery = (RadioButton) deliveryToggleGroup.getSelectedToggle();


                Lecture lecture = new Lecture(
                        courseNameTextField.getText(),
                        courseCodeTextField.getText(),
                        termChoiceBox.getValue().toString(),
                        theoryHoursTextField.getText(),
                        applicationHoursTextField.getText(),
                        localCreditTextField.getText(),
                        ectsTextField.getText(),
                        preTextField.getText(),
                        selectedLanguage.getText(),
                        selectedType.getText(),
                        selectedLevel.getText(),
                        selectedDelivery.getText(),
                        teachingMethodsTextArea.getText(),
                        coordinatorTextField.getText(),
                        instructionalStaffTextField.getText(),
                        assistantsTextField.getText(),
                        selectedCategories,
                        lessons,
                        Book.getText(),
                        Material.getText(),
                        activities,
                        workLoads,
                        competencies
                );
                String defaultDirectoryPath = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
                String syllabusPath = defaultDirectoryPath + File.separator + "Syllabus";
                File directory = new File(syllabusPath);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                String courseCode = courseCodeTextField.getText();
                String courseCodePath = syllabusPath + File.separator + courseCode;
                File directory2 = new File(courseCodePath);
                if (!directory2.exists()) {
                    directory2.mkdirs();
                }
                String path = courseCodePath;
                File file = new File(path);
                try {
                    if (file.exists()) {
                        System.out.println("Dosya zaten mevcut.");
                    } else {
                        if (file.mkdir()) {
                            System.out.println("Dosya olusturldu.");
                        } else {
                            System.out.println("Failed to create directory.");
                        }
                    }
                    Gson gson = new Gson();
                    String lectureString = gson.toJson(lecture);
                    DirectoryChooser directoryChooser = new DirectoryChooser();
                    directoryChooser.setTitle("Select Folder to Save JSON File");
                    File selectedDirectory = directoryChooser.showDialog(new Stage());
                    if (selectedDirectory != null) {
                        TextInputDialog dialog = new TextInputDialog("output");
                        dialog.setTitle("Enter File Name");
                        dialog.setHeaderText(null);
                        dialog.setContentText("Please enter the name of the JSON file:");
                        Optional<String> result = dialog.showAndWait();
                        if (result.isPresent()) {
                            String fileName = result.get().isEmpty() ? "output" : result.get();
                            String jsonFilePath = selectedDirectory.getAbsolutePath() + File.separator + fileName + ".json";
                            try (FileWriter fileWriter = new FileWriter(jsonFilePath)) {
                                fileWriter.write(lectureString);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    SyllabusVersioning versioning = new SyllabusVersioning(changedBy, changeReason, changeDate, lecture);
                    String jsonString = gson.toJson(versioning);
                    File[] jsonFiles = file.listFiles((dir, name) -> name.endsWith(".json"));
                    int counter = (jsonFiles != null) ? jsonFiles.length + 1 : 1;
                    String filePath = path + "/" + courseCodeTextField.getText() + counter + ".json";
                    try (FileWriter writer = new FileWriter(filePath)) {
                        writer.write(jsonString);
                        System.out.println("JSON successfully written to the file: " + filePath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

        });

        Scene scene1 = new Scene(new StackPane(scrollPane), 1200, 800);
        stage.setScene(scene1);
        stage.show();
    }

    private void handleCheckBoxSelection(CheckBox checkBox, List<String> selectedList) {
        if (checkBox.isSelected()) {
            selectedList.add(checkBox.getText());
        } else {
            selectedList.remove(checkBox.getText());
        }

        // Seçilen kategorileri görmek için ekrana yazdırabilirsiniz
        System.out.println("Selected Categories: " + selectedList);
    }


    public void showInputDialog() {

        Stage inputStage = new Stage();
        inputStage.setTitle("Kullanıcı Girişi");

        GridPane inputGridPane = new GridPane();
        inputGridPane.setPadding(new Insets(20, 20, 20, 20));
        inputGridPane.setVgap(10);
        inputGridPane.setHgap(10);


        Label inputLabel = new Label("değişlik yapan kişi");
        TextField userInputField = new TextField();
        inputGridPane.add(inputLabel, 0, 0);
        inputGridPane.add(userInputField, 1, 0);

        Label inputLabel1 = new Label("Değişiklik sebebi");
        TextField userInputField1 = new TextField();
        inputGridPane.add(inputLabel1, 0, 1);
        inputGridPane.add(userInputField1, 1, 1);

        Label inputLabel2 = new Label("değişiklik Tarihi");
        TextField userInputField2 = new TextField();
        inputGridPane.add(inputLabel2, 0, 2);
        inputGridPane.add(userInputField2, 1, 2);


        Button confirmButton = new Button("Onayla");
        confirmButton.setOnAction(e -> {
            changedBy = userInputField.getText();
            changeReason = userInputField1.getText();
            changeDate = userInputField2.getText();

            inputStage.close();
        });

    }

    public void goBackToMainScene() {

        FirstScene firstScene = new FirstScene();
        firstScene.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
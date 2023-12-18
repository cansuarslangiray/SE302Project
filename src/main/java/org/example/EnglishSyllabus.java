package org.example;

import com.google.gson.Gson;
import com.google.gson.Gson;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class EnglishSyllabus extends Application {
    Lecture lecture;
    boolean isSaved;

    public EnglishSyllabus (Lecture lecture, boolean isSaved) {
        this.lecture = lecture;
        this.isSaved = isSaved;
    }
    public static class Lesson {
        private final SimpleStringProperty week;
        private final SimpleStringProperty topics;
        private final SimpleStringProperty preparation;

        private Lesson(String week, String topics, String preparation) {
            this.week = new SimpleStringProperty(week);
            this.topics = new SimpleStringProperty(topics);
            this.preparation = new SimpleStringProperty(preparation);
        }

        public String getWeek() {
            return week.get();
        }

        public String getTopics() {
            return topics.get();
        }

        public String getPreparation() {
            return preparation.get();
        }
    }


    public class Activity {
        private StringProperty name;
        private IntegerProperty count;
        private IntegerProperty percentage;
        private IntegerProperty lo1;
        private IntegerProperty lo2;
        private IntegerProperty lo3;
        private IntegerProperty lo4;
        private IntegerProperty lo5;
        private IntegerProperty lo6;


        public Activity(String name, int count, int percentage, int lo1, int lo2, int lo3, int lo4, int lo5, int lo6) {
            this.name = new SimpleStringProperty(name);
            this.count = new SimpleIntegerProperty(count);
            this.percentage = new SimpleIntegerProperty(percentage);
            this.lo1 = new SimpleIntegerProperty(lo1);
            this.lo2 = new SimpleIntegerProperty(lo2);
            this.lo3 = new SimpleIntegerProperty(lo3);
            this.lo4 = new SimpleIntegerProperty(lo4);
            this.lo5 = new SimpleIntegerProperty(lo5);
            this.lo6 = new SimpleIntegerProperty(lo6);
        }

        public String getName() {
            return name.get();
        }

        public StringProperty nameProperty() {
            return name;
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public int getCount() {
            return count.get();
        }

        public IntegerProperty countProperty() {
            return count;
        }

        public void setCount(int count) {
            this.count.set(count);
        }

        public int getPercentage() {
            return percentage.get();
        }

        public IntegerProperty percentageProperty() {
            return percentage;
        }

        public void setPercentage(int percentage) {
            this.percentage.set(percentage);
        }

        public int getLo1() {
            return lo1.get();
        }

        public IntegerProperty lo1Property() {
            return lo1;
        }

        public void setLo1(int lo1) {
            this.lo1.set(lo1);
        }

        public int getLo2() {
            return lo2.get();
        }

        public IntegerProperty lo2Property() {
            return lo2;
        }

        public void setLo2(int lo2) {
            this.lo2.set(lo2);
        }

        public int getLo3() {
            return lo3.get();
        }

        public IntegerProperty lo3Property() {
            return lo3;
        }

        public void setLo3(int lo3) {
            this.lo3.set(lo3);
        }

        public int getLo4() {
            return lo4.get();
        }

        public IntegerProperty lo4Property() {
            return lo4;
        }

        public void setLo4(int lo4) {
            this.lo4.set(lo4);
        }

        public int getLo5() {
            return lo5.get();
        }

        public IntegerProperty lo5Property() {
            return lo5;
        }

        public void setLo5(int lo5) {
            this.lo5.set(lo5);
        }

        public int getLo6() {
            return lo6.get();
        }

        public IntegerProperty lo6Property() {
            return lo6;
        }

        public void setLo6(int lo6) {
            this.lo6.set(lo6);
        }

    }

    public class workLoad {

        private StringProperty activity;
        private IntegerProperty count;
        private IntegerProperty hour;
        private IntegerProperty workloud;

        public String getActivity() {
            return activity.get();
        }

        public StringProperty activityProperty() {
            return activity;
        }

        public void setActivity(String activity) {
            this.activity.set(activity);
        }

        public int getCount() {
            return count.get();
        }

        public IntegerProperty countProperty() {
            return count;
        }

        public void setCount(int count) {
            this.count.set(count);
        }

        public int getHour() {
            return hour.get();
        }

        public IntegerProperty hourProperty() {
            return hour;
        }

        public void setHour(int hour) {
            this.hour.set(hour);
        }

        public int getWorkloud() {
            return workloud.get();
        }

        public IntegerProperty workloudProperty() {
            return workloud;
        }

        public void setWorkloud(int workloud) {
            this.workloud.set(workloud);
        }


        public workLoad(String activity, int count, int hour, int workloud) {
            this.activity = new SimpleStringProperty(activity);
            this.count = new SimpleIntegerProperty(count);
            this.hour = new SimpleIntegerProperty(hour);
            this.workloud = new SimpleIntegerProperty(workloud);
        }
    }

    public class Competency {
        private StringProperty description;
        private IntegerProperty level1;
        private IntegerProperty level2;
        private IntegerProperty level3;
        private IntegerProperty level4;
        private IntegerProperty level5;
        private StringProperty lo;

        public Competency(String description, Integer level1, Integer level2, Integer level3, Integer level4, Integer level5, String lo) {
            this.description = new SimpleStringProperty(description);
            this.level1 = new SimpleIntegerProperty(level1);
            this.level2 = new SimpleIntegerProperty(level2);
            this.level3 = new SimpleIntegerProperty(level3);
            this.level4 = new SimpleIntegerProperty(level4);
            this.level5 = new SimpleIntegerProperty(level5);
            this.lo = new SimpleStringProperty(lo);
        }

        // Getters
        public String getDescription() {
            return description.get();
        }

        public int getLevel1() {
            return level1.get();
        }

        public int getLevel2() {
            return level2.get();
        }

        public int getLevel3() {
            return level3.get();
        }

        public int getLevel4() {
            return level4.get();
        }

        public int getLevel5() {
            return level5.get();
        }

        public String getLo() {
            return lo.get();
        }

        // Setters
        public void setDescription(String value) {
            description.set(value);
        }

        public void setLevel1(int value) {
            level1.set(value);
        }

        public void setLevel2(int value) {
            level2.set(value);
        }

        public void setLevel3(int value) {
            level3.set(value);
        }

        public void setLevel4(int value) {
            level4.set(value);
        }

        public void setLevel5(int value) {
            level5.set(value);
        }

        public void setLo(String value) {
            lo.set(value);
        }
    }

    private final ObservableList<Competency> competencies = FXCollections.observableArrayList(
            new Competency("Matematik, Fen Bilimleri ve Bilgisayar Mühendisliği konularında yeterli bilgi sahibidir...", 0, 0, 1, 0, 0, "1,2")
            // ... Add other competencies here
    );

    private ObservableList<Activity> data = FXCollections.observableArrayList();
    private ObservableList<workLoad> data1 = FXCollections.observableArrayList();

    private final ObservableList<Lesson> lessons = FXCollections.observableArrayList(
            new Lesson("1", "Programlama Stili ve Konvansiyonlar", "Practice of Programming, 1. Bölüm"),
            new Lesson("2", "Yapılandırma Otomasyonu ve Yazılım Dağıtımı", "Apache Maven Çevrimiçi Belgeleri"),
            new Lesson("3", "Grafik Kullanıcı Arayüzleri: JavaFX", "Java How to Program, 25. Bölüm; Java In\n" +
                    "Two Semesters, 10. Bölüm.\n"),
            new Lesson("4", "Dosyalar ile çalışma", " Java How to Program, 15. Bölüm; Java in\n" +
                    "Two Semesters, 18. Bölüm")
    );

    @Override
    public void start(Stage stage) {
        stage.setTitle("Course Form");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        GridPane gridPane1 = new GridPane();
        gridPane1.setPadding(new Insets(20, 20, 20, 20));
        gridPane1.setVgap(10);
        gridPane1.setHgap(10);

        Label titleLabel = new Label("IZMIR UNIVERSITY OF ECONOMICS\n" +
                "COURSE OUTLINE FORM");
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
        ToggleGroup levelToggleGroup1 = new ToggleGroup();
        RadioButton levelUndergraduateRadioButton1 = new RadioButton("Core Course");
        levelUndergraduateRadioButton.setToggleGroup(levelToggleGroup);
        RadioButton levelGraduateRadioButton1 = new RadioButton("Major Area Course");
        levelGraduateRadioButton1.setToggleGroup(levelToggleGroup);
        RadioButton levelGraduateRadioButton2 = new RadioButton("Supportive Course");
        levelGraduateRadioButton2.setToggleGroup(levelToggleGroup);
        RadioButton levelGraduateRadioButton3 = new RadioButton("Communication and Management Skills Course\n");
        levelGraduateRadioButton3.setToggleGroup(levelToggleGroup);
        RadioButton levelGraduateRadioButton4 = new RadioButton("Transferable Skill Course");
        levelGraduateRadioButton4.setToggleGroup(levelToggleGroup);
        VBox levelBox1 = new VBox(10, levelUndergraduateRadioButton1, levelGraduateRadioButton1, levelGraduateRadioButton2, levelGraduateRadioButton3, levelGraduateRadioButton4);
        gridPane.add(levelBox1, 1, 18);

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


        Button submitButton = new Button("Submit");
        gridPane.add(submitButton, 0, 18, 2, 1);

        submitButton.setOnAction(event -> {
            if (courseNameTextField.getText().isEmpty() || termChoiceBox.getValue() == null ||
                    typeToggleGroup.getSelectedToggle() == null || levelToggleGroup.getSelectedToggle() == null ||
                    deliveryToggleGroup.getSelectedToggle() == null) {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setTitle("Alert");
                alert.setContentText("Please fill in all fields!");
                alert.showAndWait();
            } else {
                // Proceed with creating the Lecture object and writing to JSON file

                RadioButton selectedLanguage = (RadioButton) languageToggleGroup.getSelectedToggle();
                RadioButton selectedType = (RadioButton) typeToggleGroup.getSelectedToggle();
                RadioButton selectedLevel = (RadioButton) levelToggleGroup.getSelectedToggle();
                RadioButton selectedDelivery = (RadioButton) deliveryToggleGroup.getSelectedToggle();

                Lecture lecture = new Lecture(courseNameTextField.getText(), selectedLanguage.getText(), selectedType.getText(),
                        selectedLevel.getText(), selectedDelivery.getText(), ((RadioButton) typeToggleGroup.getSelectedToggle()).getText(), courseCodeTextField.getText(),
                        termChoiceBox.getValue(), theoryHoursTextField.getText(), applicationHoursTextField.getText(),
                        localCreditTextField.getText(), ectsTextField.getText(), preTextField.getText(), teachingMethodsTextArea.getText(),
                        coordinatorTextField.getText(), instructionalStaffTextField.getText(), assistantsTextField.getText());



                Gson gson = new Gson();
                String jsonString = gson.toJson(lecture);
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
                String defaultDirectoryPath = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
                String syllabusPath = defaultDirectoryPath + File.separator + "Syllabus";
                File directory = new File(syllabusPath);
                if (!directory.exists()) {
                    directory.mkdirs();}
                String courseCode = courseCodeTextField.getText();
                String courseCodePath = syllabusPath + File.separator + courseCode;
                File directory2 = new File(courseCodePath);
                if (!directory2.exists()) {
                    directory2.mkdirs();}
                String filePath= courseCodePath+ File.separator+ "file.json";
                try {
                    File file = new File(filePath);
                    file.getParentFile().mkdirs();

                    try (FileWriter writer = new FileWriter(file)) {
                        writer.write(jsonString);
                        System.out.println("JSON successfully written to the file: " + filePath);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        TableView<Lesson> tableView = new TableView<>();

        TableColumn<Lesson, String> weekColumn = new TableColumn<>("Week");
        weekColumn.setCellValueFactory(cellData -> cellData.getValue().week);

        TableColumn<Lesson, String> topicsColumn = new TableColumn<>("Subjects");
        topicsColumn.setCellValueFactory(cellData -> cellData.getValue().topics);

        TableColumn<Lesson, String> preparationColumn = new TableColumn<>("Required Materials");
        preparationColumn.setCellValueFactory(cellData -> cellData.getValue().preparation);

        tableView.getColumns().addAll(weekColumn, topicsColumn, preparationColumn);

        weekColumn.prefWidthProperty().bind(tableView.widthProperty().divide(3));
        topicsColumn.prefWidthProperty().bind(tableView.widthProperty().divide(3));
        preparationColumn.prefWidthProperty().bind(tableView.widthProperty().divide(3));

        tableView.setItems(lessons);


        TableView<Activity> table = new TableView<>();
        data.addAll(
                new Activity("Participation", 1, 30, 1, 2, 3, 4, 5, 6),
                new Activity("Laboratory/Application", 1, 30, 1, 2, 3, 4, 5, 6),
                new Activity("Field Work", 1, 30, 1, 2, 3, 4, 5, 6),
                new Activity("Quiz/Studio Critique", 1, 30, 1, 2, 3, 4, 5, 6),
                new Activity("Homework/Assignment", 1, 30, 1, 2, 3, 4, 5, 6),
                new Activity("Presentation/Jury", 1, 30, 1, 2, 3, 4, 5, 6),
                new Activity("Project", 1, 30, 1, 2, 3, 4, 5, 6),
                new Activity("Portfolio", 1, 30, 1, 2, 3, 4, 5, 6),
                new Activity("Seminar/Workshop", 1, 30, 1, 2, 3, 4, 5, 6),
                new Activity("Oral Exam", 1, 30, 1, 2, 3, 4, 5, 6),
                new Activity("Midterm Exam", 1, 30, 1, 2, 3, 4, 5, 6),
                new Activity("Final Exam", 1, 30, 1, 2, 3, 4, 5, 6),
                new Activity("Toplam", 3, 100, 3, 3, 4, 5, 6, 7));

        TableColumn<Activity, String> nameCol = new TableColumn<>("Semester Activities");
        nameCol.setCellValueFactory(cellData -> cellData.getValue().name);

        TableColumn<Activity, Number> countCol = new TableColumn<>("Number");
        countCol.setCellValueFactory(cellData -> cellData.getValue().count);

        TableColumn<Activity, Number> percentageCol = new TableColumn<>("Weighting");
        percentageCol.setCellValueFactory(cellData -> cellData.getValue().percentage);

        TableColumn<Activity, Number> lo1Col = new TableColumn<>("LO1");
        lo1Col.setCellValueFactory(cellData -> cellData.getValue().lo1);

        TableColumn<Activity, Number> lo2Col = new TableColumn<>("LO2");
        lo2Col.setCellValueFactory(cellData -> cellData.getValue().lo2);

        TableColumn<Activity, Number> lo3Col = new TableColumn<>("LO3");
        lo3Col.setCellValueFactory(cellData -> cellData.getValue().lo3);

        TableColumn<Activity, Number> lo4Col = new TableColumn<>("LO4");
        lo4Col.setCellValueFactory(cellData -> cellData.getValue().lo4);



        table.getColumns().addAll(nameCol, countCol, percentageCol, lo1Col, lo2Col, lo3Col, lo4Col);
        table.setItems(data);

        nameCol.prefWidthProperty().bind(tableView.widthProperty().divide(7));
        countCol.prefWidthProperty().bind(tableView.widthProperty().divide(7));
        percentageCol.prefWidthProperty().bind(tableView.widthProperty().divide(7));
        lo1Col.prefWidthProperty().bind(tableView.widthProperty().divide(7));
        lo2Col.prefWidthProperty().bind(tableView.widthProperty().divide(7));
        lo3Col.prefWidthProperty().bind(tableView.widthProperty().divide(7));
        lo4Col.prefWidthProperty().bind(tableView.widthProperty().divide(7));


        TableView<workLoad> table1 = new TableView<>();
        data1.addAll(
                new workLoad("Study Hours out of Class", 14, 2, 18),
                new workLoad("Field Work", 14, 2, 18),
                new workLoad("Quiz/Studio Critique", 14, 2, 18),
                new workLoad("Homework/Assignment", 14, 2, 18),
                new workLoad("Presentation/Jury", 14, 2, 18),
                new workLoad("Project", 14, 2, 18),
                new workLoad("Portfolio", 14, 2, 18),
                new workLoad("Seminar/Workshop", 14, 2, 18),
                new workLoad("Oral Exam", 14, 2, 18),
                new workLoad("Midterm Exam", 14, 2, 18),
                new workLoad("Final Exam", 14, 2, 18),
                new workLoad("Total", 14, 2, 18)
        );



        TableColumn<workLoad, String> activityCol = new TableColumn<>("Semester Activities");
        activityCol.setCellValueFactory(cellData -> cellData.getValue().activity);

        TableColumn<workLoad, Number> countCol1 = new TableColumn<>("Number");
        countCol1.setCellValueFactory(cellData -> cellData.getValue().count);

        TableColumn<workLoad, Number> hourCol = new TableColumn<>("Duration(Hours)");
        hourCol.setCellValueFactory(cellData -> cellData.getValue().hour);

        TableColumn<workLoad, Number> workloadCol = new TableColumn<>("Workload");
        workloadCol.setCellValueFactory(cellData -> cellData.getValue().workloud);

        table1.getColumns().addAll(activityCol, countCol1, hourCol, workloadCol);
        table1.setItems(data1);
        activityCol.prefWidthProperty().bind(table1.widthProperty().divide(4));
        countCol1.prefWidthProperty().bind(table1.widthProperty().divide(4));
        hourCol.prefWidthProperty().bind(table1.widthProperty().divide(4));
        workloadCol.prefWidthProperty().bind(table1.widthProperty().divide(4));


        TableView<Competency> table12 = new TableView<>();

        TableColumn<Competency, String> descColumn = new TableColumn<>("Program Competencies/Outcomes");
        descColumn.setCellValueFactory(cellData -> cellData.getValue().description);

        TableColumn<Competency, Number> level1Column = createNumericColumn("1", "level1");
        TableColumn<Competency, Number> level2Column = createNumericColumn("2", "level2");
        TableColumn<Competency, Number> level3Column = createNumericColumn("3", "level3");
        TableColumn<Competency, Number> level4Column = createNumericColumn("4", "level4");
        TableColumn<Competency, Number> level5Column = createNumericColumn("5", "level5");

        TableColumn<Competency, String> loColumn = new TableColumn<>("LO#");
        loColumn.setCellValueFactory(cellData -> cellData.getValue().lo);

        table12.getColumns().addAll(descColumn, level1Column, level2Column, level3Column, level4Column, level5Column, loColumn);
        table12.setItems(competencies);

        VBox vBox = new VBox(gridPane, tableView, gridPane1, table, vBox1, table1, vBox2, table12, submitButton);

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
            courseCodeTextField.setText(lecture.code);
            if (lecture.term.equals("Güz")){
                termChoiceBox.setValue("Güz");
            }
            else {
                termChoiceBox.setValue("Bahar");
            }
            theoryHoursTextField.setText(lecture.theory);
            localCreditTextField.setText(lecture.localCredits);
            applicationHoursTextField.setText(lecture.application);
            ectsTextField.setText(lecture.ects);
            preTextField.setText(lecture.prerequisites);
            String lectureLanguage = lecture.language;
            switch (lectureLanguage)
            {
                case "Türkçe":
                    languageToggleGroup.selectToggle(languageTurkishRadioButton);
                    break;
                case "İngilizce":
                    languageToggleGroup.selectToggle(languageEnglishRadioButton);
                    break;
                case "İkinci Yabancı Dil":
                    languageToggleGroup.selectToggle(languageOtherRadioButton);
                    break;
            }
            if (lecture.type.equals("Zorunlu")){
                typeToggleGroup.selectToggle(typeMandatoryRadioButton);
            }
            else {
                typeToggleGroup.selectToggle(typeElectiveRadioButton);
            }

            if (lecture.courseLevel.equals("Lisans")){
                levelToggleGroup.selectToggle(levelGraduateRadioButton);
            }
            else {
                levelToggleGroup.selectToggle(levelUndergraduateRadioButton);
            }

            if (lecture.mode.equals("Yüz Yüze")){
                deliveryToggleGroup.selectToggle(deliveryFaceToFaceRadioButton);
            }
            else {
                deliveryToggleGroup.selectToggle(deliveryOnlineRadioButton);
            }
            coordinatorTextField.setText(lecture.courseCoordinator);
            teachingMethodsTextArea.setText(lecture.teachingMethod);
            instructionalStaffTextField.setText(lecture.courseLecturer);
            assistantsTextField.setText(lecture.assistant);
        }
        Scene scene1 = new Scene(new StackPane(scrollPane), 1200, 800);
        stage.setScene(scene1);
        stage.show();
    }


    private TableColumn<Competency, Number> createNumericColumn(String columnName, String property) {
        TableColumn<Competency, Number> column = new TableColumn<>(columnName);
        column.setCellValueFactory(new PropertyValueFactory<>(property));

        column.setCellFactory(tc -> {
            TableCell<Competency, Number> cell = new TableCell<Competency, Number>() {
                private final CheckBox checkBox = new CheckBox();

                {
                    checkBox.setOnAction(event -> {
                        int selectedIndex = getIndex();
                        Competency selectedCompetency = getTableView().getItems().get(selectedIndex);
                        int value = checkBox.isSelected() ? 1 : 0;
                        switch (property) {
                            case "level1Property":
                                selectedCompetency.setLevel1(value);
                                break;
                            case "level2Property":
                                selectedCompetency.setLevel2(value);
                                break;
                            case "level3Property":
                                selectedCompetency.setLevel3(value);
                                break;
                            case "level4Property":
                                selectedCompetency.setLevel4(value);
                                break;
                            case "level5Property":
                                selectedCompetency.setLevel5(value);
                                break;
                            // Add other cases if needed
                        }
                    });
                }

                @Override
                protected void updateItem(Number item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        checkBox.setSelected(item.intValue() == 1);
                        setGraphic(checkBox);
                    }
                }
            };
            return cell;
        });

        return column;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
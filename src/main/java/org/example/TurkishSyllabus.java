
package org.example;

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
import javafx.stage.*;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.Optional;

import static javax.swing.JOptionPane.showInputDialog;

public class TurkishSyllabus extends Application {
    Lecture lecture;
    boolean isSaved;
    public TurkishSyllabus(Lecture lecture, boolean isSaved) {
        this.lecture = lecture;
        this.isSaved = isSaved;
    }
    static  int counter =0;
    static Stage mainStage;
    String changedBy;
    String changeReason ;
    String changeDate ;


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
        stage.setTitle("Ders Formu");
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        GridPane gridPane1 = new GridPane();
        gridPane1.setPadding(new Insets(20, 20, 20, 20));
        gridPane1.setVgap(10);
        gridPane1.setHgap(10);


        Label titleLabel = new Label("İZMİR EKONOMİ ÜNİVERSİTESİ\n        DERS ÖNERİ FORMU");
        titleLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20;");
        titleLabel.setAlignment(Pos.CENTER);

        GridPane.setConstraints(titleLabel, 0, 0, 2, 1);
        gridPane.getChildren().add(titleLabel);
        GridPane.setHalignment(titleLabel, HPos.CENTER);

        Label orangeLabel = new Label("1.GENEL BİLGİLER");
        orangeLabel.setStyle("-fx-background-color: orange; -fx-padding: 5px;-fx-font-weight: bold;");
        GridPane.setConstraints(orangeLabel, 0, 1, 2, 1);
        gridPane.getChildren().add(orangeLabel);

        gridPane.add(new Label(""), 0, 1);

        gridPane.add(new Label("Dersin Adı:"), 0, 2);
        TextField courseNameTextField = new TextField();
        gridPane.add(courseNameTextField, 1, 2);

        gridPane.add(new Label("Dersin Kodu:"), 0, 3);
        TextField courseCodeTextField = new TextField();
        gridPane.add(courseCodeTextField, 1, 3);

        gridPane.add(new Label("Dönem:"), 0, 4);
        ChoiceBox<String> termChoiceBox = new ChoiceBox<>();
        termChoiceBox.getItems().addAll("Güz", "Bahar");
        gridPane.add(termChoiceBox, 1, 4);

        gridPane.add(new Label("Teori (saat/hafta):"), 0, 5);
        TextField theoryHoursTextField = new TextField();
        gridPane.add(theoryHoursTextField, 1, 5);

        gridPane.add(new Label("Uygulama/Lab (saat/hafta):"), 0, 6);
        TextField applicationHoursTextField = new TextField();
        gridPane.add(applicationHoursTextField, 1, 6);

        gridPane.add(new Label("Yerel Kredi:"), 0, 7);
        TextField localCreditTextField = new TextField();
        gridPane.add(localCreditTextField, 1, 7);

        gridPane.add(new Label("AKTS:"), 0, 8);
        TextField ectsTextField = new TextField();
        gridPane.add(ectsTextField, 1, 8);

        gridPane.add(new Label("Ön Koşullar:"), 0, 9);
        TextField preTextField = new TextField();
        gridPane.add(preTextField, 1, 9);

        gridPane.add(new Label("Dersin Dili:"), 0, 10);
        ToggleGroup languageToggleGroup = new ToggleGroup();
        RadioButton languageTurkishRadioButton = new RadioButton("Türkçe");
        languageTurkishRadioButton.setToggleGroup(languageToggleGroup);
        RadioButton languageEnglishRadioButton = new RadioButton("İngilizce");
        languageEnglishRadioButton.setToggleGroup(languageToggleGroup);
        RadioButton languageOtherRadioButton = new RadioButton("İkinci Yabancı Dil");
        languageOtherRadioButton.setToggleGroup(languageToggleGroup);
        HBox languageBox = new HBox(10, languageTurkishRadioButton, languageEnglishRadioButton, languageOtherRadioButton);
        gridPane.add(languageBox, 1, 10);

        gridPane.add(new Label("Dersin Türü:"), 0, 11);
        ToggleGroup typeToggleGroup = new ToggleGroup();
        RadioButton typeMandatoryRadioButton = new RadioButton("Zorunlu");
        typeMandatoryRadioButton.setToggleGroup(typeToggleGroup);
        RadioButton typeElectiveRadioButton = new RadioButton("Seçmeli");
        typeElectiveRadioButton.setToggleGroup(typeToggleGroup);
        HBox typeBox = new HBox(10, typeMandatoryRadioButton, typeElectiveRadioButton);
        gridPane.add(typeBox, 1, 11);

        gridPane.add(new Label("Dersin Düzeyi:"), 0, 12);
        ToggleGroup levelToggleGroup = new ToggleGroup();
        RadioButton levelUndergraduateRadioButton = new RadioButton("Ön Lisans");
        levelUndergraduateRadioButton.setToggleGroup(levelToggleGroup);
        RadioButton levelGraduateRadioButton = new RadioButton("Lisans");
        levelGraduateRadioButton.setToggleGroup(levelToggleGroup);
        HBox levelBox = new HBox(10, levelUndergraduateRadioButton, levelGraduateRadioButton);
        gridPane.add(levelBox, 1, 12);

        gridPane.add(new Label("Dersin Veriliş Şekli:"), 0, 13);
        ToggleGroup deliveryToggleGroup = new ToggleGroup();
        RadioButton deliveryFaceToFaceRadioButton = new RadioButton("Yüz Yüze");
        deliveryFaceToFaceRadioButton.setToggleGroup(deliveryToggleGroup);
        RadioButton deliveryOnlineRadioButton = new RadioButton("Çevrim İçi");
        deliveryOnlineRadioButton.setToggleGroup(deliveryToggleGroup);
        HBox deliveryBox = new HBox(10, deliveryFaceToFaceRadioButton, deliveryOnlineRadioButton);
        gridPane.add(deliveryBox, 1, 13);

        gridPane.add(new Label("Dersin Öğretim Yöntem ve Teknikleri:"), 0, 14);
        TextArea teachingMethodsTextArea = new TextArea();
        gridPane.add(teachingMethodsTextArea, 1, 14);

        gridPane.add(new Label("Ders Koordinatörü:"), 0, 15);
        TextField coordinatorTextField = new TextField();
        gridPane.add(coordinatorTextField, 1, 15);

        gridPane.add(new Label("Öğretim Eleman(lar):"), 0, 16);
        TextField instructionalStaffTextField = new TextField();
        gridPane.add(instructionalStaffTextField, 1, 16);

        gridPane.add(new Label("Yardımcı(lar):"), 0, 17);
        TextField assistantsTextField = new TextField();
        gridPane.add(assistantsTextField, 1, 17);

        gridPane.add(new Label("Dersin Kategorisi:"), 0, 18);
        ToggleGroup levelToggleGroup1 = new ToggleGroup();
        RadioButton levelUndergraduateRadioButton1 = new RadioButton("Temel Ders");
        levelUndergraduateRadioButton.setToggleGroup(levelToggleGroup);
        RadioButton levelGraduateRadioButton1 = new RadioButton("Uzmanlık/Alan Ders");
        levelGraduateRadioButton1.setToggleGroup(levelToggleGroup);
        RadioButton levelGraduateRadioButton2 = new RadioButton("Destek Dersi");
        levelGraduateRadioButton2.setToggleGroup(levelToggleGroup);
        RadioButton levelGraduateRadioButton3 = new RadioButton("İletişim ve Yönetim Becerileri Dersi");
        levelGraduateRadioButton3.setToggleGroup(levelToggleGroup);
        RadioButton levelGraduateRadioButton4 = new RadioButton("Aktarılabilir Beceri Dersi\n");
        levelGraduateRadioButton4.setToggleGroup(levelToggleGroup);
        VBox levelBox1 = new VBox(10, levelUndergraduateRadioButton1, levelGraduateRadioButton1,levelGraduateRadioButton2,levelGraduateRadioButton3,levelGraduateRadioButton4);
        gridPane.add(levelBox1, 1, 18);

        Label orangeLabel1 = new Label("2. HAFTALIK KONULAR VE İLGİLİ ÖN HAZIRLIK ÇALIŞMALARI\n");
        orangeLabel1.setStyle("-fx-background-color: orange; -fx-padding: 5px;-fx-font-weight: bold;");
        GridPane.setConstraints(orangeLabel1, 0, 19, 2, 1);
        gridPane.getChildren().add(orangeLabel1);

        Label spaceLabel1 = new Label("        ");
        Label orangeLabel3 = new Label("4. AKTS / İŞ YÜKÜ TABLOSU");
        orangeLabel3.setStyle("-fx-background-color: orange; -fx-padding: 5px;-fx-font-weight: bold;");
        Label spaceLabel = new Label("        ");


        VBox vBox1=new VBox(spaceLabel1,orangeLabel3,spaceLabel);


        Label spaceLabe = new Label("        ");
        Label orangeLabel4 = new Label("5. DERSİN ÖĞRENME ÇIKTILARININ PROGRAM YETERLİLİKLERİ İLE İLİŞKİSİ\n");
        orangeLabel4.setStyle("-fx-background-color: orange; -fx-padding: 5px;-fx-font-weight: bold;");
        Label spaceLabe1 = new Label("        ");

        VBox vBox2=new VBox(spaceLabe1,orangeLabel4,spaceLabe);

        gridPane1.add(new Label("ders Kitabı:"), 0, 1);
        TextArea Book = new TextArea();
        gridPane1.add(Book, 1, 1);

        gridPane1.add(new Label("Önerilen Okumalar/Materyaller:"), 0, 2);
        TextArea Material = new TextArea();
        gridPane1.add(Material, 1, 2);

        Label orangeLabel2 = new Label("3. DEĞERLENDİRME ÖLÇÜTLERİ");
        orangeLabel2.setStyle("-fx-background-color: orange; -fx-padding: 5px;-fx-font-weight: bold;");
        gridPane1.setConstraints(orangeLabel2, 0, 3, 2, 2);
        gridPane1.getChildren().add(orangeLabel2 );


        Button submitButton = new Button("Gönder");
        gridPane.add(submitButton, 0, 18, 2, 1);

        TableView<Lesson> tableView = new TableView<>();

        TableColumn<Lesson, String> weekColumn = new TableColumn<>("Hafta");
        weekColumn.setCellValueFactory(cellData -> cellData.getValue().week);

        TableColumn<Lesson, String> topicsColumn = new TableColumn<>("Konular");
        topicsColumn.setCellValueFactory(cellData -> cellData.getValue().topics);

        TableColumn<Lesson, String> preparationColumn = new TableColumn<>("Ön Hazırlık");
        preparationColumn.setCellValueFactory(cellData -> cellData.getValue().preparation);

        tableView.getColumns().addAll(weekColumn, topicsColumn, preparationColumn);

        weekColumn.prefWidthProperty().bind(tableView.widthProperty().divide(3));
        topicsColumn.prefWidthProperty().bind(tableView.widthProperty().divide(3));
        preparationColumn.prefWidthProperty().bind(tableView.widthProperty().divide(3));

        tableView.setItems(lessons);


        TableView<Activity> table = new TableView<>();
        data.addAll(
                new Activity("Katılım", 1, 30, 1,2,3,4,5,6),
                new Activity("Laboratuvar/Uygulama", 1, 30, 1,2,3,4,5,6),
                new Activity("Arazi Çalışması", 1, 30, 1,2,3,4,5,6),
                new Activity("Küçük Sınav/Stüdyo Kritiğ", 1, 30, 1,2,3,4,5,6),
                new Activity("Ödev", 1, 30, 1,2,3,4,5,6),
                new Activity("Sunum/Jüri Önünde Sunum", 1, 30, 1,2,3,4,5,6),
                new Activity("Proje", 1, 30, 1,2,3,4,5,6),
                new Activity("Seminer/Çalıştay", 1, 30, 1,2,3,4,5,6),
                new Activity("Sözlü Sınav", 1, 30, 1,2,3,4,5,6),
                new Activity("Ara Sınavı", 1, 30, 1,2,3,4,5,6),
                new Activity("Final Sınavı", 1, 30, 1,2,3,4,5,6),
                new Activity("Toplam", 3, 100, 3 ,3,4,5,6,7));

        TableColumn<Activity, String> nameCol = new TableColumn<>("Yarıyıl Aktiviteleri");
        nameCol.setCellValueFactory(cellData -> cellData.getValue().name);

        TableColumn<Activity, Number> countCol = new TableColumn<>("Sayı");
        countCol.setCellValueFactory(cellData -> cellData.getValue().count);

        TableColumn<Activity, Number> percentageCol = new TableColumn<>("Katkı Payı %");
        percentageCol.setCellValueFactory(cellData -> cellData.getValue().percentage);

        TableColumn<Activity, Number> lo1Col = new TableColumn<>("LO1");
        lo1Col.setCellValueFactory(cellData -> cellData.getValue().lo1);

        TableColumn<Activity, Number> lo2Col = new TableColumn<>("LO2");
        lo2Col.setCellValueFactory(cellData -> cellData.getValue().lo2);

        TableColumn<Activity, Number> lo3Col = new TableColumn<>("LO3");
        lo3Col.setCellValueFactory(cellData -> cellData.getValue().lo3);

        TableColumn<Activity, Number> lo4Col = new TableColumn<>("LO4");
        lo4Col.setCellValueFactory(cellData -> cellData.getValue().lo4);

        TableColumn<Activity, Number> lo5Col = new TableColumn<>("LO5");
        lo5Col.setCellValueFactory(cellData -> cellData.getValue().lo5);

        TableColumn<Activity, Number> lo6Col = new TableColumn<>("LO6");
        lo6Col.setCellValueFactory(cellData -> cellData.getValue().lo6);

        table.getColumns().addAll(nameCol, countCol, percentageCol, lo1Col,lo2Col,lo3Col,lo4Col,lo5Col,lo6Col);
        table.setItems(data);

        nameCol.prefWidthProperty().bind(tableView.widthProperty().divide(9));
        countCol.prefWidthProperty().bind(tableView.widthProperty().divide(9));
        percentageCol.prefWidthProperty().bind(tableView.widthProperty().divide(9));
        lo1Col.prefWidthProperty().bind(tableView.widthProperty().divide(9));
        lo2Col.prefWidthProperty().bind(tableView.widthProperty().divide(9));
        lo3Col.prefWidthProperty().bind(tableView.widthProperty().divide(9));
        lo4Col.prefWidthProperty().bind(tableView.widthProperty().divide(9));
        lo5Col.prefWidthProperty().bind(tableView.widthProperty().divide(9));
        lo6Col.prefWidthProperty().bind(tableView.widthProperty().divide(9));

        TableView<workLoad> table1 = new TableView<>();
        data1.addAll(new workLoad("Sınıf Dışı Ders Çalışması",14,2,18),
                new workLoad("Sınıf Dışı Ders Çalışması",14,2,18),
                new workLoad("Arazi Çalışması",14,2,18),
                new workLoad("Küçük Sınav/Stüdyo Kritiği",14,2,18),
                new workLoad("Ödev",14,2,18),
                new workLoad("Sunum/Jüri Önünde Sunum",14,2,18),
                new workLoad("Proje",14,2,18),
                new workLoad("Seminer/Çalıştay",14,2,18),
                new workLoad("Sözlü Sınav",14,2,18),
                new workLoad("Ara Sınav",14,2,18),
                new workLoad("Final Sınavı",14,2,18),
                new workLoad("toplam",14,2,18)

        );
        TableColumn<workLoad, String> activityCol = new TableColumn<>("Yarıyıl Aktiviteleri");
        activityCol.setCellValueFactory(cellData -> cellData.getValue().activity);

        TableColumn<workLoad, Number> countCol1 = new TableColumn<>("Sayı");
        countCol1.setCellValueFactory(cellData -> cellData.getValue().count);

        TableColumn<workLoad, Number>hourCol = new TableColumn<>("Süre (Saat) ");
        hourCol.setCellValueFactory(cellData -> cellData.getValue().hour);

        TableColumn<workLoad, Number> workloadCol= new TableColumn<>("İş yükü");
        workloadCol.setCellValueFactory(cellData -> cellData.getValue().workloud);

        table1.getColumns().addAll(activityCol, countCol1,hourCol,workloadCol);
        table1.setItems(data1);
        activityCol.prefWidthProperty().bind(table1.widthProperty().divide(4));
        countCol1.prefWidthProperty().bind(table1.widthProperty().divide(4));
        hourCol.prefWidthProperty().bind(table1.widthProperty().divide(4));
        workloadCol.prefWidthProperty().bind(table1.widthProperty().divide(4));


        TableView<Competency> table12 = new TableView<>();

        TableColumn<Competency, String> descColumn = new TableColumn<>("Program Yeterlilikleri/Çıktıları");
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

        VBox vBox = new VBox(gridPane,tableView,gridPane1,table,vBox1,table1,vBox2,table12,submitButton);

        int numberOfLines = 3;

        for (int i = 0; i < numberOfLines; i++) {

            Separator separator = new Separator();
            separator.setHalignment(HPos.CENTER);

            gridPane.add(separator, 0, 10 + i, 2, 2);}

        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        submitButton.setOnAction(event -> {
                    if (courseNameTextField.getText().isEmpty() || termChoiceBox.getValue() == null ||
                            typeToggleGroup.getSelectedToggle() == null || levelToggleGroup.getSelectedToggle() == null ||
                            deliveryToggleGroup.getSelectedToggle() == null) {

                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText(null);
                        alert.setTitle("Uyarı");
                        alert.setContentText("Lütfen tüm alanları doldurun!");
                        alert.showAndWait();
                    } else {
                        showInputDialog();

                        RadioButton selectedLanguage = (RadioButton) languageToggleGroup.getSelectedToggle();
                        RadioButton selectedType = (RadioButton) typeToggleGroup.getSelectedToggle();
                        RadioButton selectedLevel = (RadioButton) levelToggleGroup.getSelectedToggle();
                        RadioButton selectedDelivery = (RadioButton) deliveryToggleGroup.getSelectedToggle();

                        Lecture lecture = new Lecture(courseNameTextField.getText(), selectedLanguage.getText(), selectedType.getText(),
                                selectedLevel.getText(), selectedDelivery.getText(), ((RadioButton) typeToggleGroup.getSelectedToggle()).getText(), courseCodeTextField.getText(),
                                termChoiceBox.getValue(), theoryHoursTextField.getText(), applicationHoursTextField.getText(),
                                localCreditTextField.getText(), ectsTextField.getText(), preTextField.getText(), teachingMethodsTextArea.getText(),
                                coordinatorTextField.getText(), instructionalStaffTextField.getText(), assistantsTextField.getText());
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
                        String path= courseCodePath;
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
                            SyllabusVersioning versioning = new SyllabusVersioning(changedBy,changeReason,changeDate,lecture);
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
                }

        );
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
        Scene scene = new Scene(new StackPane(scrollPane), 1200, 800);
        stage.setScene(scene);
        stage.show();
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

        inputGridPane.add(confirmButton, 1, 4);

        Scene inputScene = new Scene(inputGridPane, 400, 200);
        inputStage.setScene(inputScene);


        inputStage.initModality(Modality.APPLICATION_MODAL);
        inputStage.initStyle(StageStyle.UNDECORATED);

        inputStage.showAndWait();
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
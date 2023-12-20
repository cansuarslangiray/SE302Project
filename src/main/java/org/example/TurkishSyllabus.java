
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.util.converter.IntegerStringConverter;

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

    private static class EnterFriendlyTextFieldTableCell<S, T> extends TextFieldTableCell<S, T> {
        public EnterFriendlyTextFieldTableCell() {
            super();
            setOnKeyPressed(event -> {
                if (event.getCode().equals(javafx.scene.input.KeyCode.ENTER)) {
                    commitEdit(getItem());
                    TablePosition<S, ?> position = getTableView().getFocusModel().getFocusedCell();
                    getTableView().getSelectionModel().selectBelowCell();
                    getTableView().edit(position.getRow() + 1, position.getTableColumn());
                    event.consume();
                }
            });
        }
    }

    private final ObservableList<Lesson> lessons = FXCollections.observableArrayList(
            new Lesson("1", "", ""),
            new Lesson("2", "", ""),
            new Lesson("3", "", ""),
            new Lesson("4", "", ""),
            new Lesson("5","",""),
            new Lesson("6","",""),
            new Lesson("7","",""),
            new Lesson("8","",""),
            new Lesson("9","",""),
            new Lesson("10","",""),
            new Lesson("11","",""),
            new Lesson("12","",""),
            new Lesson("13","",""),
            new Lesson("14","",""),
            new Lesson("15","",""),
            new Lesson("16","","")

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
        tableView.setEditable(true);

        TableColumn<Lesson, String> weekColumn = new TableColumn<>("Hafta");
        weekColumn.setCellValueFactory(cellData -> cellData.getValue().week);
        weekColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        weekColumn.setOnEditCommit(event -> {
            Lesson lesson = event.getRowValue();
            lesson.setWeek(event.getNewValue());
        });

        TableColumn<Lesson, String> topicsColumn = new TableColumn<>("Konular");
        topicsColumn.setCellValueFactory(cellData -> cellData.getValue().topics);
        topicsColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        topicsColumn.setOnEditCommit(event -> {
            Lesson lesson = event.getRowValue();
            lesson.setTopics(event.getNewValue());
        });

        TableColumn<Lesson, String> preparationColumn = new TableColumn<>("Ön Hazırlık");
        preparationColumn.setCellValueFactory(cellData -> cellData.getValue().preparation);
        preparationColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        preparationColumn.setOnEditCommit(event -> {
            Lesson lesson = event.getRowValue();
            lesson.setPreparation(event.getNewValue());
        });

        tableView.getColumns().addAll(weekColumn, topicsColumn, preparationColumn);
        tableView.setItems(lessons);

        weekColumn.prefWidthProperty().bind(tableView.widthProperty().divide(3));
        topicsColumn.prefWidthProperty().bind(tableView.widthProperty().divide(3));
        preparationColumn.prefWidthProperty().bind(tableView.widthProperty().divide(3));


        TableView<Activity> table = new TableView<>();
        table.setEditable(true);

        TableColumn<Activity, String> nameCol = new TableColumn<>("Yarıyıl Aktiviteleri");
        nameCol.setCellValueFactory(cellData -> cellData.getValue().name);
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());


        TableColumn<Activity, Integer> countCol = new TableColumn<>("Sayı");
        countCol.setCellValueFactory(cellData -> cellData.getValue().countProperty().asObject());
        countCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        countCol.setOnEditCommit(event -> event.getRowValue().setCount(event.getNewValue()));


        TableColumn<Activity, Integer> percentageCol = new TableColumn<>("Katkı Payı %");
        percentageCol.setCellValueFactory(cellData -> cellData.getValue().percentage.asObject());
        percentageCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        percentageCol.setOnEditCommit(event -> event.getRowValue().setLo5(event.getNewValue()));

        TableColumn<Activity, Integer> lo1Col = new TableColumn<>("LO1");
        lo1Col.setCellValueFactory(cellData -> cellData.getValue().lo1.asObject());
        lo1Col.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        lo1Col.setOnEditCommit(event -> event.getRowValue().setLo5(event.getNewValue()));

        TableColumn<Activity, Integer> lo2Col = new TableColumn<>("LO2");
        lo2Col.setCellValueFactory(cellData -> cellData.getValue().lo2.asObject());
        lo2Col.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        lo2Col.setOnEditCommit(event -> event.getRowValue().setLo5(event.getNewValue()));


        TableColumn<Activity, Integer> lo3Col = new TableColumn<>("LO3");
        lo3Col.setCellValueFactory(cellData -> cellData.getValue().lo3.asObject());
        lo3Col.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        lo3Col.setOnEditCommit(event -> event.getRowValue().setLo5(event.getNewValue()));

        TableColumn<Activity, Integer> lo4Col = new TableColumn<>("LO4");
        lo4Col.setCellValueFactory(cellData -> cellData.getValue().lo4.asObject());
        lo4Col.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        lo4Col.setOnEditCommit(event -> event.getRowValue().setLo5(event.getNewValue()));

        TableColumn<Activity, Integer> lo5Col = new TableColumn<>("LO5");
        lo5Col.setCellValueFactory(cellData -> cellData.getValue().lo5.asObject());
        lo5Col.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        lo5Col.setOnEditCommit(event -> event.getRowValue().setLo5(event.getNewValue()));

        TableColumn<Activity, Integer> lo6Col = new TableColumn<>("LO6");
        lo6Col.setCellValueFactory(cellData -> cellData.getValue().lo6.asObject());
        lo6Col.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        lo6Col.setOnEditCommit(event -> event.getRowValue().setLo5(event.getNewValue()));

        table.getColumns().addAll(nameCol, countCol, percentageCol, lo1Col, lo2Col, lo3Col, lo4Col, lo5Col, lo6Col);
        table.setItems(data);

        data.addAll(
                new Activity("Katılım",0,0,0,0,0,0,0,0 ),
                new Activity("Laboratuvar/Uygulama",0,0,0,0,0,0,0,0 ),
                new Activity("Arazi Çalışması", 0,0,0,0,0,0,0,0),
                new Activity("Küçük Sınav/Stüdyo Kritiğ", 0,0,0,0,0,0,0,0),
                new Activity("Ödev",  0,0,0,0,0,0,0,0),
                new Activity("Sunum/Jüri Önünde Sunum", 0,0,0,0,0,0,0,0),
                new Activity("Proje", 0,0,0,0,0,0,0,0),
                new Activity("Seminer/Çalıştay", 0,0,0,0,0,0,0,0),
                new Activity("Sözlü Sınav", 0,0,0,0,0,0,0,0),
                new Activity("Ara Sınavı",0,0,0,0,0,0,0,0),
                new Activity("Final Sınavı",0,0,0,0,0,0,0,0),
                new Activity("Toplam", 0,0,0,0,0,0,0,0));




        TableView<workLoad> table1 = new TableView<>();
        table1.setEditable(true);

        data1.addAll(new workLoad("Sınıf Dışı Ders Çalışması",0,0,0),
                new workLoad("Sınıf Dışı Ders Çalışması",0,0,0),
                new workLoad("Arazi Çalışması",0,0,0),
                new workLoad("Küçük Sınav/Stüdyo Kritiği",0,0,0),
                new workLoad("Ödev",0,0,0),
                new workLoad("Sunum/Jüri Önünde Sunum",0,0,0),
                new workLoad("Proje",0,0,0),
                new workLoad("Seminer/Çalıştay",0,0,0),
                new workLoad("Sözlü Sınav",0,0,0),
                new workLoad("Ara Sınav",0,0,0),
                new workLoad("Final Sınavı",0,0,0),
                new workLoad("toplam",0,0,0)

        );
        TableColumn<workLoad, String> activityCol = new TableColumn<>("Yarıyıl Aktiviteleri");
        activityCol.setCellValueFactory(cellData -> cellData.getValue().activity);
        activityCol.setCellFactory(TextFieldTableCell.forTableColumn());


        TableColumn<workLoad,Integer> countCol1 = new TableColumn<>("Sayı");
        countCol1.setCellValueFactory(cellData -> cellData.getValue().count.asObject());
        countCol1.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        countCol1.setOnEditCommit(event -> event.getRowValue().setCount(event.getNewValue()));



        TableColumn<workLoad, Integer>hourCol = new TableColumn<>("Süre (Saat) ");
        hourCol.setCellValueFactory(cellData -> cellData.getValue().hour.asObject());
        hourCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        hourCol.setOnEditCommit(event -> event.getRowValue().setHour(event.getNewValue()));


        TableColumn<workLoad, Integer> workloadCol= new TableColumn<>("İş yükü");
        workloadCol.setCellValueFactory(cellData -> cellData.getValue().workloud.asObject());
        workloadCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        workloadCol.setOnEditCommit(event -> event.getRowValue().setWorkloud(event.getNewValue()));


        table1.getColumns().addAll(activityCol, countCol1,hourCol,workloadCol);
        table1.setItems(data1);
        activityCol.prefWidthProperty().bind(table1.widthProperty().divide(4));
        countCol1.prefWidthProperty().bind(table1.widthProperty().divide(4));
        hourCol.prefWidthProperty().bind(table1.widthProperty().divide(4));
        workloadCol.prefWidthProperty().bind(table1.widthProperty().divide(4));


        TableView<Competency> table12 = new TableView<>();

        TableColumn<Competency, String> descColumn = new TableColumn<>("Program Yeterlilikleri/Çıktıları");
        descColumn.setCellValueFactory(cellData -> cellData.getValue().description);

        TableColumn<Competency,Integer> level1Column = new TableColumn<>("1");
        level1Column.setCellValueFactory(cellData -> cellData.getValue().level1.asObject());
        level1Column.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        level1Column.setOnEditCommit(event -> event.getRowValue().setLevel1(event.getNewValue()));

        TableColumn<Competency,Integer> level2Column = new TableColumn<>("2");
        level2Column.setCellValueFactory(cellData -> cellData.getValue().level2.asObject());
        level2Column.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        level2Column.setOnEditCommit(event -> event.getRowValue().setLevel2(event.getNewValue()));

        TableColumn<Competency,Integer> level3Column = new TableColumn<>("3");
        level3Column.setCellValueFactory(cellData -> cellData.getValue().level3.asObject());
        level3Column.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        level3Column.setOnEditCommit(event -> event.getRowValue().setLevel3(event.getNewValue()));

        TableColumn<Competency,Integer> level4Column = new TableColumn<>("4");
        level4Column.setCellValueFactory(cellData -> cellData.getValue().level4.asObject());
        level4Column.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        level4Column.setOnEditCommit(event -> event.getRowValue().setLevel4(event.getNewValue()));

        TableColumn<Competency,Integer> level5Column = new TableColumn<>("5");
        level5Column.setCellValueFactory(cellData -> cellData.getValue().level5.asObject());
        level5Column.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        level5Column.setOnEditCommit(event -> event.getRowValue().setLevel5(event.getNewValue()));


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
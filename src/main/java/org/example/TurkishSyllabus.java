
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.*;
import javafx.util.converter.IntegerStringConverter;

import javax.swing.filechooser.FileSystemView;
import java.awt.geom.QuadCurve2D;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class TurkishSyllabus extends Application {
    Lecture lecture;
    boolean isSaved;
    public TurkishSyllabus(Lecture lecture, boolean isSaved) {
        this.lecture = lecture;
        this.isSaved = isSaved;
    }
    String changedBy;
    String changeReason ;
    String changeDate ;

    List<String> selectedCategories = new ArrayList<>();
    private List<Activity> activities =new ArrayList<>();
    private List<workLoad> workLoads = new ArrayList<>();
    private List<Competency> competencies = new ArrayList<>();
    private final List<Lesson> lessons = new ArrayList<>();
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
        gridPane.add(new Label("Dersin Kategoris"), 0, 18);

        CheckBox coreCourseCheckBox = new CheckBox("Temel Ders");
        CheckBox majorAreaCourseCheckBox = new CheckBox("Uzmanlık/Alan Dersi");
        CheckBox supportiveCourseCheckBox = new CheckBox("Destek Dersi");
        CheckBox communicationCourseCheckBox = new CheckBox("İletişim ve Yönetim Becerileri Dersi");
        CheckBox transferableSkillCheckBox = new CheckBox("Aktarılabilir Beceri Dersi");

        VBox categoryBox = new VBox(10, coreCourseCheckBox, majorAreaCourseCheckBox, supportiveCourseCheckBox, communicationCourseCheckBox, transferableSkillCheckBox);
        gridPane.add(categoryBox, 1, 18);

        coreCourseCheckBox.setOnAction(event -> handleCheckBoxSelection(coreCourseCheckBox, selectedCategories));
        majorAreaCourseCheckBox.setOnAction(event -> handleCheckBoxSelection(majorAreaCourseCheckBox, selectedCategories));
        supportiveCourseCheckBox.setOnAction(event -> handleCheckBoxSelection(supportiveCourseCheckBox, selectedCategories));
        communicationCourseCheckBox.setOnAction(event -> handleCheckBoxSelection(communicationCourseCheckBox, selectedCategories));
        transferableSkillCheckBox.setOnAction(event -> handleCheckBoxSelection(transferableSkillCheckBox, selectedCategories));
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
                    preparation.setText("Ön Hazırlık");
                    preparation.setStyle("-fx-font-family: Arial;");
                    gridPane2.add(preparation, col, row);
                } else if (col == 2 && row == 0) {
                    topics = new TextField();
                    topics.setEditable(false);
                    topics.setText("Konular");
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
                    week.setEditable(true);
                }
            }
        }

        GridPane gridPane3 = new GridPane();
        gridPane.setHgap(0);
        gridPane.setVgap(0);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

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
                    textField.setText("Yarıyıl Aktiviteleri");
                } else if (col == 1 && row == 0) {
                    textField.setText("Sayı");
                } else if (col == 2 && row == 0) {
                    textField.setText("Katkı Payı %");
                } else if (col == 3 && row == 0) {
                    textField.setText("LO1");
                } else if (col == 4 && row == 0) {
                    textField.setText("LO2");
                } else if (col == 5 && row == 0) {
                    textField.setText("LO3");
                } else if (col == 6 && row == 0) {
                    textField.setText("LO4");
                } else if (col == 0 && row == 1) {
                    textField.setText("Katılım\n");
                    activitiesTextField.add(textField);
                } else if (col == 0 && row == 2) {
                    textField.setText("Laboratuvar/Uygulama");
                    activitiesTextField.add(textField);
                } else if (col == 0 && row == 3) {
                    textField.setText("Arazi Çalışması");
                    activitiesTextField.add(textField);
                } else if (col == 0 && row == 4) {
                    textField.setText("Küçük Sınav/Stüdyo Kritiği\n");
                    activitiesTextField.add(textField);
                } else if (col == 0 && row == 5) {
                    textField.setText("Ödev");
                    activitiesTextField.add(textField);
                } else if (col == 0 && row == 6) {
                    textField.setText("Sunum/Jüri Önünde Sunum");
                    activitiesTextField.add(textField);
                } else if (col == 0 && row == 7) {
                    textField.setText("Portfolyo");
                    activitiesTextField.add(textField);
                } else if (col == 0 && row == 8) {
                    textField.setText("Proje");
                    activitiesTextField.add(textField);
                } else if (col == 0 && row == 9) {
                    textField.setText("Seminer/Çalıştay");
                    activitiesTextField.add(textField);
                } else if (col == 0 && row == 10) {
                    textField.setText("Sözlü Sınav");
                    activitiesTextField.add(textField);
                } else if (col == 0 && row == 11) {
                    textField.setText("Ara Sınavı");
                    activitiesTextField.add(textField);
                } else if (col == 0 && row == 12) {
                    textField.setText("Final Sınavı\n");
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

        int rowCount1 = 15;
        int colCount1 = 4;
        int narrowWidth1=5;

        GridPane gridPane4 = new GridPane();
        gridPane.setHgap(0);
        gridPane.setVgap(0);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        List<TextField> workloadTextFields = new ArrayList<>();
        for (int row = 0; row < rowCount1; row++) {
            for (int col = 0; col < colCount1; col++) {
                TextField textField2 = new TextField();
                textField2.setEditable(false);
                TextField textField3 = new TextField();
                textField3.setEditable(true);


                if (col == 0 && row == 0) {
                    textField2.setText("Yarıyıl Aktiviteleri ");
                    gridPane4.add(textField2, col, row);
                } else if (col == 1 && row == 0) {
                    textField2.setText("Sayı");
                    gridPane4.add(textField2, col, row);
                } else if (col == 2 && row == 0) {
                    textField2.setText("Süre (Saat)");
                    gridPane4.add(textField2, col, row);
                } else if (col == 3 && row == 0) {
                    textField2.setText("İş Yükü");
                    gridPane4.add(textField2, col, row);
                } else if (col == 0 && row == 1) {
                    textField2.setText("Teorik Ders Saati\n");
                    gridPane4.add(textField2, col, row);

                } else if (col == 0 && row == 2) {
                    textField2.setText("Laboratuvar /Uygulama Ders Saati");
                    gridPane4.add(textField2, col, row);
                } else if (col == 0 && row == 3) {
                    textField2.setText("Sınıf Dışı Ders Çalışması");
                    gridPane4.add(textField2, col, row);
                } else if (col == 0 && row == 4) {
                    textField2.setText("Küçük Sınav/Stüdyo Kritiği\n");
                    gridPane4.add(textField2, col, row);
                } else if (col == 0 && row == 5) {
                    textField2.setText("Ödev");
                    gridPane4.add(textField2, col, row);
                } else if (col == 0 && row == 6) {
                    textField2.setText("Sunum/Jüri Önünde Sunum");
                    gridPane4.add(textField2, col, row);
                } else if (col == 0 && row == 7) {
                    textField2.setText("Proje");
                    gridPane4.add(textField2, col, row);
                } else if (col == 0 && row == 8) {
                    textField2.setText("Portfolyo");
                    gridPane4.add(textField2, col, row);
                } else if (col == 0 && row == 9) {
                    textField2.setText("Seminer/Çalıştay");
                    gridPane4.add(textField2, col, row);
                } else if (col == 0 && row == 10) {
                    textField2.setText("Sözlü Sınav");
                    gridPane4.add(textField2, col, row);
                } else if (col == 0 && row == 11) {
                    textField2.setText("Ara Sınav");
                    gridPane4.add(textField2, col, row);
                } else if (col == 0 && row == 12) {
                    textField2.setText("Final Sınavı");
                    gridPane4.add(textField2, col, row);
                } else if (col == 0 && row == 13) {
                    textField2.setText("Total");
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
        int narrowWidth2=5;

        GridPane gridPane5 = new GridPane();
        gridPane5.setHgap(0);
        gridPane5.setVgap(0);
        gridPane5.setPadding(new Insets(10, 10, 10, 10));


        for (int row = 0; row < rowCount2; row++) {
            for (int col = 0; col < colCount2; col++) {
                TextField textField2 = new TextField();
                textField2.setEditable(true);
                textField2.setPromptText(" ");


                if (col == 0 && row == 0) {
                    textField2.setText("#");
                } else if (col == 1 && row == 0) {
                    textField2.setText("Program Competencies/Outcomes");
                } else if (col == 2 && row == 0) {
                    textField2.setText("1");}
                else if (col == 3 && row == 0) {
                    textField2.setText("2");}
                else if (col == 4 && row == 0) {
                    textField2.setText("3");}
                else if (col == 5 && row == 0) {
                    textField2.setText("4");}
                else if (col == 6 && row == 0) {
                    textField2.setText("5");}
                else {
                    textField2.setText("" );
                }

                textField2.setStyle("-fx-border-width: 1px; -fx-border-color: grey;");
                if (col >= 2 && col <= 8) {
                    textField2.setPrefColumnCount(narrowWidth2);

                }


                if (col == 0&&row>0) {
                    textField2.setText(String.valueOf(row));}
                if (col == 0 ) {
                    textField2.setPrefColumnCount(narrowWidth2);

                }if(col==1){
                    textField2.setPrefWidth(400);
                }
                if (col >= 0 && col <= 8&&row==0) {
                    textField2.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                    ;}else {
                    textField2.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                }

                textField2.setStyle("-fx-border-width: 2px; -fx-border-color: grey;");

                gridPane5.add(textField2, col, row);
            }
        }
        VBox vBox = new VBox(gridPane, gridPane2,gridPane1,gridPane3,vBox1, gridPane4,vBox2,gridPane5, submitButton);

        int numberOfLines = 3;

        for (int i = 0; i < numberOfLines; i++) {

            Separator separator = new Separator();
            separator.setHalignment(HPos.CENTER);

            gridPane.add(separator, 0, 10 + i, 2, 2);}

        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        /*if (isSaved) {
            courseNameTextField.setText(lecture.courseName);
            courseCodeTextField.setText(lecture.courseCode);
            if (lecture.term.equals("Güz")){
                termChoiceBox.setValue("Güz");
            }
            else {
                termChoiceBox.setValue("Bahar");
            }
            theoryHoursTextField.setText(lecture.theoryHours);
            applicationHoursTextField.setText(lecture.applicationHours);
            localCreditTextField.setText(lecture.localCredit);
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

            if (lecture.deliveryMode.equals("Yüz Yüze")){
                deliveryToggleGroup.selectToggle(deliveryFaceToFaceRadioButton);
            }
            else {
                deliveryToggleGroup.selectToggle(deliveryOnlineRadioButton);
            }
            teachingMethodsTextArea.setText(lecture.teachingMethods);
            coordinatorTextField.setText(lecture.coordinator);
            instructionalStaffTextField.setText(lecture.lecturer);
            assistantsTextField.setText(lecture.assistants);
            String lectureCategory = lecture.courseCategory;

            for(int j=0;j<lecture.weeklySubject.size();j++){
                lessons.get(j).setWeek(lecture.weeklySubject.get(j).getWeek());
                lessons.get(j).setTopics(lecture.weeklySubject.get(j).getTopics());
                lessons.get(j).setPreparation(lecture.weeklySubject.get(j).getPreparation());

            }
            Book.setText(lecture.book);
            Material.setText(lecture.materials);
            for(int j=0;j<lecture.assessmentTable.size();j++){
                data.get(j).setCount(lecture.assessmentTable.get(j).count);
                data.get(j).setLo1(lecture.assessmentTable.get(j).lo1);
                data.get(j).setLo2(lecture.assessmentTable.get(j).lo2);
                data.get(j).setLo3(lecture.assessmentTable.get(j).lo3);
                data.get(j).setLo4(lecture.assessmentTable.get(j).lo4);
                data.get(j).setLo5(lecture.assessmentTable.get(j).lo5);
                data.get(j).setLo6(lecture.assessmentTable.get(j).lo6);
                data.get(j).setPercentage(lecture.assessmentTable.get(j).percentage);
                data.get(j).setName(lecture.assessmentTable.get(j).getName());

            }
            for(int j=0;j<lecture.workloadTable.size();j++){
                data1.add(lecture.workloadTable.get(j));
            }
            for(int j=0;j<lecture.outcomeTable.size();j++){
                competencies.add(lecture.outcomeTable.get(j));
            }



        }*/ submitButton.setOnAction(event -> {
                    if (courseNameTextField.getText().isEmpty() || courseCodeTextField.getText().isEmpty() || termChoiceBox.getValue() == null || theoryHoursTextField.getText().isEmpty() ||
                            applicationHoursTextField.getText().isEmpty() || localCreditTextField.getText().isEmpty() || ectsTextField.getText().isEmpty() || preTextField.getText().isEmpty() ||
                            languageToggleGroup.getSelectedToggle() == null ||
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
                        for (int j = 0; j < lessonsTextField.size() - 3; j += 3) {
                            lessons.add(new Lesson(
                                    lessonsTextField.get(j).getText(),
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
                                alert.showAndWait();

                            }
                        }


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
                        // }
                    }
                }

        );
        Scene scene = new Scene(new StackPane(scrollPane), 1200, 800);
        stage.setScene(scene);
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

        inputGridPane.add(confirmButton, 1, 4);

        Scene inputScene = new Scene(inputGridPane, 400, 200);
        inputStage.setScene(inputScene);


        inputStage.initModality(Modality.APPLICATION_MODAL);
        inputStage.initStyle(StageStyle.UNDECORATED);

        inputStage.showAndWait();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
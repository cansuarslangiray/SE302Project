package org.example;
import com.google.gson.Gson;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Syllabus");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        gridPane.add(new Label("Dersin Adı:"), 0, 0);
        TextField courseNameTextField = new TextField();
        gridPane.add(courseNameTextField, 1, 0);

        gridPane.add(new Label("Dersin kodu"), 0, 1);
        TextField courseCodeTextField = new TextField();
        gridPane.add(courseCodeTextField, 1, 1);

        gridPane.add(new Label("Dönem:"), 0, 2);
        ChoiceBox<String> termChoiceBox = new ChoiceBox<>();
        termChoiceBox.getItems().addAll("Güz", "Bahar");
        gridPane.add(termChoiceBox, 1, 2);

        gridPane.add(new Label("Teori (saat/hafta):"), 0, 3);
        TextField theoryHoursTextField = new TextField();
        gridPane.add(theoryHoursTextField, 1, 3);

        gridPane.add(new Label("Uygulama/Lab (saat/hafta):"), 0, 4);
        TextField applicationHoursTextField = new TextField();
        gridPane.add(applicationHoursTextField, 1, 4);

        gridPane.add(new Label("Yerel Kredi:"), 0, 5);
        TextField localCreditTextField = new TextField();
        gridPane.add(localCreditTextField, 1, 5);

        gridPane.add(new Label("AKTS:"), 0, 6);
        TextField ectsTextField = new TextField();
        gridPane.add(ectsTextField, 1, 6);

        gridPane.add(new Label("on kosullar:"), 0, 7);
        TextField preTextField = new TextField();
        gridPane.add(preTextField, 1, 7);

        gridPane.add(new Label("Dersin Dili:"), 0, 8);
        ToggleGroup languageToggleGroup = new ToggleGroup();

        RadioButton languageTurkishRadioButton = new RadioButton("Türkçe");
        languageTurkishRadioButton.setToggleGroup(languageToggleGroup);

        RadioButton languageEnglishRadioButton = new RadioButton("İngilizce");
        languageEnglishRadioButton.setToggleGroup(languageToggleGroup);

        RadioButton languageOtherRadioButton = new RadioButton("İkinci Yabancı Dil");
        languageOtherRadioButton.setToggleGroup(languageToggleGroup);

        gridPane.add(languageTurkishRadioButton, 1, 9);
        gridPane.add(languageEnglishRadioButton, 1, 10);
        gridPane.add(languageOtherRadioButton, 1, 11);

        gridPane.add(new Label("Dersin Türü:"), 0, 12);
        ToggleGroup typeToggleGroup = new ToggleGroup();

        RadioButton typeMandatoryRadioButton = new RadioButton("Zorunlu");
        typeMandatoryRadioButton.setToggleGroup(typeToggleGroup);

        RadioButton typeElectiveRadioButton = new RadioButton("Seçmeli");
        typeElectiveRadioButton.setToggleGroup(typeToggleGroup);

        gridPane.add(typeMandatoryRadioButton, 1, 13);
        gridPane.add(typeElectiveRadioButton, 1, 14);

        gridPane.add(new Label("Dersin Düzeyi:"), 0, 15);
        ToggleGroup levelToggleGroup = new ToggleGroup();

        RadioButton levelUndergraduateRadioButton = new RadioButton("Ön Lisans");
        levelUndergraduateRadioButton.setToggleGroup(levelToggleGroup);

        RadioButton levelGraduateRadioButton = new RadioButton("Lisans");
        levelGraduateRadioButton.setToggleGroup(levelToggleGroup);

        gridPane.add(levelUndergraduateRadioButton, 1, 15);
        gridPane.add(levelGraduateRadioButton, 1, 16);

        gridPane.add(new Label("Dersin Veriliş Şekli:"), 0, 17);
        ToggleGroup deliveryToggleGroup = new ToggleGroup();

        RadioButton deliveryFaceToFaceRadioButton = new RadioButton("Yüz yüze");
        deliveryFaceToFaceRadioButton.setToggleGroup(deliveryToggleGroup);

        RadioButton deliveryOnlineRadioButton = new RadioButton("Çevrim içi");
        deliveryOnlineRadioButton.setToggleGroup(deliveryToggleGroup);

        gridPane.add(deliveryFaceToFaceRadioButton, 1, 17);
        gridPane.add(deliveryOnlineRadioButton, 1, 18);

        gridPane.add(new Label("Dersin Öğretim Yöntem ve Teknikleri:"), 0, 19);
        TextArea teachingMethodsTextArea = new TextArea();
        gridPane.add(teachingMethodsTextArea, 1, 19);

        gridPane.add(new Label("Ders Koordinatörü:"), 0, 20);
        TextField coordinatorTextField = new TextField();
        gridPane.add(coordinatorTextField, 1, 20);

        gridPane.add(new Label("Öğretim Eleman(lar)ı:"), 0, 21);
        TextField instructionalStaffTextField = new TextField();
        gridPane.add(instructionalStaffTextField, 1, 21);

        gridPane.add(new Label("Yardımcı(lar)ı:"), 0, 22);
        TextField assistantsTextField = new TextField();
        gridPane.add(assistantsTextField, 1, 22);
        Button submitButton = new Button("Gönder");
        gridPane.add(submitButton, 0, 23, 2, 1);
        submitButton.setOnAction(event -> {
            System.out.println("Course Name: " + courseNameTextField.getText());
            System.out.println("Term: " + termChoiceBox.getValue());

            RadioButton selectedLanguage = (RadioButton) languageToggleGroup.getSelectedToggle();
            System.out.println("Selected Language: " + selectedLanguage.getText());

            RadioButton selectedType = (RadioButton) typeToggleGroup.getSelectedToggle();
            System.out.println("Selected Type: " + selectedType.getText());

            RadioButton selectedLevel = (RadioButton) levelToggleGroup.getSelectedToggle();
            System.out.println("Selected Level: " + selectedLevel.getText());

            RadioButton selectedDelivery = (RadioButton) deliveryToggleGroup.getSelectedToggle();
            System.out.println("Selected Delivery: " + selectedDelivery.getText());
            Lecture lecture = new Lecture(courseNameTextField.getText(),selectedLanguage.getText(),selectedType.getText(),
                    selectedLevel.getText(),selectedDelivery.getText(),((RadioButton) typeToggleGroup.getSelectedToggle()).getText(),courseCodeTextField.getText()
                    ,termChoiceBox.getValue(),theoryHoursTextField.getText(),applicationHoursTextField.getText()
                    ,localCreditTextField.getText(),ectsTextField.getText(),preTextField.getText(),teachingMethodsTextArea.getText(),
                    coordinatorTextField.getText(),instructionalStaffTextField.getText(),assistantsTextField.getText());
            Gson gson = new Gson();
            String jsonString = gson.toJson(lecture);
            String filePath = "SE302Project/file.json";
            try {
                File file = new File(filePath);
                file.getParentFile().mkdirs();

                try (FileWriter writer = new FileWriter(file)) {
                    writer.write(jsonString);
                    System.out.println("Successfully wrote JSON to file: " + filePath);
                }
            } catch (IOException e) {
                e.printStackTrace();
}
        });
        Scene scene = new Scene(gridPane, 500, 900);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}


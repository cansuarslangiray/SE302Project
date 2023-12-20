package org.example;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.Optional;

public class FirstScene extends Application {
    private Stage primaryStage;
    private void showHelpScene() {
        Stage helpStage = new Stage();
        VBox helpVBox = new VBox(10);
        helpVBox.setAlignment(Pos.CENTER);
        helpVBox.setPadding(new Insets(20));

        Label helpLabel = new Label("Bu ekranda yardım talimatları bulunacak.");
        helpLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        helpVBox.getChildren().addAll(helpLabel);

        Scene helpScene = new Scene(helpVBox, 400, 200);

        helpStage.setScene(helpScene);
        helpStage.setTitle("HELP");
        helpStage.show();
    }




    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Syllabus");


        BorderPane borderPane = new BorderPane();


        MenuBar menuBar = new MenuBar();


        Menu fileMenu = new Menu("File");
        Menu helpMenu = new Menu("HELP");
        MenuItem help=new MenuItem("HELP");

        help.setOnAction(e->showHelpScene());


        MenuItem openItem = new MenuItem("Open");
        MenuItem CompareItem = new MenuItem("Compare");
        MenuItem editItem = new MenuItem("Edit");
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e -> primaryStage.close());
        openItem.setOnAction(e -> showLanguageSelectionPopup());





        fileMenu.getItems().addAll(openItem, CompareItem, editItem, new SeparatorMenuItem(), exitItem);
        helpMenu.getItems().addAll(help);


        menuBar.getMenus().addAll(fileMenu, helpMenu);


        Button turkishButton = createButton("ADD First Version Of TURKISH SYLLABUS");
        Button englishButton = createButton("Add First Version OF ENGLISH SYLLABUS");
        Button listVersions = createButton("Version Of SYLLABUS");


        turkishButton.setOnAction(e -> {
            TurkishSyllabus app = new TurkishSyllabus(null, false);
            app.start(primaryStage);
        });

       englishButton.setOnAction(e -> {
            EnglishSyllabus englishSyllabus = new EnglishSyllabus(null, false);
            englishSyllabus.start(primaryStage);
        });

        listVersions.setOnAction(e -> {
            ListVersion listVersion1 = new ListVersion();
            listVersion1.start(primaryStage);
        });


        VBox buttonsVBox = new VBox(10);
        buttonsVBox.setAlignment(Pos.CENTER);
        buttonsVBox.getChildren().addAll(turkishButton, englishButton, listVersions);


        borderPane.setTop(menuBar);
        borderPane.setCenter(buttonsVBox);


        BackgroundFill backgroundFill = new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        borderPane.setBackground(background);


        Scene scene1 = new Scene(borderPane, 1200, 800);
        primaryStage.setScene(scene1);
        primaryStage.show();
    }


    private Button createButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-font-weight: bold;");
        button.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        button.setStyle("-fx-border-width: 2px; -fx-border-radius: 5px;");
        button.setPrefSize(350, 50);
        return button;
    }
    

    private void showLanguageSelectionPopup() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Syllabus Selection");
        alert.setHeaderText("Select Your Preferred Syllabus language");

        ButtonType englishButton = new ButtonType("English Syllabus");
        ButtonType turkishButton = new ButtonType("Turkish Syllabus");

        alert.getButtonTypes().setAll(englishButton, turkishButton);
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.setOnCloseRequest(e->{
            alertStage.close();
        });

        Optional<ButtonType> result = alert.showAndWait();


        result.ifPresent(selectedButton -> {
            if (selectedButton == englishButton) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Select JSON File");
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));
                File selectedFile = fileChooser.showOpenDialog(new Stage());
                if (selectedFile != null) {
                    try (Reader reader = new FileReader(selectedFile)) {
                        Gson gson = new Gson();
                        Lecture lecture = gson.fromJson(reader, Lecture.class);
                        EnglishSyllabus englishSyllabus = new EnglishSyllabus(lecture, true);
                        englishSyllabus.start(primaryStage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            } else if (selectedButton == turkishButton) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Select JSON File");
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));
                File selectedFile = fileChooser.showOpenDialog(new Stage());
                if (selectedFile != null) {
                    try (Reader reader = new FileReader(selectedFile)) {
                        Gson gson = new Gson();
                        Lecture lecture = gson.fromJson(reader, Lecture.class);
                        TurkishSyllabus app = new TurkishSyllabus(lecture, true);
                        app.start(primaryStage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}

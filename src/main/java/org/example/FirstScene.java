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
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
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
        TextFlow textFlow = new TextFlow();

        Text head=new Text("Welcome to Version Control System\n");
        head.setFont(Font.font("Arial",FontWeight.BOLD,18));

        Text feature1 = new Text("1. You can press Add First Version of Turkish Syllabus to add Turkish Syllabus.\n");
        feature1.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

        Text feature1_1 = new Text(" 1.1 When you are in the Turkish Syllabus screen, fill in the blanks in Turkish and press 'Gönder' at the end.\n");

        Text feature2 = new Text("2. You can press Add First Version of English Syllabus to add English Syllabus.\n");
        feature2.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

        Text feature2_1 = new Text(" 2.1 When you are in the English Syllabus screen, fill in the blanks in English and press 'Submit' at the end.\n");

        Text feature3 = new Text("3. You can press Version of Syllabus to see all versions you committed.\n");
        feature3.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

        Text feature4 = new Text("4. You can press View Version of Syllabus to see selected version.\n");
        feature4.setFont(Font.font("Arial", FontWeight.NORMAL, 14));


        Text feature5 = new Text("5. You can click File button at the top menu to open up a new menu.\n");
        feature5.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

        Text feature5_1 = new Text(" 5.1 When you press open you can select and view any syllabus you generated.\n");

        Text feature5_2 = new Text(" 5.2 When you press compare you can select two syllabuses and see their differences.\n");

        Text feature5_3 = new Text(" 5.3 When you press edit you can select a syllabus and edit its texts.\n");

        Text feature5_4 = new Text(" 5.4 When you press exit you close the application.\n");

        textFlow.getChildren().addAll(head ,feature1, feature1_1, feature2, feature2_1, feature3, feature4,feature5, feature5_1, feature5_2, feature5_3, feature5_4);

        Label helpLabelTurkish = new Label();
        helpLabelTurkish.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        helpLabelTurkish.setWrapText(true);
        helpLabelTurkish.setGraphic(textFlow);
        helpVBox.getChildren().addAll(helpLabelTurkish);

        Scene helpScene = new Scene(helpVBox, 800, 300);

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
        fileMenu.setStyle( "-fx-padding: 5px;-fx-font-weight: bold;");
        Menu helpMenu = new Menu("HELP");
        helpMenu.setStyle( "-fx-padding: 5px;-fx-font-weight: bold;");
        MenuItem help=new MenuItem("HELP");
        help.setStyle( "-fx-padding: 5px;-fx-font-weight: bold;");

        help.setOnAction(e->showHelpScene());


        MenuItem openItem = new MenuItem("Open");
        openItem.setStyle( "-fx-padding: 3px;-fx-font-weight: bold;");
        MenuItem CompareItem = new MenuItem("Compare");
        MenuItem editItem = new MenuItem("Edit");
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e -> primaryStage.close());
        openItem.setOnAction(e -> showLanguageSelectionPopup());





        fileMenu.getItems().addAll(openItem, CompareItem, editItem, new SeparatorMenuItem(), exitItem);
        helpMenu.getItems().addAll(help);


        menuBar.getMenus().addAll(fileMenu, helpMenu);


        Button turkishButton = createButton("ADD First Version Of TURKISH SYLLABUS");
        turkishButton.setStyle( "-fx-padding: 5px;-fx-font-weight: bold;");
        Button englishButton = createButton("Add First Version OF ENGLISH SYLLABUS");
        englishButton.setStyle( "-fx-padding: 5px;-fx-font-weight: bold;");
        Button listVersions = createButton("Version Of SYLLABUS");
        listVersions.setStyle( "-fx-padding: 5px;-fx-font-weight: bold;");
        Button viewVersions = createButton("View Version Of SYLLABUS");
        viewVersions.setStyle( "-fx-padding: 5px;-fx-font-weight: bold;");

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

        viewVersions.setOnAction(e -> {
           ViewVersion viewVersion = new ViewVersion();
            viewVersion.start(primaryStage);
        });


        VBox buttonsVBox = new VBox(10);
        buttonsVBox.setAlignment(Pos.CENTER);
        buttonsVBox.getChildren().addAll(turkishButton, englishButton, listVersions,viewVersions);


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
       // Comparator.main(args);
        launch(args);
    }

}

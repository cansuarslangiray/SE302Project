package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class FirstScene extends Application{

    @Override
    public void start(Stage primaryStage) {


        Button turkishButton = new Button("ADD First Version Of TURKISH SYLLABUS");

        turkishButton.setOnAction(e ->{
            App app = new App();
            app.start(primaryStage);
        });
        turkishButton.setStyle("-fx-font-weight: bold;");
        turkishButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        turkishButton.setStyle("-fx-border-width: 2px; -fx-border-radius: 5px;");
        turkishButton.setPrefSize(350, 50);


        Button englishButton = new Button("Add First Version OF ENGLISH SYLLSBUS");


        englishButton.setOnAction(e -> {
            EnglishSyllabus englishSyllabus= new EnglishSyllabus();
            englishSyllabus.start(primaryStage);
        });
        englishButton.setStyle("-fx-font-weight: bold;");
        englishButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        englishButton.setStyle("-fx-border-width: 2px; -fx-border-radius: 5px;");
        englishButton.setPrefSize(350, 50);



        Button listVersions = new Button("Version of Syllbuses");


        listVersions.setOnAction(e -> {
            ListVersion listVersion1= new ListVersion();
            listVersion1.start(primaryStage);
        });
        listVersions.setStyle("-fx-font-weight: bold;");
        listVersions.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        listVersions.setStyle("-fx-border-width: 2px; -fx-border-radius: 5px;");
        listVersions.setPrefSize(350, 50);


        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        vbox.getChildren().addAll(turkishButton,englishButton,listVersions);
        vbox.setAlignment(Pos.CENTER);

        BackgroundFill backgroundFill = new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        vbox.setBackground(background);

        Scene scene1 = new Scene(vbox, 1200, 800);


        primaryStage.setTitle("Syllabus");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

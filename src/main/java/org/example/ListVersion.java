package org.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

import static javafx.application.Application.launch;

public class ListVersion extends Application{

    private Stage primaryStage;

    // Use a list to store instances of App or EnglishSyllabus
    private List<Application> versions;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("List of Versions");


        BorderPane borderPane = new BorderPane();


        ListView<Application> versionListView = new ListView<>();
        versionListView.setPrefSize(300, 400);


        versions = new ArrayList<>();
       // versions.add(new EnglishSyllabus());

        ObservableList<Application> observableVersions = FXCollections.observableList(versions);
        versionListView.setItems(observableVersions);

        // Create a button to go back to the main scene
        Button backButton = new Button("Back to Main Scene");
        backButton.setOnAction(e -> goBackToMainScene());

        // Create a VBox to hold the ListView and the Back button
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20));
        vBox.getChildren().addAll(versionListView, backButton);

        borderPane.setCenter(vBox);

        Scene scene = new Scene(borderPane, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void goBackToMainScene() {

        FirstScene firstScene = new FirstScene();
        firstScene.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
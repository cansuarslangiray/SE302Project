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
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Arrays;

public class ListVersion extends Application {

    private Stage primaryStage;
    
    private ObservableList<String> fileNames;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("List of Versions");

        BorderPane borderPane = new BorderPane();

        ListView<String> versionListView = new ListView<>();
        versionListView.setPrefSize(300, 400);


        Button chooseDirectoryButton = new Button("Choose Directory");
        chooseDirectoryButton.setOnAction(e -> chooseDirectory());

        fileNames = FXCollections.observableArrayList();
        versionListView.setItems(fileNames);

        Button backButton = new Button("Back to Main Scene");
        backButton.setOnAction(e -> goBackToMainScene());


        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20));
        vBox.getChildren().addAll(versionListView, chooseDirectoryButton, backButton);

        borderPane.setCenter(vBox);

        Scene scene = new Scene(borderPane, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void goBackToMainScene() {
        FirstScene firstScene = new FirstScene();
        firstScene.start(primaryStage);
    }

    private void chooseDirectory() {

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose Directory");
        File selectedDirectory = directoryChooser.showDialog(primaryStage);

        if (selectedDirectory != null) {

            File[] files = selectedDirectory.listFiles();
            if (files != null) {
                fileNames.setAll(Arrays.asList(files).stream().map(File::getName).toArray(String[]::new));
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

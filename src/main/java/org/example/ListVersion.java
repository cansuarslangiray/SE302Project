package org.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.filechooser.FileSystemView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class ListVersion extends Application {

    private Stage primaryStage;

    private ObservableList<String> fileNames;
    private TextArea fileContentTextArea;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("List of Versions");

        BorderPane borderPane = new BorderPane();

        ListView<String> versionListView = new ListView<>();
        versionListView.setPrefSize(300, 400);

        TextField courseCodeTextField = new TextField();
        courseCodeTextField.setPromptText("Enter Course Code");

        Button listButton = new Button("List Versions");
        listButton.setOnAction(e -> listVersions(courseCodeTextField.getText()));


        Button backButton = new Button("Back to Main Scene");
        backButton.setOnAction(e -> goBackToMainScene());

        HBox searchBox = new HBox(10);
        searchBox.setAlignment(Pos.CENTER);
        searchBox.getChildren().addAll(courseCodeTextField, listButton);

        fileNames = FXCollections.observableArrayList();
        versionListView.setItems(fileNames);

        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20));
        vBox.setStyle("-fx-background-color: lavender;");
        vBox.getChildren().addAll(versionListView, searchBox,  backButton);

        versionListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                displayFileContent(courseCodeTextField.getText(), newVal);
            }
        });

        borderPane.setCenter(vBox);

        Scene scene = new Scene(borderPane, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void goBackToMainScene() {
        
        FirstScene firstScene = new FirstScene();
        firstScene.start(primaryStage);
    }

    private void listVersions(String courseCode) {
        fileNames.clear();

        String directoryPath = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() +
                File.separator + "Syllabus" + File.separator + courseCode;

        File directory = new File(directoryPath);

        if (!directory.exists() || !directory.isDirectory()) {
            showErrorAlert("Directory not found", "Directory does not exist: " + directory.getAbsolutePath());
            return;
        }

        File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));

        if (files != null) {
            Arrays.stream(files).map(File::getName).forEach(fileNames::add);
        }
    }

    private void displayFileContent(String courseCode, String fileName) {
        try {

            String filePath = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() +
                    File.separator + "Syllabus" + File.separator + courseCode + File.separator + fileName;

            StringBuilder fileContent = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    fileContent.append(line).append("\n");
                }
            }

            fileContentTextArea.setText(fileContent.toString());
        } catch (IOException e) {
            showErrorAlert("Error", "Error reading file: " + e.getMessage());
        }
    }

    private void showErrorAlert(String title, String content) {

    }


    public static void main(String[] args) {
        launch(args);
    }
}

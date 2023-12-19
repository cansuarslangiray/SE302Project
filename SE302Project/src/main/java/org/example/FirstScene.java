package org.example;
/*
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
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class FirstScene extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Ana layout
        VBox mainVBox = new VBox(10);
        mainVBox.setPadding(new Insets(10));
        mainVBox.setAlignment(Pos.CENTER);

        // Butonlar
        Button turkishButton = createButton("ADD First Version Of TURKISH SYLLABUS");
        Button englishButton = createButton("Add First Version OF ENGLISH SYLLSBUS");
        Button listVersions = createButton("Version of Syllbuses");

        // Butonlara tıklama olayları
        turkishButton.setOnAction(e -> {
            App app = new App();
            app.start(primaryStage);
        });

        englishButton.setOnAction(e -> {
            EnglishSyllabus englishSyllabus = new EnglishSyllabus();
            englishSyllabus.start(primaryStage);
        });

        listVersions.setOnAction(e -> {
            ListVersion listVersion1 = new ListVersion();
            listVersion1.start(primaryStage);
        });

        // TabPane oluştur
        TabPane tabPane = new TabPane();
        Tab tab1 = new Tab("Tab 1");
        Tab tab2 = new Tab("Tab 2");
        tabPane.getTabs().addAll(tab1, tab2);

        // Ana layout'a ekle
        mainVBox.getChildren().addAll(tabPane,turkishButton, englishButton, listVersions );

        // Arka plan rengi
        BackgroundFill backgroundFill = new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        mainVBox.setBackground(background);

        // Scene oluştur ve sahneyi ayarla
        Scene scene1 = new Scene(mainVBox, 1200, 800);
        primaryStage.setTitle("Syllabus");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    // Stil ve boyutlandırmayı ayarlamak için kullanılan yardımcı metot
    private Button createButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-font-weight: bold;");
        button.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        button.setStyle("-fx-border-width: 2px; -fx-border-radius: 5px;");
        button.setPrefSize(350, 50);
        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Optional;

public class FirstScene extends Application {
    Stage primaryStage=new Stage();
    private void showLanguageSelectionPopup() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Syllabus Selection");
        alert.setHeaderText("Select Your Preferred Syllabus language");

        ButtonType englishButton = new ButtonType("English Syllabus");
        ButtonType turkishButton = new ButtonType("Turkish Syllabus");

        alert.getButtonTypes().setAll(englishButton, turkishButton);

        Optional<ButtonType> result = alert.showAndWait();

        alert.setOnCloseRequest(e -> {
           alert.close();
        });

        result.ifPresent(selectedButton -> {
            if (selectedButton == englishButton) {
                 EnglishSyllabus englishSyllabus=new EnglishSyllabus();
                englishSyllabus.start(primaryStage);



            } else if (selectedButton == turkishButton) {
                App app=new App();
                app.start(primaryStage);

            }

            alert.close();
        })
        ;}





    @Override
    public void start(Stage primaryStage) {

        // Ana layout
        BorderPane borderPane = new BorderPane();


        // Menü çubuğu oluştur
        MenuBar menuBar = new MenuBar();

        // Menüler oluştur
        Menu fileMenu = new Menu("File");
        Menu helpMenu = new Menu("Help");

        // Menü öğelerini oluştur
        MenuItem openItem = new MenuItem("Open");
        MenuItem CompareItem = new MenuItem("Compare");
        MenuItem editItem = new MenuItem("edit");
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e -> primaryStage.close());
        openItem.setOnAction(e ->{
            showLanguageSelectionPopup();

                });


        // Menü öğelerini menülere ekle
        fileMenu.getItems().addAll(openItem,CompareItem,editItem, new SeparatorMenuItem(), exitItem);


        // Menüleri menü çubuğuna ekle
        menuBar.getMenus().addAll(fileMenu, helpMenu);

        // Butonlar
        Button turkishButton = createButton("ADD First Version Of TURKISH SYLLABUS");
        Button englishButton = createButton("Add First Version OF ENGLISH SYLLSBUS");
        Button listVersions = createButton("Version of Syllbuses");

        // Butonlara tıklama olayları
        turkishButton.setOnAction(e -> {
            App app = new App();
            app.start(primaryStage);
        });

        englishButton.setOnAction(e -> {
            EnglishSyllabus englishSyllabus = new EnglishSyllabus();
            englishSyllabus.start(primaryStage);
        });

        listVersions.setOnAction(e -> {
            ListVersion listVersion1 = new ListVersion();
            listVersion1.start(primaryStage);
        });

        // VBox kullanarak düğmeleri grupla ve ortala
        VBox buttonsVBox = new VBox(10);
        buttonsVBox.setAlignment(Pos.CENTER);
        buttonsVBox.getChildren().addAll(turkishButton, englishButton, listVersions);

        // Menü çubuğunu en üstte, diğer elemanları ortada göster
        borderPane.setTop(menuBar);
        borderPane.setCenter(buttonsVBox);

        // Arka plan rengi
        BackgroundFill backgroundFill = new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        borderPane.setBackground(background);

        // Scene oluştur ve sahneyi ayarla
        Scene scene1 = new Scene(borderPane, 1200, 800);
        primaryStage.setTitle("Syllabus");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    // Stil ve boyutlandırmayı ayarlamak için kullanılan yardımcı metot
    private Button createButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-font-weight: bold;");
        button.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        button.setStyle("-fx-border-width: 2px; -fx-border-radius: 5px;");
        button.setPrefSize(350, 50);
        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
*/
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Optional;

public class FirstScene extends Application {
    private Stage primaryStage;





    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Syllabus");


        BorderPane borderPane = new BorderPane();


        MenuBar menuBar = new MenuBar();


        Menu fileMenu = new Menu("File");
        Menu helpMenu = new Menu("Help");


        MenuItem openItem = new MenuItem("Open");
        MenuItem CompareItem = new MenuItem("Compare");
        MenuItem editItem = new MenuItem("Edit");
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e -> primaryStage.close());
        openItem.setOnAction(e -> showLanguageSelectionPopup());


        fileMenu.getItems().addAll(openItem, CompareItem, editItem, new SeparatorMenuItem(), exitItem);


        menuBar.getMenus().addAll(fileMenu, helpMenu);


        Button turkishButton = createButton("ADD First Version Of TURKISH SYLLABUS");
        Button englishButton = createButton("Add First Version OF ENGLISH SYLLABUS");
        Button listVersions = createButton("Version of Syllabuses");


        turkishButton.setOnAction(e -> {
            App app = new App();
            app.start(primaryStage);
        });

        englishButton.setOnAction(e -> {
            EnglishSyllabus englishSyllabus = new EnglishSyllabus();
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

        Optional<ButtonType> result = alert.showAndWait();

        result.ifPresent(selectedButton -> {
            if (selectedButton == englishButton) {
                EnglishSyllabus englishSyllabus = new EnglishSyllabus();
                englishSyllabus.start(primaryStage);
            } else if (selectedButton == turkishButton) {
                App app = new App();
                app.start(primaryStage);
            }
        });
    }

    private void showVersions(){

    }

    public static void main(String[] args) {
        launch(args);
    }
}

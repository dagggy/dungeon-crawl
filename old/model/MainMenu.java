package com.example.dungeaoncrawler.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenu extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // mechanizm refleksji
        // dodanie zasobu graficznego do obiektu FXML
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenu.class.getResource("hello-view.fxml"));
        // obiekt sceny - (okna) aplikacji do którego dodano FXML
        Scene scene = new Scene(fxmlLoader.load());
        // tytuł w nagłówku aplikacji / okna
        stage.setTitle("Hello11!");
        stage.setScene(scene);
        stage.show();
    }
}

package com.example.dungeaoncrawler;

import com.example.dungeaoncrawler.logic.WorldMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    WorldMap worldMap = new WorldMap(1);

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        HelloController helloController = new HelloController();
        helloController.printMap(worldMap);
    }

    public static void main(String[] args) {
        launch();
    }
}
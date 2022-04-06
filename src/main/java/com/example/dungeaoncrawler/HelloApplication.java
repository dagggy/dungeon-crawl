package com.example.dungeaoncrawler;

import com.example.dungeaoncrawler.logic.WorldMap;
import com.example.dungeaoncrawler.logic.actors.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    public static Player player = new Player(10, 0, 0,6, null);
    static WorldMap worldMap = new WorldMap(1);

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        HelloController helloController = fxmlLoader.getController();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        scene.setOnKeyPressed(helloController::onKeyPressed);
    }

    public static void main(String[] args) {
        launch();
    }
}
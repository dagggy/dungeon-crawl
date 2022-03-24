package com.example.dungeaoncrawler;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FightScene extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(FightScene.class.getResource("fight.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Fight!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch();
    }

    @FXML
    private ImageView FightBackground;

    @FXML
    private ImageView card1;
}
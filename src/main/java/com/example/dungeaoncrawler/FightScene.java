package com.example.dungeaoncrawler;

import com.example.dungeaoncrawler.logic.actors.Enemy;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.dungeaoncrawler.HelloApplication.player;

public class FightScene {

//    public void startFight(Enemy enemy) {
//        try {
//            Stage stage = new Stage();
//            FXMLLoader fxmlLoader = new FXMLLoader(UserPanel.class.getResource("fight.fxml"));
//            fxmlLoader.setRoot();
//            Scene scene = new Scene(fxmlLoader.load());
//            FightController fightController = fxmlLoader.getController();
//            fightController.setPlayer(player);
//            fightController.setEnemy(enemy);
//            fightController.displayFighters();
//            stage.setTitle("Fight!");
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @FXML
    private ImageView FightBackground;

    @FXML
    private ImageView card1;
}
package com.example.dungeaoncrawler;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

import static javafx.application.Platform.exit;

public class UserPanel extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user-panel.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("JavaFX");
        stage.setScene(scene);
        stage.show();
        stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::endGame);
    }

    public static void main(String[] args) {
        launch();
    }

    @FXML
    private TextField idUserName;

    public static String userName;

    @FXML
    void startGame(ActionEvent event) {
        HelloApplication helloApplication = new HelloApplication();
        helloApplication.loadNewGame();
    }

    @FXML
    void loadPreviousGame(ActionEvent event) {
        //TODO: wczytanie gry
    }

    @FXML
    void endGame (WindowEvent event) {
        exit();
    }

    @FXML
    void exitGame(ActionEvent event) {
        exit();
    }

    void exit() {
        Platform.exit();
        System.exit(0);
    }

    public String getIdUserName() {
        return userName = idUserName.getText();
    }
}

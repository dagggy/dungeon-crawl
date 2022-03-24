package com.example.dungeaoncrawler;


import com.example.dungeaoncrawler.logic.CellType;
import com.example.dungeaoncrawler.logic.GameMap;
import com.example.dungeaoncrawler.logic.MapLoader;
import com.example.dungeaoncrawler.logic.WorldMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HelloController {
    ImageView imageView = new ImageView("img.png");
    ImageView imageView1 = new ImageView("img.png");

    @FXML
    private GridPane gridMap;

    @FXML
    private Button printButton;

    @FXML
    private GridPane actorMap;

    private final Image tileset = new Image("mapObjects.png", 577 * 2, 577 * 2, true, false);

    @FXML
    void printMap(ActionEvent event) {
        //todo: find a way to fit game logic elsewhere
        WorldMap worldMap = new WorldMap(1);

        gridMap.getChildren().clear();
//        Image tileset = new Image("tiles.png");
//        GameMap map = MapLoader.loadMap();
        GameMap map = worldMap.getGameMap(worldMap.getCurrentPos()[0],worldMap.getCurrentPos()[1]);
        gridMap.setHgap(0);
        gridMap.setVgap(0);
        for (int i = 0; i < gridMap.getColumnCount(); i++) {
            for (int j = 0; j < gridMap.getRowCount(); j++) {

                int[] currCell = map.getCell(i, j).getCellImageCoords();

                ImageView imageView = ImageHandler.getTile(tileset, currCell[0], currCell[1]);
                imageView.setFitWidth(32);
                imageView.setFitHeight(32);
                gridMap.add(imageView,i,j);
            }
        }
        System.out.println(gridMap.getChildren().size());
        System.out.println(worldMap.toString());
//        Rectangle b = new Rectangle(32,16,Color.BLACK);
//        actorMap.add(whatever, 0, 0);
//        System.out.println(actorMap.getChildren());
    }
}

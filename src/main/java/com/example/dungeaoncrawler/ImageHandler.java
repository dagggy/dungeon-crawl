package com.example.dungeaoncrawler;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class ImageHandler {

    private static final Image tileset = new Image(new File("tiles.png").toURI().toString());
    private static final int tileWidth = 32;
    private static final int tileSpacing = 2;
    public static ImageView getTile (int x, int y) {
        System.out.println();
        Rectangle2D croppedArea = new Rectangle2D(x*(tileWidth + tileSpacing), y*(tileWidth + tileSpacing), tileWidth, tileWidth);
        ImageView tile = new ImageView(tileset);
        tile.setViewport(croppedArea);
        return tile;
    }
}

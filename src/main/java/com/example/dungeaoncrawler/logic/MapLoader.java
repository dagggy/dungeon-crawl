package com.example.dungeaoncrawler.logic;

import com.example.dungeaoncrawler.logic.actors.Player;
import com.example.dungeaoncrawler.logic.actors.Skeleton;

import java.io.InputStream;
import java.util.*;

public class MapLoader {
    static Random random = new Random();
    static CellType[] values = CellType.values();

    public static GameMap loadMap(RoomType roomType, int worldPosX, int worldPosY) {
        int width = 25;
        int height = 20;
        GameMap map = new GameMap(width, height, CellType.EMPTY, roomType, worldPosX, worldPosY);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Cell cell = map.getCell(x, y);
                if (x == 0 || x == width-1 || y == 0 || y == height-1) {
                    cell.setType(CellType.WALL);
                }
            }
        }
        return map;
    }

//    private isConnected () {}

    static CellType randomCellType() {
        return values[random.nextInt(values.length)];
    }

}

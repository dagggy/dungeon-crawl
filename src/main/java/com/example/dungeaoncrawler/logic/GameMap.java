package com.example.dungeaoncrawler.logic;

import com.example.dungeaoncrawler.logic.actors.Actor;
import com.example.dungeaoncrawler.logic.actors.ActorType;
import com.example.dungeaoncrawler.logic.actors.Player;
import com.example.dungeaoncrawler.logic.actors.Skeleton;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GameMap {
    private final int width;
    private final int height;
    private Cell[][] cells;
    private final RoomType roomType;
    private Cell[][] actorCells;
    private final int worldPosX;
    private final int worldPosY;
    private final int potentialSpawns = ThreadLocalRandom.current().nextInt(3);
    private final int potentialDecorations = ThreadLocalRandom.current().nextInt(20);


    private Player player;

    public GameMap(int width, int height, CellType defaultCellType, RoomType roomType, int worldPosX, int worldPosY) {
        this.width = width;
        this.height = height;
        this.roomType = roomType;
        this.worldPosX = worldPosX;
        this.worldPosY = worldPosY;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType, CellDecoration.EMPTY);
            }
        }
        for (int i = 0; i<4; i++) {
            placeEnemy();
        }
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void placeEnemy () {
        int x = ThreadLocalRandom.current().nextInt(1, 24);
        int y = ThreadLocalRandom.current().nextInt(1, 19);
        Actor enemy = new Skeleton(10, 10, 10, cells[x][y]);
        cells[x][y].setActor(enemy);
    }

    public ArrayList<Integer> getWorldPos() {
        ArrayList<Integer> worldPos = new ArrayList<>();
        worldPos.add(worldPosX);
        worldPos.add(worldPosY);
        return worldPos;
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addDoor(char direction) {
        switch (direction) {
            case 'u' -> cells[width / 2][0].setType(CellType.DOOR);
            case 'd' -> cells[width / 2][height - 1].setType(CellType.DOOR);
            case 'l' -> cells[0][height / 2].setType(CellType.DOOR);
            case 'r' -> cells[width-1][height / 2].setType(CellType.DOOR);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
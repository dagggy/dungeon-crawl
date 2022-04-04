package com.example.dungeaoncrawler.logic;

import com.example.dungeaoncrawler.logic.actors.Actor;

public class Cell implements Drawable {
    private CellType type;
    private CellDecoration decoration;
    private Actor actor;
    private GameMap gameMap;
    private int x, y;
    private char doorDirection;

    public Cell(GameMap gameMap, int x, int y, CellType type, CellDecoration decoration) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;
        this.decoration = decoration;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public void setDecoration (CellDecoration decoration) {
        this.decoration = decoration;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Actor getActor() {
        return actor;
    }

    public Cell getNeighbor(int dx, int dy) {
        return gameMap.getCell(x + dx, y + dy);
    }

    public int[] getCellImageCoords() {
        if (getActor() != null) {
            return actor.getActorType().getActorImageCoords();
        } else if (type != CellType.EMPTY) {
            return type.getCellImageCoords();
        } else if (decoration != null) {
            return decoration.getDecorationImageCoords();
        }
        return type.getCellImageCoords();
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getDoorDirection () {
        return doorDirection;
    }

    public void setDoorDirection (char direction) {
        this.doorDirection = direction;
    }

    @Override
    public String getTileName() {
        return this.type.getTileName();
    }
}
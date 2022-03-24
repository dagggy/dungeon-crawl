package com.example.dungeaoncrawler.logic;

public enum CellType {
    EMPTY(0, 0, "empty"),
    FLOOR(2, 0, "floor"),
    WALL(6, 11, "wall"),
    DOOR(8, 10, "door");

    private final int x;
    private final int y;
    private final String tileName;

    CellType(int x, int y, String tileName) {
        this.x = x;
        this.y = y;
        this.tileName = tileName;
    }

    public int[] getCellImageCoords() {
        return new int[]{x, y};
    }
    public String getTileName() {
        return tileName;
    }
}

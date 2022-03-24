package com.example.dungeaoncrawler.logic;

public enum CellType {
    EMPTY(1, 1, "empty"),
    WALL(7, 12, "wall"),
    DOOR(9, 11, "door");

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

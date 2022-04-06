package com.example.dungeaoncrawler.logic;

public enum CellType {
    EMPTY(1, 1, "empty"),
    WALL(7, 12, "wall"),
    CLOSED_DOOR(9, 11, "door"),
    OPEN_DOOR(8, 11, "next_room"),
    KEY(17, 24, "key"),
    HEALTH(27, 23, "health"),
    POWER(27, 24, "power"),
    ARMOR(2, 24, "armor"),
    CARD(21, 17, "card");

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

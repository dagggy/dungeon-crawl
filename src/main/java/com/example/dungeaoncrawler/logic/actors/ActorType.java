package com.example.dungeaoncrawler.logic.actors;

public enum ActorType {
    PLAYER(25, 1, "player"),
    SKELETON(30, 7, "skeleton");

    private final int x;
    private final int y;
    private final String tileName;

    ActorType(int x, int y, String tileName) {
        this.x = x;
        this.y = y;
        this.tileName = tileName;
    }

    public int[] getActorImageCoords() {
        return new int[]{x, y};
    }
    public String getTileName() {
        return tileName;
    }
}

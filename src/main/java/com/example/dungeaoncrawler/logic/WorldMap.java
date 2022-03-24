package com.example.dungeaoncrawler.logic;

import com.example.dungeaoncrawler.logic.actors.Player;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class WorldMap {
    private final int floor;
    private final int currMapX;
    private final int currMapY;
    private final int worldWidth = 20;
    private final int worldHeight = 20;
    private final GameMap[][] gameMaps = new GameMap[worldWidth][worldHeight];
    private final Random random = new Random();
    private final int normalRoomCount = random.nextInt(8, 15);
    private final int specialRoomCount = random.nextInt(3, 6);
    private final ArrayList<GameMap> gameMapStorage = new ArrayList<>();

    public WorldMap (int floor) {
        this.floor = floor;
        placeSpawn();
        this.currMapX = gameMapStorage.get(0).getWorldPos().get(0);
        this.currMapY = gameMapStorage.get(0).getWorldPos().get(1);

        for (int i = 0; i < normalRoomCount; i++) {
            placeMap(RoomType.NORMAL);
        }
        for (int i = 0; i < specialRoomCount; i++) {
            placeMap(RoomType.SPECIAL);
        }

        placeMap(RoomType.LAST);
    }

    public GameMap getGameMap (int x, int y) {
        return gameMaps[x][y];
    }

    public int[] getCurrentPos () {
        return new int[]{currMapX, currMapY};
    }

    private void placeSpawn () {
        GameMap map = MapLoader.loadMap(RoomType.SPAWN, worldWidth/2, worldHeight/2);
        Player player = new Player(map.getCell(map.getWidth()/2, map.getHeight()/2));
        gameMaps[worldWidth/2][worldHeight/2] = map;
        gameMapStorage.add(map);

    }

    private void placeMap (RoomType roomType) {
        ArrayList<ArrayList<ArrayList<Integer>>> possibleRoomPositions = getPossibleRoomPositions();
        ArrayList<ArrayList<Integer>> selectedCoordinates = possibleRoomPositions.get(ThreadLocalRandom.current().nextInt(0, possibleRoomPositions.size()));

        GameMap map = MapLoader.loadMap(roomType, selectedCoordinates.get(0).get(0), selectedCoordinates.get(0).get(1));

        if (Objects.equals(map.getWorldPos().get(0), selectedCoordinates.get(1).get(0)) && Objects.equals(map.getWorldPos().get(1)-1, selectedCoordinates.get(1).get(1))) {
            gameMaps[selectedCoordinates.get(1).get(0)][selectedCoordinates.get(1).get(1)].addDoor('r');
            map.addDoor('l');
        } else if (Objects.equals(map.getWorldPos().get(0), selectedCoordinates.get(1).get(0)) && Objects.equals(map.getWorldPos().get(1)+1, selectedCoordinates.get(1).get(1))) {
            gameMaps[selectedCoordinates.get(1).get(0)][selectedCoordinates.get(1).get(1)].addDoor('l');
            map.addDoor('r');
        } else if (Objects.equals(map.getWorldPos().get(0)-1, selectedCoordinates.get(1).get(0)) && Objects.equals(map.getWorldPos().get(1), selectedCoordinates.get(1).get(1))) {
            gameMaps[selectedCoordinates.get(1).get(0)][selectedCoordinates.get(1).get(1)].addDoor('d');
            map.addDoor('u');
        } else {
            gameMaps[selectedCoordinates.get(1).get(0)][selectedCoordinates.get(1).get(1)].addDoor('u');
            map.addDoor('d');
        }

        gameMaps[map.getWorldPos().get(0)][map.getWorldPos().get(1)] = map;
        gameMapStorage.add(map);
    }

    private ArrayList<ArrayList<ArrayList<Integer>>> getPossibleRoomPositions () {
        ArrayList<ArrayList<ArrayList<Integer>>> possibleRoomPositions = new ArrayList<>();
        for (GameMap gameMap:gameMapStorage) {
            ArrayList<Integer> mapPos = gameMap.getWorldPos();
            for (int i = 0; i < 2; i++) {
                for (int j = -1; j < 2; j=j+2) {
                    int x = mapPos.get(0);
                    int y = mapPos.get(1);
                    if (gameMaps[x+j][y] == null) {
                        if (x + j >= 0 && x + j < worldWidth) {
                            if (y-1 >=0 && y+1 < worldHeight && x+j-1 >= 0 && x+j+1 < worldWidth) {
                                if ((gameMaps[x + j][y - 1] == null || gameMaps[x + j][y - 1] == gameMap) &&
                                        (gameMaps[x + j][y + 1] == null || gameMaps[x + j][y + 1] == gameMap) &&
                                        (gameMaps[x + j - 1][y] == null || gameMaps[x + j - 1][y] == gameMap) &&
                                        (gameMaps[x + j + 1][y] == null || gameMaps[x + j + 1][y] == gameMap)
                                ) {
                                    ArrayList<Integer> coordsToCheck = new ArrayList<>();
                                    coordsToCheck.add(x + j);
                                    coordsToCheck.add(y);
                                    ArrayList<ArrayList<Integer>> totalCoords = new ArrayList<>();
                                    totalCoords.add(coordsToCheck);
                                    totalCoords.add(gameMap.getWorldPos());
                                    possibleRoomPositions.add(totalCoords);
                                }
                            }
                        }
                    }
                    if (gameMaps[x][y+j] == null) {
                        if (y+j >= 0 && y+j < worldHeight) {
                            if (y+j-1 >=0 && y+j+1 < worldHeight && x-1 >= 0 && x+1 < worldWidth) {
                                if ((gameMaps[x - 1][y + j] == null || gameMaps[x - 1][y + j] == gameMap) &&
                                        (gameMaps[x + 1][y + j] == null || gameMaps[x + 1][y + j] == gameMap) &&
                                        (gameMaps[x][y + j - 1] == null || gameMaps[x][y + j - 1] == gameMap) &&
                                        (gameMaps[x][y + j + 1] == null || gameMaps[x][y + j + 1] == gameMap)
                                ) {
                                    ArrayList<Integer> coordsToCheck = new ArrayList<>();
                                    coordsToCheck.add(x);
                                    coordsToCheck.add(y + j);
                                    ArrayList<ArrayList<Integer>> totalCoords = new ArrayList<>();
                                    totalCoords.add(coordsToCheck);
                                    totalCoords.add(gameMap.getWorldPos());
                                    possibleRoomPositions.add(totalCoords);
                                }
                            }
                        }
                    }
                }
            }
        }
        return possibleRoomPositions;
    }

    private void addMap (int coordinateX, int coordinateY, RoomType roomType) {

    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        for (int x=0; x < worldWidth; x++) {
            for (int y=0; y < worldHeight; y++) {
                GameMap room = gameMaps[x][y];
                if (room != null) {
                    returnString.append(room.getRoomType().getSymbol());
                } else {
                    returnString.append(" ");
                }
            }
            returnString.append("\n");
        }
        return returnString.toString();
    }
}

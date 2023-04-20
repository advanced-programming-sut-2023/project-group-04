package org.model;

import java.util.ArrayList;

public class Game {
    private static Game currentGame;
    private MapCell[][] map;
    public ArrayList<Empire> allEmpires;

    public Game(ArrayList<Empire> allEmpires) {
        this.allEmpires = allEmpires;
    }

    public void initializeMap() {
        String[][] mapTemplate = Map.getCurrentMap().getMap();
        int mapSize = mapTemplate.length;
        this.map = new MapCell[mapSize][mapSize];
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                map[i][j] = new MapCell(i, j, mapTemplate[i][j]);
            }
        }
    }
    public MapCell[][] getMap() {
        return map;
    }

    public ArrayList<Empire> getAllEmpires() {
        return allEmpires;
    }

    public MapCell getCellByAxises(int x, int y) {
        return map[x][y];
    }

    public static Game getCurrentGame() {
        return currentGame;
    }
}

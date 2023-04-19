package org.model;

import java.util.ArrayList;

public class Game {
    MapCell[][] map;
    public ArrayList<Empire> allEmpires;

    public Game(int mapSize, ArrayList<Empire> allEmpires) {
        this.map = new MapCell[mapSize][mapSize];
        this.allEmpires = allEmpires;
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
}

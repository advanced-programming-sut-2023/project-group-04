package org.model;

import org.model.buildings.Building;
import org.model.person.Person;

import java.util.ArrayList;

public class Game {
    private static Game currentGame;
    private MapCell[][] map;
    private final ArrayList<Empire> allEmpires;
    private Empire currentEmpire;
    private Building selectedBuilding;
    private ArrayList<Person> selectedUnit;

    public Game(ArrayList<Empire> allEmpires) {
        this.allEmpires = allEmpires;
        currentEmpire = allEmpires.get(0);
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

    public Building getSelectedBuilding() {
        return selectedBuilding;
    }

    public void setSelectedBuilding(Building selectedBuilding) {
        this.selectedBuilding = selectedBuilding;
    }

    public MapCell[][] getMap() {
        return map;
    }

    public ArrayList<Empire> getAllEmpires() {
        return allEmpires;
    }

    public MapCell getMapCellByAddress(int x, int y) {
        return map[x][y];
    }

    public static Game getCurrentGame() {
        return currentGame;
    }

    public Empire getCurrentEmpire() {
        return currentEmpire;
    }

    public void nextEmpire() {
        int index = allEmpires.indexOf(currentEmpire) + 1;
        if (index == allEmpires.size()) index = 0;
        currentEmpire = allEmpires.get(index);
    }

    public ArrayList<Person> getSelectedUnit() {
        return selectedUnit;
    }

    public void selectUnit(ArrayList<Person> selectedUnit) {
        this.selectedUnit = selectedUnit;
    }
}

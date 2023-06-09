package org.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.model.Machine.Machine;
import org.model.buildings.*;
import org.model.map.Map;
import org.model.map.MapTile;
import org.model.person.Person;
import org.model.person.Soldier;
import org.model.person.SoldiersDictionary;

import java.io.FileWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Game {
    private static Game currentGame;
    private MapCell[][] map;
    private final ArrayList<Empire> allEmpires;
    private Empire currentEmpire;
    private Building selectedBuilding;
    private int mapFirstX;
    private int mapFirstY;
    private ArrayList<Person> selectedUnit;
    private final ArrayList<Person> toMovePeople;
    private final ArrayList<Soldier> attackingSoldiers;
    private final ArrayList<Trade> allTrades;
    private final ArrayList<Machine> toMoveOrAttackMachine;

    public Game(ArrayList<Empire> allEmpires, String mapName) {
        currentGame = this;
        this.allEmpires = allEmpires;
        currentEmpire = allEmpires.get(0);
        this.toMovePeople = new ArrayList<>();
        this.attackingSoldiers = new ArrayList<>();
        toMoveOrAttackMachine = new ArrayList<>();
        initializeMap(mapName);
        allTrades = new ArrayList<>();
    }

    public void initializeMap(String mapName) {
        Map mapTemplate = Map.getMapByName(mapName);
        int mapSize = mapTemplate.getMapSize();
        this.map = new MapCell[mapSize][mapSize];
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                MapTile tile = mapTemplate.getMapTile(i, j);
                MapCell mapCell = map[i][j] = new MapCell(i, j, tile.getTexture(), tile.getTree());
//                if (j > 1 && map[i][j-1].getBuilding() != null) {
//                    if (map[i][j-1].getBuilding().getBuildingDictionary().equals(BuildingsDictionary.HEAD_QUARTER)) {
//                        Empire empire = map[i][j - 1].getBuilding().getBuildingOwner();
//
//                    }
//                }
                if (tile.getOwnerColor() != null) {
                    Empire empire = allEmpires.get(mapTemplate.getAllColors().indexOf(tile.getOwnerColor()));
                    Building building = null;
                    if (tile.isHeadQuarter()) {
                        building = new Building(empire, BuildingsDictionary.HEAD_QUARTER, mapCell);
                        empire.setHeadquarter(building);
                        mapCell.setBuilding(building);
                        for (int k = 0; k < 15; k++) {
                            Person person = new Person(empire, mapCell);
                            mapCell.addPeople(person);
                            empire.addPerson(person);
                        }
                    } else if (tile.getBuilding() != null) {
                        building = createBuilding(empire, i, j, tile.getBuilding());
                        mapCell.setBuilding(building);
                    }
                    if (tile.getSoldier() != null)
                        for (int k = 0; k < tile.getSoldiersNumber(); k++)
                            mapCell.addPeople(new Soldier(empire,
                                    SoldiersDictionary.getSoldierDictionaryByName(tile.getSoldier()), mapCell));
                }
            }
        }
    }

    private Building createBuilding(Empire empire, int x, int y, String buildingName) {
        ProductiveBuildingsDictionary productiveBuildingDictionary;
        StorageBuildingsDictionary storageBuildingDictionary;
        StructuralBuildingsDictionary structuralBuildingDictionary;
        TowerBuildingsDictionary towerBuildingDictionary;
        TrainerBuildingsDictionary trainerBuildingDictionary;
        TrapBuildingsDictionary trapBuildingDictionary;
        MapCell mapCell = Game.getCurrentGame().getMapCellByAddress(x, y);
        if ((productiveBuildingDictionary = ProductiveBuildingsDictionary.getDictionaryByName(buildingName)) != null)
            return new ProductiveBuilding(empire, productiveBuildingDictionary, mapCell);
        else if ((storageBuildingDictionary = StorageBuildingsDictionary.getDictionaryByName(buildingName)) != null)
            return new StorageBuilding(empire, storageBuildingDictionary, mapCell);
        else if ((structuralBuildingDictionary = StructuralBuildingsDictionary.getDictionaryByName(buildingName)) != null)
            return new StructuralBuilding(empire, structuralBuildingDictionary, true, mapCell);
        else if ((towerBuildingDictionary = TowerBuildingsDictionary.getDictionaryByName(buildingName)) != null)
            return new TowerBuilding(empire, towerBuildingDictionary, mapCell);
        else if ((trainerBuildingDictionary = TrainerBuildingsDictionary.getDictionaryByName(buildingName)) != null)
            return new TrainerBuilding(empire, trainerBuildingDictionary, mapCell);
        else if ((trapBuildingDictionary = TrapBuildingsDictionary.getDictionaryByName(buildingName)) != null)
            return new TrapBuilding(empire, trapBuildingDictionary, mapCell);
        else
            return new Building(empire, BuildingsDictionary.getDictionaryByName(buildingName), mapCell);
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

    public int getMapFirstX() {
        return mapFirstX;
    }

    public void setMapFirstX(int mapFirstX) {
        this.mapFirstX = mapFirstX;
    }

    public int getMapFirstY() {
        return mapFirstY;
    }

    public void setMapFirstY(int mapFirstY) {
        this.mapFirstY = mapFirstY;
    }

    public ArrayList<Person> getSelectedUnit() {
        return selectedUnit;
    }

    public void selectUnit(ArrayList<Person> selectedUnit) {
        this.selectedUnit = selectedUnit;
    }

    public ArrayList<Person> getToMovePeople() {
        return toMovePeople;
    }

    public void addMoveAblePerson(Person person) {
        this.toMovePeople.add(person);
    }

    public void removeMovedPerson(Person person) {
        this.toMovePeople.remove(person);
    }

    public ArrayList<Soldier> getAttackingSoldiers() {
        return attackingSoldiers;
    }

    public void addAttackingSoldier(Soldier soldier) {
        this.attackingSoldiers.add(soldier);
    }

    public void removeAttackingSoldier(Soldier soldier) {
        this.attackingSoldiers.remove(soldier);
    }

    public int getMapSize() {
        return map.length;
    }

    public void addTrade(Trade trade) {
        this.allTrades.add(trade);
    }

    public ArrayList<Trade> getAllTrades() {
        return allTrades;
    }

    public Trade getTradeById(int id) {
        for (Trade trade : allTrades) {
            if (trade.getId() == id)
                return trade;
        }
        return null;
    }

    public void removeTrade(Trade trade) {
        this.allTrades.remove(trade);
    }

    public ArrayList<Machine> getToMoveOrAttackMachine() {
        return toMoveOrAttackMachine;
    }

    public void addMoveOrAttackMachine(Machine machine) {
        this.toMoveOrAttackMachine.add(machine);
    }

    public void removeEmpire(Empire empire) {
        this.allEmpires.remove(empire);
    }

    public void removeMovedMachine(Machine machine) {
        this.toMoveOrAttackMachine.remove(machine);
    }

}

package org.model.buildings;

import org.model.Empire;

import java.util.HashMap;

public class Building {
    private Empire buildingOwner;
    private final BuildingsDictionary buildingDictionary;
    private int hp;

    public Building(Empire buildingOwner, BuildingsDictionary buildingDictionary) {
        this.buildingOwner = buildingOwner;
        this.buildingDictionary = buildingDictionary;
        this.hp = buildingDictionary.getHp();
    }

    public BuildingsDictionary getBuildingDictionary() {
        return buildingDictionary;
    }

    public Empire getBuildingOwner() {
        return buildingOwner;
    }

    public String getName() {
        return buildingDictionary.getName();
    }

    public BuildingType getType() {
        return buildingDictionary.getType();
    }

    public int getCurrentHp() {
        return hp;
    }

    public int getBasicHp() {
        return buildingDictionary.getHp();
    }

    public HashMap<String, Integer> getPrices() {
        return buildingDictionary.getPrices();
    }

    public int getSize() {
        return buildingDictionary.getSize();
    }

    public int getWorkerNumber() {
        return buildingDictionary.getWorkerNumber();
    }

    public int getEngineerNumber() {
        return buildingDictionary.getEngineerNumber();
    }

    public void setBuildingOwner(Empire buildingOwner) {
        this.buildingOwner = buildingOwner;
    }

    public void decreaseHp(int hp) {
        this.hp -= hp;
    }

    public void repair() {
        this.hp = this.buildingDictionary.getHp();
    }

}

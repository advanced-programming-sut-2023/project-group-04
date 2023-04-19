package org.model.buildings;

import model.buildings.BuildingsDictionary;
import model.Empire;

import java.util.HashMap;

public class Building {
    private Empire buildingOwner;
    private BuildingsDictionary buildingsDictionary;
    private int hp;


    public Building(Empire buildingOwner, BuildingsDictionary buildingsDictionary) {
        this.buildingOwner = buildingOwner;
        this.buildingsDictionary = buildingsDictionary;
        this.hp = buildingsDictionary.getHp();
    }

    public Empire getBuildingOwner() {
        return buildingOwner;
    }

    public String getName() {
        return buildingsDictionary.getName();
    }

    public String getType() {
        return buildingsDictionary.getType();
    }

    public int getHp() {
        return hp;
    }

    public HashMap<String, Integer> getPrices() {
        return buildingsDictionary.getPrices();
    }

    public int getSize() {
        return buildingsDictionary.getSize();
    }

    public int getWorkerNumber() {
        return buildingsDictionary.getWorkerNumber();
    }

    public int getEngineerNumber() {
        return buildingsDictionary.getEngineerNumber();
    }

    public void setBuildingOwner(Empire buildingOwner) {
        this.buildingOwner = buildingOwner;
    }

    public void decreaseHp(int hp) {
        this.hp -= hp;
    }

}

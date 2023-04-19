package org.model.buildings;

import model.Empire;

public class TowerBuilding extends Building {
    private TowerBuildingsDictionary towerBuildingsDictionary;


    public TowerBuilding(Empire buildingOwner, TowerBuildingsDictionary towerBuildingsDictionary) {
        super(buildingOwner, towerBuildingsDictionary.getBuildingsDictionary());
        this.towerBuildingsDictionary = towerBuildingsDictionary;
    }

    public int getFireRange() {
        return towerBuildingsDictionary.getFireRange();
    }

    public int getDefendRange() {
        return towerBuildingsDictionary.getDefendRange();
    }

    public int getSoldierCapacity() {
        return towerBuildingsDictionary.getSoldierCapacity();
    }
}

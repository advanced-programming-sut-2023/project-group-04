package org.model.buildings;

import org.model.Empire;

public class TowerBuilding extends Building {
    private TowerBuildingsDictionary towerBuildingsDictionary;


    public TowerBuilding(Empire buildingOwner, TowerBuildingsDictionary towerBuildingsDictionary) {
        super(buildingOwner, towerBuildingsDictionary.getBuildingDictionary());
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

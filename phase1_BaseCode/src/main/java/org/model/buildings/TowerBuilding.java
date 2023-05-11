package org.model.buildings;

import org.model.Empire;
import org.model.MapCell;

public class TowerBuilding extends Building {
    private TowerBuildingsDictionary towerBuildingsDictionary;


    public TowerBuilding(Empire buildingOwner, TowerBuildingsDictionary towerBuildingsDictionary, MapCell mapCell) {
        super(buildingOwner, towerBuildingsDictionary.getBuildingDictionary(), mapCell);
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

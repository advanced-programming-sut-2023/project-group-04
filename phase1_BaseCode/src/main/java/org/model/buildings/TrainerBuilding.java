package org.model.buildings;

import org.model.Empire;
import org.model.MapCell;

import java.util.ArrayList;

public class TrainerBuilding extends Building {

    private TrainerBuildingsDictionary trainerBuildingsDictionary;

    public TrainerBuilding(Empire buildingOwner, TrainerBuildingsDictionary trainerBuildingsDictionary, MapCell mapCell) {
        super(buildingOwner, trainerBuildingsDictionary.getBuildingDictionary(), mapCell);
        this.trainerBuildingsDictionary = trainerBuildingsDictionary;
    }

    public ArrayList<String> getAvailableSoldiers() {
        return trainerBuildingsDictionary.getAvailableSoldiers();
    }
}

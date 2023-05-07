package org.model.buildings;

import org.model.Empire;
import org.model.MapCell;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TrainerBuilding extends Building {

    private TrainerBuildingsDictionary trainerBuildingsDictionary;

    public TrainerBuilding(Empire buildingOwner, TrainerBuildingsDictionary trainerBuildingsDictionary, MapCell mapCell) {
        super(buildingOwner, trainerBuildingsDictionary.getBuildingDictionary(), mapCell);
    }

    public ArrayList<String> getAvailableSoldiers() {
        return trainerBuildingsDictionary.getAvailableSoldiers();
    }
}

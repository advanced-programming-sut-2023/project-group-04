package org.model.buildings;

import org.model.Empire;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TrainerBuilding extends Building {

    private TrainerBuildingsDictionary trainerBuildingsDictionary;

    public TrainerBuilding(Empire buildingOwner, TrainerBuildingsDictionary trainerBuildingsDictionary,
                           ArrayList<String> availableSoldiers) {
        super(buildingOwner, trainerBuildingsDictionary.getBuildingsDictionary());
    }

    public ArrayList<String> getAvailableSoldiers() {
        return trainerBuildingsDictionary.getAvailableSoldiers();
    }
}

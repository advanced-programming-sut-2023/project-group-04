package org.model.buildings;

import org.model.Empire;

import java.util.ArrayList;

public enum TrainerBuildingsDictionary {
    BARRACKS(BuildingsDictionary.BARRACKS,new ArrayList<>(){}),
    MERCENARY_POST(BuildingsDictionary.MERCENARY_POST,new ArrayList<>(){}),
    ENGINEER_GUILD(BuildingsDictionary.ENGINEER_GUILD,new ArrayList<>(){}),
    TUNNELER_GUILD(BuildingsDictionary.TUNNELER_GUILD,new ArrayList<>(){});

    private BuildingsDictionary buildingsDictionary;
    private ArrayList<String> availableSoldiers;

    TrainerBuildingsDictionary(BuildingsDictionary buildingsDictionary, ArrayList<String> availableSoldiers) {
        this.buildingsDictionary = buildingsDictionary;
        this.availableSoldiers = availableSoldiers;
    }

    public BuildingsDictionary getBuildingsDictionary() {
        return buildingsDictionary;
    }

    public ArrayList<String> getAvailableSoldiers() {
        return availableSoldiers;
    }
}
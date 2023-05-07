package org.model.buildings;

import org.model.Empire;

import java.util.ArrayList;

public enum TrainerBuildingsDictionary {
    BARRACKS(BuildingsDictionary.BARRACKS,new ArrayList<String>(){}),
    MERCENARY_POST(BuildingsDictionary.MERCENARY_POST,new ArrayList<String>(){}),
    ENGINEER_GUILD(BuildingsDictionary.ENGINEER_GUILD,new ArrayList<String>(){}),
    TUNNELER_GUILD(BuildingsDictionary.TUNNELER_GUILD,new ArrayList<String>(){});

    private BuildingsDictionary buildingDictionary;
    private ArrayList<String> availableSoldiers;

    TrainerBuildingsDictionary(BuildingsDictionary buildingDictionary, ArrayList<String> availableSoldiers) {
        this.buildingDictionary = buildingDictionary;
        this.availableSoldiers = availableSoldiers;
    }

    public BuildingsDictionary getBuildingDictionary() {
        return buildingDictionary;
    }

    public ArrayList<String> getAvailableSoldiers() {
        return availableSoldiers;
    }
}

package org.model.buildings;

import java.util.ArrayList;

public enum TrainerBuildingsDictionary {
    BARRACKS(BuildingsDictionary.BARRACKS, new ArrayList<>() {
    }),
    MERCENARY_POST(BuildingsDictionary.MERCENARY_POST, new ArrayList<>() {
    }),
    ENGINEER_GUILD(BuildingsDictionary.ENGINEER_GUILD, new ArrayList<>() {
    });
//    TUNNELER_GUILD(BuildingsDictionary.TUNNELER_GUILD, new ArrayList<>() {
//    });

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

    public static TrainerBuildingsDictionary getDictionaryByName(String buildingName) {
        for (TrainerBuildingsDictionary trainerBuildingsDictionary : TrainerBuildingsDictionary.values()) {
            if (trainerBuildingsDictionary.getBuildingDictionary().getName().equals(buildingName))
                return trainerBuildingsDictionary;
        }
        return null;
    }
}

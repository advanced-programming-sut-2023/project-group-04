package org.model.buildings;

import java.util.ArrayList;

public enum TrainerBuildingsDictionary {
    BARRACKS(BuildingsDictionary.BARRACKS, new ArrayList<>() {
        {
            add("archer");
            add("crossbowman");
            add("spearman");
            add("pikeman");
            add("maceman");
            add("swordsman");
            add("knight");
        }
    }),
    MERCENARY_POST(BuildingsDictionary.MERCENARY_POST, new ArrayList<>() {
        {
            add("arabianbow");
            add("slave");
            add("slinger");
            add("assassin");
            add("horse_archer");
            add("arabian_swordsman");
            add("fire_thrower");
        }
    }),
    ENGINEER_GUILD(BuildingsDictionary.ENGINEER_GUILD, new ArrayList<>() {
        {
            add("engineer");
            add("ladderman");
        }
    }),
    TUNNELER_GUILD(BuildingsDictionary.TUNNELER_GUILD, new ArrayList<>() {
        {
            add("tunneler");
        }
    });

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

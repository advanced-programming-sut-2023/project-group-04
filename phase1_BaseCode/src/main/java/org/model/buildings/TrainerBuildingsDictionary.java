package org.model.buildings;

import java.util.ArrayList;

public enum TrainerBuildingsDictionary {
    BARRACKS(BuildingsDictionary.BARRACKS, new ArrayList<>() {
        {
            add("archer");
            add("crossbow man");
            add("spearman");
            add("pike man");
            add("mace man");
            add("swordsman");
            add("knight");
        }
    }),
    MERCENARY_POST(BuildingsDictionary.MERCENARY_POST, new ArrayList<>() {
        {
            add("arabian bow");
            add("slave");
            add("slinger");
            add("assassin");
            add("horse archer");
            add("arabian swordsman");
            add("fire thrower");
        }
    }),
    ENGINEER_GUILD(BuildingsDictionary.ENGINEER_GUILD, new ArrayList<>() {
        {
            add("engineer");
            add("ladder man");
        }
    }),
    TUNNELER_GUILD(BuildingsDictionary.TUNNELER_GUILD, new ArrayList<>() {
        {
            add("tunneler");
        }
    });

    private final BuildingsDictionary buildingDictionary;
    private final ArrayList<String> availableSoldiers;

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

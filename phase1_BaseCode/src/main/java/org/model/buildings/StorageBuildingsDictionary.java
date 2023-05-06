package org.model.buildings;

import java.util.HashMap;

public enum StorageBuildingsDictionary {
    ARMOURY(BuildingsDictionary.ARMOURY, 0, new HashMap<>() {
    }),
    STOCKPILE(BuildingsDictionary.STOCKPILE, 0, new HashMap<>() {
    }),
    GRANARY(BuildingsDictionary.GRANARY, 0, new HashMap<>() {
    });

    private BuildingsDictionary buildingDictionary;
    private int capacity;
    private HashMap<String, Integer> objects;


    private StorageBuildingsDictionary(BuildingsDictionary buildingsDictionary, int capacity, HashMap<String, Integer> objects) {
        this.capacity = capacity;
        this.objects = objects;
    }

    public int getCapacity() {
        return capacity;
    }

    public BuildingsDictionary getBuildingDictionary() {
        return buildingDictionary;
    }

    public HashMap<String, Integer> getObjects() {
        return objects;
    }

    public static StorageBuildingsDictionary getDictionaryByName(String buildingName) {
        for (StorageBuildingsDictionary storageBuildingsDictionary : StorageBuildingsDictionary.values()) {
            if (storageBuildingsDictionary.buildingsDictionary.getName().equals(buildingName))
                return storageBuildingsDictionary;
        }
        return null;
    }
}

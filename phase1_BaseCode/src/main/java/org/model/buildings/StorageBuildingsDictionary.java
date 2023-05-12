package org.model.buildings;

import java.util.ArrayList;

public enum StorageBuildingsDictionary {
    ARMOURY(BuildingsDictionary.ARMOURY, 100, new ArrayList<>() {
        {
            add("sword");
            add("mace");
            add("bow");
            add("crossbow");
            add("pike");
            add("spear");
            add("leather armour");
            add("metal armour");
        }
    }),
    STOCKPILE(BuildingsDictionary.STOCKPILE, 200, new ArrayList<>() {
        {
            add("stone");
            add("wood");
            add("pitch");
            add("iron");
            add("flour");
            add("ale");
            add("wheat");
            add("hop");
        }
    }),
    GRANARY(BuildingsDictionary.GRANARY, 200, new ArrayList<>() {
        {
            add("meat");
            add("cheese");
            add("apple");
            add("bread");
        }
    });

    private BuildingsDictionary buildingDictionary;
    private int capacity;
    private ArrayList<String> objects;


    private StorageBuildingsDictionary(BuildingsDictionary buildingsDictionary, int capacity, ArrayList<String> objects) {
        this.capacity = capacity;
        this.objects = objects;
    }

    public int getCapacity() {
        return capacity;
    }

    public BuildingsDictionary getBuildingDictionary() {
        return buildingDictionary;
    }

    public ArrayList<String> getObjects() {
        return objects;
    }

    public static StorageBuildingsDictionary getDictionaryByName(String buildingName) {
        for (StorageBuildingsDictionary storageBuildingsDictionary : StorageBuildingsDictionary.values()) {
            if (storageBuildingsDictionary.getBuildingDictionary().getName().equals(buildingName))
                return storageBuildingsDictionary;
        }
        return null;
    }
}

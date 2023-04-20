package org.model.buildings;

import org.model.Empire;

import java.util.ArrayList;
import java.util.HashMap;

public class StorageBuilding extends Building{
    private static final ArrayList<StorageBuilding> allStorage = new ArrayList<>();
    private final StorageBuildingsDictionary storageBuildingsDictionary;
    private final HashMap<String , Integer> objects;
    private int freeSpace;

    public StorageBuilding(Empire buildingOwner, BuildingsDictionary buildingsDictionary,
                           StorageBuildingsDictionary storageBuildingsDictionary) {
        super(buildingOwner, buildingsDictionary);
        this.storageBuildingsDictionary = storageBuildingsDictionary;
        this.freeSpace = storageBuildingsDictionary.getCapacity();
        this.objects = storageBuildingsDictionary.getObjects();
    }

    public HashMap<String, Integer> getContent() {
        return objects;
    }

    public void addToStorage(String object , int amount) {}

    public void removeFromStorage(String object , int amount) {}

    public static ArrayList<StorageBuilding> getAllStorage() {
        return allStorage;
    }

    public int getFreeSpace() {
        return freeSpace;
    }


}

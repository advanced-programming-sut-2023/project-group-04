package org.model.buildings;

import org.model.Empire;
import org.model.MapCell;

import java.util.ArrayList;
import java.util.HashMap;

public class StorageBuilding extends Building {
    private final StorageBuildingsDictionary storageBuildingsDictionary;
    private final HashMap<String, Integer> resources;
    private int freeSpace;

    public StorageBuilding(Empire buildingOwner, StorageBuildingsDictionary storageBuildingsDictionary, MapCell mapCell) {
        super(buildingOwner, storageBuildingsDictionary.getBuildingDictionary(), mapCell);
        this.storageBuildingsDictionary = storageBuildingsDictionary;
        this.freeSpace = storageBuildingsDictionary.getCapacity();
        this.resources = storageBuildingsDictionary.getObjects();
    }

    public HashMap<String, Integer> getContent() {
        return resources;
    }

    public int getResourceAmount(String resource) {
        return this.resources.get(resource);
    }

    public void changeResourcesAmount(String resource, int amount) {
        this.resources.put(resource,this.resources.get(resource) + amount);
        this.freeSpace -= amount;
    }

    public int getFreeSpace() {
        return freeSpace;
    }

    public StorageBuildingsDictionary getStorageBuildingsDictionary() {
        return storageBuildingsDictionary;
    }
}

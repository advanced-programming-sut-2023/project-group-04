package org.model.buildings;

import org.model.Empire;
import org.model.MapCell;

import java.util.ArrayList;

public class StructuralBuilding extends Building{

    private StructuralBuildingsDictionary structuralBuildingsDictionary;
    private int freeSpace;
    private final ArrayList<MapCell> unAvailableNeighbors;
    private boolean isOpen;

    public StructuralBuilding(Empire buildingOwner, BuildingsDictionary buildingsDictionary,
                              StructuralBuildingsDictionary structuralBuildingsDictionary,
                              ArrayList<MapCell> unAvailableNeighbors) {
        super(buildingOwner, buildingsDictionary);
        this.structuralBuildingsDictionary = structuralBuildingsDictionary;
        this.freeSpace = 0;
        this.unAvailableNeighbors = unAvailableNeighbors;
        this.isOpen = false;
    }

    public int getPeople() {
        return freeSpace;
    }

    public ArrayList<MapCell> getNeighbors() {
        return unAvailableNeighbors;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void changeDoorState() {
        this.isOpen = !isOpen;
    }

    public void addPeople(int amount) {
        this.freeSpace += amount;
    }

    public int getCapacity() {
        return this.structuralBuildingsDictionary.getCapacity();
    }

    public int getFreeSpace() {
        return freeSpace;
    }

    public ArrayList<MapCell> getUnAvailableNeighbors() {
        return unAvailableNeighbors;
    }
}

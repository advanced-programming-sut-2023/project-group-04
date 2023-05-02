package org.model.buildings;

import org.model.Empire;
import org.model.MapCell;

import java.util.ArrayList;

public class StructuralBuilding extends Building {

    private final StructuralBuildingsDictionary structuralBuildingsDictionary;
    private int freeSpace;
    private final String direction;
    private boolean isOpen;

    public StructuralBuilding(Empire buildingOwner, StructuralBuildingsDictionary structuralBuildingsDictionary, String direction) {
        super(buildingOwner, structuralBuildingsDictionary.getBuildingDictionary());
        this.structuralBuildingsDictionary = structuralBuildingsDictionary;
        this.freeSpace = 0;
        this.direction = direction;
        this.isOpen = false;
    }

    public String getDirection() {
        return direction;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void changeDoorState() {
        this.isOpen = !isOpen;
    }

    public void addPeople(int amount) {
        this.freeSpace -= amount;
    }

    public int getCapacity() {
        return this.structuralBuildingsDictionary.getCapacity();
    }

    public int getFreeSpace() {
        return freeSpace;
    }

}

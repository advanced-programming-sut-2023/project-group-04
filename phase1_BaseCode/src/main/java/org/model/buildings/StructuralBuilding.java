package org.model.buildings;

import org.model.Empire;
import org.model.MapCell;

import java.util.ArrayList;

public class StructuralBuilding extends Building {

    private final StructuralBuildingsDictionary structuralBuildingsDictionary;
    private int freeSpace;
    private final boolean upDirection;
    private boolean isOpen;

    public StructuralBuilding(Empire buildingOwner, StructuralBuildingsDictionary structuralBuildingsDictionary
            , boolean upDirection, MapCell mapCell) {
        super(buildingOwner, structuralBuildingsDictionary.getBuildingDictionary(), mapCell);
        this.structuralBuildingsDictionary = structuralBuildingsDictionary;
        this.freeSpace = 0;
        this.upDirection = upDirection;
        this.isOpen = false;
    }

    public boolean isUpside() {
        return upDirection;
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

package org.model.buildings;

import java.util.PrimitiveIterator;

public enum StructuralBuildingsDictionary {
    SMALL_STONE_GATEHOUSE(BuildingsDictionary.SMALL_STONE_GATEHOUSE,0),
    LARGE_STONE_GATEHOUSE(BuildingsDictionary.LARGE_STONE_GATEHOUSE,0),
    DRAW_BRIDGE(BuildingsDictionary.DRAW_BRIDGE,0),
    HOVEL(BuildingsDictionary.HOVEL,0);

    private BuildingsDictionary buildingsDictionary;
    private int capacity;

    private StructuralBuildingsDictionary(BuildingsDictionary buildingsDictionary, int capacity) {
        this.capacity = capacity;
        this.buildingsDictionary = buildingsDictionary;
    }

    public BuildingsDictionary getBuildingsDictionary() {
        return buildingsDictionary;
    }

    public int getCapacity() {
        return capacity;
    }
}

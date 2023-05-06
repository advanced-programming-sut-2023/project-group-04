package org.model.buildings;

public enum StructuralBuildingsDictionary {
    SMALL_STONE_GATEHOUSE(BuildingsDictionary.SMALL_STONE_GATEHOUSE, 0),
    LARGE_STONE_GATEHOUSE(BuildingsDictionary.LARGE_STONE_GATEHOUSE, 0),
    DRAW_BRIDGE(BuildingsDictionary.DRAW_BRIDGE, 0),
    HOVEL(BuildingsDictionary.HOVEL, 0);

    private final BuildingsDictionary buildingDictionary;
    private final int capacity;

    private StructuralBuildingsDictionary(BuildingsDictionary buildingDictionary, int capacity) {
        this.capacity = capacity;
        this.buildingDictionary = buildingDictionary;
    }

    public BuildingsDictionary getBuildingDictionary() {
        return buildingDictionary;
    }

    public int getCapacity() {
        return capacity;
    }

    public static StructuralBuildingsDictionary getDictionaryByName(String buildingName) {
        for (StructuralBuildingsDictionary structuralBuildingsDictionary : StructuralBuildingsDictionary.values()) {
            if (structuralBuildingsDictionary.buildingsDictionary.getName().equals(buildingName))
                return structuralBuildingsDictionary;
        }
        return null;
    }
}

package org.model.buildings;

import org.model.buildings.BuildingsDictionary;
import org.model.Empire;

public class ProductiveBuilding extends Building {
    private final ProductiveBuildingsDictionary productiveBuildingsDictionary;

    public ProductiveBuilding(Empire buildingOwner, BuildingsDictionary buildingsDictionary,
                              ProductiveBuildingsDictionary productiveBuildingsDictionary) {
        super(buildingOwner, buildingsDictionary);
        this.productiveBuildingsDictionary = productiveBuildingsDictionary;
    }

    public int getRate() {
        return productiveBuildingsDictionary.getRate();
    }

    public String getInputResource() {
        return productiveBuildingsDictionary.getInputResource();
    }

    public String getOutputResource() {
        return productiveBuildingsDictionary.getInputResource();
    }

    public String getGroundTexture() {
        return productiveBuildingsDictionary.getGroundTexture();
    }


}

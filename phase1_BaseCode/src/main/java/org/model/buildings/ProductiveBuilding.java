package org.model.buildings;

import org.model.MapCell;
import org.model.buildings.BuildingsDictionary;
import org.model.Empire;

public class ProductiveBuilding extends Building {
    private final ProductiveBuildingsDictionary productiveBuildingDictionary;

    public ProductiveBuilding(Empire buildingOwner, ProductiveBuildingsDictionary productiveBuildingDictionary, MapCell mapCell) {
        super(buildingOwner, productiveBuildingDictionary.getBuildingDictionary(), mapCell);
        this.productiveBuildingDictionary = productiveBuildingDictionary;
    }

    public ProductiveBuildingsDictionary getDictionary() {
        return productiveBuildingDictionary;
    }

    public int getRate() {
        return productiveBuildingDictionary.getRate();
    }

    public String getInputResource() {
        return productiveBuildingDictionary.getInputResource();
    }

    public String getOutputResource() {
        return productiveBuildingDictionary.getInputResource();
    }

    public String getGroundTexture() {
        return productiveBuildingDictionary.getGroundTexture();
    }


}

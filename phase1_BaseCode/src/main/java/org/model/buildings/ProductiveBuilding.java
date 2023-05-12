package org.model.buildings;

import org.model.Empire;
import org.model.MapCell;

import java.util.ArrayList;

public class ProductiveBuilding extends Building {
    private final ProductiveBuildingsDictionary productiveBuildingDictionary;

    public ProductiveBuilding(Empire buildingOwner, ProductiveBuildingsDictionary productiveBuildingDictionary, MapCell mapCell) {
        super(buildingOwner, productiveBuildingDictionary.getBuildingDictionary(), mapCell);
        this.productiveBuildingDictionary = productiveBuildingDictionary;
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

    public ArrayList<String> getGroundTexture() {
        return productiveBuildingDictionary.getAcceptableTexture();
    }


}

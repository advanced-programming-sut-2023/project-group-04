package org.model.buildings;

import org.model.Empire;
import org.model.MapCell;

import java.util.ArrayList;

public class ProductiveBuilding extends Building {
    private final ProductiveBuildingsDictionary productiveBuildingDictionary;
    private String outputResource;

    public ProductiveBuilding(Empire buildingOwner, ProductiveBuildingsDictionary productiveBuildingDictionary, MapCell mapCell) {
        super(buildingOwner, productiveBuildingDictionary.getBuildingDictionary(), mapCell);
        this.productiveBuildingDictionary = productiveBuildingDictionary;
        this.outputResource = productiveBuildingDictionary.getOutputResource().get(0);
    }

    public ProductiveBuildingsDictionary getDictionary() {
        return productiveBuildingDictionary;
    }

    public void setOutputResource(String outputResource) {
        this.outputResource = outputResource;
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

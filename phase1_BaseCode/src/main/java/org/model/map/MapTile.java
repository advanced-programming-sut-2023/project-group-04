package org.model.map;

import java.util.ArrayList;

public class MapTile {
    private String texture;
    private String tree;
    private String OwnerColor;
    private String building;
    private String soldier;
    private int soldiersNumber;
    private boolean headQuarter;

    public MapTile() {
        this.texture = "GROUND";
        this.tree = null;
        this.OwnerColor = null;
        this.building = null;
        this.soldier = null;
        this.headQuarter = false;
        this.soldiersNumber = 0;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public String getTree() {
        return tree;
    }

    public void setTree(String tree) {
        this.tree = tree;
    }

    public String getOwnerColor() {
        return OwnerColor;
    }

    public void setOwnerColor(String ownerColor) {
        OwnerColor = ownerColor;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getSoldier() {
        return soldier;
    }

    public int getSoldiersNumber() {
        return soldiersNumber;
    }

    public void setSoldier(String soldier, int count) {
        this.soldier = soldier;
        this.soldiersNumber = count;
    }

    public boolean isHeadQuarter() {
        return headQuarter;
    }

    public void setHeadQuarter() {
        this.headQuarter = true;
    }
}

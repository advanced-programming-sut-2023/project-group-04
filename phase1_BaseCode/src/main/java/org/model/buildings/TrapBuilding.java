package org.model.buildings;

import org.model.Empire;

public class TrapBuilding extends Building {

    private TrapBuildingsDictionary trapBuildingsDictionary;
    private boolean activation;

    public TrapBuilding(Empire buildingOwner, TrapBuildingsDictionary trapBuildingsDictionary) {
        super(buildingOwner, trapBuildingsDictionary.getBuildingDictionary());
        this.activation = false;
    }

    public int getDamage() {
        return trapBuildingsDictionary.getDamage();
    }

    public void activeTrap() {
        this.activation = true;
    }

    public void deActiveTrap() {
        this.activation = false;
    }

    public boolean checkActivation() {
        return activation;
    }
}

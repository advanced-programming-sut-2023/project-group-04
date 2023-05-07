package org.model.person;

import org.model.Empire;
import org.model.Machine.Machine;
import org.model.MapCell;
import org.model.buildings.Building;

public class Engineer extends Person{

    private static final int  requiredGold = 0;
    private Building buildingWorkPlace;
    private Machine machineWorkPlace;

    private boolean oil;

    public Engineer(Empire personOwner, MapCell mapCell) {
        super(personOwner, 0, mapCell);
        this.buildingWorkPlace = null;
        this.machineWorkPlace = null;
        this.oil = false;
    }

    public Building getBuildingWorkPlace() {
        return buildingWorkPlace;
    }

    public void setBuildingWorkPlace(Building buildingWorkPlace) {
        this.buildingWorkPlace = buildingWorkPlace;
    }

    public Machine getMachineWorkPlace() {
        return machineWorkPlace;
    }

    public void setMachineWorkPlace(Machine machineWorkPlace) {
        this.machineWorkPlace = machineWorkPlace;
    }

    public static int getRequiredGold() {
        return requiredGold;
    }

    public boolean hasOil() {
        return oil;
    }

    public void setOil(boolean oil) {
        this.oil = oil;
    }
}

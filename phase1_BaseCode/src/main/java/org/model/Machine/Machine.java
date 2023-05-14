package org.model.Machine;

import org.model.Empire;
import org.model.MapCell;
import org.model.person.Engineer;

import java.util.ArrayList;

public class Machine {
    private Empire ownerMachine;
    private MapCell mapCell;
    private ArrayList<Engineer> engineers;
    private MachinesDictionary machinesDictionary;

    public Machine(MachinesDictionary machinesDictionary, Empire ownerMachine, MapCell mapCell) {
        this.machinesDictionary = machinesDictionary;
        this.ownerMachine = ownerMachine;
        this.mapCell = mapCell;
        this.engineers = new ArrayList<>();
    }

    public Empire getOwnerMachine() {
        return ownerMachine;
    }

    public void setOwnerMachine(Empire ownerMachine) {
        this.ownerMachine = ownerMachine;
    }

    public MapCell getMapCell() {
        return mapCell;
    }

    public void setMapCell(MapCell mapCell) {
        this.mapCell = mapCell;
    }

    public ArrayList<Engineer> getEngineers() {
        return engineers;
    }

    public void setEngineers(ArrayList<Engineer> engineers) {
        this.engineers = engineers;
    }

    public MachinesDictionary getMachinesDictionary() {
        return machinesDictionary;
    }

    public void setMachinesDictionary(MachinesDictionary machinesDictionary) {
        this.machinesDictionary = machinesDictionary;
    }
}

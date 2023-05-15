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
    private MapCell destination;
    private MapCell aim;
    private int hp;

    public Machine(MachinesDictionary machinesDictionary, Empire ownerMachine, MapCell mapCell) {
        this.machinesDictionary = machinesDictionary;
        this.ownerMachine = ownerMachine;
        this.mapCell = mapCell;
        this.engineers = new ArrayList<>();
        this.hp = machinesDictionary.getHp();
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

    public MapCell getDestination() {
        return destination;
    }

    public void setDestination(MapCell destination) {
        this.destination = destination;
    }

    public MapCell getAim() {
        return aim;
    }

    public void setAim(MapCell aim) {
        this.aim = aim;
    }

    public int getHp() {
        return hp;
    }

    public void damageMachine(int damage) {
        this.hp -= damage;
    }
}

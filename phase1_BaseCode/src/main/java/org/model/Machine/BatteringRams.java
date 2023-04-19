package org.model.Machine;

import model.Empire;
import model.MapCell;
import model.buildings.Building;

import java.util.ArrayList;

public class BatteringRams extends Machine {
    private ArrayList<Building> canAttack;

    public BatteringRams(MachinesDictionary machinesDictionary, Empire ownerMachine, MapCell mapCell) {
        super(machinesDictionary, ownerMachine, mapCell);
    }
}

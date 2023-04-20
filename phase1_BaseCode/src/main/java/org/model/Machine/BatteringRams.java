package org.model.Machine;

import org.model.Empire;
import org.model.MapCell;
import org.model.buildings.Building;

import java.util.ArrayList;

public class BatteringRams extends Machine {
    private ArrayList<Building> canAttack;

    public BatteringRams(MachinesDictionary machinesDictionary, Empire ownerMachine, MapCell mapCell) {
        super(machinesDictionary, ownerMachine, mapCell);
    }
}

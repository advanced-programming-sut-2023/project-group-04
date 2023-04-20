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
}

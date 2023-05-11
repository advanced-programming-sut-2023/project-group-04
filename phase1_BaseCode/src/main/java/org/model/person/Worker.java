package org.model.person;

import org.model.Empire;
import org.model.MapCell;
import org.model.buildings.Building;

public class Worker extends Person {

    private static final int hp = 0;
    private static final int speed = 0;
    private Building venue;

    public Worker(Empire personOwner, MapCell mapCell) {
        super(personOwner, hp, mapCell, speed);
    }

    public Building getVenue() {
        return venue;
    }

}

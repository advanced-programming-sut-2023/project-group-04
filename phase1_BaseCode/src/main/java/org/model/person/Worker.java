package org.model.person;

import org.model.Empire;
import org.model.buildings.Building;

public class Worker extends Person {
    private Building venue;

    public Worker(Empire personOwner, int hp) {
        super(personOwner, hp);
    }

    public Building getVenue() {
        return venue;
    }

}

package org.model.person;

import model.Empire;
import model.buildings.Building;

public class Worker extends Person {
    private Building venue;

    public Worker(Empire personOwner, int hp) {
        super(personOwner, hp);
    }

    public Building getVenue() {
        return venue;
    }

}

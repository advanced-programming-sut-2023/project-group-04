package org.model.person;

import model.Empire;
import model.Player;

public class Person {

    private Empire personOwner;
    private int hp;

    public Person(Empire personOwner, int hp) {
        this.personOwner = personOwner;
        this.hp = hp;
    }

    public Empire getPersonOwner() {
        return personOwner;
    }

    public int getHp() {
        return hp;
    }
}
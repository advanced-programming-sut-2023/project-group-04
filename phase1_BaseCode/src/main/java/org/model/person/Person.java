package org.model.person;

import org.model.Empire;
import org.model.Player;

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
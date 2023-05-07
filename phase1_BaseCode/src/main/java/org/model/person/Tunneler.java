package org.model.person;

import org.model.Empire;

public class Tunneler extends Person {

    private static final int requiredGold = 0;

    public Tunneler(Empire personOwner) {
        super(personOwner, 0);
    }

    public static int getRequiredGold(){
        return requiredGold;
    }
}

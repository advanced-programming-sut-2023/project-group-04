package org.model.person;

import org.model.Empire;
import org.model.Map;
import org.model.MapCell;
import org.model.Player;

import java.util.ArrayList;
import java.util.Objects;

public class Person {

    private final Empire personOwner;
    private final int hp;
    private MapCell mapCell;
    private MapCell currentDestination;
    private MapCell nextDestination;

    public Person(Empire personOwner, int hp, MapCell mapCell) {
        this.personOwner = personOwner;
        this.hp = hp;
        this.mapCell = mapCell;
    }

    public Empire getPersonOwner() {
        return personOwner;
    }

    public int getHp() {
        return hp;
    }

    public MapCell getMapCell() {
        return mapCell;
    }

    public void changePosition(MapCell mapCell) {
        this.mapCell = mapCell;
    }

    public MapCell getCurrentDestination() {
        return currentDestination;
    }

    public void setCurrentDestination(MapCell currentDestination) {
        this.currentDestination = currentDestination;
    }

    public void setNextDestination(MapCell nextDestination) {
        this.nextDestination = nextDestination;
    }

    public void swapDestinations() {
        MapCell temp = nextDestination;
        nextDestination = currentDestination;
        currentDestination = temp;
    }
}
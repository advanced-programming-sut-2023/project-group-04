package org.model.person;

import org.model.Empire;
import org.model.MapCell;

public class Person {

    private final Empire personOwner;
    private int hp;
    private MapCell mapCell;
    private MapCell currentDestination;
    private MapCell nextDestination;
    private int speed;

    public Person(Empire personOwner, int hp, MapCell mapCell, int speed) {
        this.personOwner = personOwner;
        this.hp = hp;
        this.mapCell = mapCell;
        this.currentDestination = null;
        this.nextDestination = null;
        this.speed = speed;
    }

    public Person(Empire personOwner, MapCell mapCell) {
        this.personOwner = personOwner;
        this.hp = 0;
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

    public MapCell getNextDestination() {
        return nextDestination;
    }

    public void setNextDestination(MapCell nextDestination) {
        this.nextDestination = nextDestination;
    }

    public void swapDestinations() {
        MapCell temp = nextDestination;
        nextDestination = currentDestination;
        currentDestination = temp;
    }

    public int getSpeed() {
        return speed;
    }

    public void damagePerson(int hp) {
        this.hp -= hp;
    }
}
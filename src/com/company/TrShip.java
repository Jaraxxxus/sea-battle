package com.company;

public abstract class TrShip {
    public FieldShip field;
    public  TrShip(Ships ship) {
        this.field = ship.field;
    }
    abstract public boolean Ship(int m, int n);
    abstract public boolean Border(int m, int n);
}

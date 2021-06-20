package com.company;

public class TrShipSet extends TrShip {
    private Ships ship;
    public TrShipSet(Ships ships) {
        super(ships);
        this.ship = ships;

    }

    @Override
    public boolean Ship(int m, int n) {
        field.setElement(m, n, ElementState.enWell);
        ship.elements.add(field.battleMap[m][n]);
        field.battleMap[m][n].ship = ship;
        return true;
    }

    @Override
    public boolean Border(int m, int n) {
        field.setElement(m, n, ElementState.enBorder);
        return true;
    }
}

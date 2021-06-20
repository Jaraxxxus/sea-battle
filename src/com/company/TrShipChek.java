package com.company;

public class TrShipChek extends TrShip {
    public TrShipChek(Ships ships) {
        super(ships);
    }

    @Override
    public boolean Ship(int m, int n) {
        ElementState state = field.getElement(m, n);
        return (state == ElementState.enWater);
    }

    @Override
    public boolean Border(int m, int n) {
        ElementState state = field.getElement(m, n);
        return (state == ElementState.enBorder)||
                (state == ElementState.enWater) ||
                (state == ElementState.enEmpty);
    }


}

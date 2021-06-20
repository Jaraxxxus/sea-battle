package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Ships {
    public int x, y;
    public int dx, dy;
    public int sizeShip;
    public int health;
    public ShipState state;
    public FieldShip field;
    public ArrayList <Element> elements;

    /**
     * create ship
     * @param field
     * @param size
     */
    Ships(FieldShip field, int size){
        this.sizeShip = size;
        this.health = size-1;
        this.field = field;
        this.state = ShipState.enWell;
        //System.out.printf(" health %d \n", this.health);

        do {
            this.getPlace();
        }while (!this.checkPlace());
        this.elements = new ArrayList<Element>();
        this.setShip();
    }

    private void getPlace() {
        Random rand = new Random();
        this.x = rand.nextInt(10);
        this.y = rand.nextInt(10);
        this.dx = 0;
        this.dy = 0;
        if( rand.nextInt(2) == 1) {
            this.dx = 1;
        }
        else {
            this.dy = 1;
        }
    }

    private boolean byPass(TrShip tp){
        int i, m, n;
        //ship
        for(i = 0; i < sizeShip; i++){
            m = y + i*dy;
            n = x + i*dx;
            if (!tp.Ship(m,n)){
                return false;
            }
        }
        // place above and below ship
        for(i = 0; i < sizeShip; i++){
            m = y + i*dy -dx;
            n = x + i*dx -dy;
            if (!tp.Border(m,n)){
                return false;
            }
            m = y + i*dy +dx;
            n = x + i*dx +dy;
            if (!tp.Border(m,n)){
                return false;
            }
        }
        // place left and right ship
        for(i = -1; i < 2; i++){
            m = y + i*dx -dx;
            n = x + i*dy -dy;
            if (!tp.Border(m,n)){
                return false;
            }
            m = y + i*dx +dy*sizeShip;
            n = x + i*dy +dx*sizeShip;
            if (!tp.Border(m,n)){
                return false;
            }
        }
        return true;

    }


    private boolean checkPlace() {
        return byPass(new TrShipChek(this)) ;

        }



    /**
     *
     *
     */

    private void setShip() {
        byPass (new TrShipSet(this));
    }

}

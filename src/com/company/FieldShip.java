package com.company;

import java.util.ArrayList;

public class FieldShip {
    public Element[][] battleMap;
    public ArrayList<Ships> ships;
    public String[][] rivalMap =  new String[10][10];

    /**
     * create battle`s map
     */
    public FieldShip(){
        battleMap = new Element[10][10];
        for(int j = 0; j <10; j++){
            for(int i = 0; i <10; i++){
                battleMap[j][i] = new Element(i, j);
                rivalMap[j][i] = " " ;
            }
        }
        this.putShip();
    }

    public boolean isBound(int x, int y){
        return !((x < 0) || (x > 9) || (y < 0) || (y > 9));
    }


    public ElementState getElement(int x, int y){
        if(isBound(x, y)){
            return battleMap[x][y].state;
        }
        else {
            return  ElementState.enEmpty;
        }

    }


    public void setElement(int x, int y, ElementState state){
        if(isBound(x, y)){
             battleMap[x][y].state = state;
        }
            //return  true;
    }


    public void putShip() {
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                Element elem = battleMap[j][i];
                elem.shouted = false;
                elem.state = ElementState.enWater;
            }
        }
        ships = new ArrayList<Ships>();
        for (int i = 4; i > 0; i--) {
            for (int j = (5 - i); j > 0; j--) {

                Ships ship = new Ships(this, i+1);
                ships.add(ship);

            }
        }
        //del around ship
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Element elem = battleMap[j][i];
                if (elem.state == ElementState.enBorder) {
                    elem.state = ElementState.enWater;
                }
            }
        }
    }


    public boolean doShoot(int x, int y){
        boolean shoot = false;
        ElementState state = this.getElement(x, y);
        battleMap[x][y].shouted = true;

        if(state == ElementState.enWell){
            shoot = true;
            Ships ship = battleMap[x][y].ship;
            if(ship.health != 0){
                ship.health--;
                if (ship.health == 0) {
                    ship.state = ShipState.enKilled;
                    for(Element e : ship.elements){
                        e.state = ElementState.enKilled;

                    }

                }else {
                    ship.state = ShipState.enInjured;
                    battleMap[x][y].state= ElementState.enInjured;
                }
            }
        }else {
            //if((state == ElementState.enBorder) ||
              //      (state == ElementState.enWater)) {
                this.setElement(x, y, ElementState.enMissed);
           // }
            //rivalMap[x][y] = "/";
        }
        return shoot;
    }

    //обращение к полю своего игрока, но выстрел к полю чужого
    public void correctRivalMap(Element e){
        int dx1 = e.x-1;
        int dx2 = e.x+1;
        int dy1 = e.y-1;
        int dy2 = e.y+1;
        if (dx1 < 0) dx1 = e.x;
        if (dx2 > 9) dx2 = e.x;
        if (dy1 < 0) dy1 = e.y;
        if (dy2 > 9) dy2 = e.y;


        if (rivalMap[e.y][dx1].equals(" "))
           rivalMap[e.y][dx1] = "/";
        if (rivalMap[e.y][dx2].equals(" "))
           rivalMap[e.y][dx2] = "/";

        if (rivalMap[dy2][dx1].equals(" "))
           rivalMap[dy2][dx1] = "/";
        if (rivalMap[dy2][dx2].equals(" "))
            rivalMap[dy2][dx2] = "/";

        if (rivalMap[dy1][e.x].equals(" "))
           rivalMap[dy1][e.x] = "/";

        if (rivalMap[dy2][e.x].equals(" "))
            rivalMap[dy2][e.x] = "/";


        if (rivalMap[dy1][dx1].equals(" "))
            rivalMap[dy1][dx1] = "/";
        if (rivalMap[dy1][dx2].equals(" "))
            rivalMap[dy1][dx2] = "/";



    }


}

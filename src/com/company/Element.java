package com.company;

public class Element {
    public ElementState state;
    public Ships ship;
    public boolean shouted;
    public int x, y;

    public Element(int x, int y){
        this.state = ElementState.enWater;
        this.shouted = false;
        this.x = x;
        this.y = y;
    }
}

package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NPS {
    public FieldShip field;
    public boolean onTarget = false;
    private int x, y;
    private boolean directX = true;
    private boolean increaseCord = true;
    private final int[][] indRand = new int [10][10];
    Random rand;

    public List<Cord> cord = new ArrayList<>();
    private final ArrayList<Integer> arrX = new ArrayList<>();
    private final ArrayList<Integer> arrY = new ArrayList<>();
    public NPS(FieldShip field){
        this.field = field;
        this.rand = new Random();

    }


    public  int target() {

        if (cord.size() == 0) {
            int loop = 0;
            int find = 0;
            while (true) {
                x = rand.nextInt(9);
                y = rand.nextInt(9);
                if(indRand[x][y] == 0){
                    indRand[x][y] = 1;
                    break;
                }else
                    loop++;
                //System.out.println(" not target ");
                if(loop > 11) {

                    for (int i = 0 ; i < 10; i++) {
                        for (int j = 0; j < 10; j++) {
                            if(indRand[i][j] == 0) {
                                x = i;
                                y = j;
                                find = 1;
                                return 1;
                            }
                        }
                    }
                    if (find == 0){
                        return 0;
                    }
                }
            } // while
        }

        else {
            if ((cord.size() > 0) && (arrX.size() == 0) && (arrY.size() == 0)) {
                //System.out.println("  target 1 ");
                aroundFire();
            } else {
             //   notTarget = 0;
                //System.out.println("  target direct ");
                fireToEnd();
            }
        }
        indRand[x][y] =1;

      //  return tryShot();
        return 1;

    }

    private void fireToEnd() {


        if(onTarget ) { //попали, сохраним направление

           // ВЫБОР ТОЧКИ
            if (directX) {
                if(arrX.size()>0) {
                    arrX.remove(0);
                }
                if(increaseCord){
                    x++;
                    if (x > 9) {
                        x = x-2;
                        increaseCord = false;
                    }


                } else {
                    x--;
                    if (x < 0) {
                        x = x+2;
                        increaseCord = true;
                    }
                }

            } else {
                if(arrX.size()>0) {
                    arrX.remove(0);
                }
                if(increaseCord){
                    y++;
                    if (y > 9) {
                        y = y-2;
                        increaseCord = false;
                    }
                } else {
                    y--;
                    if (y < 0) {
                        y = y+2;
                        increaseCord = true;
                    }
                }

            }

        }else{  //НЕ ПОПАЛИ
            if(arrX.size() == 0){
                directX = false;
            }


            if(directX) {

               // System.out.println(" directX ");
                if (arrX.size() > 0) {
                   // System.out.println(" new X ");
                    x = arrX.get(0);
                    arrX.clear();
                    increaseCord = !increaseCord;
                } else {
                    directX = false;
                   // System.out.println(" change directX ");
                    x = cord.get(0).getX(); //вернуть координату

                    if(arrY.size()>0) {
                      //  System.out.println(" new Y ");
                        y = arrY.get(0);
                        arrY.remove(0);
                        increaseCord = false;
                        if(arrY.size() == 0) {
                            increaseCord = true;
                        }
                    }
                }


            } else  {//!directX

                x = cord.get(0).getX(); //вернуть координату
                y = cord.get(0).getY(); //вернуть координату
                if (arrY.size()>0) {
                    y = arrY.get(0);
                    arrY.remove(0);
                    increaseCord = false;
                    if(arrY.size() == 0) {
                        increaseCord = true;
                    }
                } else {

                    if(cord.size()>0){
                        cord.remove(0);
                        onTarget = false;
                    }
                }

            }

        } //

    }

    private void aroundFire() {
        //определить направления для дальнейшего обстрела
        x = cord.get(0).getX();
        y = cord.get(0).getY();
        increaseCord = true;
        if(x-1 >= 0 ){
            arrX.add(x-1);
            increaseCord = false;
        }
        if(x+1 <= 9){
            arrX.add(x+1);
        }
        if(y-1 >= 0 ){
            arrY.add(y-1);
        }
        if(y+1 <= 9){
            arrY.add(y+1);
        }
        x = arrX.get(0);
        arrX.remove(0);
        directX = true;


    }

    public  int getX() {
        return this.x;
    }
    public  int getY() {
        return this.y;
    }

}
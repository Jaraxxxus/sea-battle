package com.company;

import java.util.List;

/**
 * Отображение всей информации на экран
 */

public class ConsoleApplic {
   //private

    public ConsoleApplic(){
       /* System.out.println("  0123456789");
        for (int i = 0; i < 10; i++){
            System.out.printf(" %d", i);
            for(int j = 0; j < 10; j++){
                System.out.printf(elements[i][j]);
            }
            System.out.println("");
        }*/


    }
    public void ShouBattle(ComGame game) {
        System.out.println("  0123456789           0123456789");
        String elem;
        for (int i = 0; i < 10; i++){
            System.out.printf(" %d", i);
            for(int j = 0; j < 10; j++){
                if (game.playerOne.battleMap[i][j].state == ElementState.enWell) {
                    elem = "░"; //""□";
                    //elem = "O";
                    //elem = "۝"; //""۞";

                } else if (game.playerOne.battleMap[i][j].state == ElementState.enKilled||
                        game.playerOne.battleMap[i][j].state == ElementState.enInjured)    {
                    elem = "X";
                }else if  (game.playerOne.battleMap[i][j].state == ElementState.enMissed) {
                    elem = "/";
                }
                else {
                    elem = " ";
                }
               System.out.print(elem);

            }
            //--отображение поля соперника
            System.out.printf("          %d", i);
            for(int j = 0; j < 10; j++){

                System.out.print(game.playerOne.rivalMap[i][j]);

            }

            //---
            System.out.println("");
        }

    }




    public void showAbout(List<String> aboutGame) {
        for(String s:aboutGame){
            System.out.println(s);
        }
    }

    public void showTableRecord() {



    }

    public void showRecordTable(List<String> tableRecord) {
        for(String s:tableRecord){
            System.out.println(s);
        }
    }
}

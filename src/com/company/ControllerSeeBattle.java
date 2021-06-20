package com.company;

import javax.swing.*;
import java.util.Scanner;

/**
 * Controller- осуществляет взаимодействие пользователя с системой
 * интерпретирует команду в набор действий
  */




public class ControllerSeeBattle {


    public static void main(String[] args) {
        boolean exitGame = false;
        //boolean rightParam = true;
        // write your code here

        //ConsoleApplic consoleApplic = new ConsoleApplic();



        ComGame modelWar = new ComGame();
        WindowApplic wnd = new WindowApplic(modelWar);


        //ComScore score = new ComScore();
        //modelWar.setScore(score);
        //ConsoleApplic consoleApplic = new ConsoleApplic();
        /*javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                WindowApplic gr = new WindowApplic();
            }
         });*/



  /*
        boolean bNeedInf = false;
        try (Scanner in = new Scanner(System.in)) {
            while (!exitGame) {
                System.out.print("Input command:[exit, about, new game, high score] ");
                if (in.hasNextLine()) {
                    String userCommand = in.nextLine();
                    //----------
                    if ("exit".equals(userCommand.toLowerCase())) {
                        exitGame = true;
                    } else if ("about".equals(userCommand.toLowerCase())) {
                        ComAbout about = new ComAbout();
                        consoleApplic.showAbout(about.aboutGame);
                    } else if ("high score".equals(userCommand.toLowerCase())) {
                        //ComScore score = new ComScore();
                        //modelWar.setScore(score);
                        consoleApplic.showRecordTable(modelWar.score.tableRecord);
                    } else if ("new game".equals(userCommand.toLowerCase())) {


                    //---------------
                        modelWar.newGame();
                        while (!exitGame) {
                            consoleApplic.ShouBattle(modelWar);
                            System.out.print("Введите координаты [1-2]: ");
                            userCommand = in.nextLine();
                            while (!modelWar.play(userCommand)) {
                                if ("exit".equals(userCommand.toLowerCase()) ) {
                                    exitGame = true;
                                    break;
                                }
                                if (modelWar.endGame){
                                    System.out.println(" Game over ");
                                    break;
                                }
                                //==вызов метода отображения
                                System.out.print("Введите координаты [1-2]: ");
                                userCommand = in.nextLine();

                            }
                            //consoleApplic.ShouBattle(modelWar);
                            //== Shot
                        }
                        //consoleApplic.showRecordTable(modelWar.recordTable);

                    }

                }
            } //while
        } //try

*/
    }
}

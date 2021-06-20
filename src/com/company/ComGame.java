package com.company;

import java.io.*;
import java.util.TreeSet;

public class ComGame /*implements Command */{

    public  FieldShip playerOne;
    public FieldShip  playerTwo;
    public NPS nps;
    public int currPlayer;
    public boolean endGame;
    private boolean badParams;
    private int steps = 0;
    public ComScore score;

    public ComGame(){
      this.currPlayer = 0;
      playerOne = new FieldShip();
      playerTwo = new FieldShip();
      nps = new NPS(playerTwo);
      score = new ComScore();


    }

    public void newGame(){
        badParams = false;
        playerOne.putShip();
        playerTwo.putShip();
        endGame = false;
    }

    public void throwYourBomb(int x, int y){
        if (playerTwo.ships.size() == 0 || playerOne.ships.size() == 0) {
            endGame = true;
        } else {
            //System.out.printf(" CurPlayer  %d \n", currPlayer);
           // System.out.printf(" Ships %d \n", playerOne.ships.size());
            if (currPlayer == 0) { // You
                if (!playerTwo.doShoot(x, y)) {
                    currPlayer = 1;
                    playerOne.rivalMap[x][y] = "/";
                } else { //попали
                    playerOne.rivalMap[x][y] = "X";
                    Ships ship = playerTwo.battleMap[x][y].ship;
                    if (ship.state == ShipState.enKilled) { //обход для прорисовки черточек
                        playerTwo.ships.remove(ship);
                     //   System.out.println(" remove ship ");
                        for (Element e : ship.elements) {
                            playerOne.correctRivalMap(e);

                        }
                    }

                }
                if(playerOne.ships.size() == 0) {
                    endGame = true;
                    currPlayer = 1;
                }
            } //YOU
            if (currPlayer == 1) { //rival  or NPS
                int circle = 0;
                //System.out.printf(" CurPlayer  %d \n", currPlayer);
                //System.out.printf(" Ships %d \n", playerTwo.ships.size());
                //boolean doIt = true;
                while (!endGame) {
                    if (playerTwo.ships.size() == 0) {
                      //  doIt = false;
                        endGame = true;
                        currPlayer = 0;
                        break;
                    }
                    int find = nps.target();  //получение координат
                    if (find == 0) {
                        endGame = true;
                    }else {
                        int nx = nps.getX();
                        int ny = nps.getY();
                        //   System.out.printf(" x-%d y-%d \n", ny, nx);
                        nps.onTarget = false;
                        if (!(playerTwo.rivalMap[ny][nx].equals("/")) &&
                                !(playerTwo.rivalMap[ny][nx].equals("X"))) {
                            if (!playerOne.doShoot(ny, nx)) {
                                currPlayer = 0;
                                playerTwo.rivalMap[ny][nx] = "/";


                                //doIt = false;
                                break;
                            } else {  //выстрел удачный
                                playerTwo.rivalMap[ny][nx] = "X";
                                Cord cr = new Cord(nx, ny);
                                nps.cord.add(cr);
                                nps.onTarget = true;
                                Ships ship = playerOne.battleMap[ny][nx].ship;
                                if (ship.state == ShipState.enKilled) { //обход для прорисовки черточек
                                    playerOne.ships.remove(ship);
                                    nps.cord.clear();
                                    for (Element e : ship.elements) {
                                        playerTwo.correctRivalMap(e);


                                    }
                                }

                            }
                        } else { //попал в место, которое уже обстреливал
                            //doIt = true;
                            circle++;

                            if (circle > 99) {
                                currPlayer = 0;
                                circle = 0;
                                break;
                            }
                        }
                    }
                }// while doIt
            }
        }
    }


    /*@Override
    public void execute(int mode) {


    }*/

    public boolean play(String stringOfCommand) {
        int x = 0, y = 0;

        badParams = false;
        //парсить строку
        String strArgs;
        String[] inputArgs = stringOfCommand.split("-", 2);
        if (inputArgs.length >= 2) {
        } else { //уходим сразу на неправильный ввод
            badParams = true;
        }

        try {
            x = Integer.parseInt(inputArgs[0]);
            y = Integer.parseInt(inputArgs[1]);
        }
        catch (NumberFormatException nfe) {
            badParams = true;
        }
        if (x < 0 || x > 9 || y < 0|| y > 9) {
            badParams = true;
        }

        if (badParams ) {
            return false;
        }
        throwYourBomb(x, y);

        steps++;
        if (playerTwo.ships.size() == 0 || playerOne.ships.size() == 0) {
            endGame = true;
            int winner = playerOne.ships.size() > 0 ? 0 : 1;
            //заполнить таблицу рекордов
            //System.out.println("!!!");
            fillRecord(winner, steps);
           //return
        }

        return !badParams;

    }

    private void fillRecord(int winner, int steps) {
// запись всей строки
        String player ;
        if (winner == 1){
            player = "computer";
        }else{
            player = "player1";
        }
        String newLine = System.getProperty("line.separator");

        PairStat pair = new PairStat((int)(100/steps * 10), player);
        if (score.recTbl == null){
            score.recTbl = new TreeSet<>() ;
        }
        this.score.recTbl.add(pair);

        //--
        try(FileWriter writer = new FileWriter("recordTable.txt", false))
        {


            for (PairStat p : score.recTbl) {

                Integer ball = p.getScore();
                String text = ball.toString();
                writer.append(text);
                writer.append(" ");
                writer.append(p.getPlayer());
                writer.append(newLine);

            }
            writer.flush();

        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }


}

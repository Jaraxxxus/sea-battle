package com.company;

import java.io.*;
import java.util.ArrayList;

import java.util.List;


public class ComAbout /*implements Command*/ {

    public List< String> aboutGame = new ArrayList<>();
    public ComAbout() {
        BufferedReader reader = null;

       try {
            File file = new File("about.txt");
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            while (line != null) {
                //==файл в формате символ пробел код морзе
               // String[] retval = line.split(" ");

                this.aboutGame.add(line);

                // считываем остальные строки в цикле
                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        /*
        try (FileWriter writer = new FileWriter("statistic.txt", false)) {
                for (PairStat p : freq) {
                    // System.out.printf(" %c - %d \n", p.getKey(), p.getValue());
                    // запись  строки
                    Integer i = p.getValue();
                    String text = i.toString();
                    writer.append(p.getKey());
                    writer.append("->");
                    writer.append(text);
                    writer.append("\n");

                }
                writer.flush();
                writer.close();
            } catch (IOException ex) {

                System.out.println(ex.getMessage());
            }
        * */

    }



  /*  @Override
    public void execute(int mode) {
      // передача режима вывода игры, что выводим
    }*/
}

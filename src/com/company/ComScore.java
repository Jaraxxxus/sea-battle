package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class ComScore /*implements Command*/ {
    public List<String> tableRecord = new ArrayList<>();
    public TreeSet<PairStat> recTbl ;
    public ComScore() {


        TreeSet<PairStat> recTbl = new TreeSet<>() ;
        //System.out.println("try Append ");

        BufferedReader reader = null;

        try {
            File file = new File("recordTable.txt");
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            while (line != null) {
                String[] splitval = line.split(" ");
                int ball = Integer.parseInt(splitval[0]);
                PairStat pr = new PairStat(ball, splitval[1] );
                recTbl.add(pr);
                //System.out.println("Append ");
                //this.tableRecord.add(line);

                // считываем остальные строки в цикле
                line = reader.readLine();
            }

            //== Для правильного порядка отдельно занесем в порядке сортировки
            for (PairStat e : recTbl) {
                String str = Integer.toString((e.getScore())) + "    " + e.getPlayer(); //+"\n";
                this.tableRecord.add(str);
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
    }

   /* @Override
    public void execute(int mode) {


    }*/
}


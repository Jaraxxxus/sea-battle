package com.company;

public final class PairStat implements Comparable <com.company.PairStat> {
    private Integer scorevalue;
    private String player;


    public PairStat(Integer ascorevalue, String aplayer ) {
        this.player = aplayer;
        this.scorevalue = ascorevalue;
    }

    public PairStat() {
        //  this.key = null;
        //  this.value = 0;
    }

    public String getPlayer() {
        return player;
    }

    public Integer getScore() {
        return scorevalue;
    }

    public void setScore(Integer ascore) {
        this.scorevalue = ascore;
    }

    public void setPlayer(String aplayer) {
        this.player = aplayer;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + player.hashCode();

        return result;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        // если передан объект другого класса, считаем, что он не равен данному

        PairStat pair = (PairStat) o;
        // сравниваем значения поля

        //return this.player.equals(pair.player);
        return this.scorevalue.equals(((PairStat) o).scorevalue) &&
                this.player.equals(((PairStat)o).player);

    }


    @Override
    public int compareTo(PairStat o) {
        int ret = 0;
        if (scorevalue > o.scorevalue) ret = -1;
        else if (scorevalue < o.scorevalue) ret = 1;
        return  ret;
        //return 0;
    }
}





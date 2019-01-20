package com.example.battleshipproject.pacificbattleship;

public class Competitor {


    private String name;
    private String score;
    private String rank;

    public Competitor(String name, String score,String rank){
        this.name=name;
        this.score=score;
        this.rank=rank;
    }

    public String getName() {
        return name;
    }

    public String getScore() {
        return score;
    }

    public String getRank() {
        return rank;
    }
}

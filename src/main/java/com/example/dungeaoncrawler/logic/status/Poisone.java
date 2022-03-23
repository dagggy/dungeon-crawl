package com.example.dungeaoncrawler.logic.status;

public class Poisone {
    private int rounds;
    private int loseLife;

    public Poisone(int rounds, int gainLife) {
        this.rounds = rounds;
        this.loseLife = gainLife;
    }

    public int getRounds() {
        return rounds;
    }

    public int getGainLife() {
        return loseLife;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public void setGainLife(int gainLife) {
        this.loseLife = gainLife;
    }
}

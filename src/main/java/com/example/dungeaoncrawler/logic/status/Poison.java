package com.example.dungeaoncrawler.logic.status;

public class Poison {
    private int rounds;
    private int loseLife;

    public Poison(int rounds, int loseLife) {
        this.rounds = rounds;
        this.loseLife = loseLife;
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

    public void setGainLife(int loseLife) {
        this.loseLife = loseLife;
    }
}

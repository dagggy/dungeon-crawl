package com.example.dungeaoncrawler.logic.status;

public class Heal {
    private int rounds;
    private int gainLife;

    public Heal(int rounds, int gainLife) {
        this.rounds = rounds;
        this.gainLife = gainLife;
    }

    public int getRounds() {
        return rounds;
    }

    public int getGainLife() {
        return gainLife;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public void setGainLife(int gainLife) {
        this.gainLife = gainLife;
    }
}

package com.josephvanderzwart.antworld.game;

public class Bonus extends Entity{

    //main progression for each tick:
    @Override
    public void grow() {
        //TODO add depletion of resources
        this.setStrength(this.getStrength());
    }

    public Bonus(boolean isPlayer, boolean inEvil, int inS) {
        super(isPlayer, inEvil, inS);
    }
}

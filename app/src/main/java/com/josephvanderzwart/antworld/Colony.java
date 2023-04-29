package com.josephvanderzwart.antworld;

import java.util.Random;

public class Colony {

    private int ants;
    private int food;
    private int queens;
    private int growth;
    private boolean enoughFood;
    private int xPos;
    private int yPos;
    //TODO  modifiers, key, value pair ??

    public Colony(int inQueens, int inAnts) {
        setup(inAnts, inQueens);
    }

    public Colony() {
        setup(10,1);
    }

    private void setup(int inAnts, int inQueens) {
        Random random = new Random();
        this.ants = inAnts;
        this.queens = inQueens;
        this.food = 1;
        this.growth = 1;
        this.xPos = random.nextInt(10);
        this.yPos = random.nextInt(10);
    }
    //main progression for each tick.
    public void grow() {

//        if (food >= (ants / 10)) {
//            enoughFood = true;
//        }
//        else enoughFood = false;
        //TODO implement food system
        enoughFood = true;

        //growth calculation:
        growth = 0 + queens;

        //size cap 1mil:
        if (ants < 1000000) {
            //enough food = normal growth
            if (enoughFood) {
                ants = ants + (1 * growth);
            }
            //else growth penalty
            else {
                ants = ants + (1 * (growth-1));
            }
        }

        //TODO food system
        // eat one food if present
        if (food > 0) {food -= 1;}
    }

    public void handleEvent() {
        //TODO unique events.
    }

    public void addGrowthMod() {
        //TODO add growth mods
    }

    public int getAnts() {
        return ants;
    }

    public String getZeroPaddedAnts() {
        String antsString = "" + ants;
        String result = "0000000";
        return result.substring(0,8-antsString.length()) + antsString;
    }

    public int getFood() {
        return food;
    }

    public int getQueens() {
        return queens;
    }

    public int getGrowth() {
        return growth;
    }

    public void killAnts(int kill) {
        ants = ants - kill;
    }

    public void addQueen() {queens = queens + 1; }

    public int getxPos() {return xPos;}

    public int getyPos() {return yPos;}
}

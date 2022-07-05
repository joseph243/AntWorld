package com.josephvanderzwart.antworld;

public class Colony {

    private int ants;
    private int food;
    private int queens;
    private int growth;
    private boolean enoughFood;
    //TODO  modifiers, key, value pair ??

    public Colony(int inQueens, int inAnts) {
        this.ants = inAnts;
        this.queens = inQueens;
        this.food = 1;
        this.growth = 1;
    }

    public Colony() {
        this.ants = 10;
        this.queens = 1;
        this.food = 1;
        this.growth = 1;
    }

    //main progression for each tick.
    public void grow() {

//        if (food >= (ants / 10)) {
//            enoughFood = true;
//        }
//        else enoughFood = false;
        //TODO implement food system
        enoughFood = true;

        //growth cap 1mil:
        if (ants < 1000000) {
            //enough food = normal growth
            if (enoughFood) {
                ants = ants + (queens * growth);
            }
            //else growth penalty
            else {
                ants = ants + (queens * (growth-1));
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
}

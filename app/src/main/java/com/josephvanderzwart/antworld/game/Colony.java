package com.josephvanderzwart.antworld.game;


public class Colony extends Entity{

    private int ants;
    private int food;
    private int queens;
    private int growth;

    public Colony(boolean isPlayer, boolean isEvil, int inQueens, int inAnts) {
        super(isPlayer, isEvil, inAnts/10000);
        setup(inAnts, inQueens);
    }

    private void setup(int inAnts, int inQueens) {
        this.ants = inAnts;
        this.queens = inQueens;
        this.food = 1;
        this.growth = 1;
    }
    //main progression for each tick.
    public void grow() {

        //TODO implement food system

        //growth calculation:
        growth = 0 + queens;

        //growth operation:
        if (ants < 1000000) {
            ants = ants + (1 * growth);
        }

        //strength calculation:
        this.setStrength(ants/10000);
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

}

package com.josephvanderzwart.antworld.game;

public class Entity {
    private int strength;
    private boolean isEvil;
    private boolean isPlayer = false;
    private int x;
    private int y;
    private int xy;

    public void setLoc(int worldLoc)
    {
        this.xy = worldLoc;
        String coordinateStr = Integer.toString(worldLoc);
        if (worldLoc < 10)
        {
            coordinateStr = "0" + coordinateStr;
        }
        char[] xy = coordinateStr.toCharArray();
        this.x = Integer.valueOf(xy[0]);
        this.y = Integer.valueOf(xy[1]);
    }

    public int getLoc() { return xy; }
    public int getXPos()
    {
        return x;
    }

    public int getYPos()
    {
        return y;
    }

    public void grow()
    {
        //TODO
    };

    public boolean isEvil()
    {
        return isEvil;
    }

    public boolean isPlayer()
    {
        return isPlayer;
    }

    public void setStrength(int inStr)
    {
        if (inStr > 100)
        {
            inStr = 100;
        }
        strength = inStr;
    }

    public void setEvil(boolean inEvil)
    {
        this.isEvil = inEvil;
    }

    public Entity (boolean isPlayer, boolean inEvil, int inS)
    {
        this.strength = inS;
        this.isEvil = inEvil;
        this.isPlayer = isPlayer;
    }

    public int getStrength() {return strength;}

    @Override
    public String toString()
    {
        return ("Entity + " + this.getClass().getSimpleName().toString() +
                "Player: " + isPlayer + ", Evil: " + isEvil + ", Strength: " + strength)
                + "Location: " + this.getLoc();
    }

}

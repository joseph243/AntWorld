package com.josephvanderzwart.antworld;

public class Entity {
    private int strength;
    private int xPos;
    private int yPos;
    private boolean isEvil;
    private boolean isPlayer = false;

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

    public Entity (int inX, int inY, int inS, boolean inEvil, boolean isPlayer)
    {
        this.xPos = inX;
        this.yPos = inY;
        this.strength = inS;
        this.isEvil = inEvil;
        this.isPlayer = isPlayer;
        System.out.println("Entity create: evil, x,y, str, player" + isEvil + "," + xPos + ","
                + yPos + "," + strength + "," + isPlayer);
    }
    public int getStrength() {return strength;}

    public int getxPos() {return xPos;}

    public int getyPos() {return yPos;}

}

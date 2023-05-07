package com.josephvanderzwart.antworld;

import android.os.Bundle;
import android.os.Message;

import com.josephvanderzwart.antworld.activities.ColonyActivity;
import com.josephvanderzwart.antworld.activities.MapActivity;
import com.josephvanderzwart.antworld.activities.ScoreActivity;
import com.josephvanderzwart.antworld.game.*;

import java.util.List;
import java.util.Random;

public class GameRunner implements Runnable {

    private volatile boolean running = true;
    private Thread thread;
    public Message colonyMessage;
    public Message mapMessage;
    public Message scoreMessage;
    private WorldMap world;
    private Colony activeColony;

    public Entity getEntityAt(int coord) {
        return world.getEntityAt(coord);
    }

    public void stopRunning() { running = false; }

    public void run() {
        //POPULATE MAP:
        //player start defaults:
        world = new WorldMap();
        world.addToWorld(new Colony(true,false,1,300000));
        populateMap(3);

        //MAIN GAME LOOP
        while (running) {

            //game logic per tick:
            System.out.println("growing..");
            world.growEveryone();

            //TODO this is a hack to always show player colony:
            activeColony = (Colony) world.getPlayerHomeColony();

            //send data to various screens:
            buildMessageColony();
            buildMessageMap();
            buildMessageScore();

            //TICK WAIT TIME 1 SEC:
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {}

        }
    }

    public void setActiveColony(int index)
    {
        activeColony = (Colony) world.getEntityAt(index);
    }

    private void buildMessageColony()
    {
        try
        {
            colonyMessage = ColonyActivity.getHandler().obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putString("ants", activeColony.getZeroPaddedAnts());
            bundle.putString("queens", "Queens Count: " + activeColony.getQueens());
            bundle.putString("growth", "Growth: " + activeColony.getGrowth());
            bundle.putString("selectedEntityName", "MY COLONY");
            colonyMessage.setData(bundle);
            ColonyActivity.getHandler().sendMessage(colonyMessage);
        }
        catch (Exception e)
        {  }
        colonyMessage = null;
    }

    private void buildMessageScore() {
        try {
            scoreMessage = ScoreActivity.getHandler().obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putString("colonies", "Colonies: " + world.getList().size());
            bundle.putString("queens", "Queens Count: " + activeColony.getQueens());
            bundle.putString("growth", "Growth: " + activeColony.getGrowth());
            bundle.putString("selectedEntityName", "MY COLONY");
            scoreMessage.setData(bundle);
            ScoreActivity.getHandler().sendMessage(scoreMessage);
        } catch (Exception e)
        {   }
        scoreMessage = null;
    }

    private void buildMessageMap()
    {
        try
        {
            mapMessage = MapActivity.getHandler().obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putString("ants", activeColony.getZeroPaddedAnts());
            bundle.putString("queens", "Queens Count: " + activeColony.getQueens());
            bundle.putString("growth", "Growth: " + activeColony.getGrowth());
            bundle.putString("selectedEntityName", "MY COLONY");
            mapMessage.setData(bundle);
            MapActivity.getHandler().sendMessage(mapMessage);
        }
        catch (Exception e)
        {  }
        mapMessage = null;
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this, "Game Main");
            thread.start();
        }
    }

    private void populateMap(int inPopulation)
    {
        Random random = new Random();
        for (int i = 0; i < inPopulation; i++)
        {
            // random entity add:
            world.addToWorld(new Bonus(false, random.nextBoolean(), random.nextInt(100)));
        }
    }

    public Colony getActiveColony()
    {
        return activeColony;
    }

    public List getEntityList()
    {
        return world.getList();
    }
}

package com.josephvanderzwart.antworld;

import android.os.Bundle;
import android.os.Message;

import com.josephvanderzwart.antworld.activities.ColonyActivity;
import com.josephvanderzwart.antworld.game.*;

import java.util.List;
import java.util.Random;

public class GameRunner implements Runnable {

    private volatile boolean running = true;
    private Thread thread;
    public Message message;
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
        world.addToWorld(new Colony(true,false,1,10));
        populateMap(3);

        //MAIN GAME LOOP
        while (running) {

            //GAME TICK LOGIC:
            System.out.println("growing..");
            world.growEveryone();


            //TODO this is a hack to always show player colony:
            activeColony = (Colony) world.getPlayerHomeColony();



            //TICK WAIT TIME 1 SEC:
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {}



            //TODO all this should be moved to a generic sendmessage and
            //TODO not worry about what screen i'm on.  if screen != null etc.
            //handler attempt
            while (message == null) {
                try
                {
                    message = ColonyActivity.getHandler().obtainMessage();
                }
                catch (Exception e)
                {  }
            }
            Bundle bundle = new Bundle();
            bundle.putString("ants", activeColony.getZeroPaddedAnts());
            bundle.putString("queens", "Queens Count: " + activeColony.getQueens());
            bundle.putString("growth", "Growth: " + activeColony.getGrowth());
            bundle.putString("selectedEntityName", "MY COLONY");
            message.setData(bundle);
            ColonyActivity.getHandler().sendMessage(message);
            message = null;
        }
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

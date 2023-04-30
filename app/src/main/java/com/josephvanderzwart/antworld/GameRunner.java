package com.josephvanderzwart.antworld;

import android.os.Bundle;
import android.os.Message;

import com.josephvanderzwart.antworld.activities.ColonyActivity;
import com.josephvanderzwart.antworld.game.Colony;
import com.josephvanderzwart.antworld.game.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameRunner implements Runnable {

    private volatile boolean running = true;
    private Thread thread;
    public Message message;
    private Colony colony = null;
    private List<Entity> map = new ArrayList<>();
    private Entity playerentity;

    public Colony getColony() {
        return colony;
    }

    public void stopRunning() { running = false; }

    public void run() {
        colony = new Colony();
        playerentity = new Entity(colony.getxPos(), colony.getyPos(), 0, false, true);
        map.add(playerentity);
        populateMap(3);

        //MAIN GAME LOOP
        while (running) {
            colony.grow();
            //TODO This will move out at some point:
            //need to integrate entity and colony togetehr:
            playerentity.setStrength(colony.getAnts()/10000);
            refreshMap();

            //if (Events.randomEventOccurs) { handle event }



            //tick time = 1 second.
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {}



                //TODO all this should be moved to a generic sendmessage and
            //TODO not worry about what screen i'm on.  if screen != null etc.
                //handler attempt
                while (message == null) {
                    try {
                        System.out.println("");
                        System.out.println(ColonyActivity.getHandler());
                        message = ColonyActivity.getHandler().obtainMessage();
                    }
                    catch (Exception e)
                    {

                    }
                }
                Bundle bundle = new Bundle();
                bundle.putString("ants", colony.getZeroPaddedAnts());
                bundle.putString("queens", "Queens Count: " + colony.getQueens());
                bundle.putString("growth", "Growth: " + colony.getGrowth());
                message.setData(bundle);
                ColonyActivity.getHandler().sendMessage(message);
                message = null;
                //end attempt
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
            // random entity at x,y 0-10 and strength 0-100 and evil Y-N
            Entity e = new Entity(random.nextInt(10), random.nextInt(10),
                    random.nextInt(100), random.nextBoolean(), false);
            if (isVacant(e.getxPos(), e.getyPos()))
            {
                map.add(e);
            }
            else
            {
                i--;
            }
        }
    }

    private boolean isVacant(int inX, int inY)
    {
        for (Entity e : map)
        {
            if (e.getxPos() == inX && e.getyPos() == inY) {
                return false;
            }
        }
        return true;
    }

    private void refreshMap()
    {
        //getting concurrentModificationException when trying to modify 'map' here (in main loop).
        //need to pull out the map stuff into its own class i think.
    }

    public List<Entity> getEntityList()
    {
        return map;
    }
}

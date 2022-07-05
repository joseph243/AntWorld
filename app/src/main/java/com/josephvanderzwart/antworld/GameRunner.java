package com.josephvanderzwart.antworld;

import android.os.Bundle;
import android.os.Message;

public class GameRunner implements Runnable {

    private volatile boolean running = true;
    private int i = 0;
    private Thread thread;
    public Message message;

    public void run() {
        Colony colony = new Colony();

        //MAIN GAME LOOP
        while (running) {
            colony.grow();

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
            System.out.println("thread start: " + thread.getName());
        }
    }
}

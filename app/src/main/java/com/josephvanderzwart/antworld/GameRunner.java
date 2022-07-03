package com.josephvanderzwart.antworld;

import android.os.Bundle;
import android.os.Message;

public class GameRunner implements Runnable {

    private volatile boolean running = true;
    private int i = 0;
    private Thread thread;
    public Message message;

    public void run() {
        System.out.println("entering run");
        while (running) {
            i++;
            try {
                System.out.println("running: " + i);

                //handler attempt
                while (message == null) {
                    try {
                        message = Colony.getHandler().obtainMessage();
                    }
                    catch (Exception e)
                    {

                    }
                }
                Bundle bundle = new Bundle();
                bundle.putString("key", "Ants Count: " + i);
                message.setData(bundle);
                Colony.getHandler().sendMessage(message);
                message = null;
                //end attempt

                Thread.sleep(1000);
            }
            catch (InterruptedException e) {

            }
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

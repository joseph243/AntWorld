package com.josephvanderzwart.antworld;

public class GameRunner implements Runnable {

    private volatile boolean running = true;
    private int i = 0;
    private Thread thread;

    public void run() {
        System.out.println("entering run");
        while (running) {
            i++;
            try {
                System.out.println("running: " + i);
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

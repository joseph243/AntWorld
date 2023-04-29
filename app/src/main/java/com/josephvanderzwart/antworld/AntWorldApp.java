package com.josephvanderzwart.antworld;

import android.app.Application;

public class AntWorldApp extends Application {

    private static AntWorldApp antWorldApp;
    private GameRunner game = null;

    public static AntWorldApp getAppInstance() {
        return antWorldApp;
    }

    public void startGameRunner() {
        if (game == null) {
            game = new GameRunner();
            game.start();
        }
    }

    public void endGame(boolean winner) {
        game.stopRunning();
        //TODO export stats
        //TODO handle win/loss
        game = null;
    }

    public GameRunner getGameRunner() {
        return game;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        antWorldApp = this;
    }
}

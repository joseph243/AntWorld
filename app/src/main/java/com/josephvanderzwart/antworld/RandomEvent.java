package com.josephvanderzwart.antworld;

import java.util.concurrent.ThreadLocalRandom;

public class RandomEvent {

    private int getRand10() {
        return ThreadLocalRandom.current().nextInt(0, 10);
    }

    private int getRand100() {
        return ThreadLocalRandom.current().nextInt(0, 100);
    }

    public boolean random10Event() {
        return (getRand10() == 4);
    }

    public boolean random100Event() {
        return (getRand100() == 32);
    }





}

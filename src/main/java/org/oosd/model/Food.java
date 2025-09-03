package org.oosd.model;

import java.util.concurrent.ThreadLocalRandom;

public class Food extends GameEntity {

    private static final int MIN_LIFE = 5;
    private static final int MAX_LIFE = 15;
    private boolean isEaten = false;

    public Food() {
        super();
        setX(Math.random() * Game.fieldWidth);
        setY(Math.random() * Game.fieldHeight);
        if (Math.random() > 0.5) { // 50% can move
            setDx(Math.random() * 3.0 - 1.5);
            setDy(Math.random() * 3.0 - 1.5);
        }
        setSize(10);
        life = ThreadLocalRandom.current().nextInt(MIN_LIFE, MAX_LIFE);
    }

    @Override
    public boolean showLifeCountdown() {
        return true;
    }

    public void setIsEaten() {
        isEaten = true;
    }

    @Override
    public boolean isDead() {
        if (isEaten) return true;
        return super.isDead();
    }

    @Override
    void process() {
        processMove();
    }

    @Override
    public EntityType getType() {
        return EntityType.FOOD;
    }

    @Override
    boolean isBounce() {
        return false;
    }
}

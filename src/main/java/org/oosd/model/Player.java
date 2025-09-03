package org.oosd.model;


public class Player extends GameEntity {
    static final int MAX_SPEED = 5;

    public Player() {
        super();
        setSize(GameConfig.getInstance().getSize());
        setDx(1);
        setDy(1);
    }

    public void increaseX() {
        setDy(0);
        setDx(Math.min(getDx() + 1, MAX_SPEED));
    }

    public void decreaseX() {
        setDy(0);
        setDx(Math.max(getDx() - 1, -MAX_SPEED));
    }

    public void increaseY() {
        setDx(0);
        setDy(Math.min(getDy() + 1, MAX_SPEED));
    }

    public void decreaseY() {
        setDx(0);
        setDy(Math.max(getDy() - 1, -MAX_SPEED));
    }


    @Override
    void process() {
        processMove();
    }

    @Override
    public EntityType getType() {
        return EntityType.PLAYER;
    }
}

package org.oosd.model;

public class Player extends GameEntity {
    static final int MAX_SPEED = 5;

    public Player() {
        super();
        setSize(GameConfig.getInstance().getSize());
        setDX(1);
        setDY(1);
    }

    public void increaseX() {
        setDY(0);
        setDX(Math.min(getDX()+1, MAX_SPEED));
    }
    public void decreaseX() {
        setDY(0);
        setDX(Math.max(getDX()-1, -MAX_SPEED));
    }
    public void increaseY() {
        setDX(0);
        setDY(Math.min(getDY()+1, MAX_SPEED));
    }
    public void decreaseY() {
        setDX(0);
        setDY(Math.max(getDY()-1, -MAX_SPEED));
    }

    @Override
    void process() {processMove();}

    @Override
    public EntityType getType() {return EntityType.PLAYER;}
}

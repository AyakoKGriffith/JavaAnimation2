package org.oosd.model;

public class Message extends GameEntity {

    private final String message;

    public Message(String message, double x, double y) {
        super();
        setX(x);
        setY(y);
        setDx(0.5);
        setDy(-0.5);
        this.message = message;
        life = 3;
    }

    public String getMessage() {
        return message;
    }

    @Override
    void process() {
        processMove();
    }

    @Override
    public EntityType getType() {
        return EntityType.MESSAGE;
    }
}

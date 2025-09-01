package org.oosd.model;

public class Game {
    static public final double fieldWidth = 400;
    static public final double fieldHeight = 270;
    private double x = fieldWidth / 2, y = fieldHeight / 2;

    private double dx = 3;       // X velocity
    private double dy = 3;       // Y velocity
    static final int MAX_SPEED = 5;

    public double getX() {return x;}
    public double getY() {return y;}

    public void increaseX() {
        dy=0;
        dx = Math.min (dx+1, MAX_SPEED);
    }
    public void decreaseX() {
        dy = 0;
        dx = Math.max(dx-1,-MAX_SPEED);
    }
    public void increaseY() {
        dx=0;
        dy = Math.min (dy+1, MAX_SPEED);
    }
    public void decreaseY() {
        dx=0;
        dy = Math.max (dy-1, -MAX_SPEED);
    }

    public void proceed(){
        double r = GameConfig.getInstance().getSize();
        double nextX = x + dx;
        double nextY = y + dy;
        // Bounce off edges
        if (nextX - r < 0 || nextX + r > Game.fieldWidth) {
            dx = -dx;
        }
        if (nextY - r < 0 || nextY + r > Game.fieldHeight) {
            dy = -dy;
        }
        x += dx;
        y += dy;
    }
}

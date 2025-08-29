package org.oosd.model;

import javafx.scene.paint.Color;

public class Game {
    static public final double fieldWidth = 400;
    static public final double fieldHeight = 270;
    private double x = fieldWidth / 2, y = fieldHeight / 2;

    private double dx = 3;       // X velocity
    private double dy = 3;       // Y velocity
    static final int MAX_SPEED = 5;

    private boolean hasShadow = false;
    private String colorString = "RED";
    private int size = 10;

    public double getX() {return x;}
    public double getY() {return y;}

    public boolean isHasShadow() {return hasShadow;}
    public void setHasShadow(boolean hasShadow) {this.hasShadow = hasShadow;}

    public String getColorString() {return colorString;}
    public void setColorString(String colorString) {this.colorString = colorString;}

    public Color getColor() {
        return switch (colorString){
            case "RED" -> Color.RED;
            case "GREEN" -> Color.GREEN;
            case "BLUE" -> Color.BLUE;
            default -> Color.RED;
        };
    }

    public int getSize() {return size;}
    public void setSize(int size) {this.size = size;}

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
        double nextX = x + dx;
        double nextY = y + dy;
        // Bounce off edges
        if (nextX - size < 0 || nextX + size > Game.fieldWidth) {
            dx = -dx;
        }
        if (nextY - size < 0 || nextY + size > Game.fieldHeight) {
            dy = -dy;
        }
        x += dx;
        y += dy;
    }
}

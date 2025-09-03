package org.oosd.model;

import javafx.scene.paint.Color;

public class GameConfig {
    public static final int MAX_SIZE = 15;
    public static final int MIN_SIZE = 5;
    private int size;
    private String colorString;
    private boolean hasShadow;

    private GameConfig() {
        size = 10;
        colorString = "RED";
        hasShadow = false;
    }

    private static class InternalClass {
        private static final GameConfig instance = new GameConfig();
    }

    public static GameConfig getInstance() {
        return InternalClass.instance;
    }

    public Color getColor() {
        return switch (colorString) {
            case "BLUE" -> Color.BLUE;
            case "GREEN" -> Color.GREEN;
            default -> Color.RED;
        };
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getColorString() {
        return colorString;
    }

    public void setColorString(String colorString) {
        this.colorString = colorString;
    }

    public boolean isHasShadow() {
        return hasShadow;
    }

    public void setHasShadow(boolean hasShadow) {
        this.hasShadow = hasShadow;
    }
}

package org.oosd.controller;

import javafx.scene.input.KeyCode;
import org.oosd.model.Game;

public class GameController {
    private final Game game;

    public GameController(Game game) {
        this.game = game;
    }

    public void receiveKeyPress(KeyCode key) {
        switch (key) {
            case KeyCode.DOWN -> game.increaseY();
            case KeyCode.UP -> game.decreaseY();
            case KeyCode.RIGHT -> game.increaseX();
            case KeyCode.LEFT -> game.decreaseX();
        }
    }
}

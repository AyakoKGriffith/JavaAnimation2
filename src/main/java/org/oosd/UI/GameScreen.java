package org.oosd.UI;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.oosd.model.Game;

public class GameScreen implements ScreenWithGame {
    private Game game;
    private Pane gamePane;
    private BorderPane borderPane;
    private StackPane gameArea;
    private Screen mainScreen;
    private Frame parent;
    private AnimationTimer timer;
    private Circle ball;

    public GameScreen(Frame frame) {
        parent = frame;
        borderPane = new BorderPane();
        gameArea = new StackPane();
        borderPane.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                buildScreen();
                setControl(newScene);
                startGame();
            }
        });
    }

    private StackPane getTopPane(){
        // Size the logical playfield
        gameArea.setMinSize(Game.fieldWidth, Game.fieldHeight);
        gameArea.setPrefSize(Game.fieldWidth, Game.fieldHeight);
        gameArea.setMaxSize(Game.fieldWidth, Game.fieldHeight);
        gameArea.setFocusTraversable(false);

        // Create field border
        Rectangle field = new Rectangle(Game.fieldWidth, Game.fieldHeight);
        field.setFill(Color.TRANSPARENT);
        field.setStroke(Color.BLACK);

        // Create red ball
        ball = new Circle(game.getSize(), game.getColor());
        ball.setCenterX(Game.fieldWidth / 2);
        ball.setCenterY(Game.fieldHeight / 2);

        if (game.isHasShadow()) {
            DropShadow shadow = new DropShadow();
            shadow.setOffsetX(5);
            shadow.setOffsetY(5);
            ball.setEffect(shadow);
        }

        gameArea.getChildren().setAll(field, ball);
        return gameArea;
    }

    private StackPane getBottomPane(){
        Button backButton = new Button("Back");
        backButton.getStyleClass().add("menu-button");
        backButton.setFocusTraversable(false);

        backButton.setOnAction(e -> {
            timer.stop();
            parent.showScreen(mainScreen);
        });

        StackPane bottomPane = new StackPane(backButton);
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setPadding(new Insets(0, 0, 20, 0));
        bottomPane.setFocusTraversable(false);
        return bottomPane;
    }

    private void buildScreen() {
        StackPane top = getTopPane();
        BorderPane.setAlignment(top, Pos.TOP_CENTER);
        BorderPane.setMargin(top, new Insets(20, 20, 10, 20));
        StackPane bottom = getBottomPane();

        borderPane.setTop(top);
        borderPane.setBottom(bottom);
    }

    private void setControl(Scene scene) {
        // Key control
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                game.increaseY();
            } else if (e.getCode() == KeyCode.DOWN) {
                game.decreaseY();
            } else if (e.getCode() == KeyCode.LEFT) {
                game.decreaseX();
            } else if (e.getCode() == KeyCode.RIGHT) {
                game.increaseX();
            }
        });

    }

    private void startGame() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                game.proceed();
                ball.setCenterX(game.getX());
                ball.setCenterY(game.getY());
            }
        };
        timer.start();
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public Node getScreen() {
        return borderPane;
    }

    @Override
    public void setRoute(String path, Screen screen) {
        if ("back".equals(path)) {
            mainScreen = screen;
        }

    }
}

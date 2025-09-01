package org.oosd.UI;

import javafx.animation.AnimationTimer;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.oosd.UI.sprite.Sprite;
import org.oosd.UI.sprite.SpriteFactory;
import org.oosd.model.Game;
import org.oosd.model.GameConfig;

import java.util.ArrayList;
import java.util.List;

public class GamePane extends Pane {
    private AnimationTimer timer;
    private Circle ball;
    private Game game;

    private final List<Sprite> sprites;
    public GamePane() {sprites = new ArrayList<>();}

    public void setGame(Game game) {
        this.game = game;
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                game.proceed();
                ball.setCenterX(game.getX());
                ball.setCenterY(game.getY());
            }
        };
    }

    private void initSprites (int num){
        sprites.clear();
        for (int i=0; i<num; ++i){
            Sprite sprite = SpriteFactory.getFactory().createSprite();
            sprites.add(sprite);
        }
    }

    private void buildGamePane(){
        // Create field border
        Rectangle field = new Rectangle(0, 0, Game.fieldWidth, Game.fieldHeight);
        field.setFill(Color.TRANSPARENT);
        field.setStroke(Color.BLACK);

        // Create red ball
        ball = new Circle(
                GameConfig.getInstance().getSize(),
                GameConfig.getInstance().getColor()
        );
        if (GameConfig.getInstance().isHasShadow()) {
            DropShadow shadow = new DropShadow();
            shadow.setOffsetX(5);
            shadow.setOffsetY(5);
            ball.setEffect(shadow);
        }

        getChildren().setAll(field, ball);

        initSprites(10);
        for (Sprite sprite: sprites)
            getChildren().add(sprite.getNode());

        requestFocus();
    }
    void stopGame(){timer.stop();}

    void startGame(){
        buildGamePane();
        timer.start();
    }
}

import javafx.animation.AnimationTimer;

//package org.oosd.UI;
//
//import javafx.animation.AnimationTimer;
//import javafx.scene.control.Button;
//import javafx.scene.effect.DropShadow;
//import javafx.scene.input.KeyCode;
//import javafx.scene.layout.Pane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Circle;
//import javafx.scene.shape.Rectangle;
//import org.oosd.model.Game;
//
//public class GameScreen {
//        private AnimationTimer timer;
//
//    private void showGameScreen() {
//        Pane gamePane = new Pane();
//
//        // Create field border
//        Rectangle field = new Rectangle(0, 0, Game.fieldWidth, Game.fieldHeight);
//        field.setFill(Color.TRANSPARENT);
//        field.setStroke(Color.BLACK);
//
//        // Create red ball
//        Circle ball = new Circle(game.getSize(), game.getColor());
//        ball.setCenterX(Game.fieldWidth / 2);
//        ball.setCenterY(Game.fieldHeight / 2);
//
//        if(game.isHasShadow()){
//            DropShadow shadow = new DropShadow();
//            shadow.setOffsetX(5);
//            shadow.setOffsetY(5);
//            ball.setEffect(shadow);
//        }
//
//        Button backButton = new Button("Back");
//        backButton.setLayoutX(10);
//        backButton.setLayoutY(10);
//        backButton.setOnAction(e -> {
//            timer.stop();
//            showMainScreen();
//        });
//
//        gamePane.getChildren().addAll(field, ball, backButton);
//
//        // Key control
//        scene.setOnKeyPressed(e -> {
//            if (e.getCode() == KeyCode.UP) {
//                game.increaseY();
//            } else if (e.getCode() == KeyCode.DOWN) {
//                game.decreaseY();
//            } else if (e.getCode() == KeyCode.LEFT) {
//                game.decreaseX();
//            } else if (e.getCode() == KeyCode.RIGHT) {
//                game.increaseX();
//            }
//        });
//
//        timer = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                double nextX = ball.getCenterX() + game.getDx();
//                double nextY = ball.getCenterY() + game.getDy();
//
//                // Bounce off edges
//                if (nextX - ball.getRadius() < 0 || nextX + ball.getRadius() > Game.fieldWidth) {
//                    game.setDx(-game.getDx());
//                }
//                if (nextY - ball.getRadius() < 0 || nextY + ball.getRadius() > Game.fieldHeight) {
//                    game.setDy(-game.getDy());
//                }
//
//                ball.setCenterX(ball.getCenterX() + game.getDx());
//                ball.setCenterY(ball.getCenterY() + game.getDy());
//            }
//        };
//
//        timer.start();
//
//        root.getChildren().setAll(gamePane);
//        gamePane.requestFocus();  // Ensure pane gets key input
//    }
//
//}

package org.oosd;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    private StackPane root;      // Main container
    private Scene scene;
    private AnimationTimer timer;

    private double dx = 3;       // X velocity
    private double dy = 3;       // Y velocity

    private final double fieldWidth = 400;
    private final double fieldHeight = 300;

    private boolean hasShadow = false;
    private String colorString = "RED";
    private int size = 10;

    private Color getColor() {
        return switch (colorString){
            case "RED" -> Color.RED;
            case "GREEN" -> Color.GREEN;
            case "BLUE" -> Color.BLUE;
            default -> Color.RED;
        };
    }

    @Override
    public void start(Stage primaryStage) {
        root = new StackPane();
        scene = new Scene(root, fieldWidth, fieldHeight);

        showMainScreen();

        primaryStage.setTitle("JavaFX Multi-Screen Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showMainScreen() {
        VBox mainScreen = new VBox(10);
        mainScreen.setPadding(new Insets(20));

        Label label = new Label("Main Screen");

        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> showGameScreen());

        Button configButton = new Button("Configuration");
        configButton.setOnAction(e -> showConfigurationScreen());

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e-> System.exit(0));

        mainScreen.getChildren().addAll(label, startButton, configButton, exitButton);
        root.getChildren().setAll(mainScreen);
    }

    private void showConfigurationScreen() {
        VBox mainScreen = new VBox(10);
        mainScreen.setPadding(new Insets(20));

        Label label = new Label("Configuration");

        CheckBox cb = new CheckBox("Has Shadow");
        cb.setSelected(hasShadow);
        cb.setOnAction(e-> hasShadow = cb.isSelected());

        Label colorLabel = new Label("Color");
        RadioButton rbRed = new RadioButton("RED");
        rbRed.setOnAction(e-> colorString = "RED");
        RadioButton rbGreen = new RadioButton("GREEN");
        rbGreen.setOnAction(e-> colorString = "GREEN");
        RadioButton rbBlue = new RadioButton("BLUE");
        rbBlue.setOnAction(e-> colorString = "BLUE");
        ToggleGroup colorGroup = new ToggleGroup();
        rbRed.setToggleGroup(colorGroup);
        rbGreen.setToggleGroup(colorGroup);
        rbBlue.setToggleGroup(colorGroup);
        switch(colorString){
            case "RED" -> rbRed.setSelected(true);
            case "GREEN" -> rbGreen.setSelected(true);
            case "BLUE" -> rbBlue.setSelected(true);
            default-> rbRed.setSelected(true);
        }

        Label sizeLabel = new Label("Size: " + size);
        Slider sizeSlider = new Slider(5, 20, size);
        sizeSlider.setShowTickMarks(true);
        sizeSlider.setShowTickLabels(true);
        sizeSlider.setMajorTickUnit(5);
        sizeSlider.valueProperty().addListener(
                (obs, oldVal, newVal) -> {
                    size = newVal.intValue();
                    sizeLabel.setText("Size: " + size);
                }
        );

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showMainScreen());

        mainScreen.getChildren().addAll(label, cb, colorLabel, rbRed, rbGreen, rbBlue, sizeLabel, sizeSlider, backButton);
        root.getChildren().setAll(mainScreen);
    }

    private void showGameScreen() {
        Pane gamePane = new Pane();

        // Create field border
        Rectangle field = new Rectangle(0, 0, fieldWidth, fieldHeight);
        field.setFill(Color.TRANSPARENT);
        field.setStroke(Color.BLACK);

        // Create red ball
        Circle ball = new Circle(size, getColor());
        ball.setCenterX(fieldWidth / 2);
        ball.setCenterY(fieldHeight / 2);

        if(hasShadow){
            DropShadow shadow = new DropShadow();
            shadow.setOffsetX(5);
            shadow.setOffsetY(5);
            ball.setEffect(shadow);
        }

        Button backButton = new Button("Back");
        backButton.setLayoutX(10);
        backButton.setLayoutY(10);
        backButton.setOnAction(e -> {
            timer.stop();
            showMainScreen();
        });

        gamePane.getChildren().addAll(field, ball, backButton);

        // Key control
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                dy = dy>0? dy+1: dy-1;
            } else if (e.getCode() == KeyCode.DOWN) {
                dy = dy<0? dy+1: dy-1;
            } else if (e.getCode() == KeyCode.LEFT) {
                dx = dx<0? dx+1: dx-1;
            } else if (e.getCode() == KeyCode.RIGHT) {
                dx = dx>0? dx+1: dx-1;
            }
        });

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                double nextX = ball.getCenterX() + dx;
                double nextY = ball.getCenterY() + dy;

                // Bounce off edges
                if (nextX - ball.getRadius() < 0 || nextX + ball.getRadius() > fieldWidth) {
                    dx = -dx;
                }
                if (nextY - ball.getRadius() < 0 || nextY + ball.getRadius() > fieldHeight) {
                    dy = -dy;
                }

                ball.setCenterX(ball.getCenterX() + dx);
                ball.setCenterY(ball.getCenterY() + dy);
            }
        };

        timer.start();

        root.getChildren().setAll(gamePane);
        gamePane.requestFocus();  // Ensure pane gets key input
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package org.oosd;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import org.oosd.model.Game;

import java.awt.*;
import java.util.Optional;

public class Main extends Application {
    private Game game;

    private StackPane root;      // Main container
    private Scene scene;
    private AnimationTimer timer;

    private String colorString = "RED";
    private int size = 10;

    @Override
    public void start(Stage primaryStage) {
        game = new Game();
        game.setColorString(colorString);
        game.setSize(size);

        root = new StackPane();
        scene = new Scene(root, Game.fieldWidth, Game.fieldHeight);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        showMainScreen();

        primaryStage.setTitle("JavaFX Multi-Screen Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showMainScreen() {
        VBox mainScreen = new VBox(10);
        mainScreen.setAlignment(Pos.CENTER);
        mainScreen.setPadding(new Insets(20));
        mainScreen.setSpacing(20);

        Label label = new Label("Main Screen");
        label.getStyleClass().add("title-label");

        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> showGameScreen());
        startButton.getStyleClass().add("menu-button");

        Button configButton = new Button("Configuration");
        configButton.setOnAction(e -> showConfigurationScreen());
        configButton.getStyleClass().add("menu-button");

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e-> showExitConfirmation());
        exitButton.getStyleClass().add("menu-button");

        mainScreen.getChildren().addAll(label, startButton, configButton, exitButton);
        root.getChildren().setAll(mainScreen);
    }

    public void showExitConfirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Confirmation");
        alert.setContentText("Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }

    private final BorderPane configurationScreen = new BorderPane();

    private void showConfigurationScreen() {
        buildScreen();
        root.getChildren().setAll(configurationScreen);
    }

    private StackPane getTopPane(){
        Label label = new Label("Configuration");
        label.getStyleClass().add("title-label");
        StackPane topPane = new StackPane(label);
        topPane.setPadding(new Insets(10, 0, 0, 0));
        topPane.setAlignment(Pos.CENTER);
        return topPane;
    }

    private VBox getCenterPane() {
        VBox centerPane = new VBox(10);
        centerPane.setPadding(new Insets(20));
        centerPane.getChildren().addAll(getShadowCheckBox(), getColorRadioPane(), getSizePane());
        return centerPane;
    }

    private StackPane getBottomPane(){
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showMainScreen());
        backButton.getStyleClass().add("menu-button");
        StackPane bottomPane = new StackPane(backButton);
        bottomPane.setPadding(new Insets(00, 0, 20, 0));
        bottomPane.setAlignment(Pos.CENTER);
        return bottomPane;
    }

    private CheckBox getShadowCheckBox(){
        CheckBox cbShadow = new CheckBox("Enable Shadow");
        cbShadow.setSelected(game.isHasShadow());
        cbShadow.setOnAction(e-> game.setHasShadow(cbShadow.isSelected()));
        return cbShadow;
    }

    private HBox getColorRadioPane(){
        HBox colorPane = new HBox(20);
        Label colorLabel = new Label("Color");
        RadioButton rbRed = new RadioButton("RED");
        RadioButton rbGreen = new RadioButton("GREEN");
        RadioButton rbBlue = new RadioButton("BLUE");
        ToggleGroup colorGroup = new ToggleGroup();
        rbRed.setToggleGroup(colorGroup);
        rbGreen.setToggleGroup(colorGroup);
        rbBlue.setToggleGroup(colorGroup);
        switch(game.getColorString()){
            case "GREEN" -> rbGreen.setSelected(true);
            case "BLUE" -> rbBlue.setSelected(true);
            default-> rbRed.setSelected(true);
        }
        rbRed.setOnAction(e -> { colorString = "RED";   game.setColorString("RED"); });
        rbGreen.setOnAction(e -> { colorString = "GREEN"; game.setColorString("GREEN"); });
        rbBlue.setOnAction(e -> { colorString = "BLUE";  game.setColorString("BLUE"); });
        colorPane.getChildren().addAll(colorLabel, rbRed, rbGreen, rbBlue);
        return colorPane;
    }

    private HBox getSizePane(){
        HBox sizePane = new HBox(10);
        Label sizeLabel = new Label("Size: ");
        Slider sizeSlider = new Slider(5, 20, game.getSize());
        Label sizeSet = new Label("" + game.getSize());
        sizeSlider.setShowTickMarks(true);
        sizeSlider.setShowTickLabels(true);
        sizeSlider.setMajorTickUnit(5);
        sizeSlider.valueProperty().addListener(
                (obs, oldVal, newVal) -> {
                    int newSize = newVal.intValue();
                    game.setSize(newSize);
                    sizeSet.setText("" + newSize);
                }
        );
        HBox.setHgrow(sizeSlider, Priority.ALWAYS);
        sizePane.getChildren().addAll(sizeLabel, sizeSlider, sizeSet);
        return sizePane;
    }


    private void buildScreen() {
        configurationScreen.setTop(getTopPane());
        configurationScreen.setBottom(getBottomPane());
        configurationScreen.setCenter(getCenterPane());
    }

    private void showGameScreen() {
        Pane gamePane = new Pane();

        // Create field border
        Rectangle field = new Rectangle(0, 0, Game.fieldWidth, Game.fieldHeight);
        field.setFill(Color.TRANSPARENT);
        field.setStroke(Color.BLACK);

        // Create red ball
        Circle ball = new Circle(game.getSize(), game.getColor());
        ball.setCenterX(Game.fieldWidth / 2);
        ball.setCenterY(Game.fieldHeight / 2);

        if(game.isHasShadow()){
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
                game.increaseY();
            } else if (e.getCode() == KeyCode.DOWN) {
                game.decreaseY();
            } else if (e.getCode() == KeyCode.LEFT) {
                game.decreaseX();
            } else if (e.getCode() == KeyCode.RIGHT) {
                game.increaseX();
            }
        });

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                double nextX = ball.getCenterX() + game.getDx();
                double nextY = ball.getCenterY() + game.getDy();

                // Bounce off edges
                if (nextX - ball.getRadius() < 0 || nextX + ball.getRadius() > Game.fieldWidth) {
                    game.setDx(-game.getDx());
                }
                if (nextY - ball.getRadius() < 0 || nextY + ball.getRadius() > Game.fieldHeight) {
                    game.setDy(-game.getDy());
                }

                ball.setCenterX(ball.getCenterX() + game.getDx());
                ball.setCenterY(ball.getCenterY() + game.getDy());
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

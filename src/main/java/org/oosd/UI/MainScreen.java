package org.oosd.UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MainScreen implements Screen {
    private VBox mainScreen;
    private Screen configScreen, gameScreen;
    private Frame parent;

    @Override
    public Node getScreen() {
        return mainScreen;
    }

    @Override
    public void setRoute(String path, Screen screen) {
        switch (path) {
            case "config" -> configScreen = screen;
            case "game" -> gameScreen = screen;
            default -> {
            }
        }
    }

    public MainScreen(Frame frame) {
        parent = frame;
        buildScreen();
    }

    private void buildScreen() {
        mainScreen = new VBox(10);
        mainScreen.setAlignment(Pos.CENTER);
        mainScreen.setPadding(new Insets(20));
        mainScreen.setSpacing(20);

        Label label = new Label("Main Screen");
        label.getStyleClass().add("title-label");

        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> parent.showScreen(gameScreen));
        startButton.getStyleClass().add("menu-button");

        Button configButton = new Button("Configuration");
        configButton.setOnAction(e -> parent.showScreen(configScreen));
        configButton.getStyleClass().add("menu-button");

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> parent.showExitConfirmation());
        exitButton.getStyleClass().add("menu-button");


        mainScreen.getChildren().addAll(label, startButton, configButton, exitButton);

    }

}

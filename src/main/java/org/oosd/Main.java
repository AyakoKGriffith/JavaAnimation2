package org.oosd;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.oosd.UI.*;
import org.oosd.UI.MainScreen;
import org.oosd.UI.Screen;
import org.oosd.model.Game;
import java.util.Optional;

public class Main extends Application implements Frame{
    private StackPane root;
    private Game game;
    private Screen configScreen;
    private Screen gameScreen;

    private void buildScreens(){
        Screen mainScreen = new MainScreen(this);
        ScreenWithGame configScreen = new ConfigScreen(this);

        mainScreen.setRoute("config", configScreen);
        mainScreen.setRoute("game", gameScreen);

        configScreen.setGame(game);
        configScreen.setRoute("back", mainScreen);

        showScreen(mainScreen);
    }

    @Override
    public void start(Stage primaryStage) {
        game = new Game();
        root = new StackPane();
        Scene scene = new Scene(root, Game.fieldWidth, Game.fieldHeight);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        primaryStage.setTitle("JavaFX Multi-Screen Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        buildScreens();
        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            showExitConfirmation();
        });
    }

    public void showScreen(Screen scr) {
        root.getChildren().setAll(scr.getScreen());
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

    public static void main(String[] args) {
        launch(args);
    }
}
package org.oosd.UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.oosd.model.Game;
import org.oosd.model.GameConfig;

public class ConfigScreen implements Screen {
    private final Frame parent;
    private Game game;
    private final BorderPane configScreen;
    private Screen mainScreen;

    public ConfigScreen(Frame frame){
        parent = frame;
        configScreen = new BorderPane();
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
        backButton.setOnAction(e -> parent.showScreen(mainScreen));
        backButton.getStyleClass().add("menu-button");
        StackPane bottomPane = new StackPane(backButton);
        bottomPane.setPadding(new Insets(0, 0, 20, 0));
        bottomPane.setAlignment(Pos.CENTER);
        return bottomPane;
    }

    private CheckBox getShadowCheckBox(){
        CheckBox cbShadow = new CheckBox("Enable Shadow");
        cbShadow.setSelected(GameConfig.getInstance().isHasShadow());
        cbShadow.setOnAction(e-> GameConfig.getInstance().setHasShadow(cbShadow.isSelected()));
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
        switch(GameConfig.getInstance().getColorString()){
            case "GREEN" -> rbGreen.setSelected(true);
            case "BLUE" -> rbBlue.setSelected(true);
            default-> rbRed.setSelected(true);
        }
        rbRed.setOnAction(e -> GameConfig.getInstance().setColorString("RED"));
        rbGreen.setOnAction(e -> GameConfig.getInstance().setColorString("GREEN"));
        rbBlue.setOnAction(e -> GameConfig.getInstance().setColorString("BLUE"));
        colorPane.getChildren().addAll(colorLabel, rbRed, rbGreen, rbBlue);
        return colorPane;
    }

    private HBox getSizePane(){
        HBox sizePane = new HBox(10);
        Label sizeLabel = new Label("Size: ");
        Slider sizeSlider = new Slider(5, 20, GameConfig.getInstance().getSize());
        Label sizeSet = new Label("" + GameConfig.getInstance().getSize());
        sizeSlider.setShowTickMarks(true);
        sizeSlider.setShowTickLabels(true);
        sizeSlider.setMajorTickUnit(5);
        sizeSlider.valueProperty().addListener(
                (obs, oldVal, newVal) -> {
                    int newSize = newVal.intValue();
                    GameConfig.getInstance().setSize(newSize);
                    sizeSet.setText("" + newSize);
                }
        );
        HBox.setHgrow(sizeSlider, Priority.ALWAYS);
        sizePane.getChildren().addAll(sizeLabel, sizeSlider, sizeSet);
        return sizePane;
    }

    private void buildScreen(){
        configScreen.setTop(getTopPane());
        configScreen.setCenter(getCenterPane());
        configScreen.setBottom(getBottomPane());
    }

    @Override
    public Node getScreen(){return configScreen; }

    @Override
    public void setGame(Game game) {this.game = game; }

    @Override
    public void setRoute(String path, Screen screen){
        if ("back".equals(path)){
            mainScreen = screen;
            buildScreen();
        }
    }
}

package org.oosd.UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.oosd.model.GameConfig;

public class ConfigScreen implements Screen {
    private final Frame parent;
    private final BorderPane configScreen;
    private Screen mainScreen;

    public ConfigScreen(Frame frame) {
        parent = frame;
        configScreen = new BorderPane();
    }

    private StackPane getTopPane() {
        Label label = new Label("Configuration");
        label.getStyleClass().add("title-label");
        StackPane topPane = new StackPane(label);
        topPane.setPadding(new Insets(10, 0, 0, 0));
        topPane.setAlignment(Pos.CENTER);
        return topPane;
    }

    private StackPane getBottomPane() {
        Button back = new Button("Back");
        back.setOnAction(e -> parent.showScreen(mainScreen));
        back.getStyleClass().add("menu-button");

        StackPane bottomPane = new StackPane(back);
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setPadding(new Insets(0, 0, 20, 0));
        return bottomPane;
    }

    private CheckBox getShadowCheckBox() {
        CheckBox cbShadow = new CheckBox("Enable Shadow");
        cbShadow.setSelected(GameConfig.getInstance().isHasShadow());
        cbShadow.setOnAction(e -> GameConfig.getInstance().setHasShadow(cbShadow.isSelected()));
        return cbShadow;
    }

    private HBox getColorRadioPane() {
        HBox colorPane = new HBox(20);
        Label colorLabel = new Label("Color:");
        RadioButton rbRed = new RadioButton("Red");
        RadioButton rbGreen = new RadioButton("Green");
        RadioButton rbBlue = new RadioButton("Blue");
        ToggleGroup colorGroup = new ToggleGroup();
        rbBlue.setToggleGroup(colorGroup);
        rbRed.setToggleGroup(colorGroup);
        rbGreen.setToggleGroup(colorGroup);
        rbBlue.setOnAction(e -> GameConfig.getInstance().setColorString("BLUE"));
        rbRed.setOnAction(e -> GameConfig.getInstance().setColorString("RED"));
        rbGreen.setOnAction(e -> GameConfig.getInstance().setColorString("GREEN"));
        switch (GameConfig.getInstance().getColorString()) {
            case "RED" -> rbRed.setSelected(true);
            case "GREEN" -> rbGreen.setSelected(true);
            case "BLUE" -> rbBlue.setSelected(true);
            default -> rbRed.setSelected(true);
        }
        colorPane.getChildren().addAll(colorLabel, rbRed, rbGreen, rbBlue);
        return colorPane;
    }

    private HBox getSizePane() {
        HBox sizePane = new HBox(10);
        Label label = new Label("Size: ");
        Slider sizeSlider = new Slider(GameConfig.MIN_SIZE, GameConfig.MAX_SIZE, GameConfig.getInstance().getSize());
        Label sizeLabel = new Label("" + GameConfig.getInstance().getSize());

        sizeSlider.setShowTickLabels(true);
        sizeSlider.setShowTickMarks(true);
        sizeSlider.setMajorTickUnit(5);
        sizeSlider.setMinorTickCount(1);
        sizeSlider.valueProperty().addListener(
                (obs, oldVal, newVal) -> {
                    int size = newVal.intValue();
                    GameConfig.getInstance().setSize(size);
                    sizeLabel.setText("" + size);
                }
        );
        sizePane.getChildren().addAll(label, sizeSlider, sizeLabel);
        HBox.setHgrow(sizeSlider, Priority.ALWAYS);
        return sizePane;
    }

    private VBox getCenterPane() {
        VBox centerPane = new VBox(10);
        centerPane.setPadding(new Insets(20));
        centerPane.getChildren().addAll(
                getShadowCheckBox(),
                getColorRadioPane(),
                getSizePane()
        );


        return centerPane;
    }

    private void buildScreen() {

        configScreen.setTop(getTopPane());
        configScreen.setBottom(getBottomPane());

        configScreen.setCenter(getCenterPane());

    }

    @Override
    public Node getScreen() {
        return configScreen;
    }


    @Override
    public void setRoute(String path, Screen screen) {
        if ("back".equals(path)) {
            mainScreen = screen;
            buildScreen();
        }
    }
}

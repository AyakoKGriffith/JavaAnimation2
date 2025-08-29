//package org.oosd.UI;
//
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.control.*;
//import javafx.scene.layout.*;
//
//public class ConfigScreen {
//    private String colorString = "RED";
//    private int size = 10;
//
//    private final BorderPane configurationScreen = new BorderPane();
//
//    private void showConfigurationScreen() {
//        buildScreen();
//        root.getChildren().setAll(configurationScreen);
//    }
//
//    private StackPane getTopPane(){
//        Label label = new Label("Configuration");
//        label.getStyleClass().add("title-label");
//        StackPane topPane = new StackPane(label);
//        topPane.setPadding(new Insets(10, 0, 0, 0));
//        topPane.setAlignment(Pos.CENTER);
//        return topPane;
//    }
//
//    private VBox getCenterPane() {
//        VBox centerPane = new VBox(10);
//        centerPane.setPadding(new Insets(20));
//        centerPane.getChildren().addAll(getShadowCheckBox(), getColorRadioPane(), getSizePane());
//        return centerPane;
//    }
//
//    private StackPane getBottomPane(){
//        Button backButton = new Button("Back");
//        backButton.setOnAction(e -> showMainScreen());
//        backButton.getStyleClass().add("menu-button");
//        StackPane bottomPane = new StackPane(backButton);
//        bottomPane.setPadding(new Insets(00, 0, 20, 0));
//        bottomPane.setAlignment(Pos.CENTER);
//        return bottomPane;
//    }
//
//    private CheckBox getShadowCheckBox(){
//        CheckBox cbShadow = new CheckBox("Enable Shadow");
//        cbShadow.setSelected(game.isHasShadow());
//        cbShadow.setOnAction(e-> game.setHasShadow(cbShadow.isSelected()));
//        return cbShadow;
//    }
//
//    private HBox getColorRadioPane(){
//        HBox colorPane = new HBox(20);
//        Label colorLabel = new Label("Color");
//        RadioButton rbRed = new RadioButton("RED");
//        RadioButton rbGreen = new RadioButton("GREEN");
//        RadioButton rbBlue = new RadioButton("BLUE");
//        ToggleGroup colorGroup = new ToggleGroup();
//        rbRed.setToggleGroup(colorGroup);
//        rbGreen.setToggleGroup(colorGroup);
//        rbBlue.setToggleGroup(colorGroup);
//        switch(game.getColorString()){
//            case "GREEN" -> rbGreen.setSelected(true);
//            case "BLUE" -> rbBlue.setSelected(true);
//            default-> rbRed.setSelected(true);
//        }
//        rbRed.setOnAction(e -> { colorString = "RED";   game.setColorString("RED"); });
//        rbGreen.setOnAction(e -> { colorString = "GREEN"; game.setColorString("GREEN"); });
//        rbBlue.setOnAction(e -> { colorString = "BLUE";  game.setColorString("BLUE"); });
//        colorPane.getChildren().addAll(colorLabel, rbRed, rbGreen, rbBlue);
//        return colorPane;
//    }
//
//    private HBox getSizePane(){
//        HBox sizePane = new HBox(10);
//        Label sizeLabel = new Label("Size: ");
//        Slider sizeSlider = new Slider(5, 20, game.getSize());
//        Label sizeSet = new Label("" + game.getSize());
//        sizeSlider.setShowTickMarks(true);
//        sizeSlider.setShowTickLabels(true);
//        sizeSlider.setMajorTickUnit(5);
//        sizeSlider.valueProperty().addListener(
//                (obs, oldVal, newVal) -> {
//                    int newSize = newVal.intValue();
//                    game.setSize(newSize);
//                    sizeSet.setText("" + newSize);
//                }
//        );
//        HBox.setHgrow(sizeSlider, Priority.ALWAYS);
//        sizePane.getChildren().addAll(sizeLabel, sizeSlider, sizeSet);
//        return sizePane;
//    }
//
//
//    private void buildScreen() {
//        configurationScreen.setTop(getTopPane());
//        configurationScreen.setBottom(getBottomPane());
//        configurationScreen.setCenter(getCenterPane());
//    }
//
//}

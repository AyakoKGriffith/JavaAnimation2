package org.oosd.UI.sprite;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.oosd.model.GameEntity;


public final class LifeCountdownDecorator<E extends GameEntity> implements Sprite<E, Parent> {

    private final Sprite<E, ? extends javafx.scene.Node> delegate;

    private final StackPane container = new StackPane();
    private final Label countdown = new Label();

    private final int thresholdSeconds;

    public LifeCountdownDecorator(Sprite<E, ? extends javafx.scene.Node> delegate,
                                  int thresholdSeconds) {
        this.delegate = delegate;
        this.thresholdSeconds = thresholdSeconds;

        container.getChildren().addAll(delegate.getNode(), countdown);

        countdown.setTextFill(Color.BLUE);
        countdown.setFont(Font.font(12));
        countdown.getStyleClass().add("message-life-count-down");

        countdown.setVisible(false);         // hidden until we’re under threshold
    }

    @Override
    public Parent getNode() {
        return container;
    }

    @Override
    public E getEntity() {
        return delegate.getEntity();
    }

    @Override
    public void update() {
        container.setTranslateX(getEntity().getX());
        container.setTranslateY(getEntity().getY());
        int remainLife = getEntity().getRemainLife();
        if (remainLife < thresholdSeconds) {
            countdown.setVisible(true);
            countdown.setText(Integer.toString(remainLife));
        } else {
            countdown.setVisible(false);
        }
    }
}

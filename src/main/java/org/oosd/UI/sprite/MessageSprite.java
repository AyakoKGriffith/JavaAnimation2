package org.oosd.UI.sprite;

import javafx.scene.control.Label;
import org.oosd.model.Message;

public class MessageSprite implements Sprite<Message, Label> {
    private final Message message;
    private Label text;

    public MessageSprite(Message message) {
        this.message = message;
        text = new Label(message.getMessage());
        text.getStyleClass().add("message-fly");
    }

    @Override
    public Label getNode() {
        return text;
    }

    @Override
    public Message getEntity() {
        return message;
    }

    @Override
    public void update() {
        text.setTranslateX(message.getX());
        text.setTranslateY(message.getY());

    }
}

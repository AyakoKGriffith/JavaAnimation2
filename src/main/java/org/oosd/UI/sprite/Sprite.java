package org.oosd.UI.sprite;

import javafx.scene.Node;
import org.oosd.model.GameEntity;

public interface Sprite <E extends GameEntity, N extends Node> {
    public N getNode();

    public E getEntity();

    public default boolean isDead() {
        return getEntity().isDead();
    }

    public void update();
}

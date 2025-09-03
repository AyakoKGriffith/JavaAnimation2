package org.oosd.UI.sprite;

import javafx.scene.Node;
import org.oosd.model.GameConfig;
import org.oosd.model.GameEntity;
import org.oosd.model.Player;

public class PlayerSprite implements Sprite<Player, Ball> {
    private final Ball ball;
    private final Player player;

    public PlayerSprite(Player player) {
        this.player = player;
        ball = new Ball(player.getSize(), GameConfig.getInstance().getColor(), GameConfig.getInstance().isHasShadow());
    }

    @Override
    public Ball getNode() {
        return ball;
    }

    @Override
    public Player getEntity() {
        return player;
    }

    @Override
    public void update() {
        ball.setTranslateX(player.getX());
        ball.setTranslateY(player.getY());
    }

}

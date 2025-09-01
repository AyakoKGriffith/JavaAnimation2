package org.oosd.UI.sprite;

import javafx.scene.Node;
import org.oosd.model.GameConfig;
import org.oosd.model.GameEntity;
import org.oosd.model.Player;

public class PlayerSprite implements Sprite {
    private final Node ball;
    private final Player player;

    public PlayerSprite(Player player) {
        System.out.println("Player is created.");
        this.player = player;
        ball = new Ball(player.getSize(), GameConfig.getInstance().getColor(), GameConfig.getInstance().isHasShadow());
        update();
    }

    @Override
    public Node getNode(){return ball; }

    @Override
    public GameEntity getEntity() {return player;}

    @Override
    public void update(){
        ball.setTranslateX(player.getX());
        ball.setTranslateY(player.getY());
    }
}

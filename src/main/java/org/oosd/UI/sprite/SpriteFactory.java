
package org.oosd.UI.sprite;

import org.oosd.model.GameEntity;
import org.oosd.model.Message;
import org.oosd.model.Player;
import javafx.scene.Node;
import java.util.*;

public class SpriteFactory {

    private final Deque<GameEntity> entities = new ArrayDeque<>();

    public synchronized void initFactory() {
        entities.clear();
    }

    public synchronized void addEntity(GameEntity entity) {
        entities.addLast(entity);
    }

    public synchronized void addEntities(Collection<? extends GameEntity> toAdd) {
        for (GameEntity e : toAdd) {entities.addLast(e);}
    }

    public synchronized List<Sprite<?, ?>> produceSprites() {
        if (entities.isEmpty()) return null;
        List<Sprite<?, ?>> retSprites = new ArrayList<>();
        while (!entities.isEmpty()) {
            GameEntity entity = entities.removeFirst();
            Sprite<?, ?> sprite = produceSprite(entity);
            retSprites.add(sprite);
        }
        return retSprites;
    }

    private SpriteFactory() {
    }

    private static class SingletonFactory {
        private static final SpriteFactory factory = new SpriteFactory();
    }

    public static SpriteFactory getFactory() {
        return SingletonFactory.factory;
    }

    private Sprite<? extends GameEntity, ?> produceSprite(GameEntity entity) {
        SpriteType type = EntitySpriteMapper.getSpriteType(entity.getType());
        Sprite<?, ?> sprite = switch (type) {
            case SpriteType.PLAYER -> new PlayerSprite((Player) entity);
            case SpriteType.STAR -> new StarSprite(entity);
            case SpriteType.MESSAGE -> new MessageSprite((Message) entity);
        };
        if (entity.showLifeCountdown()) sprite = new LifeCountdownDecorator<>(sprite, 10);
        return sprite;
    }

}

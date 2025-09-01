package org.oosd.UI.sprite;

import org.oosd.model.GameEntity;
import org.oosd.model.Player;
import java.util.ArrayList;
import java.util.List;

public class SpriteFactory {
    private List<GameEntity> entities;
    public synchronized void initFactory() {
        entities = new ArrayList<>();
    }
    public synchronized void addEntity(GameEntity entity) {
        entities.add(entity);
    }

    public synchronized List<Sprite> produceSprites() {
        if (entities.isEmpty()) return null;
        List<Sprite> retSprites = new ArrayList<>();
        while (!entities.isEmpty()) {
            GameEntity entity = entities.removeFirst();
            Sprite sprite = produceSprite(entity);
            retSprites.add(sprite);
        }
        return retSprites;
    }

    private SpriteFactory(){}

    private static class SingletonFactory {
        private static final SpriteFactory factory = new SpriteFactory();
    }

    public static SpriteFactory getFactory() {
        return SingletonFactory.factory;
    }

    private Sprite produceSprite(GameEntity entity) {
        SpriteType type = EntitySpriteMapper.getSpriteType(entity.getType());
        return switch (type) {
            case SpriteType.PLAYER -> new PlayerSprite((Player) entity);
            case SpriteType.STAR -> new StarSprite(entity);
        };
    }

}

package org.oosd.UI.sprite;

import org.oosd.model.EntityType;

public class EntitySpriteMapper {
    public static SpriteType getSpriteType(EntityType type) {
        return switch (type) {
            case PLAYER -> SpriteType.PLAYER;
            case FOOD -> SpriteType.STAR;
            case MESSAGE -> SpriteType.MESSAGE;
        };
    }
}

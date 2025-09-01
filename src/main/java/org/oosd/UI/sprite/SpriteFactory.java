package org.oosd.UI.sprite;

import org.oosd.model.Game;
import org.oosd.model.GameEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpriteFactory {
    private List<GameEntity> entities;
    public synchronized void initFactory() {
        entities = new ArrayList<>();
    }
    public synchronized void addEntity(GameEntity entity) {
        entities.add(entity);
    }

    private SpriteFactory(){}

    private static class SingletonFactory{
        private static final SpriteFactory factory = new SpriteFactory();
    }

    public static SpriteFactory getFactory(){return SingletonFactory.factory;}
    public Sprite createSprite(SpriteType type, double x, double y){
        Sprite sprite = switch (type){
            case STAR -> new StarSprite();
        };
        sprite.setXY(x,y);
        return sprite;
    }

    public Sprite createSprite(SpriteType type){
        double x = new Random().nextInt((int) Game.fieldWidth);
        double y = new Random().nextInt((int) Game.fieldHeight);
        return createSprite(type,x,y);
    }

    public Sprite createSprite(){
        SpriteType type = getRandomType();
        return createSprite(type);
    }
    private SpriteType getRandomType(){
        SpriteType[] values = SpriteType.values();
        int index = new Random().nextInt(values.length);
        return values[index];
    }

}

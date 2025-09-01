package org.oosd.model;

import org.oosd.UI.sprite.SpriteFactory;

import java.util.ArrayList;
import java.util.List;

public class Game {
    static public final double fieldWidth = 400;
    static public final double fieldHeight = 270;
    private Player player;
    private int foodNum;
    private List<GameEntity> entities;

    public Game() {foodNum = 8;}

    public void initGame() {
        player = new Player();
        entities = new ArrayList<>();
        entities.add(player);
        SpriteFactory.getFactory().addEntity(player);
        player.setX(fieldWidth /2);
        player.setY(fieldHeight /2);
    }

    private synchronized void fillFoods() {
        int curFoodNum = getEntityNum(EntityType.FOOD);
        if (curFoodNum * 2 > foodNum) return;
        for (int i = curFoodNum; i < foodNum; ++i) {
            Food food = new Food();
            entities.add(food);
            SpriteFactory.getFactory().addEntity(food);
        }
    }

    private int getEntityNum(EntityType type) {
        return (int) entites.stream().filter(e-> e.getType() == type).count();
    }

    private synchronized void removeDeadEntities() {
        entities.removeIf(GameEntity::isDead);
    }

    public void proceed(){
        for (GameEntity entity: entities) entity.process();
        removeDeadEntities();
        fillFoods();
    }

    public void increaseX()  {player.increaseX();}
    public void decreaseX()  {player.decreaseX();}
    public void increaseY()  {player.increaseY();}
    public void decreaseY()  {player.decreaseY();}
}

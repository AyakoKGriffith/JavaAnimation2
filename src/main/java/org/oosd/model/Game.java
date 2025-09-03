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
    private boolean isGameOver;

    public Game() {
        foodNum = 8;
    }

    public void initGame() {
        isGameOver = false;
        player = new Player();
        entities = new ArrayList<>();
        entities.add(player);
        SpriteFactory.getFactory().addEntity(player);
        player.setX(fieldWidth / 2);
        player.setY(fieldHeight / 2);

    }

    private synchronized void addMessage(String text, double x, double y) {
        Message message = new Message(text, x, y);
        entities.add(message);
        SpriteFactory.getFactory().addEntity(message);
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
        return (int) entities.stream().filter(e -> e.getType() == type).count();
    }

    private void eatFood() {
        List<GameEntity> foods = entities.stream().filter(e -> e.getType() == EntityType.FOOD).toList();
        for (GameEntity food : foods) {
            if (food.isCollide(player) && food instanceof Food f) {
                f.setIsEaten();
                player.eatFood(f);
                addMessage("+1", player.getX(), player.getY());
            }
        }
    }


    private synchronized void removeDeadEntities() {
        entities.removeIf(GameEntity::isDead);
    }

    public void proceed() {
        if (isGameOver) return;
        if (player == null || player.getRemainLife() == 0) {

            addMessage("Game Over", fieldWidth / 2, fieldHeight / 2);
            isGameOver = true;
        }
        for (GameEntity entity : entities) entity.process();
        removeDeadEntities();
        fillFoods();
        eatFood();
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void increaseX() {
        player.increaseX();
    }

    public void decreaseX() {
        player.decreaseX();
    }

    public void increaseY() {
        player.increaseY();
    }

    public void decreaseY() {
        player.decreaseY();
    }
}

package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class Model {
    public static int FIELD_CELL_SIZE = 20;
    EventListener eventListener;
    GameObjects gameObjects;
    int currentLevel = 1;
    LevelLoader levelLoader = new LevelLoader(Paths.get("4.JavaCollections/src/"+ LevelLoader.class.getPackage().getName().replace(".", "/") + "/../res/levels.txt").toAbsolutePath().normalize());

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        currentLevel++;
        restart();
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void move(Direction direction) {
        if (checkWallCollision(gameObjects.getPlayer(), direction))
            return;
        if (checkBoxCollisionAndMoveIfAvailable(direction))
            return;
        move0(gameObjects.getPlayer(), direction);
        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (Wall wall : gameObjects.getWalls()) {
            if (gameObject.isCollision(wall, direction)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkBoxCollisionAndMoveIfAvailable(Direction direction) {
        for (Box box : gameObjects.getBoxes()) {
            if (gameObjects.getPlayer().isCollision(box, direction)) {
                if (checkWallCollision(box, direction)) {
                    return true;
                }
                for (Box box1 : gameObjects.getBoxes()) {
                    if (box.isCollision(box1, direction)) {
                        return true;
                    }
                }
                move0(box, direction);
                break;
            }
        }
        return false;
    }

    public void checkCompletion() {
        Set<Box> boxes = new HashSet<>(gameObjects.getBoxes());
        for (Home home : gameObjects.getHomes()) {
            boolean isFree = true;
            for (Box box : boxes) {
                if (home.getX() == box.getX() && home.getY() == box.getY()) {
                    boxes.remove(box);
                    isFree = false;
                    break;
                }
            }
            if (isFree) break;
        }
        if (boxes.isEmpty()) {
            eventListener.levelCompleted(currentLevel);
        }
    }

    private void move0(Movable movable, Direction direction) {
        switch (direction) {
            case UP:
                movable.move(0, -FIELD_CELL_SIZE);
                break;
            case DOWN:
                movable.move(0, FIELD_CELL_SIZE);
                break;
            case LEFT:
                movable.move(-FIELD_CELL_SIZE, 0);
                break;
            case RIGHT:
                movable.move(FIELD_CELL_SIZE, 0);
        }
    }

}

package com.javarush.task.task34.task3410.controller;

import com.javarush.task.task34.task3410.model.*;
import com.javarush.task.task34.task3410.view.View;

public class Controller implements EventListener {
    private View view;
    private Model model;

    public Controller() {
        this.view = new View(this);
        this.model = new Model();
        model.setEventListener(this);
        model.restart();
        view.init();
        view.setEventListener(this);
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
    }

    public GameObjects getGameObjects(){
        return model.getGameObjects();
    }


    @Override
    public void move(Direction direction) {
        model.move(direction);
        view.update();
    }

    @Override
    public void restart() {
        model.restart();
        view.update();
    }

    @Override
    public void startNextLevel() {
        model.startNextLevel();
        view.update();
    }

    @Override
    public void levelCompleted(int level) {
        view.completed(level);
    }
}

package com.javarush.task.task05.task0511;

/* 
Создать класс Dog
*/

public class Dog {
    private String name;
    private int height;
    private String color;

    public void initialize(String name, int height, String color) {
        this.name = name;
        this.height = height;
        this.color = color;
    }

    public void initialize(String name, int height) {
        this.initialize(name, height, null);
    }

    public void initialize(String name) {
        this.initialize(name, 0, null);
    }

    public static void main(String[] args) {

    }
}

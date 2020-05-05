package com.javarush.task.task05.task0509;

/* 
Создать класс Friend
*/

public class Friend {
    private String name;
    private int age;
    private char sex;

    public void initialize(String name, int age, char sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public void initialize(String name, int age) {
        this.initialize(name, age, '\u0000');
    }

    public void initialize(String name) {
        this.initialize(name, 0, '\u0000');
    }

    public static void main(String[] args) {

    }
}

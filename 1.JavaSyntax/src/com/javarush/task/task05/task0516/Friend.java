package com.javarush.task.task05.task0516;

/* 
Друзей не купишь
*/

public class Friend {
    private String name;
    private int age;
    private char sex;

    public Friend(String name, int age, char sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }


    public Friend(String name, int age) {
        this(name, age, (char) 0);
    }


    public Friend(String name) {
        this(name, 0, (char) 0);
    }

    public static void main(String[] args) {

    }
}

package com.javarush.task.task05.task0518;

/* 
Регистрируем собачек
*/


public class Dog {
    private String name;
    private int height;
    private String color;

    public Dog(String name, int height, String color) {
        this.name = name;
        this.height = height;
        this.color = color;
    }

    public Dog(String name, int height){
        this(name, height, null);
    }
    public Dog(String name){
        this(name,0,null);
    }

    public static void main(String[] args) {

    }
}

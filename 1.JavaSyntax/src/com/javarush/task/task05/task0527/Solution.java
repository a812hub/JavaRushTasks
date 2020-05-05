package com.javarush.task.task05.task0527;

/* 
Том и Джерри
*/

public class Solution {
    public static void main(String[] args) {
        Mouse jerryMouse = new Mouse("Jerry", 12, 5);
        Cat tomCat = new Cat("Tom", "blue", 100);
        Dog dog = new Dog("Dog", "gray", 2);
    }

    public static class Mouse {
        String name;
        int height;
        int tail;

        public Mouse(String name, int height, int tail) {
            this.name = name;
            this.height = height;
            this.tail = tail;
        }
    }

    public static class Cat {
        String name;
        String color;
        int height;

        public Cat(String name, String color, int height) {
            this.name = name;
            this.color = color;
            this.height = height;
        }
    }

    public static class Dog {
        String name;
        String color;
        int tail;

        public Dog(String name, String color, int tail) {
            this.name = name;
            this.color = color;
            this.tail = tail;
        }
    }
}

package com.javarush.task.task05.task0504;


/* 
Трикотаж
*/

public class Solution {
    public static void main(String[] args) {
        Cat barsik = new Cat("Barsik", 5, 5, 5);
        Cat matroskin = new Cat("Matroskin", 7, 10, 6);
        Cat che = new Cat("Che", 3, 6, 10);
    }

    public static class Cat {
        private String name;
        private int age;
        private int weight;
        private int strength;

        public Cat(String name, int age, int weight, int strength) {
            this.name = name;
            this.age = age;
            this.weight = weight;
            this.strength = strength;
        }
    }
}
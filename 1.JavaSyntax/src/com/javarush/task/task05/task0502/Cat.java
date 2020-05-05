package com.javarush.task.task05.task0502;

/* 
Реализовать метод fight
*/

public class Cat {
    public int age;
    public int weight;
    public int strength;

    public Cat() {
    }

    public boolean fight(Cat anotherCat) {
        int count = 0;
        if (this.age > anotherCat.age) count++;
        else if (this.age < anotherCat.age) count--;
        if (this.weight > anotherCat.weight) count++;
        else if (this.weight < anotherCat.weight) count--;
        if (this.strength > anotherCat.strength) count++;
        else if (this.strength < anotherCat.strength) count--;
        return count > 0;
    }

    public static void main(String[] args) {
//        Cat cat1 = new Cat();
//        cat1.age = 10;
//        cat1.weight = 11;
//        cat1.strength = 14;
//        Cat cat2 = new Cat();
//        cat2.age = 10;
//        cat2.weight = 12;
//        cat2.strength = 13;
//        boolean b1 = cat1.fight(cat2);
//        boolean b2 = cat2.fight(cat1);
//        System.out.println(b1);
//        System.out.println(b2);
    }
}

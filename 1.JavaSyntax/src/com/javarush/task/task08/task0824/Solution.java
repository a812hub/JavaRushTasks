package com.javarush.task.task08.task0824;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Собираем семейство
*/

public class Solution {
    public static void main(String[] args) {
        Human ch1 = new Human("Jack", true, 5);
        Human ch2 = new Human("John", true, 7);
        Human ch3 = new Human("Jessica", false, 9);
        Human f = new Human("Mike", true, 30, new ArrayList<>(Arrays.asList(ch1, ch2, ch3)));
        Human m = new Human("Monica", false, 29, new ArrayList<>(Arrays.asList(ch1, ch2, ch3)));
        Human gf1 = new Human("Donald", true, 60, new ArrayList<>(Arrays.asList(f)));
        Human gm1 = new Human("Diana", false, 59, new ArrayList<>(Arrays.asList(f)));
        Human gf2 = new Human("Alex", true, 56, new ArrayList<>(Arrays.asList(m)));
        Human gm2 = new Human("Angela", false, 55, new ArrayList<>(Arrays.asList(m)));
        System.out.println(ch1);
        System.out.println(ch2);
        System.out.println(ch3);
        System.out.println(f);
        System.out.println(m);
        System.out.println(gf1);
        System.out.println(gm1);
        System.out.println(gf2);
        System.out.println(gm2);

    }

    public static class Human {
        String name;
        boolean sex;
        int age;
        ArrayList<Human> children;

        public Human(String name, boolean sex, int age, ArrayList<Human> children){
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.children = children;
        }
        public Human(String name, boolean sex, int age){
            this(name, sex, age, new ArrayList<>());
        }

        public String toString() {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            int childCount = this.children.size();
            if (childCount > 0) {
                text += ", дети: " + this.children.get(0).name;

                for (int i = 1; i < childCount; i++) {
                    Human child = this.children.get(i);
                    text += ", " + child.name;
                }
            }
            return text;
        }
    }
}

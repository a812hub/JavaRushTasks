package com.javarush.task.task07.task0724;

/* 
Семейная перепись
*/

public class Solution {
    public static void main(String[] args) {
        Human gf1 = new Human("Mike", true, 70);
        Human gm1 = new Human("Elena", false, 68);
        Human gf2 = new Human("Petr", true, 80);
        Human gm2 = new Human("Ekaterina", false, 70);
        Human f = new Human("John", true, 45, gf1, gm1);
        Human m = new Human("Klara", false, 40, gf2, gm2);
        Human ch1 = new Human("Rebbecca", false, 13, f, m);
        Human ch2 = new Human("Jack", true, 10, f, m);
        Human ch3 = new Human("Monica", false, 5, f, m);

        System.out.println(gf1);
        System.out.println(gm1);
        System.out.println(gf2);
        System.out.println(gm2);
        System.out.println(f);
        System.out.println(m);
        System.out.println(ch1);
        System.out.println(ch2);
        System.out.println(ch3);
    }

    public static class Human {
        private String name;
        private boolean sex;
        private int age;
        private Human father;
        private Human mother;

        public Human(String name, boolean sex, int age, Human father, Human mother) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.father = father;
            this.mother = mother;
        }

        public Human(String name, boolean sex, int age) {
            this(name, sex, age, null, null);
        }

        public String toString() {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            if (this.father != null) {
                text += ", отец: " + this.father.name;
            }

            if (this.mother != null) {
                text += ", мать: " + this.mother.name;
            }

            return text;
        }
    }
}
package com.javarush.task.task10.task1013;

/* 
Конструкторы класса Human
*/

import java.util.Date;

public class Solution {
    public static void main(String[] args) {
    }

    public static class Human {
        private Date birth;
        private boolean sex;
        private String name;
        private Human mother;
        private Human father;
        private String address;

        public Human(Date birth, boolean sex, String name, Human mother, Human father, String address) {
            this.birth = birth;
            this.sex = sex;
            this.name = name;
            this.mother = mother;
            this.father = father;
            this.address = address;
        }
        public Human (Date birth, boolean sex, String name, Human mother, String address) {
            this(birth, sex, name, mother, null, address);
        }
        public Human (Date birth, boolean sex, String name, Human mother) {
            this(birth, sex, name, mother, null, null);
        }
        public Human (Date birth, boolean sex, String name, String address) {
            this(birth, sex, name, null, null, address);
        }
        public Human (Date birth, boolean sex, String name) {
            this(birth, sex, name, null, null, null);
        }
        public Human (boolean sex, String name, Human mother) {
            this(null, sex, name, mother, null, null);
        }
        public Human (boolean sex, String name, String address) {
            this(null, sex, name, null, null, address);
        }
        public Human (boolean sex, String name) {
            this(null, sex, name, null, null, null);
        }
        public Human (boolean sex) {
            this(null, sex, null, null, null, null);
        }
        public Human (String name) {
            this(null, false, name, null, null, null);
        }
    }
}

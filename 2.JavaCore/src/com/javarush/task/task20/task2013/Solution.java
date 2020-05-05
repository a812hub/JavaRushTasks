package com.javarush.task.task20.task2013;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Externalizable Person
*/
public class Solution {
    public static class Person implements Externalizable {
        private String firstName;
        private String lastName;
        private int age;
        private Person mother;
        private Person father;
        private List<Person> children;

        public Person() {
        }

        public Person(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public void setMother(Person mother) {
            this.mother = mother;
        }

        public void setFather(Person father) {
            this.father = father;
        }

        public void setChildren(List<Person> children) {
            this.children = children;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeUTF(firstName);
            out.writeUTF(lastName);
            out.writeObject(mother);
            out.writeObject(father);
            out.writeInt(age);
            out.writeObject(children);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            firstName = in.readUTF();
            lastName = in.readUTF();
            mother = (Person) in.readObject();
            father = (Person) in.readObject();
            age = in.readInt();
            children = (List) in.readObject();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person person = new Person("1", "1", 30);
        Person mother = new Person("mother", "1", 60);
        Person father = new Person("father", "1", 70);
        Person child1 = new Person("11", "11", 3);
        Person child2 = new Person("12", "12", 5);
        List<Person> children = new ArrayList<>();
        children.add(child1);
        children.add(child2);
        person.setChildren(children);
        person.setMother(mother);
        person.setFather(father);

        FileOutputStream fos = new FileOutputStream("D:\\1.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(person);
        oos.close();

        FileInputStream fis = new FileInputStream("D:\\1.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Person person1 = (Person) ois.readObject();
        ois.close();

        System.out.println(person1.firstName);
        System.out.println(person1.lastName);
        System.out.println(person1.age);
        System.out.println(person1.mother.firstName);
        System.out.println(person1.father.firstName);
        System.out.println(person1.children);
    }
}

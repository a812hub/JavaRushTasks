package com.javarush.task.task21.task2109;

/* 
Запретить клонирование
*/
public class Solution {
    public static class A implements Cloneable {
        private int i;
        private int j;

        public A(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        public A clone() throws CloneNotSupportedException {
            return (A) super.clone();
        }

        public boolean equals(Object o) {
            if (o == this) return true;
            if (o == null) return false;
            if (!(o instanceof A)) return false;
            if (((A)o).i != this.i) return false;
            return ((A)o).j == this.j;
        }

        public int hashCode() {
            return i * 31 + j;
        }
    }

    public static class B extends A {
        private String name;

        public B(int i, int j, String name) {
            super(i, j);
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public B clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException();
        }

        public boolean equals(Object o) {
            if (o == this) return true;
            if (o == null) return false;
            if (!(o instanceof B)) return false;
            if (((B)o).getI() != this.getI()) return false;
            if (((B)o).getJ() != this.getJ()) return false;
            return this.name.equals(((B)o).getName());
        }

        public int hashCode() {
            return ((A)this).hashCode() * 31 + this.name.hashCode();
        }
    }

    public static class C extends B {
        public C(int i, int j, String name) {
            super(i, j, name);
        }

        public C clone() throws CloneNotSupportedException {
            return new C(this.getI(), this.getJ(), this.getName());
        }

        public boolean equals(Object o) {
            if (o == this) return true;
            if (o == null) return false;
            if (!(o instanceof C)) return false;
            if (((C)o).getI() != this.getI()) return false;
            if (((C)o).getJ() != this.getJ()) return false;
            return this.getName().equals(((C)o).getName());
        }

        public int hashCode() {
            return ((B)this).hashCode() * 31;
        }

    }

    public static void main(String[] args) throws CloneNotSupportedException {

    }
}

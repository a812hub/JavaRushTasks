package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object s = "string";
        Integer n = (int) s;
    }

    public void methodThrowsNullPointerException() {
        String s = null;
        int n = s.length();
    }

    public static void main(String[] args) {

    }
}

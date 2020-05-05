package com.javarush.task.task14.task1419;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Нашествие исключений
*/

public class Solution {
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args) {
        initExceptions();

        for (Exception exception : exceptions) {
            System.out.println(exception);
        }
    }

    private static void initExceptions() {   //the first exception
        try {
            float i = 1 / 0;

        } catch (Exception e) {
            exceptions.add(e);
        }

        int[] a = new int[5];
        int b;
        boolean bool;
        try {
            b = a[5];
        } catch (ArrayIndexOutOfBoundsException e) {
            exceptions.add(e);
        }

        String s = "Hello";
        try {
            char ch = s.charAt(8);
        } catch (StringIndexOutOfBoundsException e) {
            exceptions.add(e);
        }

        try {
            Exception ee = exceptions.get(7);
        } catch (IndexOutOfBoundsException e) {
            exceptions.add(e);
        }

        try {
            b = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            exceptions.add(e);
        }

        try {
            InputStreamReader in = new InputStreamReader(new FileInputStream("1.txt"));
        } catch (FileNotFoundException e) {
            exceptions.add(e);
        }

        try {
            InputStreamReader in = new InputStreamReader((InputStream) exceptions);
        } catch (Exception e) {
            exceptions.add(e);
        }

        s = null;
        try {
            b = s.length();
        } catch (NullPointerException e) {
            exceptions.add(e);
        }

        b = -4;
        try {
            a = new int[b];
        } catch (Exception e) {
            exceptions.add(e);
        }

        try {
            String ss = String.format("Hello %d", a);
        } catch (Exception e) {
            exceptions.add(e);
        }
    }
}

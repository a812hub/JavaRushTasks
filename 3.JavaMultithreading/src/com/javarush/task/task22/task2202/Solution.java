package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) throws TooShortStringException {
        if (string == null) throw new TooShortStringException();
        int begin = string.indexOf(" ");
        int end = string.indexOf(" ", begin + 1);
        for (int i = 0; i < 3; i++) {
            if (end == -1) {
                throw new TooShortStringException();
            }
            end = string.indexOf(" ", end + 1);
        }
        if (end == -1) {
            return string.substring(begin + 1);
        }
        return string.substring(begin + 1, end);
    }

    public static class TooShortStringException extends RuntimeException {
    }
}

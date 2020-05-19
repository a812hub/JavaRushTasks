package com.javarush.task.task39.task3909;

/* 
Одно изменение
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(Solution.isOneEditAway("", "aweq"));
    }

    public static boolean isOneEditAway(String first, String second) {
        if (Math.abs(first.length() - second.length()) > 1)
            return false;
        first = first.toLowerCase();
        second = second.toLowerCase();
        int i = 0;
        int minLength = Integer.min(first.length(), second.length());
        int maxLength = Integer.max(first.length(), second.length());
        while (i < minLength && first.charAt(i) == second.charAt(i)) {
            i++;
        }
        int j = 0;
        StringBuilder sbFirst = new StringBuilder(first).reverse();
        StringBuilder sbSecond = new StringBuilder(second).reverse();
        while (j < minLength && sbFirst.charAt(j) == sbSecond.charAt(j)) {
            j++;
        }
        System.out.println(i + " " + (maxLength - j));
        return (maxLength - j) - i <= 1;
    }
}

package com.javarush.task.task34.task3403;

/* 
Разложение на множители с помощью рекурсии
*/
public class Solution {
    public void recurse(int n) {
        if (n <= 1) return;
        int del = 2;
        while (n % del != 0) {
            del++;
        }
        System.out.print(del + " ");
        if (n / del != 1) {
            recurse(n / del);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recurse(750);
    }
}

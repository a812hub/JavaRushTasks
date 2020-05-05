package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
    }

    public void createExpression(int number) {
        StringBuilder result = new StringBuilder();
        result.append(number);
        result.append(" =");
        int power = 0;
        while (number > 0) {
            int mod = number % 3;
            number = number / 3;
            if (mod == 1) {
                result.append(" + ");
                result.append((int) Math.pow(3, power));
            }
            if (mod == 2) {
                result.append(" - ");
                result.append((int) Math.pow(3, power));
                number++;
            }
            power++;
        }
        System.out.println(result);
    }

}
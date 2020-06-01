package com.javarush.task.task20.task2025;

import java.util.*;

/* 
Алгоритмы-числа
*/
public class Solution {

    static long[][] pow = null; // матрица значений степеней

    public static long[] getNumbers(long N) {
        pow = new long[10][countOfDigits(N) + 1];
        fillPow();

        Set<Long> numbers = new TreeSet<>();
        for (int i = 1; i <= countOfDigits(N); i++) {
            getNumbers(0, 1, i, 0, N, numbers);
        }

        return numbers.stream()
                .mapToLong(Long::longValue)
                .toArray();
    }

    /* Алгоритм поиска чисел Армстронга.
    Рассматриваются числа с количеством цифр countOfDigits.
    Рассматриваются числа, у которых любая цифра не меньше предыдущей и не больше последующей.
    например, для countOfDigits == 3: 000, 001, 002, ... , 119, 122, 123, ... , 889, 899, 999
    Для каждого числа вычисляется sum - сумма цифр в степени countOfDigits.
    Если sum является числом Армстронга - sum добавляется в numbers.
     */
    private static void getNumbers(int currentDigit, int currentDeep, int countOfDigits, long sum, long maxNumber, Set<Long> numbers) {
        if (currentDeep == countOfDigits) {
            for (int i = currentDigit; i <= 9; i++) {
                long currentSum = sum + pow[i][countOfDigits];
                if (currentSum < maxNumber && currentSum > 0 && isArmstrongNumber(currentSum)) {
                    numbers.add(currentSum);
                }
            }
        } else {
            currentDeep++;
            for (int i = currentDigit; i <= 9; i++) {
                getNumbers(i, currentDeep, countOfDigits, sum + pow[i][countOfDigits], maxNumber, numbers);
            }
        }
    }

    // если переданное число n является числом Армстронга возвращает true
    private static boolean isArmstrongNumber(long n) {
        int countOfDigits = countOfDigits(n);
        long sum = 0L;
        long tmp = n;
        for (int i = 0; i < countOfDigits && sum <= n; i++) {
            sum += pow[(int)(tmp % 10)][countOfDigits];
            tmp = tmp / 10;
        }
        return n == sum;
    }

    private static void fillPow() {
        for (int i = 1; i < pow.length; i++) {
            for (int j = 0; j < pow[0].length; j++) {
                long value = 1;
                for (int k = 1; k <= j; k++) {
                    value *= i;
                }
                pow[i][j] = value;
            }
        }
    }

    // вычисление количества цифр в числе
    private static int countOfDigits(long n) {
        if (n == 0)
            return 1;
        int i = 0;
        while (n != 0) {
            i++;
            n = n / 10;
        }
        return i;
    }

    public static void main(String[] args) {
        long a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(1000)));
        long b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);

        a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(1000000)));
        b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);

        a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(Long.MAX_VALUE)));
        b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);
    }
}

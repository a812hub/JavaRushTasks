package com.javarush.task.task20.task2025;

import java.util.ArrayList;
import java.util.Arrays;

/*
Алгоритмы-числа
*/
public class Solution {
    public static long[] getNumbers(long N) {
        ArrayList<Long> arrayList = new ArrayList<>();


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < i; j++) {
                for (int k = 0; k < 10; k++) {
                    System.out.println(k + Math.pow(10, i));
                }
            }
        }

//
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                for (int k = 0; k < 10; k++) {
//                    System.out.println("" + i + j + k);
//                }
//            }
//        }

        long[] result = new long[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            result[i] = arrayList.get(i);
        }
        return result;
    }



//// вычисление суммы цифр в цисле i
//    public static long sumPawM(long i) {
//        int countOfNumbers = 0;
//        long iTmp = i;
//        while (iTmp > 0) {
//            iTmp = iTmp / 10;
//            countOfNumbers++;
//        }
//
//        long result = 0;
//        iTmp = i;
//        for (int j = 0; j < countOfNumbers; j++) {
//            result += (long) Math.pow((iTmp % 10), countOfNumbers);
//            if (result > i) {
//                break;
//            }
//            iTmp = iTmp / 10;
//        }
//        return result;
//    }

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(getNumbers(9000L)));
        System.out.println(Arrays.toString(getNumbers(9_000_000L)));
//        System.out.println(Arrays.toString(getNumbers(9000_000_000_000_000_000L)));
    }
}

/*
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 153, 370, 371, 407, 1634, 8208, 9474, 54748, 92727, 93084, 548834, 1741725, 4210818]
 */
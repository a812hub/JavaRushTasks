package com.javarush.task.task08.task0830;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Задача по алгоритмам
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] array = new String[20];
        for (int i = 0; i < array.length; i++) {
            array[i] = reader.readLine();
        }

        sort(array);

        for (String word : array) {
            System.out.println(word);
        }
    }

    public static void sort(String[] array) {
        for (int i = 1; i < array.length; i++) {
            if (isGreaterThan(array[i - 1], array[i])){
                String tmp = array[i];
                array[i] = array[i - 1];
                int j = i - 1;
                while (j > 0 && isGreaterThan(array[j - 1], tmp)) {
                    array[j] = array[j - 1];
                    j--;
                }
                array[j] = tmp;
            }
        }
    }

    //Метод для сравнения строк: 'а' больше чем 'b'
    public static boolean isGreaterThan(String a, String b) {
        return a.compareTo(b) > 0;
    }
}

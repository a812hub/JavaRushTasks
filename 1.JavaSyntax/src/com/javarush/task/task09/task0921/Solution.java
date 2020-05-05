package com.javarush.task.task09.task0921;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Метод в try..catch
*/

public class Solution {
    public static void main(String[] args) {
        readData();
    }

    public static void readData() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> a = new ArrayList<>();
        while (true) {
            try {
                a.add(Integer.parseInt(in.readLine()));
            } catch (IOException e) {

            } catch (NumberFormatException e) {
                for (int i : a) {
                    System.out.println(i);
                }
                break;
            }
        }
    }
}

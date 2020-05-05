package com.javarush.task.task07.task0713;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Играем в Jолушку
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> listDev3 = new ArrayList<>();
        ArrayList<Integer> listDev2 = new ArrayList<>();
        ArrayList<Integer> listNoDev = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            list.add(Integer.parseInt(in.readLine()));
            if (list.get(i) % 2 != 0 && list.get(i) % 3 != 0) {
                listNoDev.add(list.get(i));
            } else {
                if (list.get(i) % 3 == 0) {
                    listDev3.add(list.get(i));
                }
                if (list.get(i) % 2 == 0) {
                    listDev2.add(list.get(i));
                }
            }
        }
        printList(listDev3);
        printList(listDev2);
        printList(listNoDev);
    }

    public static void printList(ArrayList<Integer> list) {
        for (int i : list) {
            System.out.println(i);
        }
    }
}

package com.javarush.task.task03.task0325;

import java.io.*;

/* 
Финансовые ожидания
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sSalary = br.readLine();
        int nSalary = Integer.parseInt(sSalary);
        System.out.printf("Я буду зарабатывать $%d в час", nSalary);
    }
}

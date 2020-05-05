package com.javarush.task.task03.task0318;

/* 
План по захвату мира
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String sAge = bufferedReader.readLine(); //читаем строку с клавиатуры
        int nAge = Integer.parseInt(sAge); //преобразовываем строку в число.
        String name = bufferedReader.readLine(); //читаем строку с клавиатуры
        System.out.printf("%s захватит мир через %d лет. Му-ха-ха!", name, nAge);
    }
}

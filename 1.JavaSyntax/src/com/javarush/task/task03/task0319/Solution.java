package com.javarush.task.task03.task0319;

/* 
Предсказание на будущее
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String name = bufferedReader.readLine();
        String sNum1 = bufferedReader.readLine();
        String sNum2 = bufferedReader.readLine();
        int nNum1 = Integer.parseInt(sNum1);
        int nNum2 = Integer.parseInt(sNum2);
        System.out.printf("%s получает %d через %d лет.", name, nNum1, nNum2);
    }
}

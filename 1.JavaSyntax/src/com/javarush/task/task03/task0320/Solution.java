package com.javarush.task.task03.task0320;

/* 
Скромность украшает программиста
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(inputStreamReader);

        String name = br.readLine();
        System.out.printf("%s зарабатывает $5,000. Ха-ха-ха!", name);
    }
}

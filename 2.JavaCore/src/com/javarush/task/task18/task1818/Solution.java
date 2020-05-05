package com.javarush.task.task18.task1818;

/* 
Два в одном
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        String file3 = reader.readLine();
        reader.close();
        FileInputStream fis = new FileInputStream(file2);
        FileOutputStream fos = new FileOutputStream(file1);
        while (fis.available() > 0) {
            fos.write(fis.read());
        }
        fis.close();
        fis = new FileInputStream(file3);
        while (fis.available() > 0) {
            fos.write(fis.read());
        }
        fis.close();
        fos.close();
    }
}
/*
D:\2.txt
D:\3.txt
D:\4.txt
 */
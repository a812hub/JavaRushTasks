package com.javarush.task.task18.task1819;

/* 
Объединение файлов
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        reader.close();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FileInputStream fis = new FileInputStream(file2);
        while (fis.available() > 0) {
            baos.write(fis.read());
        }
        fis.close();
        fis = new FileInputStream(file1);
        while (fis.available() > 0) {
            baos.write(fis.read());
        }
        fis.close();
        FileOutputStream fos = new FileOutputStream(file1);
        baos.writeTo(fos);
        fos.close();
        baos.close();
    }
}
/*
D:\2.txt
D:\3.txt
D:\4.txt
 */
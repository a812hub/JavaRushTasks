package com.javarush.task.task18.task1808;

/* 
Разделение файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        String file3 = reader.readLine();
        reader.close();

        FileInputStream fis = new FileInputStream(file1);
        FileOutputStream fos1 = new FileOutputStream(file2);
        byte[] buffer = new byte[fis.available() / 2 + fis.available() % 2];
        int count = fis.read(buffer);
        if (count > 0) {
            fos1.write(buffer, 0, count);
        }
        fos1.close();
        FileOutputStream fos2 = new FileOutputStream(file3);
        count = fis.read(buffer);
        if (count > 0) {
            fos2.write(buffer, 0, count);
        }
        fis.close();
        fos2.close();
    }
}

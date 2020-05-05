package com.javarush.task.task18.task1820;

/* 
Округление чисел
*/
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        reader.close();

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file1));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file2));
        StringBuilder sb = new StringBuilder();
        int currentByte;
        while (bis.available() > 0) {
            currentByte = bis.read();
            if (currentByte == (int)' ') {
                Integer n = (int) Math.round(Double.parseDouble(sb.toString()));
                bos.write(n.toString().getBytes());
                bos.write(' ');
                sb = new StringBuilder();
            } else if (bis.available() == 0) {
                sb.append((char) currentByte);
                Integer n = (int) Math.round(Double.parseDouble(sb.toString()));
                bos.write(n.toString().getBytes());
            } else {
                sb.append((char)currentByte);
            }
        }
        bis.close();
        bos.close();
    }
}
/*
D:\2.txt
D:\3.txt
 */
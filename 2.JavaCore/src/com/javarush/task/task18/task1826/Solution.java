package com.javarush.task.task18.task1826;

/* 
Шифровка
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
//        encoder("D:\\4.txt", "D:\\5.txt");
//        decoder("D:\\5.txt", "D:\\6.txt");
        if (args[0].equals("-e")) {
            encoder(args[1], args[2]);
        } else if (args[0].equals("-d")) {
            decoder(args[1], args[2]);
        }
    }

    private static void encoder(String fileName, String fileOutputName) throws IOException {
        BufferedInputStream fis = new BufferedInputStream(new FileInputStream(fileName));
        BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(fileOutputName));
        while (fis.available() > 0) {
            fos.write(fis.read() + 44);
        }
        fis.close();
        fos.close();
    }

    private static void decoder(String fileName, String fileOutputName) throws IOException {
        BufferedInputStream fis = new BufferedInputStream(new FileInputStream(fileName));
        BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(fileOutputName));
        while (fis.available() > 0) {
            fos.write(fis.read() - 44);
        }
        fis.close();
        fos.close();
    }

}

package com.javarush.task.task32.task3201;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(args[0], "rw")) {
            long skipBytes = Math.min(randomAccessFile.length(), Long.parseLong(args[1]));
            randomAccessFile.seek(skipBytes);
            randomAccessFile.write(args[2].getBytes());
        } catch (IOException ignored) {}
    }
}

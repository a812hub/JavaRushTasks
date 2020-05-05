package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Path path = Paths.get(reader.readLine());
        if (!Files.isDirectory(path)) {
            System.out.println(path.toString() + " - не папка");
        } else {
            final int[] countFiles = {0};
            final int[] countDirectories = {-1};
            final long[] size = {0};

            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {

                        @Override
                        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                            countDirectories[0]++;
                            return super.preVisitDirectory(dir, attrs);
                        }

                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                            countFiles[0]++;
                            size[0] += Files.size(file);
                            return super.visitFile(file, attrs);
                        }
                    }
            );

            System.out.println("Всего папок - " + countDirectories[0]);
            System.out.println("Всего файлов - " + countFiles[0]);
            System.out.println("Общий размер - " + size[0]);
        }

        reader.close();

    }
}

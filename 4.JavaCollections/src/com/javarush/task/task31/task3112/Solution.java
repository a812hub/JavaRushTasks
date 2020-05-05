package com.javarush.task.task31.task3112;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.*;

/* 
Загрузчик файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("D:/MyDownloads"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        URL url = new URL(urlString);
        Path tmpFile = Files.createTempFile("tmpJR-", "");
        Path filePath = null;

        try (InputStream inputStream = url.openStream()) {

            Files.copy(inputStream, tmpFile, StandardCopyOption.REPLACE_EXISTING);

            filePath = Paths.get(downloadDirectory.toString() + urlString.substring(urlString.lastIndexOf('/')));
            Files.createDirectories(downloadDirectory);
            Files.move(tmpFile, filePath, StandardCopyOption.REPLACE_EXISTING);

        } finally {
            if (Files.exists(tmpFile)) {
                Files.delete(tmpFile);
            }
        }

        return filePath;

    }
}

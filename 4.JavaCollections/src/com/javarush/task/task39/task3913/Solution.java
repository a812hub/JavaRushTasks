package com.javarush.task.task39.task3913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Set;

public class Solution {
    public static void main(String[] args) throws IOException {
        LogParser logParser = new LogParser(Paths.get("C:\\Users\\Asus VivoBook\\IdeaProjects\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task39\\task3913\\logs\\"));
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String query = reader.readLine();
            Set<Object> set = logParser.execute(query);
            for (Object o : set) {
                System.out.println(o);
            }
        }
    }
}
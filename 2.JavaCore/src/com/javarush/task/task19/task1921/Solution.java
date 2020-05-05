package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        String[] line;
        Date date;
        while (reader.ready()) {
            line = reader.readLine().split(" ");
            date = new Date(Integer.parseInt(line[line.length - 1]) - 1900,
                    Integer.parseInt(line[line.length - 2]) - 1,
                    Integer.parseInt(line[line.length - 3]));
            StringBuilder sb = new StringBuilder(line[0]);
            if (line.length > 4) {
                for (int i = 1; i < line.length - 3; i++) {
                    sb.append(" ").append(line[i]);
                }
            }
            PEOPLE.add(new Person(sb.toString(), date));
        }
        reader.close();
        for (Person person : PEOPLE) {
            System.out.println(person.getName() + " " + person.getBirthDate());
        }

    }
}

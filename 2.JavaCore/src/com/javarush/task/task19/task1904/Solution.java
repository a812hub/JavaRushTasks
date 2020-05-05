package com.javarush.task.task19.task1904;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) throws IOException, ParseException {
//        PersonScannerAdapter reader = new PersonScannerAdapter(new Scanner(new FileReader("D:/3.txt")));
//        Person p = reader.read();
//        Person p2 = reader.read();
//        System.out.println(p);
//        System.out.println(p2);
    }

    public static class PersonScannerAdapter implements PersonScanner {
        private Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException, ParseException {
            String[] fullName = this.fileScanner.nextLine().split(" ");
            DateFormat df = new SimpleDateFormat("dd MM yyyy");
            Date birthDate = df.parse(String.format("%s %s %s", fullName[3], fullName[4], fullName[5]));
            return new Person(fullName[1], fullName[2], fullName[0], birthDate);
        }

        @Override
        public void close() throws IOException {
            this.fileScanner.close();
        }
    }
}

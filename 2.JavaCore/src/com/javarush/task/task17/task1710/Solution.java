package com.javarush.task.task17.task1710;

import org.w3c.dom.ls.LSOutput;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        if (args[0].equals("-c")) {
            Date bd = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[3]);
            if (args[2].equals("м")) {
                allPeople.add(Person.createMale(args[1], bd));
                System.out.println(allPeople.size() - 1);
            } else if (args[2].equals("ж")) {
                allPeople.add(Person.createFemale(args[1], bd));
                System.out.println(allPeople.size() - 1);
            }
        } else if (args[0].equals("-u")) {
            Date bd = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[4]);
            if (args[3].equals("м")) {
                allPeople.set(Integer.parseInt(args[1]), Person.createMale(args[2], bd));
            } else if (args[3].equals("ж")) {
                allPeople.set(Integer.parseInt(args[1]), Person.createFemale(args[2], bd));
            }
        } else if (args[0].equals("-d")) {
            allPeople.get(Integer.parseInt(args[1])).setName(null);
            allPeople.get(Integer.parseInt(args[1])).setSex(null);
            allPeople.get(Integer.parseInt(args[1])).setBirthDate(null);
        } else if ((args[0].equals("-i"))) {
            int id = Integer.parseInt(args[1]);
            String s = null;
            if (allPeople.get(id).getSex() == Sex.MALE) {
                s = "м";
            } else if ((allPeople.get(id).getSex() == Sex.FEMALE)) {
                s = "ж";
            }
            DateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            System.out.printf("%s %s %s\n", allPeople.get(id).getName(), s, df.format(allPeople.get(id).getBirthDate()));
        }
    }
}

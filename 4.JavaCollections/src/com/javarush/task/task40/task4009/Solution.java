package com.javarush.task.task40.task4009;

/* 
Buon Compleanno!
*/

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;

public class Solution {
    public static void main(String[] args) {
        System.out.println(getWeekdayOfBirthday("30.05.1984", "2015"));
        System.out.println(getWeekdayOfBirthday("1.12.2015", "2016"));
    }

    public static String getWeekdayOfBirthday(String birthday, String year) {
        try {
            LocalDate date = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("d.M.yyyy"));
            Year askedYear = Year.parse(year);
            date = date.withYear(askedYear.getValue());
            return date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ITALIAN);
        } catch (DateTimeParseException e) {
            return "";
        }
    }
}

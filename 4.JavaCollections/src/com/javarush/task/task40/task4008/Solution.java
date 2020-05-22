package com.javarush.task.task40.task4008;

/* 
Работа с Java 8 DateTime API
*/

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {
        printDate("9.10.2017 5:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        try {
            StringBuilder result = new StringBuilder();

            Pattern pDate = Pattern.compile("\\d{1,2}\\.\\d{1,2}\\.\\d{2,4}");
            Pattern pTime = Pattern.compile("\\d{1,2}:\\d{1,2}:\\d{1,2}");
            Matcher m = pDate.matcher(date);
            if (m.find()) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d.M.yyyy");
                LocalDate localDate = LocalDate.parse(m.group(), dtf);

                result.append("День: ").append(localDate.get(ChronoField.DAY_OF_MONTH)).append("\r\n");
                result.append("День недели: ").append(localDate.get(ChronoField.DAY_OF_WEEK)).append("\r\n");
                result.append("День месяца: ").append(localDate.get(ChronoField.DAY_OF_MONTH)).append("\r\n");
                result.append("День года: ").append(localDate.get(ChronoField.DAY_OF_YEAR)).append("\r\n");
                result.append("Неделя месяца: ").append(localDate.get(ChronoField.ALIGNED_WEEK_OF_MONTH)).append("\r\n");
                result.append("Неделя года: ").append(localDate.get(ChronoField.ALIGNED_WEEK_OF_YEAR)).append("\r\n");
                result.append("Месяц: ").append(localDate.get(ChronoField.MONTH_OF_YEAR)).append("\r\n");
                result.append("Год: ").append(localDate.get(ChronoField.YEAR)).append("\r\n");
            }

            m = pTime.matcher(date);
            if (m.find()) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:m:s");
                LocalTime time = LocalTime.parse(m.group(), dtf);
                String am_pm = time.get(ChronoField.AMPM_OF_DAY) == 0 ? "AM" : "PM";
                result.append("AM или PM: ").append(am_pm).append("\r\n");
                result.append("Часы: ").append(time.get(ChronoField.HOUR_OF_AMPM)).append("\r\n");
                result.append("Часы дня: ").append(time.get(ChronoField.HOUR_OF_DAY)).append("\r\n");
                result.append("Минуты: ").append(time.get(ChronoField.MINUTE_OF_HOUR)).append("\r\n");
                result.append("Секунды: ").append(time.get(ChronoField.SECOND_OF_MINUTE)).append("\r\n");
            }
            System.out.print(result);
        } catch (DateTimeParseException ignored) {
        }
    }
}

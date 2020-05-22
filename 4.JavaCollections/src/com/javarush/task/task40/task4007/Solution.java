package com.javarush.task.task40.task4007;

/* 
Работа с датами
*/

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        try {
            StringBuilder result = new StringBuilder();
            Calendar calendar = Calendar.getInstance();
            Pattern pDate = Pattern.compile("\\d{1,2}\\.\\d{1,2}\\.\\d{2,4}");
            Pattern pTime = Pattern.compile("\\d{1,2}:\\d{1,2}:\\d{1,2}");
            Matcher m = pDate.matcher(date);
            if (m.find()) {
                DateFormat df1 = new SimpleDateFormat("d.M.yyyy");
                calendar.setTime(df1.parse(m.group()));
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ? 7 : calendar.get(Calendar.DAY_OF_WEEK) - 1;

                result.append("День: ").append(calendar.get(Calendar.DATE)).append("\r\n");
                result.append("День недели: ").append(dayOfWeek).append("\r\n");
                result.append("День месяца: ").append(calendar.get(Calendar.DAY_OF_MONTH)).append("\r\n");
                result.append("День года: ").append(calendar.get(Calendar.DAY_OF_YEAR)).append("\r\n");
                result.append("Неделя месяца: ").append(calendar.get(Calendar.WEEK_OF_MONTH)).append("\r\n");
                result.append("Неделя года: ").append(calendar.get(Calendar.WEEK_OF_YEAR)).append("\r\n");
                result.append("Месяц: ").append(calendar.get(Calendar.MONTH) + 1).append("\r\n");
                result.append("Год: ").append(calendar.get(Calendar.YEAR)).append("\r\n");
            }
            m = pTime.matcher(date);
            if (m.find()) {
                DateFormat df1 = new SimpleDateFormat("H:m:s");
                calendar.setTime(df1.parse(m.group()));

                result.append("AM или PM: ").append(calendar.getDisplayName(Calendar.AM_PM, Calendar.SHORT, Locale.getDefault())).append("\r\n");
                result.append("Часы: ").append(calendar.get(Calendar.HOUR)).append("\r\n");
                result.append("Часы дня: ").append(calendar.get(Calendar.HOUR_OF_DAY)).append("\r\n");
                result.append("Минуты: ").append(calendar.get(Calendar.MINUTE)).append("\r\n");
                result.append("Секунды: ").append(calendar.get(Calendar.SECOND)).append("\r\n");
            }
            System.out.print(result);
        } catch (ParseException ignored) {
        }
    }
}

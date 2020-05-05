package com.javarush.task.task08.task0827;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/* 
Работа с датой
*/

public class Solution {
    public static void main(String[] args) throws ParseException {

        System.out.println(isDateOdd("JANUARY 2 2000"));
    }

    public static boolean isDateOdd(String date) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("MMMMM d yyyy", Locale.ENGLISH);
        Date d = dateFormat.parse(date);
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(d);
        int dayOfYear = c.get(Calendar.DAY_OF_YEAR);

/*
        DateFormat dateFormat1 = new SimpleDateFormat("D");
        String sDayOfYear = dateFormat1.format(d);
        int nDayOfYear = Integer.parseInt(sDayOfYear);
        System.out.println(nDayOfYear);
*/

        return (dayOfYear % 2 == 1);
    }
}

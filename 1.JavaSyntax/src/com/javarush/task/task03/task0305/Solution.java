package com.javarush.task.task03.task0305;

/* 
Я так давно родился
*/

import java.time.LocalDate;
import java.time.Month;

public class Solution {
    public static void main(String[] args) {
        LocalDate ld = LocalDate.of(1986, Month.SEPTEMBER, 15);
        System.out.println(ld.getMonth() + " " + ld.getDayOfMonth() + " " + ld.getYear());
    }
}

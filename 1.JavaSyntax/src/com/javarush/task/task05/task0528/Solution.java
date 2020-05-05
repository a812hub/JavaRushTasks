package com.javarush.task.task05.task0528;

/* 
Вывести на экран сегодняшнюю дату
*/

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Solution {
    public static void main(String[] args) {
        LocalDate ld = LocalDate.now();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd MM yyyy");
        System.out.println(ld.format(f));
    }
}

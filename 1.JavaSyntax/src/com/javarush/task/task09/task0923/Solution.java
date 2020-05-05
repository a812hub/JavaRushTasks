package com.javarush.task.task09.task0923;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Гласные и согласные
*/

public class Solution {
    public static char[] vowels = new char[]{'а', 'я', 'у', 'ю', 'и', 'ы', 'э', 'е', 'о', 'ё'};

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s = in.readLine();
        String sVovels = "";
        String sConsonants = "";
        for (int i = 0; i < s.length(); i++) {
            if (isVowel(s.charAt(i))) {
                sVovels += s.charAt(i) + " ";
            }
            else if (s.charAt(i) != ' '){
                sConsonants += s.charAt(i) + " ";
            }
        }
        System.out.println(sVovels);
        System.out.println(sConsonants);
    }

    // метод проверяет, гласная ли буква
    public static boolean isVowel(char c) {
        c = Character.toLowerCase(c);  // приводим символ в нижний регистр - от заглавных к строчным буквам
        for (char d : vowels) {  // ищем среди массива гласных
            if (c == d) {
                return true;
            }
        }
        return false;
    }
}
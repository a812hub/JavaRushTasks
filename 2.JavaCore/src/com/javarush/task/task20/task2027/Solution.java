package com.javarush.task.task20.task2027;

import java.util.*;
import java.util.stream.Collectors;

/* 
Кроссворд
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        detectAllWords(crossword, "home", "same");
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {

        List<Word> result = Arrays.stream(words)
                .map(Word::new)
                .collect(Collectors.toList());

        NEXT_WORD:
        for (Word word : result) {
            String text = word.text;
            String reverseText = new StringBuilder(text).reverse().toString();

            // Поиск в горизонталях
            for (int i = 0; i < crossword.length; i++) {
                StringBuilder line = new StringBuilder();
                for (int j = 0; j < crossword[i].length; j++) {
                    line.append((char) crossword[i][j]);
                }
                if (line.toString().contains(text)) {
                    word.setStartPoint(line.indexOf(text), i);
                    word.setEndPoint(line.indexOf(text) + text.length() - 1, i);
                    continue NEXT_WORD;
                } else if (line.toString().contains(reverseText)) {
                    word.setStartPoint(line.indexOf(reverseText) + reverseText.length() - 1, i);
                    word.setEndPoint(line.indexOf(reverseText), i);
                    continue NEXT_WORD;
                }
            }

            // Поиск в вертикалях
            for (int j = 0; j < crossword[0].length; j++) {
                StringBuilder line = new StringBuilder();
                for (int i = 0; i < crossword.length; i++) {
                    line.append((char) crossword[i][j]);
                }
                if (line.toString().contains(text)) {
                    word.setStartPoint(j, line.indexOf(text));
                    word.setEndPoint(j, line.indexOf(text) + text.length() - 1);
                    continue NEXT_WORD;
                } else if (line.toString().contains(reverseText)) {
                    word.setStartPoint(j, line.indexOf(reverseText) + reverseText.length() - 1);
                    word.setEndPoint(j, line.indexOf(reverseText));
                    continue NEXT_WORD;
                }
            }

            // Поиск в диагоналях ++/--
            for (int i = 0; i < crossword.length; i++) {
                StringBuilder line = new StringBuilder();
                int m = 0;
                while (i + m < crossword.length && m < crossword[0].length) {
                    line.append((char) crossword[i + m][m]);
                    m++;
                }
                if (line.toString().contains(text)) {
                    word.setStartPoint(line.indexOf(text), i + line.indexOf(text));
                    word.setEndPoint(line.indexOf(text) + text.length() - 1, i + line.indexOf(text) + text.length() - 1);
                    continue NEXT_WORD;
                } else if (line.toString().contains(reverseText)) {
                    word.setStartPoint(line.indexOf(reverseText) + reverseText.length() - 1, i + line.indexOf(reverseText) + reverseText.length() - 1);
                    word.setEndPoint(line.indexOf(reverseText), i + line.indexOf(reverseText));
                    continue NEXT_WORD;
                }
            }
            for (int j = 1; j < crossword[0].length; j++) {
                StringBuilder line = new StringBuilder();
                int m = 0;
                while (j + m < crossword[0].length && m < crossword.length) {
                    line.append((char) crossword[m][j + m]);
                    m++;
                }
                if (line.toString().contains(text)) {
                    word.setStartPoint(j + line.indexOf(text), line.indexOf(text));
                    word.setEndPoint(j + line.indexOf(text) + text.length() - 1, line.indexOf(text) + text.length() - 1);
                    continue NEXT_WORD;
                } else if (line.toString().contains(reverseText)) {
                    word.setStartPoint(j + line.indexOf(reverseText) + reverseText.length() - 1, line.indexOf(reverseText) + reverseText.length() - 1);
                    word.setEndPoint(j + line.indexOf(reverseText), line.indexOf(reverseText));
                    continue NEXT_WORD;
                }
            }

            // Поиск в диагоналях +-/-+
            for (int i = 0; i < crossword.length; i++) {
                StringBuilder line = new StringBuilder();
                int m = 0;
                while (i - m >= 0 && m < crossword[0].length) {
                    line.append((char) crossword[i - m][m]);
                    m++;
                }
                if (line.toString().contains(text)) {
                    word.setStartPoint(line.indexOf(text), i - line.indexOf(text));
                    word.setEndPoint(line.indexOf(text) + text.length() - 1, i - line.indexOf(text) - text.length() + 1);
                    continue NEXT_WORD;
                } else if (line.toString().contains(reverseText)) {
                    word.setStartPoint(line.indexOf(reverseText) + reverseText.length() - 1, i - line.indexOf(reverseText) - reverseText.length() + 1);
                    word.setEndPoint(line.indexOf(reverseText), i - line.indexOf(reverseText));
                    continue NEXT_WORD;
                }
            }
            for (int j = 1; j < crossword[0].length; j++) {
                StringBuilder line = new StringBuilder();
                int m = 0;
                while (j + m < crossword[0].length && crossword.length - m >= 0) {
                    line.append((char) crossword[crossword.length - 1 - m][j + m]);
                    m++;
                }
                if (line.toString().contains(text)) {
                    word.setStartPoint(j + line.indexOf(text), crossword.length - 1 - line.indexOf(text));
                    word.setEndPoint(j + line.indexOf(text) + text.length() - 1, crossword.length - 1 - line.indexOf(text) - text.length() + 1);
                    continue NEXT_WORD;
                } else if (line.toString().contains(reverseText)) {
                    word.setStartPoint(j + line.indexOf(reverseText) + reverseText.length() - 1, crossword.length - 1 - line.indexOf(reverseText) - reverseText.length() + 1);
                    word.setEndPoint(j + line.indexOf(reverseText), crossword.length - 1 - line.indexOf(reverseText));
                    continue NEXT_WORD;
                }
            }

            System.out.println(text + " - not found");
        }

        return result;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}

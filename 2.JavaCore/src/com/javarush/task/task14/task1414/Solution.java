package com.javarush.task.task14.task1414;

/* 
MovieFactory
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception {
        ArrayList<String> ss = new ArrayList<>();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s;
        do {
            s = in.readLine();
            ss.add(s);
        } while (s.equals("cartoon") || s.equals("thriller") || s.equals("soapOpera"));
            in.close();

        Movie movie;
        for (String s1 : ss) {
            movie = MovieFactory.getMovie(s1);
            if (movie != null) {
                System.out.println(movie.getClass().getSimpleName());
            }
        }
    }

    static class MovieFactory {

        static Movie getMovie(String key) {
            Movie movie = null;
            //создание объекта SoapOpera (мыльная опера) для ключа "soapOpera"
            if ("soapOpera".equals(key)) {
                movie = new SoapOpera();
            } else if ("cartoon".equals(key)) {
                movie = new Cartoon();
            } else if ("thriller".equals(key)) {
                movie = new Thriller();
            }
            return movie;
        }
    }

    static abstract class Movie {
    }

    static class SoapOpera extends Movie {
    }

    static class Cartoon extends Movie {
    }

    static class Thriller extends Movie {
    }
}

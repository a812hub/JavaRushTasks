package com.javarush.task.task38.task3804;

import java.util.Locale;

public class ExceptionFactory {
    public static Throwable createException(Enum e) {
        if (e != null) {
            switch (e.getClass().getSimpleName()) {
                case "ApplicationExceptionMessage":
                    return new Exception(e.name().substring(0, 1) + e.name().substring(1).replace("_", " ").toLowerCase());
                case "DatabaseExceptionMessage":
                    return new RuntimeException(e.name().substring(0, 1) + e.name().substring(1).replace("_", " ").toLowerCase());
                case "UserExceptionMessage":
                    return new Error(e.name().substring(0, 1) + e.name().substring(1).replace("_", " ").toLowerCase());
            }
        }
        return new IllegalArgumentException();
    }
}

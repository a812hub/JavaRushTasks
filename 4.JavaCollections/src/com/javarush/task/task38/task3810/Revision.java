package com.javarush.task.task38.task3810;

import java.lang.annotation.Repeatable;

public @interface Revision {
    int revision();
    Date date();
    Author[] authors() default {};
    String comment() default "";
}

package com.javarush.task.task38.task3812;

/* 
Обработка аннотаций
*/

import java.lang.reflect.Method;

public class Solution {
    public static void main(String[] args) {
        printFullyQualifiedNames(Solution.class);
        printFullyQualifiedNames(SomeTest.class);

        printValues(Solution.class);
        printValues(SomeTest.class);
    }

    public static boolean printFullyQualifiedNames(Class c) {
        boolean isPrepareMyTest = false;
        if (c.isAnnotationPresent(PrepareMyTest.class)) {
            PrepareMyTest prepareMyTest = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
            String[] fullyQualifiedNames = prepareMyTest.fullyQualifiedNames();
            for (String qualifiedName : fullyQualifiedNames) {
                System.out.println(qualifiedName);
            }
            isPrepareMyTest = true;
        }
        return isPrepareMyTest;
    }

    public static boolean printValues(Class c) {
        boolean isPrepareMyTest = false;
        if (c.isAnnotationPresent(PrepareMyTest.class)) {
            PrepareMyTest prepareMyTest = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
            Class<?>[] values = prepareMyTest.value();
            for (Class clazz : values) {
                System.out.println(clazz.getSimpleName());
            }
            isPrepareMyTest = true;
        }
        return isPrepareMyTest;
    }
}

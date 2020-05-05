package com.javarush.task.task36.task3602;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

/*
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Class list = null;
        Class[] classes = Collections.class.getDeclaredClasses();
        for (Class clazz : classes) {
            try {
                if (List.class.isAssignableFrom(clazz)) {
                    Constructor constructor = clazz.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    List obj = (List) constructor.newInstance();
                    obj.get(0);
                }
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ignored) {
            } catch (IndexOutOfBoundsException e) {
                list = clazz;
            }
        }
        return list;
    }
}

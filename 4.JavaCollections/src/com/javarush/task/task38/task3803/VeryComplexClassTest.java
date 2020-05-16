package com.javarush.task.task38.task3803;

import org.junit.Test;

import static org.junit.Assert.*;

public class VeryComplexClassTest {

    VeryComplexClass vcc = new VeryComplexClass();

    @Test(expected = ClassCastException.class)
    public void methodThrowsClassCastException() {
        vcc.methodThrowsClassCastException();
    }

    @Test(expected = NullPointerException.class)
    public void methodThrowsNullPointerException() {
        vcc.methodThrowsNullPointerException();
    }
}
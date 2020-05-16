package com.javarush.task.task38.task3802;

import org.junit.Test;

import static org.junit.Assert.*;

public class VeryComplexClassTest {

    @Test(expected = Exception.class)
    public void veryComplexMethod() throws Exception {
        VeryComplexClass veryComplexClass = new VeryComplexClass();
        veryComplexClass.veryComplexMethod();
    }
}
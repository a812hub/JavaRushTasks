package com.javarush.task.task38.task3801;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class SolutionTest {
    private final ByteArrayOutputStream baos = new ByteArrayOutputStream();

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(baos));
    }

    @Test
    public void main_test_empty_exception() {
        Solution.main(new String[]{""});
        Assert.assertEquals("Ошибка: Имя пустое\r\n", baos.toString());
    }

    @Test
    public void main_test_null_exception() {
        Solution.main(new String[]{null});
        Assert.assertEquals("Ошибка: Имя не задано\r\n", baos.toString());
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.setOut(null);
    }
}
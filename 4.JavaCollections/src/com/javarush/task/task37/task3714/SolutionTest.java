package com.javarush.task.task37.task3714;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {

    @Test
    public void romanToIntegerIV() {
        Assert.assertEquals(Solution.romanToInteger("IV"), 4);
    }

    @Test
    public void romanToIntegerVI() {
        Assert.assertEquals(Solution.romanToInteger("VI"), 6);
    }

    @Test
    public void romanToIntegerVIII() {
        Assert.assertEquals(Solution.romanToInteger("VIII"), 8);
    }

    @Test
    public void romanToIntegerIX() {
        Assert.assertEquals(Solution.romanToInteger("IX"), 9);
    }

    @Test
    public void romanToIntegerXV() {
        Assert.assertEquals(Solution.romanToInteger("XV"), 15);
    }

    @Test
    public void romanToIntegerXIX() {
        Assert.assertEquals(Solution.romanToInteger("XIX"), 19);
    }

    @Test
    public void romanToIntegerXL() {
        Assert.assertEquals(Solution.romanToInteger("XL"), 40);
    }

    @Test
    public void romanToIntegerXLII() {
        Assert.assertEquals(Solution.romanToInteger("XLII"), 42);
    }

    @Test
    public void romanToIntegerLX() {
        Assert.assertEquals(Solution.romanToInteger("LX"), 60);
    }

    @Test
    public void romanToIntegerLXXX() {
        Assert.assertEquals(Solution.romanToInteger("LXXX"), 80);
    }

    @Test
    public void romanToIntegerLXXXIII() {
        Assert.assertEquals(Solution.romanToInteger("LXXXIII"), 83);
    }

    @Test
    public void romanToIntegerXC() {
        Assert.assertEquals(Solution.romanToInteger("XC"), 90);
    }

    @Test
    public void romanToIntegerCL() {
        Assert.assertEquals(Solution.romanToInteger("CL"), 150);
    }

    @Test
    public void romanToIntegerCCLXXXIII() {
        Assert.assertEquals(Solution.romanToInteger("CCLXXXIII"), 283);
    }

    @Test
    public void romanToIntegerDCCC() {
        Assert.assertEquals(Solution.romanToInteger("DCCC"), 800);
    }

    @Test
    public void romanToIntegerMCMLXXXVIII() {
        Assert.assertEquals(Solution.romanToInteger("MCMLXXXVIII"), 1988);
    }

    @Test
    public void romanToIntegerMMDCLXXXIII() {
        Assert.assertEquals(Solution.romanToInteger("MMDCLXXXIII"), 2683);
    }

    @Test
    public void romanToIntegerMMDDCCLLXXVVII() {
        Assert.assertEquals(Solution.romanToInteger("MMDDCCLLXXVVII"), 3332);
    }

    @Test
    public void romanToIntegerMMMD() {
        Assert.assertEquals(Solution.romanToInteger("MMMD"), 3500);
    }

    @Test
    public void romanToIntegerCM() {
        Assert.assertEquals(Solution.romanToInteger("CM"), 900);
    }
}
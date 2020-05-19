package com.javarush.task.task39.task3909;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void isOneEditAway_1() {
        Assert.assertTrue(Solution.isOneEditAway("asdfg", "asdfh"));
    }
    @Test
    public void isOneEditAway_2() {
        Assert.assertTrue(Solution.isOneEditAway("asdfg", "asdfgh"));
    }
    @Test
    public void isOneEditAway_3() {
        Assert.assertTrue(Solution.isOneEditAway("asdfgh", "asdfg"));
    }
    @Test
    public void isOneEditAway_4() {
        Assert.assertTrue(Solution.isOneEditAway("qsdfg", "asdfg"));
    }
    @Test
    public void isOneEditAway_5() {
        Assert.assertTrue(Solution.isOneEditAway("qasdfg", "asdfg"));
    }
    @Test
    public void isOneEditAway_6() {
        Assert.assertTrue(Solution.isOneEditAway("asdfg", "qasdfg"));
    }
    @Test
    public void isOneEditAway_7() {
        Assert.assertTrue(Solution.isOneEditAway("asdfg", "arsdfg"));
    }
    @Test
    public void isOneEditAway_8() {
        Assert.assertTrue(Solution.isOneEditAway("arsdfg", "asdfg"));
    }
    @Test
    public void isOneEditAway_9() {
        Assert.assertTrue(Solution.isOneEditAway("ar1sdfg", "arrsdfg"));
    }
    @Test
    public void isOneEditAway_10() {
        Assert.assertFalse(Solution.isOneEditAway("aq1sdfg", "arrsdfg"));
    }
}
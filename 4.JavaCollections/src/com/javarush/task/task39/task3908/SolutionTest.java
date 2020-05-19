package com.javarush.task.task39.task3908;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void isPalindromePermutation() {
        Assert.assertTrue(Solution.isPalindromePermutation("qrrrwertyqweyt"));
        Assert.assertFalse(Solution.isPalindromePermutation("rrwertyqweyt"));
    }
}
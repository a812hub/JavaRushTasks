package com.javarush.task.task39.task3912;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void maxSquare_1() {
        int[][] matrix = new int[][]{
                {1, 1, 1, 0},
                {1, 1, 1, 1},
                {1, 1, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 1, 1, 0}};
        int actual = Solution.maxSquare(matrix);
        Assert.assertEquals(4, actual);
    }

    @Test
    public void maxSquare_2() {
        int[][] matrix = new int[][]{
                {1, 1, 1, 0},
                {1, 1, 1, 1},
                {1, 0, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 1, 1},
                {0, 1, 1, 1},
                {1, 1, 1, 1}};
        int actual = Solution.maxSquare(matrix);
        Assert.assertEquals(9, actual);
    }

    @Test
    public void maxSquare_3() {
        int[][] matrix = new int[][]{
                {1, 1, 1, 0, 1},
                {1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 1, 1, 1, 0},
                {1, 1, 1, 1, 1}};
        int actual = Solution.maxSquare(matrix);
        Assert.assertEquals(9, actual);
    }

    @Test
    public void maxSquare_4() {
        int[][] matrix = new int[][]{
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}};
        int actual = Solution.maxSquare(matrix);
        Assert.assertEquals(16, actual);
    }

    @Test
    public void maxSquare_5() {
        int[][] matrix = new int[][]{
                {1, 1, 1, 0},
                {1, 0, 0, 1},
                {1, 1, 0, 1},
                {1, 0, 1, 0}};
        int actual = Solution.maxSquare(matrix);
        Assert.assertEquals(1, actual);
    }

    @Test
    public void maxSquare_0000() {
        int[][] matrix = new int[][]{
                {0, 0},
                {0, 0}};
        int actual = Solution.maxSquare(matrix);
        Assert.assertEquals(0, actual);
    }

    @Test
    public void maxSquare_0001() {
        int[][] matrix = new int[][]{
                {0, 0},
                {0, 1}};
        int actual = Solution.maxSquare(matrix);
        Assert.assertEquals(1, actual);
    }

    @Test
    public void maxSquare_0010() {
        int[][] matrix = new int[][]{
                {0, 0},
                {1, 0}};
        int actual = Solution.maxSquare(matrix);
        Assert.assertEquals(1, actual);
    }

    @Test
    public void maxSquare_0011() {
        int[][] matrix = new int[][]{
                {0, 0},
                {1, 1}};
        int actual = Solution.maxSquare(matrix);
        Assert.assertEquals(1, actual);
    }

    @Test
    public void maxSquare_0100() {
        int[][] matrix = new int[][]{
                {0, 1},
                {0, 0}};
        int actual = Solution.maxSquare(matrix);
        Assert.assertEquals(1, actual);
    }

    @Test
    public void maxSquare_0101() {
        int[][] matrix = new int[][]{
                {0, 1},
                {0, 1}};
        int actual = Solution.maxSquare(matrix);
        Assert.assertEquals(1, actual);
    }

    @Test
    public void maxSquare_0110() {
        int[][] matrix = new int[][]{
                {0, 1},
                {1, 0}};
        int actual = Solution.maxSquare(matrix);
        Assert.assertEquals(1, actual);
    }

    @Test
    public void maxSquare_0111() {
        int[][] matrix = new int[][]{
                {0, 1},
                {1, 1}};
        int actual = Solution.maxSquare(matrix);
        Assert.assertEquals(1, actual);
    }

    @Test
    public void maxSquare_1000() {
        int[][] matrix = new int[][]{
                {1, 0},
                {0, 0}};
        int actual = Solution.maxSquare(matrix);
        Assert.assertEquals(1, actual);
    }

    @Test
    public void maxSquare_1001() {
        int[][] matrix = new int[][]{
                {1, 0},
                {0, 1}};
        int actual = Solution.maxSquare(matrix);
        Assert.assertEquals(1, actual);
    }

    @Test
    public void maxSquare_1010() {
        int[][] matrix = new int[][]{
                {1, 0},
                {1, 0}};
        int actual = Solution.maxSquare(matrix);
        Assert.assertEquals(1, actual);
    }

    @Test
    public void maxSquare_1011() {
        int[][] matrix = new int[][]{
                {1, 0},
                {1, 1}};
        int actual = Solution.maxSquare(matrix);
        Assert.assertEquals(1, actual);
    }

    @Test
    public void maxSquare_1100() {
        int[][] matrix = new int[][]{
                {1, 1},
                {0, 0}};
        int actual = Solution.maxSquare(matrix);
        Assert.assertEquals(1, actual);
    }

    @Test
    public void maxSquare_1101() {
        int[][] matrix = new int[][]{
                {1, 1},
                {0, 1}};
        int actual = Solution.maxSquare(matrix);
        Assert.assertEquals(1, actual);
    }

    @Test
    public void maxSquare_1110() {
        int[][] matrix = new int[][]{
                {1, 1},
                {1, 0}};
        int actual = Solution.maxSquare(matrix);
        Assert.assertEquals(1, actual);
    }

    @Test
    public void maxSquare_1111() {
        int[][] matrix = new int[][]{
                {1, 1},
                {1, 1}};
        int actual = Solution.maxSquare(matrix);
        Assert.assertEquals(4, actual);
    }
}
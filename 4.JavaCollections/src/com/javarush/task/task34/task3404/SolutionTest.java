package com.javarush.task.task34.task3404;

import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class SolutionTest {

    Solution solution = new Solution();
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(out));
    }

    @After
    public void tearDown() throws Exception {
        out.close();
    }

    @Test
    public void recurse_0() {
        solution.recurse("sin(2*(-5+1.5*4)+28)", 0); //expected output 0.5 6
        Assert.assertEquals("0,5 6\r\n", out.toString());

    }

    @Test
    public void recurse_1() {
        solution.recurse("tan(45)", 0); //         expected output 1 1
        Assert.assertEquals("1 1\r\n", out.toString());
    }

    @Test
    public void recurse_2() {
        solution.recurse("tan(-45)", 0); //         expected output -1 2
        Assert.assertEquals("-1 2\r\n", out.toString());
    }

    @Test
    public void recurse_3() {
        solution.recurse("0.305", 0); //         expected output 0.3 0
        Assert.assertEquals("0,3 0\r\n", out.toString());
    }

    @Test
    public void recurse_4() {
        solution.recurse("0.3051", 0); //         expected output 0.31 0
        Assert.assertEquals("0,31 0\r\n", out.toString());
    }

    @Test
    public void recurse_5() {
        solution.recurse("(0.3051)", 0); //         expected output 0.31 0
        Assert.assertEquals("0,31 0\r\n", out.toString());
    }

    @Test
    public void recurse_6() {
        solution.recurse("1+(1+(1+1)*(1+1))*(1+1)+1", 0); //         expected output 12 8
        Assert.assertEquals("12 8\r\n", out.toString());
    }

    @Test
    public void recurse_7() {
        solution.recurse("tan(44+sin(89-cos(180)^2))", 0); //         expected output 1 6
        Assert.assertEquals("1 6\r\n", out.toString());
    }

    @Test
    public void recurse_8() {
        solution.recurse("-2+(-2+(-2)-2*(2+2))", 0); //         expected output -14 8
        Assert.assertEquals("-14 8\r\n", out.toString());
    }

    @Test
    public void recurse_9() {
        solution.recurse("sin(80+(2+(1+1))*(1+1)+2)", 0); //         expected output 1 7
        Assert.assertEquals("1 7\r\n", out.toString());
    }

    @Test
    public void recurse_10() {
        solution.recurse("1+4/2/2+2^2+2*2-2^(2-1+1)", 0); //         expected output 6 11
        Assert.assertEquals("6 11\r\n", out.toString());
    }

    @Test
    public void recurse_11() {
        solution.recurse("10-2^(2-1+1)", 0); //         expected output 6 4
        Assert.assertEquals("6 4\r\n", out.toString());
    }

    @Test
    public void recurse_12() {
        solution.recurse("2^10+2^(5+5)", 0); //         expected output 2048 4
        Assert.assertEquals("2 048 4\r\n", out.toString());
    }

    @Test
    public void recurse_13() {
        solution.recurse("1.01+(2.02-1+1/0.5*1.02)/0.1+0.25+41.1", 0); //         expected output 72.96 8
        Assert.assertEquals("72,96 8\r\n", out.toString());
    }

    @Test
    public void recurse_14() {
        solution.recurse("0.000025+0.000012", 0); //         expected output 0 1
        Assert.assertEquals("0 1\r\n", out.toString());
    }

    @Test
    public void recurse_15() {
        solution.recurse("-2-(-2-1-(-2)-(-2)-(-2-2-(-2)-2)-2-2)", 0); //         expected output -3 16
        Assert.assertEquals("-3 16\r\n", out.toString());
    }

    @Test
    public void recurse_16() {
        solution.recurse("cos(3 + 19*3)", 0); //         expected output 0.5 3
        Assert.assertEquals("0,5 3\r\n", out.toString());
    }

    @Test
    public void recurse_17() {
        solution.recurse("2*(589+((2454*0.1548/0.01*(-2+9^2))+((25*123.12+45877*25)+25))-547)", 0); //         expected output 8302231.36 14
        Assert.assertEquals("8 302 231,36 14\r\n", out.toString());
    }

    @Test
    public void recurse_18() {
        solution.recurse("(-1 + (-2))", 0); //         expected output -3 3
        Assert.assertEquals("-3 3\r\n", out.toString());
    }

    @Test
    public void recurse_19() {
        solution.recurse("-sin(2*(-5+1.5*4)+28)", 0); //         expected output -0.5 7
        Assert.assertEquals("-0,5 7\r\n", out.toString());
    }

    @Test
    public void recurse_20() {
        solution.recurse("sin(100)-sin(100)", 0); //         expected output 0 3
        Assert.assertEquals("0 3\r\n", out.toString());
    }

    @Test
    public void recurse_21() {
        solution.recurse("-(-22+22*2)", 0);
        Assert.assertEquals("-22 4\r\n", out.toString());
    }

    @Test
    public void recurse_22() {
        solution.recurse("-2^(-2)", 0);
        Assert.assertEquals("-0,25 3\r\n", out.toString());
    }

    @Test
    public void recurse_23() {
        solution.recurse("-(-2^(-2))+2+(-(-2^(-2)))", 0);
        Assert.assertEquals("2,5 10\r\n", out.toString());
    }

    @Test
    public void recurse_24() {
        solution.recurse("(-2)*(-2)", 0);
        Assert.assertEquals("4 3\r\n", out.toString());
    }

    @Test
    public void recurse_25() {
        solution.recurse("(-2)/(-2)", 0);
        Assert.assertEquals("1 3\r\n", out.toString());
    }

    @Test
    public void recurse_26() {
        solution.recurse("sin(-30)", 0);
        Assert.assertEquals("-0,5 2\r\n", out.toString());
    }

    @Test
    public void recurse_27() {
        solution.recurse("cos(-30)", 0);
        Assert.assertEquals("0,87 2\r\n", out.toString());
    }

    @Test
    public void recurse_28() {
        solution.recurse("tan(-30)", 0);
        Assert.assertEquals("-0,58 2\r\n", out.toString());
    }

    @Test
    public void recurse_29() {
        solution.recurse("2+8*(9/4-1.5)^(1+1)", 0);
        Assert.assertEquals("6,5 6\r\n", out.toString());
    }

    @Test
    public void recurse_30() {
        solution.recurse("0.005", 0);
        Assert.assertEquals("0,01 0\r\n", out.toString());
    }

    @Test
    public void recurse_31() {
        solution.recurse("0.0049", 0);
        Assert.assertEquals("0 0\r\n", out.toString());
    }

    @Test
    public void recurse_32() {
        solution.recurse("0+0.304", 0);
        Assert.assertEquals("0,3 1\r\n", out.toString());
    }

    @Test
    public void recurse_33() {
        solution.recurse("sin(45) - cos(45)", 0);
        Assert.assertEquals("0 3\r\n", out.toString());
    }

    @Test
    public void recurse_34() {
        solution.recurse("0/(-3)", 0);
        Assert.assertEquals("0 2\r\n", out.toString());
    }

    @Test
    public void recurse_35() {
        solution.recurse("-0", 0);
        Assert.assertEquals("0 1\r\n", out.toString());
    }

    @Test
    public void recurse_36() {
        solution.recurse("2-(-2)^2", 0);
        Assert.assertEquals("-2 3\r\n", out.toString());
    }
}
package com.javarush.task.task34.task3404;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Рекурсия для мат. выражения
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recurse("-2+(-2+(-2)-2*(2+2))", 0); //         expected output -14 8
    }


    public void recurse(final String expression, int countOperation) {
//        System.out.println("Enter recurse... expression: " + expression + " countOperation: " + countOperation);

        Pattern pDigit = Pattern.compile("-?\\d+(\\.\\d+)*"); //число
        Pattern pBraceDigit = Pattern.compile("\\(" + pDigit + "\\)");  //число в скобках
        Pattern pSin = Pattern.compile("sin" + pBraceDigit);
        Pattern pCos = Pattern.compile("cos" + pBraceDigit);
        Pattern pTan = Pattern.compile("tan" + pBraceDigit);
        Pattern pBrace = Pattern.compile("\\([^()]+\\)");   //выражение в скобках
        Pattern pPow = Pattern.compile("(?<!\\d)" + pDigit + "\\^" + pDigit);
        Pattern pMult = Pattern.compile("(?<!\\d)" + pDigit + "\\*" + pDigit);
        Pattern pDiv = Pattern.compile("(?<!\\d)" + pDigit + "/" + pDigit);
        Pattern pPlus = Pattern.compile("(?<!\\d)" + pDigit + "\\+" + pDigit);
        Pattern pMinus = Pattern.compile("(?<!\\d)" + pDigit + "-" + pDigit);

        String newExpression = expression;

        // Удаляем пробелы и добавляем нули для учета унарных минусов
        if (countOperation == 0) {
            newExpression = newExpression
                    .replace(" ", "")
                    .replace("(-", "(0-");
            if (newExpression.startsWith("-")) {
                newExpression = "0" + newExpression;
            }
        }

        // Вычисление sin(), cos(), tan()
        Matcher m = pSin.matcher(newExpression);
        if (m.find()) {
            newExpression = newExpression.replaceFirst(pSin.toString(), sin(m.group()));
            recurse(newExpression, ++countOperation);
            return;
        }
        m = pCos.matcher(newExpression);
        if (m.find()) {
            recurse(newExpression.replaceFirst(pCos.toString(), cos(m.group())), ++countOperation);
            return;
        }
        m = pTan.matcher(newExpression);
        if (m.find()) {
            recurse(newExpression.replaceFirst(pTan.toString(), tan(m.group())), ++countOperation);
            return;
        }

        //у чисел в скобках - удаляем скобки
        m = pBraceDigit.matcher(newExpression);
        if (m.find()) {
            String group = m.group();
            newExpression = newExpression
                    .replace(group, group.substring(1, group.length() - 1));
            recurse(newExpression, countOperation);
            return;
        }

        // если в выражении нет скобок, добавляем скобки
        if (!newExpression.contains("(")) {
            newExpression = "(" + newExpression + ")";
        }


        m = pBrace.matcher(newExpression);
//        if (m.find()) {
        m.find();
        String braceGroup = m.group();
        if (braceGroup.contains("^")) {
            Matcher m1 = pPow.matcher(braceGroup);
            m1.find();
            newExpression = newExpression.replaceFirst
                    (Pattern.quote(braceGroup), braceGroup.replaceFirst(pPow.toString(), pow(m1.group())));
            recurse(newExpression, ++countOperation);
            return;
        }
        if (braceGroup.contains("/")) {
            Matcher m1 = pDiv.matcher(braceGroup);
            m1.find();
            newExpression = newExpression.replaceFirst
                    (Pattern.quote(braceGroup), braceGroup.replaceFirst(pDiv.toString(), dev(m1.group())));
            recurse(newExpression, ++countOperation);
            return;
        }
        if (braceGroup.contains("*")) {
            Matcher m1 = pMult.matcher(braceGroup);
            m1.find();
            newExpression = newExpression.replaceFirst
                    (Pattern.quote(braceGroup), braceGroup.replaceFirst(pMult.toString(), mult(m1.group())));
            recurse(newExpression, ++countOperation);
            return;
        }

        if (braceGroup.contains("--")) {
            recurse(newExpression.replace("--", "+"), countOperation);
            return;
        }

        if (braceGroup.contains("-")) {
            Matcher m1 = pMinus.matcher(braceGroup);
            if (m1.find()) {
                newExpression = newExpression.replaceFirst
                        (Pattern.quote(braceGroup), braceGroup.replaceFirst(pMinus.toString(), dif(m1.group())));
                recurse(newExpression, ++countOperation);
                return;
            }
        }
        if (braceGroup.contains("+")) {
            Matcher m1 = pPlus.matcher(braceGroup);
            m1.find();
            newExpression = newExpression.replaceFirst
                    (Pattern.quote(braceGroup), braceGroup.replaceFirst(pPlus.toString(), sum(m1.group())));
            recurse(newExpression, ++countOperation);
            return;
        }

        newExpression = newExpression.substring(1, newExpression.length() - 1);

        //вывод результата
        if (newExpression.matches(pDigit.toString())) {
            Double result = Double.parseDouble(newExpression);
            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
            numberFormat.setMaximumFractionDigits(2);
            String res = numberFormat.format(result);
            if (res.equals("-0")) {
                res = "0";
            }
            System.out.println(res + " " + countOperation);
        } else {
            System.out.println("Что-то пошло не так :(");
        }
    }


    public String sin(String expression) {
//        System.out.println("count sin... " + expression);
        String s = expression.substring(3)
                .replace("(", "")
                .replace(")", "");
        double d = Double.parseDouble(s);
        return String.valueOf(Math.sin(Math.toRadians(d)));
    }

    public String cos(String expression) {
//        System.out.println("count cos... " + expression);
        String s = expression.substring(3)
                .replace("(", "")
                .replace(")", "");
        double d = Double.parseDouble(s);
        return String.valueOf(Math.cos(Math.toRadians(d)));
    }

    public String tan(String expression) {
//        System.out.println("count tan... " + expression);
        String s = expression.substring(3)
                .replace("(", "")
                .replace(")", "");
        double d = Double.parseDouble(s);
        return String.valueOf(Math.tan(Math.toRadians(d)));
    }

    public String pow(String expression) {
//        System.out.println("count pow... " + expression);
        double d1 = Double.parseDouble(expression.substring(0, expression.indexOf('^')));
        double d2 = Double.parseDouble(expression.substring(expression.indexOf('^') + 1));
        return String.format(Locale.ENGLISH, "%f", Math.pow(d1, d2));
    }

    public String sum(String expression) {
//        System.out.println("count sum... " + expression);
        double d1 = Double.parseDouble(expression.substring(0, expression.lastIndexOf('+')));
        double d2 = Double.parseDouble(expression.substring(expression.lastIndexOf('+') + 1));
        return String.format(Locale.ENGLISH, "%f", d1 + d2);
    }

    public String dif(String expression) {
//        System.out.println("count dif(-)... " + expression);
        double d1 = Double.parseDouble(expression.substring(0, expression.lastIndexOf("-")));
        double d2 = Double.parseDouble(expression.substring(expression.lastIndexOf("-") + 1));
        return String.format(Locale.ENGLISH, "%f", d1 - d2);

    }

    public String mult(String expression) {
//        System.out.println("count mult... " + expression);
        double d1 = Double.parseDouble(expression.substring(0, expression.indexOf('*')));
        double d2 = Double.parseDouble(expression.substring(expression.indexOf('*') + 1));
        return String.format(Locale.ENGLISH, "%f", d1 * d2);
    }

    public String dev(String expression) {
//        System.out.println("count dev... " + expression);
        double d1 = Double.parseDouble(expression.substring(0, expression.indexOf('/')));
        double d2 = Double.parseDouble(expression.substring(expression.indexOf('/') + 1));
        return String.format(Locale.ENGLISH, "%f", d1 / d2);
    }


    public Solution() {
        //don't delete
    }
}


/*
    public void recurse(final String expression, int countOperation) {
//        System.out.println("Enter recurse... expression: " + expression + " countOperation: " + countOperation);

        Pattern pDigitPositive = Pattern.compile("\\d+(\\.\\d+)*");
        Pattern pDigit = Pattern.compile("[-]?" + pDigitPositive);
        Pattern pBraceDigit = Pattern.compile("\\(" + pDigit + "\\)");
        Pattern pSin = Pattern.compile("sin" + pBraceDigit);
        Pattern pCos = Pattern.compile("cos" + pBraceDigit);
        Pattern pTan = Pattern.compile("tan" + pBraceDigit);
        Pattern pBrace = Pattern.compile("\\((?<braceGroup>[^()]+)\\)");
        Pattern pPow = Pattern.compile(pDigitPositive + "\\^" + pDigit);
        Pattern pMult = Pattern.compile(pDigit + "\\*" + pDigit);
        Pattern pDev = Pattern.compile(pDigit + "/" + pDigit);
        Pattern pPlus = Pattern.compile(pDigit + "\\+" + pDigit);
        Pattern pMinus = Pattern.compile(pDigit + "-" + pDigitPositive);

        String newExpression = expression.
                replace(" ", "")
                .replace("--", "+")
                .replace("(+", "(");
        if (newExpression.charAt(0) == '+')
            newExpression = newExpression.substring(1);


        // Подсчет количества отрицаний в исходном выражении
        if (countOperation == 0) {
            if (expression.startsWith("-")) {
                countOperation++;
            }
            Pattern p = Pattern.compile("[^\\d)]-\\d");
            Matcher m = p.matcher(expression);
            while (m.find()) {
                countOperation++;
            }
            p = Pattern.compile("[^\\d)]-\\(");
            m = p.matcher(expression);
            while (m.find()) {
                countOperation++;
            }
        }

        // Вычисление sin(), cos(), tan()
        Matcher m = pSin.matcher(newExpression);
        if (m.find()) {
            newExpression = newExpression.replaceFirst(pSin.toString(), sin(m.group()));
            recurse(newExpression, ++countOperation);
            return;
        }
        m = pCos.matcher(newExpression);
        if (m.find()) {
            recurse(newExpression.replaceFirst(pCos.toString(), cos(m.group())), ++countOperation);
            return;
        }
        m = pTan.matcher(newExpression);
        if (m.find()) {
            recurse(newExpression.replaceFirst(pTan.toString(), tan(m.group())), ++countOperation);
            return;
        }

        m = pBraceDigit.matcher(newExpression);
        if (m.find()) {
            String group = m.group();
            newExpression = newExpression
                    .replace(group, group.substring(1, group.length() - 1))
                    .replace("--", "+");
            recurse(newExpression, countOperation);
            return;
        }

        if (!newExpression.contains("(")) {
            newExpression = "(" + newExpression + ")";
        }

        m = pBrace.matcher(newExpression);
        if (m.find()) {
            String braceGroup = m.group("braceGroup");
            if (braceGroup.contains("^")) {
                Matcher m1 = pPow.matcher(braceGroup);
                m1.find();
                newExpression = newExpression.replaceFirst
                        (Pattern.quote(braceGroup), braceGroup.replaceFirst(pPow.toString(), pow(m1.group())));
                recurse(newExpression, ++countOperation);
                return;
            }
            if (braceGroup.contains("/")) {
                Matcher m1 = pDev.matcher(braceGroup);
                m1.find();
                newExpression = newExpression.replaceFirst
                        (Pattern.quote(braceGroup), braceGroup.replaceFirst(pDev.toString(), dev(m1.group())));
                recurse(newExpression, ++countOperation);
                return;
            }
            if (braceGroup.contains("*")) {
                Matcher m1 = pMult.matcher(braceGroup);
                m1.find();
                newExpression = newExpression.replaceFirst
                        (Pattern.quote(braceGroup), braceGroup.replaceFirst(pMult.toString(), mult(m1.group())));
                recurse(newExpression, ++countOperation);
                return;
            }
            if (braceGroup.contains("-")) {
                Matcher m1 = pMinus.matcher(braceGroup);
                if (m1.find()) {
                    newExpression = newExpression.replaceFirst
                            (Pattern.quote(braceGroup), braceGroup.replaceFirst(pMinus.toString(), dif(m1.group())));
                    recurse(newExpression, ++countOperation);
                    return;
                }
            }
            if (braceGroup.contains("+")) {
                Matcher m1 = pPlus.matcher(braceGroup);
                m1.find();
                newExpression = newExpression.replaceFirst
                        (Pattern.quote(braceGroup), braceGroup.replaceFirst(pPlus.toString(), sum(m1.group())));
                recurse(newExpression, ++countOperation);
                return;
            }
        }
        newExpression = newExpression.substring(1, newExpression.length() - 1);

        if (newExpression.matches(String.valueOf(pDigit))) {
            //вывод результата
            Double result = Double.parseDouble(newExpression);
            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
            numberFormat.setMaximumFractionDigits(2);
            String res = numberFormat.format(result);
            if (res.equals("-0")) {
                res = "0";
            }
            System.out.println(res + " " + countOperation);

        } else {
            recurse(newExpression, countOperation);
        }
    }

 */
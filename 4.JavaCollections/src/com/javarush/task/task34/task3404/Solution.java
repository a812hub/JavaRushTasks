package com.javarush.task.task34.task3404;

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
//        solution.recurse("sin(2*(-5+1.5*4)+28)", 0); //expected output 0.5 6
//        solution.recurse("tan(45)", 0); //         expected output 1 1
        solution.recurse("tan(-45)", 0); //         expected output -1 2
//        solution.recurse("0.305", 0); //         expected output 0.3 0
//        solution.recurse("0.3051", 0); //         expected output 0.31 0
//        solution.recurse("(0.3051)", 0); //         expected output 0.31 0
//        solution.recurse("1+(1+(1+1)*(1+1))*(1+1)+1", 0); //         expected output 12 8
//        solution.recurse("tan(44+sin(89-cos(180)^2))", 0); //         expected output 1 6
//        solution.recurse("-2+(-2+(-2)-2*(2+2))", 0); //         expected output -14 8
//        solution.recurse("sin(80+(2+(1+1))*(1+1)+2)", 0); //         expected output 1 7
//!!        solution.recurse("1+4/2/2+2^2+2*2-2^(2-1+1)", 0); //         expected output 6 11
//!!!!        solution.recurse("10-2^(2-1+1)", 0); //         expected output 6 4
//        solution.recurse("2^10+2^(5+5)", 0); //         expected output 2048 4
//        solution.recurse("1.01+(2.02-1+1/0.5*1.02)/0.1+0.25+41.1", 0); //         expected output 72.96 8
//        solution.recurse("0.000025+0.000012", 0); //         expected output 0 1
//        solution.recurse("-2-(-2-1-(-2)-(-2)-(-2-2-(-2)-2)-2-2)", 0); //         expected output -3 16
//        solution.recurse("cos(3 + 19*3)", 0); //         expected output 0.5 3
//        solution.recurse("2*(589+((2454*0.1548/0.01*(-2+9^2))+((25*123.12+45877*25)+25))-547)", 0); //         expected output 8302231.36 14
//        solution.recurse("(-1 + (-2))", 0); //         expected output -3 3
//        solution.recurse("-sin(2*(-5+1.5*4)+28)", 0); //         expected output -0.5 7
//        solution.recurse("sin(100)-sin(100)", 0); //         expected output 0 3

    }

    public void recurse(final String expression, int countOperation) {
        String newExpression = expression.replaceAll(" ", "");
        System.out.println("recurse " + newExpression + " " + countOperation);
        Pattern pDigit = Pattern.compile("-?\\d+(\\.\\d+)*");
        Pattern pBraceDigit = Pattern.compile("\\(" + pDigit + "\\)");
        Pattern pWOBraceDigit = Pattern.compile("[^(]" + pDigit + "[^)]");
        Pattern pSin = Pattern.compile("sin" + pBraceDigit);
        Pattern pCos = Pattern.compile("cos" + pBraceDigit);
        Pattern pTan = Pattern.compile("tan" + pBraceDigit);
        Pattern pBrace = Pattern.compile("\\((?<braceGroup>[^()]+)\\)");
        Pattern pPow = Pattern.compile(pDigit + "\\^" + pDigit);
        Pattern pMult = Pattern.compile(pDigit + "\\*" + pDigit);
        Pattern pDev = Pattern.compile(pDigit + "/" + pDigit);
        Pattern pPlus = Pattern.compile(pDigit + "\\+" + pDigit);
        Pattern pMinus = Pattern.compile(pDigit + "-" + pDigit);

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
        }

        System.out.println(newExpression);
        Matcher m2 = pWOBraceDigit.matcher(newExpression);
        while (m2.find()) {
            System.out.println("wobrace " + m2.group());
        }


        // Вычисление sin(), cos(), tan()
        boolean change = false;
        Matcher m = pSin.matcher(newExpression);
        while (m.find()) {
            String group = m.group();
            newExpression = newExpression.replace(group, sin(group));
            countOperation++;
//            System.out.println(newExpression  + " " + countOperation);
            change = true;
        }

        m = pCos.matcher(newExpression);
        while (m.find()) {
            String group = m.group();
            newExpression = newExpression.replace(group, cos(group));
            countOperation++;
//            System.out.println(newExpression  + " " + countOperation);
            change = true;
        }

        m = pTan.matcher(newExpression);
        while (m.find()) {
            String group = m.group();
            newExpression = newExpression.replace(group, tan(group));
            countOperation++;
//            System.out.println(newExpression  + " " + countOperation);
            change = true;
        }

        if (change) {
            recurse(newExpression, countOperation);
        } else {
            m = pBraceDigit.matcher(newExpression);
            while (m.find()) {
                String group = m.group();
                newExpression = newExpression.replace(group, group.substring(1, group.length() - 1));
                newExpression = newExpression.replace("--", "+");
            }

            newExpression = "(" + newExpression + ")";
            System.out.println(newExpression);

            m = pBrace.matcher(newExpression);
            if (m.find()) {
                String braceGroup = m.group("braceGroup");
                String tmpBraceGroup = braceGroup;
                if (tmpBraceGroup.contains("^")) {
                    Matcher m1 = pPow.matcher(tmpBraceGroup);
                    while (m1.find()) {
                        String groupPow = m1.group();
//                        System.out.println(groupPow);
                        tmpBraceGroup = tmpBraceGroup.replaceFirst(Pattern.quote(groupPow), pow(groupPow));
                        countOperation++;
//                        System.out.println(tmpBraceGroup  + " " + countOperation);
                    }
                }
                if (tmpBraceGroup.contains("/")) {
                    Matcher m1 = pDev.matcher(tmpBraceGroup);
                    while (m1.find()) {
                        String groupDev = m1.group();
//                        System.out.println(groupDev);
                        tmpBraceGroup = tmpBraceGroup.replace(groupDev, dev(groupDev));
                        countOperation++;
//                        System.out.println(tmpBraceGroup  + " " + countOperation);
                    }
                }
                if (tmpBraceGroup.contains("*")) {
                    Matcher m1 = pMult.matcher(tmpBraceGroup);
                    while (m1.find()) {
                        String groupMult = m1.group();
//                        System.out.println(groupMult);
                        tmpBraceGroup = tmpBraceGroup.replace(groupMult, mult(groupMult));
                        countOperation++;
//                        System.out.println(tmpBraceGroup  + " " + countOperation);
                    }
                }
                if (tmpBraceGroup.contains("-")) {
                    Matcher m1 = pMinus.matcher(tmpBraceGroup);
                    while (m1.find()) {
                        String groupMinus = m1.group();
//                        System.out.println(groupMinus);
                        tmpBraceGroup = tmpBraceGroup.replace(groupMinus, dif(groupMinus));
                        countOperation++;
//                        System.out.println(tmpBraceGroup  + " " + countOperation);
                    }
                }
                if (tmpBraceGroup.contains("+")) {
                    Matcher m1 = pPlus.matcher(tmpBraceGroup);
                    String groupPlus = "";
                    while (m1.find()) {
                        groupPlus = m1.group();
//                        System.out.println(groupPlus);
                        countOperation++;
                        tmpBraceGroup = tmpBraceGroup.replace(groupPlus, sum(groupPlus));
//                        System.out.println(tmpBraceGroup  + " " + countOperation);
                    }
                }
//                System.out.println("braceGroup " + braceGroup );
//                System.out.println("tmpbraceGroup " + tmpBraceGroup);
//                System.out.println("newExp " + newExpression);
//                System.out.println(Pattern.quote(braceGroup));
//                System.out.println("replaceF " + newExpression.replaceFirst(Pattern.quote(braceGroup), tmpBraceGroup));
                newExpression = newExpression.replaceFirst(Pattern.quote(braceGroup), tmpBraceGroup);
//                System.out.println(newExpression  + " " + countOperation);
            }


            newExpression = newExpression.substring(1, newExpression.length() - 1);
            if (newExpression.matches(String.valueOf(pDigit))) {
                //вывод результата
                Double result = Double.parseDouble(newExpression);
                NumberFormat numberFormat = NumberFormat.getNumberInstance();
                numberFormat.setMaximumFractionDigits(2);
                NumberFormat.getInstance().format(result);
                System.out.printf(numberFormat.format(result) + " " + countOperation);
//                System.out.printf("%.2f %d", result, countOperation);
                System.out.println();
            } else {
                recurse(newExpression, countOperation);
            }
        }
    }


    public String sin(String expression) {
        String s = expression.substring(3)
                .replaceAll(" ", "")
                .replaceAll("\\(", "")
                .replaceAll("\\)", "");
        double d = Double.parseDouble(s);
        return String.valueOf(Math.sin(Math.toRadians(d)));
    }

    public String cos(String expression) {
        String s = expression.substring(3)
                .replaceAll(" ", "")
                .replaceAll("\\(", "")
                .replaceAll("\\)", "");
        double d = Double.parseDouble(s);
        return String.valueOf(Math.cos(Math.toRadians(d)));
    }

    public String tan(String expression) {
        String s = expression.substring(3)
                .replaceAll(" ", "")
                .replaceAll("\\(", "")
                .replaceAll("\\)", "");
        double d = Double.parseDouble(s);
        return String.valueOf(Math.tan(Math.toRadians(d)));
    }

    public String pow(String expression) {
        double d1 = Double.parseDouble(expression.substring(0, expression.indexOf('^')));
        double d2 = Double.parseDouble(expression.substring(expression.indexOf('^') + 1));
        return String.valueOf(Math.pow(d1, d2));
    }

    public String sum(String expression) {
        double d1 = Double.parseDouble(expression.substring(0, expression.indexOf('+')));
        double d2 = Double.parseDouble(expression.substring(expression.indexOf('+') + 1));
        String result = String.format(Locale.ENGLISH, "%f", d1 + d2);
        System.out.println("result " + result);
        return String.format(Locale.ENGLISH, "%f", d1 + d2);
    }

    public String dif(String expression) {
        Pattern pDigit = Pattern.compile("-?\\d+(\\.\\d+)*");
        Pattern p = Pattern.compile("(?<f>" + pDigit + ")-(?<s>" + pDigit + ")");
        Matcher m = p.matcher(expression);
        m.find();
        double d1 = Double.parseDouble(m.group("f"));
        double d2 = Double.parseDouble(m.group("s"));
        return String.valueOf(d1 - d2);
    }

    public String mult(String expression) {
        double d1 = Double.parseDouble(expression.substring(0, expression.indexOf('*')));
        double d2 = Double.parseDouble(expression.substring(expression.indexOf('*') + 1));
        return String.valueOf(d1 * d2);
    }

    public String dev(String expression) {
        double d1 = Double.parseDouble(expression.substring(0, expression.indexOf('/')));
        double d2 = Double.parseDouble(expression.substring(expression.indexOf('/') + 1));
        return String.valueOf(d1 / d2);
    }


    public String simple(String expression) {
        System.out.println("inside simple" + expression);
        if (expression.matches("sin\\(*[ -]*\\d+(.\\d+)*\\)*")) {
            return sin(expression);
        }
        if (expression.matches("cos[ -]*\\d+(.\\d+)*")) {
            return cos(expression);
        }
        if (expression.matches("tan[ -]*\\d+(.\\d+)*")) {
            return tan(expression);
        }
        if (expression.matches("-*\\d+(.\\d+)* *\\^ *\\d+(.\\d+)*")) {
            return pow(expression);
        }
        if (expression.matches("-*\\d+(.\\d+)* *\\+ *\\d+(.\\d+)*")) {
            return sum(expression);
        }
        if (expression.matches("-*\\d+(.\\d+)* *\\- *\\d+(.\\d+)*")) {
            return dif(expression);
        }
        if (expression.matches("-*\\d+(.\\d+)* *\\* *\\d+(.\\d+)*")) {
            return mult(expression);
        }
        if (expression.matches("-*\\d+(.\\d+)* */ *\\d+(.\\d+)*")) {
            return dev(expression);
        } else return "!!!!!!";
    }

    public Solution() {
        //don't delete
    }
}

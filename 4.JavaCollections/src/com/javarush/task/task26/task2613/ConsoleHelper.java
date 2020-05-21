package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static final ResourceBundle res
            = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String s = "";
        try {
            s = bis.readLine();
            if (s.toUpperCase().equals("EXIT")) {
                printExitMessage();
                throw new InterruptOperationException();
            }
        } catch (IOException ignored) {
        }
        return s;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        writeMessage(res.getString("choose.currency.code"));
        String code = readString();
        while (code.length() != 3) {
            writeMessage(res.getString("invalid.data"));
            code = readString();
        }
        return code.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
        String[] result;
        do {
            result = readString().split(" ");
            try {
                if (result.length == 2 && Integer.parseInt(result[0]) > 0 && Integer.parseInt(result[1]) > 0)
                    break;
            } catch (NumberFormatException ignored) {
            }
            writeMessage(res.getString("invalid.data"));
        } while (true);
        return result;
    }

    public static Operation askOperation() throws InterruptOperationException {
        writeMessage(res.getString("choose.operation") + "\r\n" +
                "\t1 - " + res.getString("operation.INFO") + "\r\n" +
                "\t2 - " + res.getString("operation.DEPOSIT") + "\r\n" +
                "\t3 - " + res.getString("operation.WITHDRAW") + "\r\n" +
                "\t4 - " + res.getString("operation.EXIT"));
        try {
            return Operation.getAllowableOperationByOrdinal(Integer.parseInt(readString()));
        } catch (IllegalArgumentException e) {
            writeMessage(res.getString("invalid.data"));
            return askOperation();
        }
    }

    public static void printExitMessage() {
        writeMessage(res.getString("the.end"));
    }
}

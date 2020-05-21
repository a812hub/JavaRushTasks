package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class LoginCommand implements Command {
    private final ResourceBundle validCreditCards
            = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private final ResourceBundle res
            = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String number = ConsoleHelper.readString();
            String pin = ConsoleHelper.readString();
            if (!number.matches("\\d{12}") || !pin.matches("\\d{4}")) {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                continue;
            }
            if (!validCreditCards.containsKey(number) || !validCreditCards.getString(number).equals(pin)) {
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), number));
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                continue;
            }
            ConsoleHelper.writeMessage(String.format(res.getString("success.format"), number));
            break;
        }
    }
}

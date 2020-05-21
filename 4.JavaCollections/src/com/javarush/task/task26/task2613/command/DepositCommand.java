package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class DepositCommand implements Command {
    private ResourceBundle res
            = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "deposit");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator cm = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);

        

        String[] denomination = ConsoleHelper.getValidTwoDigits(code);
        int den = Integer.parseInt(denomination[0]);
        int countOfNotes = Integer.parseInt(denomination[1]);
        cm.addAmount(den, countOfNotes);
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), den * countOfNotes, code));
    }
}

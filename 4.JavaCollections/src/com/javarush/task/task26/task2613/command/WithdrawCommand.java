package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command  {
    private final ResourceBundle res
            = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));

        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator cm = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        while (cm.getTotalAmount() == 0) {
            ConsoleHelper.writeMessage(res.getString("not.enough.money"));
            code = ConsoleHelper.askCurrencyCode();
            cm = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        }

        int expectedAmount;
        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
            try {
                expectedAmount = Integer.parseInt(ConsoleHelper.readString());
                if (expectedAmount <= 0) {
                    continue;
                }
                if (!cm.isAmountAvailable(expectedAmount)) {
                    ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                    continue;
                }
                Map<Integer, Integer> resultOfWithdraw = cm.withdrawAmount(expectedAmount);
                for (Map.Entry<Integer, Integer> entry : resultOfWithdraw.entrySet()) {
                    ConsoleHelper.writeMessage("\t" + entry.getKey() + " - " + entry.getValue());
                }
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), expectedAmount, code));
                break;
            } catch (NumberFormatException ignored) {
                ConsoleHelper.writeMessage(res.getString("specify.amount"));
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
            }
        }

    }
}

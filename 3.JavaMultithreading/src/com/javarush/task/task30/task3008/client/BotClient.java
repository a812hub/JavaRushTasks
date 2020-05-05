package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BotClient extends Client {

    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected String getUserName() {
        return String.format("date_bot_%d", (int)(Math.random() * 100));
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    public class BotSocketThread extends SocketThread {
        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            String userName;
            String request;
            if (message.contains(": ")) {
                userName = message.substring(0, message.indexOf(':'));
                request = message.substring(message.indexOf(':') + 2);
                Map<String, String> patterns = new HashMap<>();
                patterns.put("дата","d.MM.YYYY");
                patterns.put("день","d");
                patterns.put("месяц","MMMM");
                patterns.put("год","YYYY");
                patterns.put("время","H:mm:ss");
                patterns.put("час","H");
                patterns.put("минуты","m");
                patterns.put("секунды","s");

                if (patterns.containsKey(request)) {
                    Date date = new GregorianCalendar().getTime();
                    String response = "Информация для " + userName + ": " + new SimpleDateFormat(patterns.get(request)).format(date);
                    sendTextMessage(response);
                }
            }
        }

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }
    }
}

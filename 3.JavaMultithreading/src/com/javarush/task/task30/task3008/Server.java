package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void sendBroadcastMessage(Message message) {
        for (Connection connection : connectionMap.values()) {
            try {
                connection.send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Сообщение не отправлено.");
            }
        }

    }

    public static void main(String[] args) throws IOException {

        System.out.println("Введите порт:");
        try (ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt())) {
            ConsoleHelper.writeMessage("Сервер запущен");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Handler(clientSocket).start();
            }

        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            boolean isNameAccepted = false;
            String userName = null;

            while (!isNameAccepted) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message message = connection.receive();
                userName = message.getData();
                if (message.getType() == MessageType.USER_NAME &&
                        userName != null && userName.length() > 0 && !connectionMap.containsKey(userName)) {
                    connectionMap.put(userName, connection);
                    connection.send(new Message(MessageType.NAME_ACCEPTED));
                    isNameAccepted = true;
                }
            }
            return userName;
        }

        private void notifyUsers(Connection connection, String userName) throws IOException {
            for (String users : connectionMap.keySet()) {
                if (!userName.equals(users)) {
                    connection.send(new Message(MessageType.USER_ADDED, users));
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT) {
                    String text = String.format("%s: %s", userName, message.getData());
                    sendBroadcastMessage(new Message(MessageType.TEXT, text));
                } else {
                    ConsoleHelper.writeMessage("Ошибка: не текст.");
                }
            }
        }

        @Override
        public void run() {

            ConsoleHelper.writeMessage("Установлено новое соединение с удаленным адресом " + socket.getRemoteSocketAddress());
            String userName = null;
            try (Connection connection = new Connection(socket)) {
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                notifyUsers(connection, userName);
                serverMainLoop(connection, userName);
            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом.");
            } finally {
                if (userName != null) {
                    connectionMap.remove(userName);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
                }
                ConsoleHelper.writeMessage("Соединение с удаленным адресом закрыто.");
            }
        }
    }
}

package com.javarush.task.task40.task4006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;

/* 
Отправка GET-запроса через сокет
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        getSite(new URL("http://javarush.ru/social.html"));
    }

    public static void getSite(URL url) {
        try {
            Socket clientSocket = new Socket(url.getHost(), 80);

            OutputStream outputStream = clientSocket.getOutputStream();
            outputStream.write(("GET " + url.getFile() + " HTTP/1.0\\r\\n\\r\\n").getBytes());
            outputStream.close();

            BufferedReader inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            StringBuilder response = new StringBuilder();
            while (inputStream.ready()){
                response.append(inputStream.readLine());
            }
            inputStream.close();
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
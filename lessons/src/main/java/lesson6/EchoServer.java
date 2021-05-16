package lesson6;

import java.io.*;
import java.net.*;

public class EchoServer extends EchoClass{
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println("Сервер запущен, ожидается подключение...");
            socket = serverSocket.accept();
            EchoClient.openServerConnection();
            System.out.println("Клиент подключился");
            EchoClient.sendMessage();
            EchoClient.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

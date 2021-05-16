package lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class EchoClass {
    public static final int SERVER_PORT = 8189;
    public static String QUIT_FLAG = "";
    public static String QUIT_KEY = "/end";
    static final String SERVER_ADDR = "localhost";
    static Socket socket = null;
    static DataInputStream in;
    static DataOutputStream out;
    static Scanner scanner;

    static void openServerConnection() throws IOException {
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        scanner = new Scanner(System.in);
    }

    static void openClientConnection() throws IOException {
        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        scanner = new Scanner(System.in);
    }

    public static void closeConnection() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!QUIT_FLAG.equalsIgnoreCase(QUIT_KEY)) {
                    String message = scanner.nextLine();
                    if (message.equalsIgnoreCase(QUIT_KEY)) {
                        QUIT_FLAG = QUIT_KEY;
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        scanner.close();
                        break;
                    }
                    if (!message.trim().isEmpty()) {
                        try {
                            out.writeUTF(message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    public static void getMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (!QUIT_FLAG.equalsIgnoreCase(QUIT_KEY)) {
                        String answer = in.readUTF();
                        if (answer.equalsIgnoreCase(QUIT_KEY)) {
                            QUIT_FLAG = QUIT_KEY;
                            try {
                                in.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        } else {
                            System.out.println(answer);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Диалог завершен");
                }
            }
        }).start();
    }
}

package lesson7;

import java.io.*;
import java.net.Socket;

public class ClientHandler {
    private EchoServer echoServer;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private String name;

    public String getName() {
        return name;
    }

    public ClientHandler(EchoServer echoServer, Socket socket) {
        try {
            this.echoServer = echoServer;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.name = "";
            new Thread(() -> {
                try {
                    authentication();
                    readMessages();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }
            }).start();
        } catch (IOException e) {
            throw new RuntimeException("Проблемы при создании обработчика клиента");
        }
    }

    public void authentication() throws IOException {
        while (true) {
            String str = in.readUTF();
            if (str.startsWith(ChatParams.AUTH_FLAG)) {
                String[] parts = str.split("\\s");
                String nick = echoServer.getAuthService().getNickByLoginPass(parts[1], parts[2]);
                if (nick != null) {
                    if (!echoServer.isNickBusy(nick)) {
                        sendMsg(ChatParams.AUTH_OK_FLAG + nick);
                        name = nick;
                        echoServer.broadcastMsg(name + " зашел в чат");
                        echoServer.subscribe(this);
                        return;
                    } else {
                        sendMsg("Учетная запись уже используется");
                    }
                } else {
                    sendMsg("Неверные логин/пароль");
                }
            }
        }
    }

    public void readMessages() throws IOException {
        while (true) {
            String strFromClient = in.readUTF();
            System.out.println("от " + name + ": " + strFromClient);
            if (strFromClient.equals(ChatParams.BREAK_FLAG)) {
                return;
            }
            if (strFromClient.startsWith(ChatParams.PRIVAT_FLAG)) {
                String nickTo = strFromClient.substring(strFromClient.indexOf(" ") + 1);
                String mesIs = nickTo.substring(nickTo.indexOf(" ") + 1);
                nickTo = nickTo.substring(0, nickTo.indexOf(" "));
                echoServer.sendPrivatMsg(this, nickTo, nickTo + ": " + mesIs);
            } else{
                echoServer.broadcastMsg(name + ": " + strFromClient);
            }
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        echoServer.unsubscribe(this);
        echoServer.broadcastMsg(name + " вышел из чата");
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

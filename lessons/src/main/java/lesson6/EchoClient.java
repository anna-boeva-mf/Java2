package lesson6;

import java.io.*;


public class EchoClient extends EchoClass {

    public static void main(String[] args) {
        try {
            EchoClient.openClientConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        EchoClient.sendMessage();
        EchoClient.getMessage();
    }

}

package lesson7;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    TextField answerField;
    @FXML
    TextArea dialogArea;

    private static List<String> messageLogs;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public void EchoClient() {
        try {
            openConnection();
        } catch (IOException e) {
            System.out.println("Клиент покинул чат");;
        }
    }

    private void openConnection() throws IOException {
        socket = new Socket(ChatParams.SERVER_ADDR, ChatParams.SERVER_PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        String strFromServer = in.readUTF();
                        if(strFromServer.startsWith(ChatParams.AUTH_OK_FLAG)) {
                            break;
                        }
                        Controller.printMesLogs(strFromServer, messageLogs, dialogArea);
                    }
                    while (true) {
                        String strFromServer = in.readUTF();
                        if (strFromServer.equalsIgnoreCase(ChatParams.BREAK_FLAG)) {
                            break;
                        }
                        Controller.printMesLogs(strFromServer, messageLogs, dialogArea);
                    }
                } catch (Exception e) {
                    System.out.println("Диалог завершен");
                }
            }
        }).start();
    }

    public void closeConnection() {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messageLogs = new ArrayList<>();
        dialogArea.setFocusTraversable(false);
        this.EchoClient();
    }

    public void btnSend(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        this.sendMessage();
    }

    public void btnExitAction(ActionEvent actionEvent) {
        Platform.exit();
        try {
            out.writeUTF(ChatParams.BREAK_FLAG);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection();
        }
    }

    public void txtSendAnswer(ActionEvent actionEvent) {
        this.sendMessage();
    }

    public void sendMessage() {
        if (!answerField.getText().trim().isEmpty()) {
            try {
                out.writeUTF(answerField.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        answerField.clear();
    }

    public static void printMesLogs(String answer, List<String> messageLogs, TextArea dialogArea) {
        messageLogs.add(answer);
        String resultStr = "";
        for (String s : messageLogs) {
            resultStr = resultStr.concat(s).concat("\n");
        }
        dialogArea.setText(resultStr);
    }
}

package lesson4;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messageLogs = new ArrayList<>();
        dialogArea.setFocusTraversable(false);
    }

    public void btnSend(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        this.printMesLogs(answerField, messageLogs, dialogArea);
    }

    public void btnExitAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void txtSendAnswer(ActionEvent actionEvent) {
        this.printMesLogs(answerField, messageLogs, dialogArea);
    }

    public void printMesLogs(TextField answerField, List<String> messageLogs, TextArea dialogArea) {
        if (!answerField.getText().equals("")) {
            messageLogs.add(answerField.getText());
        }
        String resultStr = "";
        for (String s : messageLogs) {
            resultStr = resultStr.concat(s).concat("\n");
        }
        dialogArea.setText(resultStr);
        answerField.clear();
    }
}

package com.example.bankgui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DepositScreenController {

    @FXML
    private Button nextButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField enteredAmount;
    @FXML
    private Label invalidMessage;

    public void depositAmount() throws InterruptedException, IOException {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        double depositedAmount = Double.parseDouble(enteredAmount.getText());
       DatabaseManager db = new DatabaseManager();
       db.updateMoneyByUsername(UserSession.getLoggedInUsername(), db.getMoneyByUsername(UserSession.getLoggedInUsername()) + depositedAmount);
       db.insertTransaction(UserSession.getLoggedInUsername(), "Deposit", depositedAmount, formattedDateTime);
       invalidMessage.setText("Money Deposited Successfully!");
       Main m = new Main();
       Thread.sleep(1000);
       m.changeScene("BankMainMenu.FXML");
    }
    public void cancel() throws IOException {
        Main m = new Main();
        m.changeScene("BankMainMenu.FXML");
    }


}

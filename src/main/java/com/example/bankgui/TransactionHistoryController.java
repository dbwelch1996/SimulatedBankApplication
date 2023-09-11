package com.example.bankgui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class TransactionHistoryController {

    @FXML
    private Label transactionHistory;
    @FXML
    private Label moneyLabel;


    public void initialize(){
        DatabaseManager db = new DatabaseManager();
        String username = UserSession.getLoggedInUsername();
        double amount = db.getMoneyByUsername(username);
        moneyLabel.setText("$" + amount);
        String returnString = db.getAllTransactionsByUsername(UserSession.getLoggedInUsername());
        transactionHistory.setText(returnString);
    }


    public void cancel() throws IOException {
        Main m = new Main();
        m.changeScene("BankMainMenu.FXML");
    }




}

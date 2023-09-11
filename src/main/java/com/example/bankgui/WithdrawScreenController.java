package com.example.bankgui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class WithdrawScreenController {

    @FXML
    private Button nextButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField enteredAmount;
    @FXML
    private Label invalidMessage;
    @FXML
    private Label moneyLabel;

    public void initialize(){
        DatabaseManager db = new DatabaseManager();
        String username = UserSession.getLoggedInUsername();
        double amount = db.getMoneyByUsername(username);
        moneyLabel.setText("$" + amount);
    }
    public void withdraw() throws InterruptedException, IOException {
        DatabaseManager db = new DatabaseManager();
        String username = UserSession.getLoggedInUsername();
        double amount = db.getMoneyByUsername(username);
        double userEntered = Double.parseDouble(enteredAmount.getText());
        if(amount - userEntered < 0){
            invalidMessage.setText("You are trying to withdraw more than you have, please try again");
        }else{
            db.updateMoneyByUsername(username, amount - userEntered);
            invalidMessage.setText("Withdraw Successful");
            Thread.sleep(100);
            Main m = new Main();
            m.changeScene("BankMainMenu.fxml");
        }
    }
    public void cancel() throws IOException {
        Main m = new Main();
        m.changeScene("BankMainMenu.FXML");
    }

}

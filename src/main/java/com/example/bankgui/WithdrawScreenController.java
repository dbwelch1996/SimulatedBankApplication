package com.example.bankgui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        //Getting time for the date column
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Define a DateTimeFormatter to specify the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
        // Format the LocalDateTime as a string
        String formattedDateTime = currentDateTime.format(formatter);


        DatabaseManager db = new DatabaseManager();
        String username = UserSession.getLoggedInUsername();
        double amount = db.getMoneyByUsername(username);
        double userEntered = Double.parseDouble(enteredAmount.getText());
        if(amount - userEntered < 0){
            invalidMessage.setText("You are trying to withdraw more than you have, please try again");
        }else{
            db.updateMoneyByUsername(username, amount - userEntered);
            db.insertTransaction(UserSession.getLoggedInUsername(), "Withdraw", amount, formattedDateTime);
            Main m = new Main();
            m.changeScene("BankMainMenu.fxml");
        }
    }
    public void cancel() throws IOException {
        Main m = new Main();
        m.changeScene("BankMainMenu.FXML");
    }


}

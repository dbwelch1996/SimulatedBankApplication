package com.example.bankgui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainMenuController {
    @FXML
    private Button withdrawButton;
    @FXML
    private Button depositButton;
    @FXML
    private Button cbth; //Check balance and transaction history
    @FXML
    private Button logoutButton;

    public void withdraw() throws IOException {
        Main m = new Main();
        m.changeScene("WithdrawScreen.fxml");
    }
    public void deposit() throws IOException {
        Main m = new Main();
        m.changeScene("DepositScreen.fxml");
    }

    public void cbth() throws IOException {
       Main m = new Main();
       m.changeScene("TransactionHistory.fxml");
    }

    public void logout() throws IOException {
        Main m = new Main();
        m.changeScene("BankLogin.fxml");
    }

}

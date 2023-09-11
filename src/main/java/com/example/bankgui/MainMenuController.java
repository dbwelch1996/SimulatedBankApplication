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
    private Button checkBalanceButton;
    @FXML
    private Button logoutButton;

    public void withdraw(){

    }
    public void deposit(){
        //getDepositAmount();
    }

    public void checkBalance(){

    }
    public void logout() throws IOException {
        Main m = new Main();
        m.changeScene("BankMainMenu.fxml");
    }

}

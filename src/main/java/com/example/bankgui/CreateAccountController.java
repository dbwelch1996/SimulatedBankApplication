package com.example.bankgui;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CreateAccountController {

    @FXML
    private Button createAccountButton;
    @FXML
    private TextField createdUsername;
    @FXML
    private PasswordField createdPassword;
    @FXML
    private PasswordField verifyPassword;
    @FXML
    private Label invalidPassword;

    public void createAccount() throws IOException {
        Main m = new Main();
        String sCreatedPassword = createdPassword.getText();
        String sVerifyPassword = verifyPassword.getText();
        if(sCreatedPassword.equals(sVerifyPassword)){
            addAccount();
            m.changeScene("BankLogin.fxml");

        }else{
            invalidPassword.setText("Your Passwords do not match, try again");
        }
    }

    public void addAccount(){
        String sCreatedPassword = createdPassword.getText();
        String sUsername = createdUsername.getText();
        DatabaseManager db = new DatabaseManager();
        db.insertData(sUsername,sCreatedPassword);
        invalidPassword.setText("Account created successfully");
    }

}

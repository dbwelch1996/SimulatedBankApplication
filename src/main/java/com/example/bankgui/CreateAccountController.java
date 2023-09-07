package com.example.bankgui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CreateAccountController {


    @FXML
    private TextField createdUsername;
    @FXML
    private PasswordField createdPassword;
    @FXML
    private PasswordField verifyPassword;
    @FXML
    private Label invalidPassword;

    public void createAccount(){
        if(createdPassword.equals(verifyPassword)){
            addAccount();
        }else{
            invalidPassword.setVisible(true);
        }
    }
    public void addAccount(){
        DatabaseManager.insertData(createdUsername.toString(),createdPassword.toString());
    }

}

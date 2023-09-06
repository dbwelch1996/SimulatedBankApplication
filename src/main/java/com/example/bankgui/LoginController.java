package com.example.bankgui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

public class LoginController {

    public LoginController(){

    }

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button login;
    @FXML
    private Button createAcc;
    @FXML
    private Label invalidLogin;


    public void userLogin(ActionEvent event) throws IOException {
        checkLogin();
    }
    public void createAccount(ActionEvent event) throws IOException{
        //Create Account work
    }


    private void checkLogin() throws IOException{
    Main m = new Main();
    Auth au = new Auth();

    if(username.getText().equals("testusername") && password.getText().equals("testpassword")){
        invalidLogin.setText("Successful Login");
        //m.changeScene("BankMainMenu.fxml"); This is the next scene once the user logs in
    }else if(username.getText().isEmpty() || password.getText().isEmpty()){
        invalidLogin.setText("Please enter your username or password");
    }else{
        invalidLogin.setText("Invalid username or password");
    }


    }


















}

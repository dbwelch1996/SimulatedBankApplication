package com.example.bankgui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
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
    public void createAccount() throws IOException {
        Main m = new Main();
        m.changeScene("BankCreateAccount.fxml");
    }


    private void checkLogin() throws IOException{
    Main m = new Main();
    DatabaseManager db = new DatabaseManager();
    if(db.isPasswordCorrect(username.getText(),password.getText())){
        UserSession.setLoggedInUsername(username.getText());
        invalidLogin.setText("Successful Login");
        m.changeScene("BankMainMenu.fxml");
    }else if(username.getText().isEmpty() || password.getText().isEmpty()){
        invalidLogin.setText("Please enter your username or password");
    }else{
        invalidLogin.setText("Invalid username or password");
    }

    }


















}

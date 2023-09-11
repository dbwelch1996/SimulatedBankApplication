package com.example.bankgui;

public class UserSession {

    private static String loggedUsername;


    public static String getLoggedInUsername(){
        return loggedUsername;
    }
    public static void setLoggedInUsername(String username){
        loggedUsername = username;
    }

}

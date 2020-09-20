package com.company.controller;

public class LoginBean {

    private static boolean activeUser;
    private static int userID;
    private static String username;
    private static String password;

    public static synchronized int getUserID() {
        return userID;
    }

    public static synchronized  void setUserID(int userID) {
        LoginBean.userID = userID;
    }

    public static synchronized String getUsername() {
        return username;
    }

    public static synchronized void setUsername(String username) {
        LoginBean.username = username;
    }

    public static synchronized String getPassword() {
        return password;
    }

    public static synchronized void setPassword(String password) {
        LoginBean.password = password;
    }
}
package com.company;

import com.company.controller.LoginBean;
import com.company.controller.LoginController;
import com.company.controller.MainController;

import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {

        LoginBean.setUsername("Robin");
        LoginBean.setPassword("root");
        MainController mainController = new MainController();
        mainController.loginUser();
    }

    /*
    public static void main(String[] args) {

        int nreq = 1;
        try {
            ServerSocket sock = new ServerSocket(3306);
            while(true) {
                Socket newSock = sock.accept();
                System.out.println("Creating thread ...");
                Thread t = new ClientHandler(newSock, nreq);
                t.start();
            }
        } catch (Exception e) {
            System.out.println("IO error " + e);
        }
        System.out.println("End!");
    }
     */
}

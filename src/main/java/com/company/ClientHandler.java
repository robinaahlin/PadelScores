package com.company;

import com.company.controller.LoginBean;
import com.company.controller.MainController;
import com.company.model.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientHandler extends Thread {

    private Socket newSock;
    private int n;

    private MainController mainController;
    private User user;

    public ClientHandler(Socket s, int v) {
        newSock = s;
        n = v;
    }

    public void run() {
        try {
            PrintWriter write = new PrintWriter(newSock.getOutputStream(), true);
            BufferedReader read = new BufferedReader(new InputStreamReader(
                    newSock.getInputStream()));

            write.printf("Hello :: enter QUIT to exit \n");
            boolean moreData = true;
            String username, password;

            username = read.readLine();
            password = read.readLine();

            LoginBean.setUsername(username);
            LoginBean.setPassword(password);
            mainController = new MainController();
            int userID = mainController.loginUser();

            if(userID != 0){
                user = new User(userID, username, password);
                LoginBean.setUserID(userID);
                write.println("SUCCESS" + userID);
            } else {
                write.println("FAILED");
                newSock.close();
            }

            String ans;
            while(moreData){
                ans = read.readLine();
                if(ans.equals("QUIT")){
                    moreData = false;
                } else {
                    String json = nextCommand(ans, newSock);
                    write.println(json);
                }
            }

            newSock.close();
            System.out.println("Disconnected from client number: " + n);
        } catch (Exception e) {
            System.out.println("IO error " + e);
        }
    }

    /**
     * Use for checking what input is given by the user.
     * @param str
     * @param newSock
     * @return
     */
    private String nextCommand(String str, Socket newSock){
        return "NEXT COMMAND FIX!";
    }

    /*
    private String convertToJSON(List<User> users){
        String json = new Gson().toJson(users);
        return json;
    }
     */
}

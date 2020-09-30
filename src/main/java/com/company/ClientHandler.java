package com.company;

import com.company.controller.LoginBean;
import com.company.controller.LoginController;
import com.company.dbhandler.QueryClass;
import com.company.model.User;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientHandler extends Thread {

    private Socket socket;
    private int n;

    public ClientHandler(Socket s, int v) {
        socket = s;
        n = v;
    }

    public void run() {
        try {
            PrintWriter write = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader read = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));

            write.printf("Hello from server! \n");
            boolean moreData = true;
            boolean loginSuccess;

            String action = read.readLine();
            String username = read.readLine();
            String password = read.readLine();

            if(action.equals("LOGIN_USER")){
                LoginBean.setUsername(username);
                LoginBean.setPassword(password);
                LoginController loginController = new LoginController();
                if(loginController.loginUser()){
                    System.out.println("Login success!");
                    loginSuccess = true;
                } else {
                    System.out.println("Login failed!");
                    loginSuccess = false;
                }
            } else if(action.equals("NEW_USER")) {
                QueryClass queryClass = new QueryClass();
                if(queryClass.addUser(username,password)){
                    loginSuccess = true;
                } else {
                    loginSuccess = false;
                }
            } else {
                loginSuccess = false;
            }

            if(loginSuccess){
                // Change to save actual user_id
                LoginBean.setUserID(1);
                write.println("SUCCESS");
            } else {
                write.println("FAILED");
                socket.close();
            }

            String ans;
            while(moreData){
                ans = read.readLine();
                if(ans.equals("QUIT")){
                    moreData = false;
                } else {
                    String json = nextCommand(ans);
                    write.println(json);
                }
            }

            socket.close();
            System.out.println("Disconnected from client number: " + n);
        } catch (Exception e) {
            System.out.println("IO error " + e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Use for checking what input is given by the user.
     * @param str
     * @return
     */
    private String nextCommand(String str){
        if ("GET ALL USERS".equals(str)) {
            QueryClass queryClass = new QueryClass();
            List<User> users = queryClass.getAllUsers();
            return convertToJSON(users);
        }
        return "ERROR!";
    }

    private String convertToJSON(List<User> users){
        return new Gson().toJson(users);
    }
}

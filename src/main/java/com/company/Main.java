package com.company;

import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {

        int nreq = 1;
        try {
            ServerSocket sock = new ServerSocket(3308);
            while(true) {
                Socket socket = sock.accept();
                System.out.println("server connected to " + socket.getInetAddress());
                System.out.println("Creating thread ...");
                Thread t = new ClientHandler(socket, nreq);
                t.start();
            }
        } catch (Exception e) {
            System.out.println("IO error " + e);
        }
        System.out.println("End!");
    }
}

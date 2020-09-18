package com.company;

import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {

        int nreq = 1;
        try {
            ServerSocket sock = new ServerSocket(3333);
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
}

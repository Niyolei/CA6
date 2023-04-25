package com.dkit.gd2.dominikHampejs.Server;

import com.dkit.gd2.dominikHampejs.Core.ServerDetails;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(ServerDetails.SERVER_PORT);
            System.out.println("Server is listening on port " + ServerDetails.SERVER_PORT);

            ThreadGroup clientThreads = new ThreadGroup("Client Threads");
            clientThreads.setMaxPriority(Thread.currentThread().getPriority() - 1);
            int clientCount = 0;

            while (true){
                Socket dataSocket = serverSocket.accept();
                clientCount++;

                System.out.println("Client " + clientCount + " has connected to the server");

                ServerThread clientThread = new ServerThread(clientThreads,dataSocket.getInetAddress() + "" , dataSocket, clientCount);
                clientThread.start();
            }
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

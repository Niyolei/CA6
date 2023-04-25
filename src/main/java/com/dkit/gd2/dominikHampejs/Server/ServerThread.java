package com.dkit.gd2.dominikHampejs.Server;

import com.dkit.gd2.dominikHampejs.Commands.*;
import com.dkit.gd2.dominikHampejs.Core.ServerDetails;
import com.dkit.gd2.dominikHampejs.DAO.MySqlChampionDAO;
import com.dkit.gd2.dominikHampejs.DAO.MySqlDAO;


import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerThread extends Thread {
    private Socket dataSocket;
    private PrintWriter out;
    private Scanner in;
    private int clientNumber;

    public ServerThread(ThreadGroup group, String name,Socket dataSocket, int clientNumber) {
        super(group, name);
        this.dataSocket = dataSocket;
        this.clientNumber = clientNumber;

        try {
            out = new PrintWriter(dataSocket.getOutputStream(), true);
            in = new Scanner(dataSocket.getInputStream());
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        String command = "";
        String response;
        CommandFactory commandFactory = new CommandFactory();


        try {
            while (!command.equals(ServerDetails.QUIT_COMMAND)) {
                command = in.nextLine();
                System.out.println("Client " + clientNumber + " sent: " + command);

                String[] commandParts = command.split(ServerDetails.BREAKING_CHARACTER);

                Command c = commandFactory.getCommand(commandParts[0]);
                response = c.generateResponse(commandParts);

                out.println(response);
                System.out.println("Response sent to client " + clientNumber + ": " + response);
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        finally {
            try {
                dataSocket.close();
            }
            catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

}

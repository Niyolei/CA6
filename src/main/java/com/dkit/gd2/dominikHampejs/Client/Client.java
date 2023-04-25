package com.dkit.gd2.dominikHampejs.Client;

import com.dkit.gd2.dominikHampejs.Commands.Command;
import com.dkit.gd2.dominikHampejs.Commands.CommandFactory;
import com.dkit.gd2.dominikHampejs.Core.Color;
import com.dkit.gd2.dominikHampejs.Core.ServerDetails;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static com.dkit.gd2.dominikHampejs.Core.ServerUtility.getIntInput;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", ServerDetails.SERVER_PORT);
            System.out.println("Connected to server on port " + ServerDetails.SERVER_PORT);

            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            Scanner input = new Scanner(socket.getInputStream());

            Scanner keyboard = new Scanner(System.in);
            int choice = -1;
            String command;
            CommandFactory commandFactory = new CommandFactory();

            while(choice != 0){
                command = "";
                printMenu();
                choice = getIntInput(keyboard);

                switch (choice){
                    case 0:
                        command = ServerDetails.QUIT_COMMAND;
                        break;
                    case 1:
                        command = ServerDetails.BYID_COMMAND;
                        break;
                    case 2:
                        System.out.println("I am just a placeholder for now");
                        break;
                    default:
                        System.out.println("Not on the Menu");
                        continue;
                }

                Command c = commandFactory.getCommand(command);
                command = c.generateRequest(keyboard);

                output.println(command);

                c.handleResponse(input.nextLine());
            }

        }
        catch (UnknownHostException e) {
            System.out.println( Color.RED + "Server not found" + Color.RESET);
        }
        catch (NoSuchElementException e){
            System.out.println( Color.RED + "Server was unable to process your request" + Color.RESET);
        }
        catch (IOException e) {
            System.out.println(Color.RED + "Error: " + e.getMessage() + Color.RESET);
        }

        System.out.println("Client shutting down");
    }

    private static void printMenu() {
        System.out.println(Color.BLUE +"\n0. Quit");
        System.out.println("1. Get Champion by ID");
        System.out.println("2. Placeholder");
        System.out.print(Color.GREEN + "Enter your choice: " + Color.RESET);
    }

}

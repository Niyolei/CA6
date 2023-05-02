package com.dkit.gd2.dominikHampejs.Client;

import com.dkit.gd2.dominikHampejs.Commands.Command;
import com.dkit.gd2.dominikHampejs.Commands.CommandFactory;
import com.dkit.gd2.dominikHampejs.Core.Color;
import com.dkit.gd2.dominikHampejs.Core.ServerDetails;
import com.dkit.gd2.dominikHampejs.MenuHandler.MenuFactory;
import com.dkit.gd2.dominikHampejs.MenuHandler.SubMenu;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static com.dkit.gd2.dominikHampejs.Core.ServerUtility.getIntInput;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", ServerDetails.SERVER_PORT)){
            System.out.println("Connected to server on port " + ServerDetails.SERVER_PORT);

            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            Scanner input = new Scanner(socket.getInputStream());

            Scanner keyboard = new Scanner(System.in);
            int choice = -1;
            String command;
            CommandFactory commandFactory = new CommandFactory();
            MenuFactory menuFactory = new MenuFactory();

            while(choice != 0){
                command = "";
                String menu = "";
                printMenu();
                choice = getIntInput(keyboard);

                switch (choice) {
                    case 0 -> command = ServerDetails.QUIT_COMMAND;
                    case 1 -> menu = ServerDetails.CHAMPION_MENU;
                    case 2 -> menu = ServerDetails.ITEM_MENU;
                    case 3 -> menu = ServerDetails.BUILD_MENU;
                    default -> {
                        System.out.println("Not on the Menu");
                        continue;
                    }
                }

                if (!command.equals(ServerDetails.QUIT_COMMAND)){
                    SubMenu subMenu = menuFactory.getMenu(menu);
                    command = subMenu.getOption(keyboard);

                    if (command.equals(ServerDetails.GO_BACK)){
                        continue;
                    }
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
        System.out.println(Color.BLUE + "\nMain Menu");
        System.out.println("0. Quit");
        System.out.println("1. Champion Menu");
        System.out.println("2. Item Menu");
        System.out.println("3. Build Menu" + Color.RESET);
        System.out.print(Color.GREEN + "Enter your choice: " + Color.RESET);
    }


}

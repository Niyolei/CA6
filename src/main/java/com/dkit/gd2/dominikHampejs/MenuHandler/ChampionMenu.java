package com.dkit.gd2.dominikHampejs.MenuHandler;

import com.dkit.gd2.dominikHampejs.Core.Color;
import com.dkit.gd2.dominikHampejs.Core.ServerDetails;

import java.util.Scanner;

import static com.dkit.gd2.dominikHampejs.Core.ServerUtility.getIntInput;

public class ChampionMenu implements SubMenu {
    @Override
    public String getOption(Scanner keyboard) {
        int choice = -1;
        String command = "";

        while (choice == -1) {
            command = "";
            printChampionMenu();
            choice = getIntInput(keyboard);

            switch (choice) {
                case 0 -> command = ServerDetails.GO_BACK;
                case 1 -> command = ServerDetails.CHAMPIONBYID_COMMAND;
                case 2 -> command = ServerDetails.ALLCHAMPIONS_COMMAND;
                case 3 -> command = ServerDetails.CHAMPIONBYROLE_COMMAND;
                case 4 -> command = ServerDetails.ADDCHAMPION_COMMAND;
                case 5 -> command = ServerDetails.DELETECHAMPION_COMMAND;
                default -> {
                    System.out.println("Not on the Menu");
                    choice = -1;
                }
            }
        }
        return command;
    }

    private static void printChampionMenu() {
        System.out.println(Color.BLUE + "\nChampion Menu");
        System.out.println("0. Go Back");
        System.out.println("1. Get Champion by ID");
        System.out.println("2. Get All Champions");
        System.out.println("3. Get Champion by Role");
        System.out.println("4. Add Champion");
        System.out.println("5. Delete Champion");
        System.out.print(Color.GREEN + "Enter your choice: " + Color.RESET);
    }
}
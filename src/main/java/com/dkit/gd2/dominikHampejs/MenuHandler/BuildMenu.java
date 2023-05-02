package com.dkit.gd2.dominikHampejs.MenuHandler;

import com.dkit.gd2.dominikHampejs.Core.Color;
import com.dkit.gd2.dominikHampejs.Core.ServerDetails;

import java.util.Scanner;

import static com.dkit.gd2.dominikHampejs.Core.ServerUtility.getIntInput;

public class BuildMenu implements SubMenu{
    @Override
    public String getOption(Scanner keyboard) {
        int choice = -1;
        String command = "";

        while (choice == -1) {
            command = "";
            printBuildMenu();
            choice = getIntInput(keyboard);

            switch (choice) {
                case 0 -> command = ServerDetails.GO_BACK;
                case 1 -> command = ServerDetails.ALLBUILDS_COMMAND;
                case 2 -> command = ServerDetails.BUILDBYCHAMPION_COMMAND;
                case 3 -> command = ServerDetails.ADDBUILD_COMMAND;
                case 4 -> command = ServerDetails.DELETEBUILD_COMMAND;
                default -> {
                    System.out.println("Not on the Menu");
                    choice = -1;
                }
            }
        }
        return command;
    }

    private void printBuildMenu() {
        System.out.println(Color.BLUE + "\nBuild Menu");
        System.out.println("0. Go Back");
        System.out.println("1. Get All Builds");
        System.out.println("2. Get Build by Champion");
        System.out.println("3. Add Build");
        System.out.println("4. Delete Build");
        System.out.print(Color.GREEN + "Enter your choice: " + Color.RESET);
    }
}

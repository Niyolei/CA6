package com.dkit.gd2.dominikHampejs.MenuHandler;

import com.dkit.gd2.dominikHampejs.Core.Color;
import com.dkit.gd2.dominikHampejs.Core.ServerDetails;

import java.util.Scanner;

import static com.dkit.gd2.dominikHampejs.Core.ServerUtility.getIntInput;

public class ItemMenu implements SubMenu{

    @Override
    public String getOption(Scanner keyboard) {
        int choice = -1;
        String command = "";

        while (choice == -1) {
            command = "";
            printItemMenu();
            choice = getIntInput(keyboard);

            switch (choice) {
                case 0 -> command = ServerDetails.GO_BACK;
                case 1 -> command = ServerDetails.ITEMBYID_COMMAND;
                case 2 -> command = ServerDetails.ALLITEMS_COMMAND;
                case 3 -> command = ServerDetails.ITEMBYTYPE_COMMAND;
                case 4 -> command = ServerDetails.ADDITEM_COMMAND;
                case 5 -> command = ServerDetails.DELETEITEM_COMMAND;
                default -> {
                    System.out.println("Not on the Menu");
                    choice = -1;
                }
            }
        }
        return command;
    }

    private void printItemMenu() {
        System.out.println(Color.BLUE + "\nItem Menu");
        System.out.println("0. Go Back");
        System.out.println("1. Get Item by ID");
        System.out.println("2. Get All Items");
        System.out.println("3. Get Item by Type");
        System.out.println("4. Add Item");
        System.out.println("5. Delete Item");
        System.out.print(Color.GREEN + "Enter your choice: " + Color.RESET);
    }
}

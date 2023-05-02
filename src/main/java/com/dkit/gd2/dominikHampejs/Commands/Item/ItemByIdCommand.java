package com.dkit.gd2.dominikHampejs.Commands.Item;

import com.dkit.gd2.dominikHampejs.Commands.Command;
import com.dkit.gd2.dominikHampejs.Core.Color;
import com.dkit.gd2.dominikHampejs.Core.ServerDetails;
import com.dkit.gd2.dominikHampejs.DAO.MySqlItemDAO;
import com.dkit.gd2.dominikHampejs.DTO.Item;
import com.dkit.gd2.dominikHampejs.Exceptions.DAOexception;

import java.util.Scanner;

import static com.dkit.gd2.dominikHampejs.Core.ServerUtility.*;

public class ItemByIdCommand implements Command {
    @Override
    public String generateResponse(String[] commandParts) {
        MySqlItemDAO itemDAO = new MySqlItemDAO();

        try {
            return itemDAO.getItemAsJSON(Integer.parseInt(commandParts[1]));
        }
        catch (DAOexception e) {
            System.out.println(e.getMessage());
        }

        return Color.RED + "Error: Item not found" + Color.RESET;
    }

    @Override
    public String generateRequest(Scanner keyboard) {
        System.out.print(Color.GREEN + "Enter the id of the item you want to find: " + Color.RESET);
        int id = getIdInput(keyboard);

        return ServerDetails.ITEMBYID_COMMAND + ServerDetails.BREAKING_CHARACTER + id;
    }

    @Override
    public void handleResponse(String response) {
        System.out.println(Color.PURPLE + "\nServer response:" + Color.RESET);

        try {
            Item item = getItemFromJson(response);
            if (item != null) {
                System.out.println("Item found successfully");
                System.out.printf(ITEM_HEADER);
                item.printItem();
            }
            else
                System.out.println(Color.RED + "Error: Item not found" + Color.RESET);
        }
        catch (Exception e) {
            System.out.println(response);
        }
    }
}

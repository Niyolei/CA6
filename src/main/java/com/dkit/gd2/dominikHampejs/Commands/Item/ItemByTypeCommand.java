package com.dkit.gd2.dominikHampejs.Commands.Item;

import com.dkit.gd2.dominikHampejs.Commands.Command;
import com.dkit.gd2.dominikHampejs.Core.Color;
import com.dkit.gd2.dominikHampejs.Core.ServerDetails;
import com.dkit.gd2.dominikHampejs.DAO.MySqlItemDAO;
import com.dkit.gd2.dominikHampejs.DTO.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.dkit.gd2.dominikHampejs.Core.ServerUtility.*;

public class ItemByTypeCommand implements Command {
    @Override
    public String generateResponse(String[] commandParts) {
        MySqlItemDAO dao = new MySqlItemDAO();
        String itemType = commandParts[1];

        try {
            return dao.getItemsByTypeAsJSON(itemType);
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }

        return Color.RED + "Error: Items not found" + Color.RESET;
    }

    @Override
    public String generateRequest(Scanner keyboard) {
        String itemType = "";
        itemType = getItemType(keyboard);

        return ServerDetails.ITEMBYTYPE_COMMAND + ServerDetails.BREAKING_CHARACTER + itemType;
    }

    @Override
    public void handleResponse(String response) {
        System.out.println(Color.PURPLE + "\nServer response:" + Color.RESET);
        try {

            List<Item> items = getItemsFromJson(response);

            if (items != null) {
                System.out.printf(ITEM_HEADER);
                for (Item item : items) {
                    item.printItem();
                }
            } else {
                System.out.println(Color.RED + "Error: Items not found" + Color.RESET);
            }
        }
        catch (Exception e) {
            System.out.println(response);
        }
    }
}

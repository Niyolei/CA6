package com.dkit.gd2.dominikHampejs.Commands.Item;

import com.dkit.gd2.dominikHampejs.Commands.Command;
import com.dkit.gd2.dominikHampejs.Core.Color;
import com.dkit.gd2.dominikHampejs.Core.ServerDetails;
import com.dkit.gd2.dominikHampejs.DAO.MySqlItemDAO;
import com.dkit.gd2.dominikHampejs.DTO.Item;
import com.dkit.gd2.dominikHampejs.Exceptions.DAOexception;

import java.util.List;
import java.util.Scanner;

import static com.dkit.gd2.dominikHampejs.Core.ServerUtility.*;

public class addItemCommand implements Command {
    @Override
    public String generateResponse(String[] commandParts) {
        MySqlItemDAO itemDAO = new MySqlItemDAO();
        try {
            Item item = getItemFromJson(commandParts[1]);
            itemDAO.insertItem(item);
            List<Item> items = itemDAO.findAllItems();
            return getJsonFromItem(items.get(items.size()-1));

        } catch (DAOexception e) {
            System.out.println("Couldn't add item. " + e.getMessage());
        }
        return "Item unable to be added.";
    }

        @Override
    public String generateRequest(Scanner keyboard) {
        String name;
        int price;
        String type;
        String description;

        System.out.print(Color.GREEN + "Enter name of item: " + Color.RESET);
        name = getNameInput(keyboard);
        System.out.print(Color.GREEN + "Enter price of item: " + Color.RESET);
        price = getPriceInput(keyboard);
        System.out.print(Color.GREEN + "Enter type of item: " + Color.RESET);
        type = getItemType(keyboard);
        System.out.print(Color.GREEN + "Enter description of item: " + Color.RESET);
        description = getSentenceInput(keyboard);

        Item item = new Item(0 ,name, price, type, description);

        return ServerDetails.ADDITEM_COMMAND + ServerDetails.BREAKING_CHARACTER + getJsonFromItem(item);
    }

    @Override
    public void handleResponse(String response) {
        System.out.println(Color.PURPLE + "\nServer response:" + Color.RESET);
        try {
            Item item = getItemFromJson(response);

            if (item == null)
                System.out.println(Color.RED + "Error: Item not added" + Color.RESET);
            else{
                System.out.println(Color.GREEN + "Item added successfully" + Color.RESET);
                System.out.println("Item added successfully");
                System.out.printf(ITEM_HEADER);
                item.printItem();
            }
        } catch (Exception e) {
            System.out.println(response);
        }

    }
}

package com.dkit.gd2.dominikHampejs.Commands.Item;

import com.dkit.gd2.dominikHampejs.Commands.Command;
import com.dkit.gd2.dominikHampejs.Core.Color;
import com.dkit.gd2.dominikHampejs.Core.ServerDetails;
import com.dkit.gd2.dominikHampejs.DAO.MySqlItemDAO;
import com.dkit.gd2.dominikHampejs.DTO.Item;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.dkit.gd2.dominikHampejs.Core.ServerUtility.ITEM_HEADER;

public class AllItemsCommand implements Command {
    @Override
    public String generateResponse(String[] commandParts) {
        MySqlItemDAO itemDAO = new MySqlItemDAO();

        try {
            return itemDAO.getItemsAsJSON();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "Error: Items not found";
    }

    @Override
    public String generateRequest(Scanner keyboard) {
        return ServerDetails.ALLITEMS_COMMAND;
    }

    @Override
    public void handleResponse(String response) {
        Gson gson = new Gson();
        System.out.println(Color.PURPLE + "\nServer response:" + Color.RESET);
        Type type = new TypeToken<List<Item>>(){}.getType();
        ArrayList<Item> items = gson.fromJson(response, type);

        if (items.size() == 0)
            System.out.println(Color.RED + "Error: Items not found" + Color.RESET);
        else {
            System.out.println("Items found successfully");
            System.out.printf(ITEM_HEADER);
            for (Item item : items)
                item.printItem();
        }


    }
}

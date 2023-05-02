package com.dkit.gd2.dominikHampejs.Commands.Item;

import com.dkit.gd2.dominikHampejs.Commands.Command;
import com.dkit.gd2.dominikHampejs.Core.Color;
import com.dkit.gd2.dominikHampejs.Core.ServerDetails;
import com.dkit.gd2.dominikHampejs.DAO.MySqlItemDAO;
import com.dkit.gd2.dominikHampejs.DTO.Item;

import java.util.Scanner;

import static com.dkit.gd2.dominikHampejs.Core.ServerUtility.*;

public class deleteItemCommand implements Command {
    @Override
    public String generateResponse(String[] commandParts) {
        MySqlItemDAO dao = new MySqlItemDAO();
        int championId = Integer.parseInt(commandParts[1]);

        try {
            Item item = dao.findItemById(championId);
            if(dao.deleteItem(championId)){
                return getJsonFromItem(item);
            }
            else{
                return "Item unable to delete";
            }
        }
        catch (Exception e){
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public String generateRequest(Scanner keyboard) {
        int championId = 0;
        int itemId = 0;

        System.out.print("Enter the ID of the champion you wish to delete an item from: ");
        championId = getIntInput(keyboard);
        System.out.print("Enter the ID of the item you wish to delete: ");
        itemId = getIntInput(keyboard);

       return ServerDetails.DELETEITEM_COMMAND + ServerDetails.BREAKING_CHARACTER + championId + ServerDetails.BREAKING_CHARACTER + itemId;
    }

    @Override
    public void handleResponse(String response) {
        System.out.println(Color.PURPLE + "\nServer response:" + Color.RESET);
        Item item = getItemFromJson(response);
        if(item != null){
            System.out.println("Item deleted successfully");
            System.out.printf(ITEM_HEADER);
            item.printItem();
        }
        else{
            System.out.println(Color.RED + "Error: Item not found" + Color.RESET);
        }


    }
}

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
        int itemId = 0;

        System.out.print(Color.GREEN + "Enter the ID of the item you wish to delete: " + Color.RESET);
        itemId = getIdInput(keyboard);

       return ServerDetails.DELETEITEM_COMMAND + ServerDetails.BREAKING_CHARACTER + itemId;
    }

    @Override
    public void handleResponse(String response) {
        System.out.println(Color.PURPLE + "\nServer response:" + Color.RESET);
        try {
            Item item = getItemFromJson(response);
            if (item != null) {
                System.out.println("Item deleted successfully");
                System.out.printf(ITEM_HEADER);
                item.printItem();
            } else {
                System.out.println(Color.RED + "Error: Item not found" + Color.RESET);
            }
        }
        catch (Exception e){
            System.out.println(response);
        }
    }
}

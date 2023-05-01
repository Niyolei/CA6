package com.dkit.gd2.dominikHampejs.Commands;

import com.dkit.gd2.dominikHampejs.Core.Color;
import com.dkit.gd2.dominikHampejs.Core.ServerDetails;
import com.dkit.gd2.dominikHampejs.DAO.MySqlChampionDAO;

import java.util.Scanner;

public class deleteChampionCommand implements Command{

    @Override
    public String generateResponse(String[] commandParts) {
        MySqlChampionDAO dao = new MySqlChampionDAO();
        int id = Integer.parseInt(commandParts[1]);

        try {
            if(dao.deleteChampion(id)){
                return "Champion deleted";
            }
            else{
                return "Champion unable to delete";
            }
        }
        catch (Exception e){
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public String generateRequest(Scanner keyboard) {
        System.out.print(Color.GREEN + "Enter the ID of the champion you wish to delete: " + Color.RESET);
        int id = keyboard.nextInt();
        return ServerDetails.DELETECHAMPION_COMMAND + ServerDetails.BREAKING_CHARACTER + id;
    }

    @Override
    public void handleResponse(String response) {
        System.out.println(Color.PURPLE + "\nServer response:" + Color.RESET);
        System.out.println(response);

    }
}

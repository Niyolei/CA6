package com.dkit.gd2.dominikHampejs.Commands;

import com.dkit.gd2.dominikHampejs.Core.Color;
import com.dkit.gd2.dominikHampejs.Core.ServerDetails;
import com.dkit.gd2.dominikHampejs.DAO.MySqlChampionDAO;
import com.dkit.gd2.dominikHampejs.DTO.Champion;

import java.util.Scanner;

import static com.dkit.gd2.dominikHampejs.Core.ServerUtility.*;

public class deleteChampionCommand implements Command{

    @Override
    public String generateResponse(String[] commandParts) {
        MySqlChampionDAO dao = new MySqlChampionDAO();
        int id = Integer.parseInt(commandParts[1]);

        try {
            Champion champion = dao.findChampionById(id);
            if(dao.deleteChampion(id)){
                return getJsonFromChampion(champion);
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
        Champion champion = getChampionFromJson(response);
        if(champion != null){
            System.out.println("Champion deleted successfully");
            System.out.printf(CHAMPION_HEADER);
            champion.printChampion();
        }
        else{
            System.out.println(Color.RED + "Error: Champion not found" + Color.RESET);
        }

    }
}

package com.dkit.gd2.dominikHampejs.Commands;

import com.dkit.gd2.dominikHampejs.Core.Color;
import com.dkit.gd2.dominikHampejs.Core.ServerDetails;
import com.dkit.gd2.dominikHampejs.DAO.MySqlChampionDAO;
import com.dkit.gd2.dominikHampejs.DTO.Champion;

import java.util.Scanner;

import static com.dkit.gd2.dominikHampejs.Core.ServerUtility.*;

public class addChampionCommand implements Command{

    @Override
    public String generateResponse(String[] commandParts) {
        Champion champion = new Champion(0, commandParts[1], commandParts[2], Double.parseDouble(commandParts[3]));
        MySqlChampionDAO dao = new MySqlChampionDAO();

        try {
            if(dao.insertChampion(champion)){
                return "Champion added";
            }
            else{
                return "Champion unable to add";
            }
        }
        catch (Exception e){
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public String generateRequest(Scanner keyboard) {
        String name = "";
        String role = "";
        double winRate = 0.0;

        System.out.print(Color.GREEN +  "Enter the name of the champion you wish to add: " + Color.RESET);
        name = getChampionNameInput(keyboard);
        System.out.println(Color.GREEN +"Enter the role of the champion you wish to add: " + Color.RESET);
        role = getChampionRoleInput(keyboard);
        System.out.print(Color.GREEN + "Enter the win rate of the champion you wish to add: " + Color.RESET);
        winRate = getChampionWinRateInput(keyboard);

        return ServerDetails.ADDCHAMPION_COMMAND + ServerDetails.BREAKING_CHARACTER + name + ServerDetails.BREAKING_CHARACTER + role + ServerDetails.BREAKING_CHARACTER + winRate;
    }

    @Override
    public void handleResponse(String response) {
        System.out.println(Color.PURPLE + "\nServer response:" + Color.RESET);
        System.out.println(response);

    }
}

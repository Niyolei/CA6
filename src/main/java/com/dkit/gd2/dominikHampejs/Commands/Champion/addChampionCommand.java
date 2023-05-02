package com.dkit.gd2.dominikHampejs.Commands.Champion;

import com.dkit.gd2.dominikHampejs.Commands.Command;
import com.dkit.gd2.dominikHampejs.Core.Color;
import com.dkit.gd2.dominikHampejs.Core.ServerDetails;
import com.dkit.gd2.dominikHampejs.DAO.MySqlChampionDAO;
import com.dkit.gd2.dominikHampejs.DTO.Champion;

import java.util.List;
import java.util.Scanner;

import static com.dkit.gd2.dominikHampejs.Core.ServerUtility.*;

public class addChampionCommand implements Command {

    @Override
    public String generateResponse(String[] commandParts) {
        Champion champion = getChampionFromJson(commandParts[1]);
        MySqlChampionDAO dao = new MySqlChampionDAO();

        try {
            if(dao.insertChampion(champion)){
                List<Champion> champions = dao.findAllChampions();
                return getJsonFromChampion(champions.get(champions.size() - 1));
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
        name = getNameInput(keyboard);
        System.out.println(Color.GREEN +"Enter the role of the champion you wish to add: " + Color.RESET);
        role = getChampionRoleInput(keyboard);
        System.out.print(Color.GREEN + "Enter the win rate of the champion you wish to add: " + Color.RESET);
        winRate = getDoubleInput(keyboard);

        Champion champion = new Champion(0, name, role, winRate);

        return ServerDetails.ADDCHAMPION_COMMAND + ServerDetails.BREAKING_CHARACTER + getJsonFromChampion(champion);
    }

    @Override
    public void handleResponse(String response) {
        System.out.println(Color.PURPLE + "\nServer response:" + Color.RESET);
        try {

            Champion champion = getChampionFromJson(response);
            if (champion != null) {
                System.out.println("Champion added successfully!");
                System.out.printf(CHAMPION_HEADER);
                champion.printChampion();
            } else {
                System.out.println(response);
            }
        }
        catch (Exception e){
            System.out.println(response);
        }
    }
}

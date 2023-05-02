package com.dkit.gd2.dominikHampejs.Commands.Champion;

import com.dkit.gd2.dominikHampejs.Commands.Command;
import com.dkit.gd2.dominikHampejs.Core.Color;
import com.dkit.gd2.dominikHampejs.Core.ServerDetails;
import com.dkit.gd2.dominikHampejs.DAO.MySqlChampionDAO;
import com.dkit.gd2.dominikHampejs.DTO.Champion;

import java.util.List;
import java.util.Scanner;

import static com.dkit.gd2.dominikHampejs.Core.ServerUtility.*;

public class championByRoleCommand implements Command {


    @Override
    public String generateResponse(String[] commandParts) {
        MySqlChampionDAO championDAO = new MySqlChampionDAO();

        try {
            return championDAO.findChampionsByRoleAsJSON(commandParts[1]);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Color.RED + "Error: Champions not found" + Color.RESET;
    }

    @Override
    public String generateRequest(Scanner keyboard) {
        printRoles();
        String role = getChampionRoleInput(keyboard);
        return ServerDetails.CHAMPIONBYROLE_COMMAND + ServerDetails.BREAKING_CHARACTER + role;
    }

    @Override
    public void handleResponse(String response) {
        System.out.println(Color.PURPLE + "\nServer response:" + Color.RESET);

        List<Champion> champions = getChampionsFromJson(response);

        if(champions != null){
            System.out.println("Champions found successfully");
            System.out.printf(CHAMPION_HEADER);
            for(Champion champion : champions){
                champion.printChampion();
            }
        }
        else{
            System.out.println(Color.RED + "Error: Champions not found" + Color.RESET);
        }


    }
}

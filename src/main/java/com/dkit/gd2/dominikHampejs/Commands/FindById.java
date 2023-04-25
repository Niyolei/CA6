package com.dkit.gd2.dominikHampejs.Commands;

import com.dkit.gd2.dominikHampejs.Core.Color;
import com.dkit.gd2.dominikHampejs.Core.ServerDetails;
import com.dkit.gd2.dominikHampejs.DAO.MySqlChampionDAO;
import com.dkit.gd2.dominikHampejs.DTO.Champion;
import com.dkit.gd2.dominikHampejs.Exceptions.DAOexception;
import com.google.gson.Gson;

import java.util.Scanner;

import static com.dkit.gd2.dominikHampejs.Core.ServerUtility.getIdInput;

public class FindById implements Command{
    @Override
    public String generateResponse(String[] commandParts) {
        MySqlChampionDAO championDAO = new MySqlChampionDAO();

        try {
            return championDAO.findChampionByIdAsJSON(Integer.parseInt(commandParts[1]));
        }
        catch (DAOexception e) {
            System.out.println(e.getMessage());
        }

        return Color.RED + "Error: Champion not found" + Color.RESET;
    }

    @Override
    public String generateRequest(Scanner keyboard) {
        StringBuilder commandToBuild = new StringBuilder();

        System.out.println(Color.GREEN + "Enter the id of the champion you want to find: " + Color.RESET);
        int id = getIdInput(keyboard);

        commandToBuild.append(ServerDetails.BYID_COMMAND);
        commandToBuild.append(ServerDetails.BREAKING_CHARACTER);
        commandToBuild.append(id);

        return commandToBuild.toString();
    }

    @Override
    public void handleResponse(String response) {
        Gson gson = new Gson();
        System.out.println(Color.PURPLE + "\nServer response:" + Color.RESET);

        Champion champion = gson.fromJson(response, Champion.class);

        if (champion != null)
            System.out.println(champion);
        else
            System.out.println(Color.RED + "Error: Champion not found" + Color.RESET);
    }
}

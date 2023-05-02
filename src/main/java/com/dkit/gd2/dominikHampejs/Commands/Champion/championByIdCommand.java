package com.dkit.gd2.dominikHampejs.Commands.Champion;

import com.dkit.gd2.dominikHampejs.Commands.Command;
import com.dkit.gd2.dominikHampejs.Core.Color;
import com.dkit.gd2.dominikHampejs.Core.ServerDetails;
import com.dkit.gd2.dominikHampejs.DAO.MySqlChampionDAO;
import com.dkit.gd2.dominikHampejs.DTO.Champion;
import com.dkit.gd2.dominikHampejs.Exceptions.DAOexception;

import java.util.Scanner;

import static com.dkit.gd2.dominikHampejs.Core.ServerUtility.*;

public class championByIdCommand implements Command {
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
        System.out.print(Color.GREEN + "Enter the id of the champion you want to find: " + Color.RESET);
        int id = getIdInput(keyboard);

        return ServerDetails.CHAMPIONBYID_COMMAND + ServerDetails.BREAKING_CHARACTER + id;
    }

    @Override
    public void handleResponse(String response) {
        System.out.println(Color.PURPLE + "\nServer response:" + Color.RESET);

        try {
            Champion champion = getChampionFromJson(response);

            if (champion != null) {
                System.out.println("Champion found successfully");
                System.out.printf(CHAMPION_HEADER);
                champion.printChampion();
            }
            else
                System.out.println(Color.RED + "Error: Champion not found" + Color.RESET);
        }
        catch (Exception e) {
            System.out.println(response);
        }
    }
}

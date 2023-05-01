package com.dkit.gd2.dominikHampejs.Commands;

import com.dkit.gd2.dominikHampejs.Core.Color;
import com.dkit.gd2.dominikHampejs.Core.ServerDetails;
import com.dkit.gd2.dominikHampejs.DAO.MySqlChampionDAO;
import com.dkit.gd2.dominikHampejs.DTO.Champion;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.dkit.gd2.dominikHampejs.Core.ServerUtility.CHAMPION_HEADER;

public class allChampionsCommand implements Command{

    @Override
    public String generateResponse(String[] commandParts) {
        MySqlChampionDAO championDAO = new MySqlChampionDAO();

        try {
            return championDAO.findAllChampionsAsJSON();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "Error: Champions not found";
    }

    @Override
    public String generateRequest(Scanner keyboard) {
        return ServerDetails.ALLCHAMPIONS_COMMAND;
    }

    @Override
    public void handleResponse(String response) {
        Gson gson = new Gson();

        System.out.println(Color.PURPLE + "\nServer response:" + Color.RESET);
        Type type = new TypeToken<List<Champion>>(){}.getType();
        ArrayList<Champion> champions = gson.fromJson(response, type);

        if (champions.size() == 0)
            System.out.println(Color.RED + "Error: Champions not found" + Color.RESET);
        else {
            System.out.println("Champions found successfully");
            System.out.printf(CHAMPION_HEADER);
            for (Champion champion : champions)
                champion.printChampion();
        }
    }
}

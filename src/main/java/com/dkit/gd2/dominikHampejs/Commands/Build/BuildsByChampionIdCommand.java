package com.dkit.gd2.dominikHampejs.Commands.Build;

import com.dkit.gd2.dominikHampejs.Commands.Command;
import com.dkit.gd2.dominikHampejs.Core.Color;
import com.dkit.gd2.dominikHampejs.Core.ServerDetails;
import com.dkit.gd2.dominikHampejs.DAO.MySqlBuildDAO;
import com.dkit.gd2.dominikHampejs.DAO.MySqlChampionDAO;
import com.dkit.gd2.dominikHampejs.DAO.MySqlItemDAO;
import com.dkit.gd2.dominikHampejs.DTO.Build;
import com.dkit.gd2.dominikHampejs.DTO.DetailedBuild;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.dkit.gd2.dominikHampejs.Core.ServerUtility.*;

public class BuildsByChampionIdCommand implements Command {
    @Override
    public String generateResponse(String[] commandParts) {
        MySqlBuildDAO buildDAO = new MySqlBuildDAO();
        MySqlChampionDAO championDAO = new MySqlChampionDAO();
        MySqlItemDAO itemDAO = new MySqlItemDAO();

        try {
            int championId = Integer.parseInt(commandParts[1]);
            List<Build> builds = buildDAO.findBuildsByChampionId(championId);
            List<DetailedBuild> detailedBuilds = new ArrayList<>();

            for (Build build : builds) {
                String championName = championDAO.findChampionById(build.getChampionId()).getName();
                String itemName = itemDAO.findItemById(build.getItemId()).getName();
                detailedBuilds.add(new DetailedBuild(build.getChampionId(), build.getItemId(), build.getExplanation(), championName, itemName));
            }

            return getJsonFromList(detailedBuilds);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "Error: Builds not found";
    }

    @Override
    public String generateRequest(Scanner keyboard) {
        int championId;
        System.out.print(Color.GREEN + "Enter champion id: " + Color.RESET);
        championId = getIdInput(keyboard);
        return ServerDetails.BUILDBYCHAMPION_COMMAND + ServerDetails.BREAKING_CHARACTER + championId;
    }

    @Override
    public void handleResponse(String response) {
        System.out.println(Color.PURPLE + "\nServer response:" + Color.RESET);
        try {
            List<DetailedBuild> builds = getDetailedBuildsFromJson(response);

            if (builds.size() == 0)
                System.out.println(Color.RED + "Error: Builds not found" + Color.RESET);
            else {
                System.out.println("Builds found successfully");
                System.out.printf(BUILD_HEADER);
                for (DetailedBuild build : builds)
                    build.printBuild();
            }
        }
        catch (Exception e) {
            System.out.println(response);
        }


    }
}

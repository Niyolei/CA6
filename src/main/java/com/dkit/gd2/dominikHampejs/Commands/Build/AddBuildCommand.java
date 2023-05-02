package com.dkit.gd2.dominikHampejs.Commands.Build;

import com.dkit.gd2.dominikHampejs.Commands.Command;
import com.dkit.gd2.dominikHampejs.Core.Color;
import com.dkit.gd2.dominikHampejs.Core.ServerDetails;
import com.dkit.gd2.dominikHampejs.DAO.MySqlBuildDAO;
import com.dkit.gd2.dominikHampejs.DTO.Build;

import java.util.Scanner;

import static com.dkit.gd2.dominikHampejs.Core.ServerUtility.*;

public class AddBuildCommand implements Command {
    @Override
    public String generateResponse(String[] commandParts) {
        MySqlBuildDAO buildDAO = new MySqlBuildDAO();
        Build build = getBuildFromJson(commandParts[1]);

        try {
            if (buildDAO.insertBuild(build))
                return getJsonFromBuild(build);
            else
                return "Error: Build already exists";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public String generateRequest(Scanner keyboard) {
        int champId;
        int itemId;
        String explanation;

        System.out.print(Color.GREEN + "Enter the champion ID of the build you want to add: " + Color.RESET);
        champId = getIdInput(keyboard);
        System.out.print(Color.GREEN + "Enter the item ID of the build you want to add: " + Color.RESET);
        itemId = getIdInput(keyboard);
        System.out.print(Color.GREEN + "Enter the explanation of the build you want to add: " + Color.RESET);
        explanation = getSentenceInput(keyboard);

        Build build = new Build(itemId, champId, explanation);

        return ServerDetails.ADDBUILD_COMMAND + ServerDetails.BREAKING_CHARACTER + getJsonFromBuild(build);
    }

    @Override
    public void handleResponse(String response) {
        System.out.println(Color.PURPLE + "\nServer response:" + Color.RESET);
        try {
            Build build = getBuildFromJson(response);

            if (build == null)
                System.out.println(Color.RED + "Error: Build already exists" + Color.RESET);
            else{
                System.out.println("Build added successfully");
                build.printBuild();
            }
        }
        catch (Exception e) {
            System.out.println(response);
        }

    }
}

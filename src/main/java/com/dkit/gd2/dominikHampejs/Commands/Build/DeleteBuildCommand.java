package com.dkit.gd2.dominikHampejs.Commands.Build;

import com.dkit.gd2.dominikHampejs.Commands.Command;
import com.dkit.gd2.dominikHampejs.Core.Color;
import com.dkit.gd2.dominikHampejs.Core.ServerDetails;
import com.dkit.gd2.dominikHampejs.DAO.MySqlBuildDAO;
import com.dkit.gd2.dominikHampejs.DTO.Build;
import com.dkit.gd2.dominikHampejs.Exceptions.DAOexception;

import java.util.Scanner;

import static com.dkit.gd2.dominikHampejs.Core.ServerUtility.*;

public class DeleteBuildCommand implements Command {
    @Override
    public String generateResponse(String[] commandParts) {
        MySqlBuildDAO buildDAO = new MySqlBuildDAO();
        try {
            Build toDelete = buildDAO.findBuildByChampionIdAndItemId(Integer.parseInt(commandParts[1]), Integer.parseInt(commandParts[2]));
            if (toDelete == null)
                throw new DAOexception();

            if (buildDAO.deleteBuild(toDelete.getChampionId(), toDelete.getItemId()))
                return getJsonFromBuild(toDelete);
            else
                return "Error: Build not found";
        } catch (DAOexception e) {
            return "Build does not exist";
        }
    }
    @Override
    public String generateRequest(Scanner keyboard) {
        int champId;
        int itemId;

        System.out.print(Color.GREEN + "Enter the champion ID of the build you want to delete: " + Color.RESET);
        champId = getIdInput(keyboard);
        System.out.print(Color.GREEN +  "Enter the item ID of the build you want to delete: " + Color.RESET);
        itemId = getIdInput(keyboard);


        return ServerDetails.DELETEBUILD_COMMAND + ServerDetails.BREAKING_CHARACTER + champId + ServerDetails.BREAKING_CHARACTER + itemId;
    }

    @Override
    public void handleResponse(String response) {
        System.out.println(Color.PURPLE + "\nServer response:" + Color.RESET);
        try {
            Build build = getBuildFromJson(response);

            if (build == null)
                System.out.println(Color.RED + "Error: Build not found" + Color.RESET);
            else{
                System.out.println("Build deleted successfully");
                build.printBuild();
            }
        }
        catch (Exception e) {
            System.out.println(response);
        }
    }
}

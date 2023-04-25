package com.dkit.gd2.dominikHampejs.Commands;

import com.dkit.gd2.dominikHampejs.Core.Color;
import com.dkit.gd2.dominikHampejs.Core.ServerDetails;
import com.dkit.gd2.dominikHampejs.DAO.MySqlDAO;

import java.util.Scanner;

public class UnknownCommand implements Command {
    @Override
    public String generateResponse(String[] commandParts) {
        return ServerDetails.UNKNOWN_COMMAND;
    }

    @Override
    public String generateRequest(Scanner keyboard) {
        return ServerDetails.UNKNOWN_COMMAND;
    }

    @Override
    public void handleResponse(String response) {
        System.out.println(Color.PURPLE + "\nServer response:" + Color.RESET);
        System.out.println(Color.RED  + response + Color.RESET);
    }
}

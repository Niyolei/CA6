package com.dkit.gd2.dominikHampejs.Commands;

import com.dkit.gd2.dominikHampejs.Core.Color;
import com.dkit.gd2.dominikHampejs.Core.ServerDetails;

import java.util.Scanner;

public class QuitCommand implements Command {
    @Override
    public String generateResponse(String[] commandParts) {
        return "Goodbye";
    }

    @Override
    public String generateRequest(Scanner keyboard) {
        return ServerDetails.QUIT_COMMAND;
    }

    @Override
    public void handleResponse(String response) {
        System.out.println(Color.PURPLE + "\nServer response:" + Color.RESET);
        System.out.println(response);
    }
}

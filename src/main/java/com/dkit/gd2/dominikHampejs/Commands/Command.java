package com.dkit.gd2.dominikHampejs.Commands;

import com.dkit.gd2.dominikHampejs.DAO.MySqlDAO;


import java.util.Scanner;

public interface Command {
    public String generateResponse(String[] commandParts);
    public String generateRequest(Scanner keyboard);

    public void handleResponse(String response);
}

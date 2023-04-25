package com.dkit.gd2.dominikHampejs.Commands;

import com.dkit.gd2.dominikHampejs.Core.ServerDetails;

public class CommandFactory {
    public Command getCommand(String command){
        return switch (command) {
           case ServerDetails.BYID_COMMAND -> new FindById();
            case ServerDetails.QUIT_COMMAND -> new QuitCommand();
            default -> new UnknownCommand();
        };
    }
}

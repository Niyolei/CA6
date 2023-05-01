package com.dkit.gd2.dominikHampejs.Commands;

import com.dkit.gd2.dominikHampejs.Core.ServerDetails;

public class CommandFactory {
    public Command getCommand(String command){
        return switch (command) {
           case ServerDetails.CHAMPIONBYID_COMMAND -> new championByIdCommand();
           case ServerDetails.ALLCHAMPIONS_COMMAND -> new allChampionsCommand();
           case ServerDetails.DELETECHAMPION_COMMAND -> new deleteChampionCommand();
           case ServerDetails.ADDCHAMPION_COMMAND -> new addChampionCommand();
           case ServerDetails.QUIT_COMMAND -> new QuitCommand();
            default -> new UnknownCommand();
        };
    }
}

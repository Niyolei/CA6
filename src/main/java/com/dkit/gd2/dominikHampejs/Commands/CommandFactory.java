package com.dkit.gd2.dominikHampejs.Commands;

import com.dkit.gd2.dominikHampejs.Commands.Build.AddBuildCommand;
import com.dkit.gd2.dominikHampejs.Commands.Build.AllBuildsCommand;
import com.dkit.gd2.dominikHampejs.Commands.Build.BuildsByChampionIdCommand;
import com.dkit.gd2.dominikHampejs.Commands.Build.DeleteBuildCommand;
import com.dkit.gd2.dominikHampejs.Commands.Champion.*;
import com.dkit.gd2.dominikHampejs.Commands.Item.*;
import com.dkit.gd2.dominikHampejs.Core.ServerDetails;

public class CommandFactory {
    public Command getCommand(String command){
        return switch (command) {
           case ServerDetails.CHAMPIONBYID_COMMAND -> new championByIdCommand();
           case ServerDetails.ALLCHAMPIONS_COMMAND -> new allChampionsCommand();
           case ServerDetails.DELETECHAMPION_COMMAND -> new deleteChampionCommand();
           case ServerDetails.ADDCHAMPION_COMMAND -> new addChampionCommand();
           case ServerDetails.CHAMPIONBYROLE_COMMAND -> new championByRoleCommand();
           case ServerDetails.ITEMBYID_COMMAND -> new ItemByIdCommand();
           case ServerDetails.ALLITEMS_COMMAND -> new AllItemsCommand();
           case ServerDetails.DELETEITEM_COMMAND -> new deleteItemCommand();
           case ServerDetails.ADDITEM_COMMAND -> new addItemCommand();
           case ServerDetails.ITEMBYTYPE_COMMAND -> new ItemByTypeCommand();
           case ServerDetails.ALLBUILDS_COMMAND -> new AllBuildsCommand();
           case ServerDetails.BUILDBYCHAMPION_COMMAND -> new BuildsByChampionIdCommand();
           case ServerDetails.DELETEBUILD_COMMAND -> new DeleteBuildCommand();
           case ServerDetails.ADDBUILD_COMMAND -> new AddBuildCommand();
           case ServerDetails.QUIT_COMMAND -> new QuitCommand();
            default -> new UnknownCommand();
        };
    }
}

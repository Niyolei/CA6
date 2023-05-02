package com.dkit.gd2.dominikHampejs.MenuHandler;

import com.dkit.gd2.dominikHampejs.Core.ServerDetails;

public class MenuFactory {
    public SubMenu getMenu(String menu){
        return switch (menu) {
            case ServerDetails.CHAMPION_MENU -> new ChampionMenu();
            case ServerDetails.ITEM_MENU -> new ItemMenu();
            case ServerDetails.BUILD_MENU -> new BuildMenu();
            default -> null;
        };
    }
}

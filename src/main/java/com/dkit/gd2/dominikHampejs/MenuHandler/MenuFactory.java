package com.dkit.gd2.dominikHampejs.MenuHandler;

public class MenuFactory {
    public SubMenu getMenu(String menu){
        return switch (menu) {
            case "champion" -> new ChampionMenu();
            case "item" -> new ItemMenu();
            default -> null;
        };
    }
}

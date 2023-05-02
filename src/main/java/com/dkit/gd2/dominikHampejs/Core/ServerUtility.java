package com.dkit.gd2.dominikHampejs.Core;

import com.dkit.gd2.dominikHampejs.DTO.Champion;
import com.dkit.gd2.dominikHampejs.DTO.Item;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

import static java.lang.String.format;

public class ServerUtility {

    public static final String CHAMPION_HEADER = format("%-6s%-16s%-15s%-14s\n", "ID", "Name", "Role", "Win rate");
    public static final String ITEM_HEADER = format("%-6s%-16s%-15s%-15s%-15s\n", "ID", "Name", "Price", "Type", "Description");

    public static int getIntInput(Scanner keyboard) {
        int choice = -1;
        try{
            choice = keyboard.nextInt();
        }
        catch (Exception e){
            System.out.println(Color.RED + "Invalid input, enter a valid number." + Color.RESET);
        }

        return choice;
    }


    public static int getIdInput(Scanner keyboard) {
        int choice = -1;

        while (choice < 1){
            try{
                choice = keyboard.nextInt();
            }
            catch (Exception e){
                System.out.println(Color.RED + "Invalid input, enter a valid ID." + Color.RESET);
            }
        }
        return choice;
    }


    public static String getNameInput(Scanner keyboard) {
        String name = "";
        while (name.length() < 2){
            try{
                name = keyboard.nextLine();
            }
            catch (Exception e){
                System.out.println(Color.RED + "Invalid input, enter a valid name." + Color.RESET);
            }
        }
        return name;
    }

    public static String getChampionRoleInput(Scanner keyboard) {
        printRoles();
        int choice = getIntInput(keyboard);
        String role = "";
        switch (choice) {
            case 1 -> role = "Tank";
            case 2 -> role = "Fighter";
            case 3 -> role = "Mage";
            case 4 -> role = "Assassin";
            case 5 -> role = "Marksman";
            case 6 -> role = "Support";
            default -> {
                System.out.println(Color.RED + "Invalid input, enter a valid role." + Color.RESET);
                role = getChampionRoleInput(keyboard);
            }
        }

        return role;
    }

    public static void printRoles() {
        System.out.println(Color.BLUE + "1. Tank");
        System.out.println("2. Fighter");
        System.out.println("3. Mage");
        System.out.println("4. Assassin");
        System.out.println("5. Marksman");
        System.out.println("6. Support" + Color.RESET);
        System.out.print(Color.GREEN + "Select a role:" + Color.RESET);
    }

    public static double getDoubleInput(Scanner keyboard) {
        double winRate = -1;
        while (winRate < 0 || winRate > 100){
            try{
                winRate = keyboard.nextDouble();
            }
            catch (Exception e){
                System.out.println(Color.RED + "Invalid input, enter a valid win rate." + Color.RESET);
            }
        }
        return winRate;
    }

    public static Champion getChampionFromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, Champion.class);
    }

    public static String getJsonFromChampion(Champion champion){
        Gson gson = new Gson();
        return gson.toJson(champion);
    }

    public static List<Champion> getChampionsFromJson(String json){
        Gson gson = new Gson();
        Type listType = new com.google.gson.reflect.TypeToken<List<Champion>>(){}.getType();
        return gson.fromJson(json, listType);
    }


    public static String getItemType(Scanner keyboard){
        printItemTypes();
        int choice = getIntInput(keyboard);
        String type = "";
        switch (choice) {
            case 1 -> type = "Starting item";
            case 2 -> type = "Legendary";
            case 3 -> type = "Mythic";
            case 4 -> type = "Consumable";
            case 5 -> type = "Boots";
            default -> {
                System.out.println(Color.RED + "Invalid input, enter a valid type." + Color.RESET);
                type = getItemType(keyboard);
            }
        }
        return type;
    }

    private static void printItemTypes() {
        System.out.println(Color.BLUE + "1. Starting item");
        System.out.println("2. Legendary");
        System.out.println("3. Mythic");
        System.out.println("4. Consumable");
        System.out.println("5. Boots" + Color.RESET);
        System.out.print(Color.GREEN + "Select a type:" + Color.RESET);
    }


    public static Item getItemFromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, Item.class);
    }

    public static String getJsonFromItem(Item item){
        Gson gson = new Gson();
        return gson.toJson(item);
    }

    public static List<Item> getItemsFromJson(String json){
        Gson gson = new Gson();
        Type listType = new com.google.gson.reflect.TypeToken<List<Item>>(){}.getType();
        return gson.fromJson(json, listType);
    }



}

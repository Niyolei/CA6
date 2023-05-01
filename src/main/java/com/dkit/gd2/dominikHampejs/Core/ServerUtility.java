package com.dkit.gd2.dominikHampejs.Core;

import com.dkit.gd2.dominikHampejs.DTO.Champion;
import com.google.gson.Gson;

import java.util.Scanner;

public class ServerUtility {
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


    public static String getChampionNameInput(Scanner keyboard) {
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

    private static void printRoles() {
        System.out.println(Color.BLUE + "1. Tank");
        System.out.println("2. Fighter");
        System.out.println("3. Mage");
        System.out.println("4. Assassin");
        System.out.println("5. Marksman");
        System.out.println("6. Support" + Color.RESET);
        System.out.println(Color.GREEN + "Select a role:" + Color.RESET);
    }

    public static double getChampionWinRateInput(Scanner keyboard) {
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



}

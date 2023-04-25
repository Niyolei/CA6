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

    public static Champion getChampionFromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, Champion.class);
    }

}

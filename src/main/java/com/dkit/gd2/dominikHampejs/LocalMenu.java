package com.dkit.gd2.dominikHampejs;

import com.dkit.gd2.dominikHampejs.DAO.MySqlChampionDAO;
import com.dkit.gd2.dominikHampejs.DTO.Champion;
import com.dkit.gd2.dominikHampejs.Exceptions.DAOexception;

import java.util.List;
import java.util.Scanner;

public class LocalMenu {

    public static void main(String[] args) {

        MySqlChampionDAO championDAO = new MySqlChampionDAO();
        Scanner keyboard = new Scanner(System.in);

        int choice = -1;
        //The menu
        while(choice != 8){
            printMenu();
            choice = getNumber(keyboard);

            switch (choice){
                case 1:
                    List<Champion> champions = findAllChampions(championDAO);
                    if(champions != null){
                        for(Champion c : champions){
                            System.out.println(c);
                        }
                    }
                    break;
                case 2:
                    Champion c = findChampionById(championDAO, keyboard);
                    if(c != null){
                        System.out.println(c);
                    }
                    break;
                case 3:
                    List<Champion> championsByRole = findChampionsByRole(championDAO, keyboard);
                    if(championsByRole != null){
                        for(Champion c1 : championsByRole){
                            System.out.println(c1);
                        }
                    }
                    break;
                case 4:
                    boolean deleted = deleteChampion(championDAO, keyboard);
                    if(deleted){
                        System.out.println("Champion deleted");
                    }else{
                        System.out.println("Champion not deleted");
                    }
                    break;
                case 5:
                    boolean inserted = insertChampion(championDAO, keyboard);
                    if(inserted){
                        System.out.println("Champion inserted");
                    }else{
                        System.out.println("Champion not inserted");
                    }
                    break;
                case 6:
                    String championsAsJSON = findAllChampionsAsJSON(championDAO);
                    if(championsAsJSON != null){
                        System.out.println(championsAsJSON);
                    }
                    break;
                case 7:
                    String championAsJSON = findChampionByIdAsJSON(championDAO, keyboard);
                    if(championAsJSON != null){
                        System.out.println(championAsJSON);
                    }
                    break;
                case 8:
                    System.out.println("Goodbye");
                    break;
                default:
                    System.out.println("Invalid input, please try again");
            }

        }


        
        
        
    }
    
    private static void printMenu(){
        System.out.println("1. Find all champions");
        System.out.println("2. Find champion by id");
        System.out.println("3. Find champions by role");
        System.out.println("4. Delete champion");
        System.out.println("5. Insert champion");
        System.out.println("6. Find all champions as JSON");
        System.out.println("7. Find champion by id as JSON");
        System.out.println("8. Exit");
    }
    
    public static int getNumber(Scanner keyboard){
        int number = -1;
        boolean valid = false;
        while(!valid){
            try{
                number = Integer.parseInt(keyboard.nextLine());
                valid = true;
            }catch(NumberFormatException e){
                System.out.println("Invalid input, please try again");
            }
        }
        return number;
    }
    
    public static List<Champion> findAllChampions(MySqlChampionDAO championDAO){
        List<Champion> champions = null;
        try{
            champions = championDAO.findAllChampions();
        }catch(DAOexception e){
            System.out.println(e.getMessage());
        }
        return champions;
    }
    
    public static Champion findChampionById(MySqlChampionDAO championDAO, Scanner keyboard){
        System.out.println("Enter id: ");
        int id = getNumber(keyboard);
        Champion c = null;
        try{
            c = championDAO.findChampionById(id);
        }catch(DAOexception e){
            System.out.println(e.getMessage());
        }
        return c;
    }
    
    public static List<Champion> findChampionsByRole(MySqlChampionDAO championDAO, Scanner keyboard){
        System.out.println("Enter role: ");
        String role = keyboard.nextLine();
        List<Champion> champions = null;
        try{
            champions = championDAO.findChampionsByRole(role);
        }catch(DAOexception e){
            System.out.println(e.getMessage());
        }
        return champions;
    }
    
    public static boolean deleteChampion(MySqlChampionDAO championDAO, Scanner keyboard){
        System.out.println("Enter id: ");
        int id = getNumber(keyboard);
        boolean deleted = false;
        try{
            deleted = championDAO.deleteChampion(id);
        }catch(DAOexception e){
            System.out.println(e.getMessage());
        }
        return deleted;
    }
    
    public static boolean insertChampion(MySqlChampionDAO championDAO, Scanner keyboard){
        System.out.println("Enter id: ");
        int id = getNumber(keyboard);
        System.out.println("Enter name: ");
        String name = keyboard.nextLine();
        System.out.println("Enter role: ");
        String role = keyboard.nextLine();
        System.out.println("Enter win rate: ");
        double winRate = keyboard.nextDouble();
        Champion c = new Champion(id, name, role, winRate);
        boolean inserted = false;
        try{
            inserted = championDAO.insertChampion(c);
        }catch(DAOexception e){
            System.out.println(e.getMessage());
        }
        return inserted;
    }

    public static String findAllChampionsAsJSON(MySqlChampionDAO championDAO){
        String champions = null;
        try{
            champions = championDAO.findAllChampionsAsJSON();
        }catch(DAOexception e){
            System.out.println(e.getMessage());
        }
        return champions;
    }

    public static String findChampionByIdAsJSON(MySqlChampionDAO championDAO, Scanner keyboard){
        System.out.println("Enter id: ");
        int id = getNumber(keyboard);
        String c = null;
        try{
            c = championDAO.findChampionByIdAsJSON(id);
        }catch(DAOexception e){
            System.out.println(e.getMessage());
        }
        return c;
    }
}

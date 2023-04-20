package com.dkit.gd2.dominikHampejs;

import com.dkit.gd2.dominikHampejs.DAO.MySqlChampionDAO;
import com.dkit.gd2.dominikHampejs.DTO.Champion;
import com.dkit.gd2.dominikHampejs.Exceptions.DAOexception;
import com.dkit.gd2.dominikHampejs.DAO.IChampionDAO;

import java.util.ArrayList;
import java.util.List;

public class LocalMenu {
    public static void main(String[] args) {

        try {
            printMenu();
            printAllChampions(); 
        } catch (DAOexception e) {
            System.out.println(e.getMessage());
        }


    }

    public static void printMenu(){
        System.out.println("1. Print all Champions");
        System.out.println("2. Print Champion by ID");
        System.out.println("3. Delete Champion by ID");
        System.out.println("4. Add new Champion");
    }

    public static void printAllChampions() throws DAOexception {
        List<Champion> champions;
        IChampionDAO championDAO = new MySqlChampionDAO();
        champions = championDAO.findAllChampions();
        for(Champion c : champions){
            System.out.println(c);
        }
    }
}

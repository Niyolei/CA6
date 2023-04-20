package com.dkit.gd2.dominikHampejs;

import com.dkit.gd2.dominikHampejs.DAO.MySqlChampionDAO;
import com.dkit.gd2.dominikHampejs.DTO.Champion;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.dkit.gd2.dominikHampejs.LocalMenu.*;

public class ChampionTests {

    @Test
    public void findAllChampionsTest() {
        MySqlChampionDAO championDAO = new MySqlChampionDAO();

        List<Champion> championsActual = findAllChampions(championDAO);

        List<Champion> championsExpected = new ArrayList<>();
        championsExpected.add(new Champion(1, "Ashe", "Marksman", 52.37));
        championsExpected.add(new Champion(2, "Garen", "Tank", 52.29));
        championsExpected.add(new Champion(3, "Zed", "Assassin", 49.95));
        championsExpected.add(new Champion(4, "Jinx", "Marksman", 51.75));
        championsExpected.add(new Champion(5, "Evelynn", "Assassin", 50.03));
        championsExpected.add(new Champion(6, "Malphite", "Tank", 52.54));
        championsExpected.add(new Champion(7, "Lux", "Mage", 49.58));
        championsExpected.add(new Champion(8, "Alistar", "Support", 52.15));
        championsExpected.add(new Champion(9, "Yasuo", "Fighter", 48.82));
        championsExpected.add(new Champion(10, "Soraka", "Support", 51.14));

        for (int i = 0; i < championsActual.size(); i++) {
            Assert.assertEquals(championsExpected.get(i), championsActual.get(i));
        }
    }

    @Test
    public void findChampionByIdTest() {
        MySqlChampionDAO championDAO = new MySqlChampionDAO();

        Champion championActual = findChampionById(championDAO, new Scanner("1"));

        Champion championExpected = new Champion(1, "Ashe", "Marksman", 52.37);

        Assert.assertEquals(championExpected, championActual);
    }

    @Test
    public void findChampionsByRoleTest(){
        MySqlChampionDAO championDAO = new MySqlChampionDAO();

        List<Champion> championsActual = findChampionsByRole(championDAO, new Scanner("Marksman"));

        List<Champion> championsExpected = new ArrayList<>();
        championsExpected.add(new Champion(1, "Ashe", "Marksman", 52.37));
        championsExpected.add(new Champion(4, "Jinx", "Marksman", 51.75));

        for (int i = 0; i < championsActual.size(); i++) {
            Assert.assertEquals(championsExpected.get(i), championsActual.get(i));
        }
    }

    @Test
    public void deleteChampionTest(){
        MySqlChampionDAO championDAO = new MySqlChampionDAO();

        boolean deleted = deleteChampion(championDAO, new Scanner("1"));

        Assert.assertTrue(deleted);
    }

    @Test
    public void insertChampionTest(){
        MySqlChampionDAO championDAO = new MySqlChampionDAO();

        boolean inserted = insertChampion(championDAO, new Scanner("1,Ashe,Marksman,52.37"));

        Assert.assertTrue(inserted);
    }

    @Test
    public void findAllChampionsJson(){
        MySqlChampionDAO championDAO = new MySqlChampionDAO();

        String championsActual = findAllChampionsAsJSON(championDAO);

        List<Champion> championsExpected = new ArrayList<>();
        championsExpected.add(new Champion(1, "Ashe", "Marksman", 52.37));
        championsExpected.add(new Champion(2, "Garen", "Tank", 52.29));
        championsExpected.add(new Champion(3, "Zed", "Assassin", 49.95));
        championsExpected.add(new Champion(4, "Jinx", "Marksman", 51.75));
        championsExpected.add(new Champion(5, "Evelynn", "Assassin", 50.03));
        championsExpected.add(new Champion(6, "Malphite", "Tank", 52.54));
        championsExpected.add(new Champion(7, "Lux", "Mage", 49.58));
        championsExpected.add(new Champion(8, "Alistar", "Support", 52.15));
        championsExpected.add(new Champion(9, "Yasuo", "Fighter", 48.82));
        championsExpected.add(new Champion(10, "Soraka", "Support", 51.14));

        Gson gson = new Gson();
        String championsExpectedJson = gson.toJson(championsExpected);

        Assert.assertEquals(championsExpectedJson, championsActual);
    }

    @Test
    public void findChampionByIdJson(){
        MySqlChampionDAO championDAO = new MySqlChampionDAO();

        String championActual = findChampionByIdAsJSON(championDAO, new Scanner("1"));

        Champion championExpected = new Champion(1, "Ashe", "Marksman", 52.37);

        Gson gson = new Gson();
        String championExpectedJson = gson.toJson(championExpected);

        Assert.assertEquals(championExpectedJson, championActual);
    }

}

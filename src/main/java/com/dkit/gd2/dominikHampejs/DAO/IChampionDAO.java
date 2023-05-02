package com.dkit.gd2.dominikHampejs.DAO;

import com.dkit.gd2.dominikHampejs.DTO.Champion;
import com.dkit.gd2.dominikHampejs.Exceptions.DAOexception;

import java.util.List;

public interface IChampionDAO {
    List<Champion> findAllChampions() throws DAOexception;
    Champion findChampionById(int id) throws DAOexception;
    boolean deleteChampion(int id) throws DAOexception;
    boolean insertChampion(Champion c) throws DAOexception;
    List<Champion> findChampionsByRole(String role) throws DAOexception;
    String findAllChampionsAsJSON() throws DAOexception;
    String findChampionByIdAsJSON(int id) throws DAOexception;
    String findChampionsByRoleAsJSON(String role) throws DAOexception;
}

package com.dkit.gd2.dominikHampejs.DAO;

import com.dkit.gd2.dominikHampejs.DTO.Champion;
import com.dkit.gd2.dominikHampejs.Exceptions.DAOexception;

import java.util.List;

public class MySqlChampionDAO extends MySqlDAO implements IChampionDAO {

    @Override
    public List<Champion> findAllChampions() throws DAOexception {
        return null;
    }

    @Override
    public Champion findChampionById(int id) throws DAOexception {
        return null;
    }

    @Override
    public boolean deleteChampion(int id) throws DAOexception {
        return false;
    }

    @Override
    public boolean insertChampion(Champion c) throws DAOexception {
        return false;
    }
}

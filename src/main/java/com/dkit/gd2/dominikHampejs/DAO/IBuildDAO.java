package com.dkit.gd2.dominikHampejs.DAO;

import com.dkit.gd2.dominikHampejs.DTO.Build;
import com.dkit.gd2.dominikHampejs.Exceptions.DAOexception;

import java.util.List;

public interface IBuildDAO {
    List<Build> findAllBuilds() throws DAOexception;
    List<Build> findBuildsByChampionId(int id) throws DAOexception;
    Build findBuildByChampionIdAndItemId(int champId, int itemId) throws DAOexception;
    boolean deleteBuild(int champId, int itemId) throws DAOexception;
    boolean insertBuild(Build b) throws DAOexception;
}

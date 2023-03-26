package com.dkit.gd2.dominikHampejs.DAO;

import com.dkit.gd2.dominikHampejs.DTO.Champion;
import com.dkit.gd2.dominikHampejs.Exceptions.DAOexception;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
        Connection con = null;
        PreparedStatement ps = null;
        int result = 0;

        try{
            con = this.getConnection();
            String query = "INSERT INTO champion (id, name, role, difficulty) VALUES (?, ?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, c.getId());
            ps.setString(2, c.getName());
            ps.setString(3, c.getRole());
            ps.setDouble(4, c.getWinRate());
            result = ps.executeUpdate();
        } catch (Exception e) {
            throw new DAOexception("insertChampion() " + e.getMessage());
        } finally {
            try{
                if(ps != null){
                    ps.close();
                }
                this.freeConnection(con);
            } catch (Exception e) {
                throw new DAOexception("insertChampion() " + e.getMessage());
            }
        }
        return result == 1;
    }
}

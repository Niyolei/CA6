package com.dkit.gd2.dominikHampejs.DAO;

import com.dkit.gd2.dominikHampejs.DTO.Champion;
import com.dkit.gd2.dominikHampejs.Exceptions.DAOexception;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class MySqlChampionDAO extends MySqlDAO implements IChampionDAO {

    @Override
    public List<Champion> findAllChampions() throws DAOexception {
        return null;
    }

    @Override
    public Champion findChampionById(int id) throws DAOexception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Champion c = null;

        try{
            con = this.getConnection();
            String query = "SELECT * FROM champion WHERE id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if(rs.next()){
                String name = rs.getString("name");
                String role = rs.getString("role");
                double winRate = rs.getDouble("winRate");
                c = new Champion(id, name, role, winRate);
            }
        } catch (Exception e) {
            throw new DAOexception("findChampionById() " + e.getMessage());
        } finally {
            try{
                if(rs != null){
                    rs.close();
                }
                if(ps != null){
                    ps.close();
                }
                this.freeConnection(con);
            } catch (Exception e) {
                throw new DAOexception("findChampionById() " + e.getMessage());
            }
        }
        return c;
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

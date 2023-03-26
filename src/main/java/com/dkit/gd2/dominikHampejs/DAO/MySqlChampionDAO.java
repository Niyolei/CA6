package com.dkit.gd2.dominikHampejs.DAO;

import com.dkit.gd2.dominikHampejs.DTO.Champion;
import com.dkit.gd2.dominikHampejs.Exceptions.DAOexception;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MySqlChampionDAO extends MySqlDAO implements IChampionDAO {

    @Override
    public List<Champion> findAllChampions() throws DAOexception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Champion> champions = new ArrayList<>();

        try {
            con = this.getConnection();
            String query = "SELECT * FROM champion";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String role = rs.getString("role");
                double winRate = rs.getDouble("win_rate");
                Champion c = new Champion(id, name, role,winRate);
                champions.add(c);
            }
        } catch (Exception e) {
            throw new DAOexception("findAllChampions() " + e.getMessage());
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
                throw new DAOexception("findAllChampions() " + e.getMessage());
            }
        }
        return champions;
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

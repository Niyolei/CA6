package com.dkit.gd2.dominikHampejs.DAO;

import com.dkit.gd2.dominikHampejs.DTO.Champion;
import com.dkit.gd2.dominikHampejs.Exceptions.DAOexception;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class MySqlChampionDAO extends MySqlDAO implements IChampionDAO {

    private static HashSet<Integer> ids = new HashSet<>();

    public MySqlChampionDAO() {
        super();
        List<Champion> champions = null;
        try {
            champions = findAllChampions();
        } catch (DAOexception e) {
            System.out.println("Couldn't build the cache. " + e.getMessage());
        }
        for(Champion c : champions){
            ids.add(c.getId());
        }
    }

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
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Champion c = null;

        if (ids.contains(id)) {
            try {
                con = this.getConnection();
                String query = "SELECT * FROM champion WHERE id = ?";
                ps = con.prepareStatement(query);
                ps.setInt(1, id);
                rs = ps.executeQuery();

                if (rs.next()) {
                    String name = rs.getString("name");
                    String role = rs.getString("role");
                    double winRate = rs.getDouble("winRate");
                    c = new Champion(id, name, role, winRate);
                }
            } catch (Exception e) {
                throw new DAOexception("findChampionById() " + e.getMessage());
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                    this.freeConnection(con);
                } catch (Exception e) {
                    throw new DAOexception("findChampionById() " + e.getMessage());
                }
            }
        }
        return c;
    }

    @Override
    public boolean deleteChampion(int id) throws DAOexception {
        Connection con = null;
        PreparedStatement ps = null;
        int result = 0;

        if (ids.contains(id)){
            try {
                con = this.getConnection();
                String query = "DELETE FROM champion WHERE id = ?";
                ps = con.prepareStatement(query);
                ps.setInt(1, id);
                result = ps.executeUpdate();
            } catch (Exception e) {
                throw new DAOexception("deleteChampion() " + e.getMessage());
            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    this.freeConnection(con);
                } catch (Exception e) {
                    throw new DAOexception("deleteChampion() " + e.getMessage());
                }

            }
        }
        return result == 1;
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

    @Override
    public List<Champion> findChampionsByRole(String role) throws DAOexception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Champion> champions = new ArrayList<>();

        try {
            con = this.getConnection();
            String query = "SELECT * FROM champion WHERE role = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, role);
            rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double winRate = rs.getDouble("win_rate");
                Champion c = new Champion(id, name, role, winRate);
                champions.add(c);
            }
        } catch (Exception e) {
            throw new DAOexception("findChampionsByRole() " + e.getMessage());
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
                throw new DAOexception("findChampionsByRole() " + e.getMessage());
            }
        }
        return champions;
    }

    @Override
    public String findAllChampionsAsJSON() throws DAOexception{
        List<Champion> champions = findAllChampions();
        Gson gson = new Gson();
        return gson.toJson(champions);
    }

    @Override
    public String findChampionByIdAsJSON(int id) throws DAOexception{
        Champion c = findChampionById(id);
        Gson gson = new Gson();
        return gson.toJson(c);
    }
}

package com.dkit.gd2.dominikHampejs.DAO;

import com.dkit.gd2.dominikHampejs.DTO.Build;
import com.dkit.gd2.dominikHampejs.Exceptions.DAOexception;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MySqlBuildDAO extends MySqlDAO implements IBuildDAO{

    private static HashSet<Integer> ids = new HashSet<>();

    public MySqlBuildDAO() {
        super();
        List<Build> builds = null;
        try {
            builds = findAllBuilds();
        } catch (DAOexception e) {
            System.out.println("Couldn't build the cache. " + e.getMessage());
        }
        for(Build b : builds){
            ids.add(b.getChampionId()*10+b.getItemId());
        }
    }
    @Override
    public List<Build> findAllBuilds() throws DAOexception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Build> builds = new ArrayList<>();

        try {
            con = this.getConnection();
            String query = "SELECT * FROM build";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next()){
                int champId = rs.getInt("champion_id");
                int itemId = rs.getInt("item_id");
                String explanation = rs.getString("explanation");
                Build b = new Build(champId, itemId, explanation);
                builds.add(b);
            }
        } catch (Exception e) {
            System.out.println("findAllBuilds() " + e.getMessage());
        } finally {
            try{
                if(rs != null){
                    rs.close();
                }
                if(ps != null){
                    ps.close();
                }
                if(con != null){
                    freeConnection(con);
                }
            } catch (Exception e){
                System.out.println("findAllBuilds() " + e.getMessage());
            }
        }
        return builds;
    }

    @Override
    public List<Build> findBuildsByChampionId(int id) throws DAOexception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Build> builds = new ArrayList<>();

        try {
            con = this.getConnection();
            String query = "SELECT * FROM build WHERE champion_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while(rs.next()){
                int champId = rs.getInt("champion_id");
                int itemId = rs.getInt("item_id");
                String explanation = rs.getString("explanation");
                Build b = new Build(champId, itemId, explanation);
                builds.add(b);
            }
        } catch (Exception e) {
            System.out.println("findBuildsByChampionId() " + e.getMessage());
        } finally {
            try{
                if(rs != null){
                    rs.close();
                }
                if(ps != null){
                    ps.close();
                }
                if(con != null){
                    freeConnection(con);
                }
            } catch (Exception e){
                System.out.println("findBuildsByChampionId() " + e.getMessage());
            }
        }
        return builds;
    }

    @Override
    public boolean deleteBuild(int champId, int itemId) throws DAOexception {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;

        try {
            con = this.getConnection();
            String query = "DELETE FROM build WHERE champion_id = ? AND item_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, champId);
            ps.setInt(2, itemId);
            rowsAffected = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("deleteBuild() " + e.getMessage());
        } finally {
            try{
                if(ps != null){
                    ps.close();
                }
                if(con != null){
                    freeConnection(con);
                }
            } catch (Exception e){
                System.out.println("deleteBuild() " + e.getMessage());
            }
        }
        return rowsAffected == 1;
    }

    @Override
    public boolean insertBuild(Build b) throws DAOexception {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;

        try {
            con = this.getConnection();
            String query = "INSERT INTO build VALUES(?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, b.getChampionId());
            ps.setInt(2, b.getItemId());
            ps.setString(3, b.getExplanation());
            rowsAffected = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("insertBuild() " + e.getMessage());
        } finally {
            try{
                if(ps != null){
                    ps.close();
                }
                if(con != null){
                    freeConnection(con);
                }
            } catch (Exception e){
                System.out.println("insertBuild() " + e.getMessage());
            }
        }
        return rowsAffected == 1;
    }

    @Override
    public String getBuildsAsJSON() throws DAOexception {
        List<Build> builds = findAllBuilds();
        Gson gson = new Gson();
        return gson.toJson(builds);
    }

    @Override
    public String getBuildsByChampionIdAsJSON(int id) throws DAOexception {
        List<Build> builds = findBuildsByChampionId(id);
        Gson gson = new Gson();
        return gson.toJson(builds);
    }
}

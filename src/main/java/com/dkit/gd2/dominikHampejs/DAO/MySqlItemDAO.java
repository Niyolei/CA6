package com.dkit.gd2.dominikHampejs.DAO;

import com.dkit.gd2.dominikHampejs.DTO.Item;
import com.dkit.gd2.dominikHampejs.Exceptions.DAOexception;
import com.google.gson.Gson;

import java.security.spec.ECField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MySqlItemDAO extends MySqlDAO implements IItemDAO{

    private static HashSet<Integer> ids = new HashSet<>();

    public MySqlItemDAO() {
        super();
        List<Item> items = null;
        try {
            items = findAllItems();
        } catch (DAOexception e) {
            System.out.println("Couldn't build the cache. " + e.getMessage());
        }
        for(Item i : items){
            ids.add(i.getId());
        }
    }

    @Override
    public List<Item> findAllItems() throws DAOexception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Item> items = new ArrayList<>();

        try {
            con = this.getConnection();
            String query = "SELECT * FROM item";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                String type = rs.getString("type");
                String description = rs.getString("description");
                Item i = new Item(id, name, price, type, description);
                items.add(i);
            }
        } catch (Exception e) {
            System.out.println("findAllItems() " + e.getMessage());
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
                throw new DAOexception("findAllItems() " + e.getMessage());
            }
        }
        return items;
    }

    @Override
    public Item findItemById(int id) throws DAOexception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Item i = null;

        if (ids.contains(id)){
            try {
                con = this.getConnection();
                String query = "SELECT * FROM item WHERE id = ?";
                ps = con.prepareStatement(query);
                ps.setInt(1, id);
                rs = ps.executeQuery();

                if(rs.next()){
                    String name = rs.getString("name");
                    int price = rs.getInt("price");
                    String type = rs.getString("type");
                    String description = rs.getString("description");
                    i = new Item(id, name, price, type, description);
                }
            } catch (Exception e) {
                throw new DAOexception("findItemById() " + e.getMessage());
            }
            finally {
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
                    throw new DAOexception("findItemById() " + e.getMessage());
                }
            }
        }
        return i;
    }

    @Override
    public boolean deleteItem(int id) throws DAOexception {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean deleted = false;

        if (ids.contains(id)){
            try {
                con = this.getConnection();
                String query = "DELETE FROM item WHERE id = ?";
                ps = con.prepareStatement(query);
                ps.setInt(1, id);
                int rowsAffected = ps.executeUpdate();

                if(rowsAffected > 0){
                    deleted = true;
                    ids.remove(id);
                }
            } catch (Exception e) {
                throw new DAOexception("deleteItem() " + e.getMessage());
            }
            finally {
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
                    throw new DAOexception("deleteItem() " + e.getMessage());
                }
            }
        }
        return deleted;

    }

    @Override
    public boolean insertItem(Item i) throws DAOexception {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;

        try {
            con = this.getConnection();
            String query = "INSERT INTO item VALUES(?, ?, ?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, i.getId());
            ps.setString(2, i.getName());
            ps.setInt(3, i.getPrice());
            ps.setString(4, i.getType());
            ps.setString(5, i.getDescription());
            rowsAffected = ps.executeUpdate();
        } catch (Exception e) {
            throw new DAOexception("insertItem() " + e.getMessage());
        }
        finally {
            try{
                if(ps != null){
                    ps.close();
                }
                if(con != null){
                    freeConnection(con);
                }
            } catch (Exception e){
                throw new DAOexception("insertItem() " + e.getMessage());
            }
        }
        return rowsAffected == 1;
    }

    @Override
    public List<Item> findItemsByType(String type) throws DAOexception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Item> items = new ArrayList<>();

        try {
            con = this.getConnection();
            String query = "SELECT * FROM item WHERE type = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, type);
            rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                String description = rs.getString("description");
                Item i = new Item(id, name, price, type, description);
                items.add(i);
            }
        } catch (Exception e) {
            throw new DAOexception("findItemsByType() " + e.getMessage());
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
                throw new DAOexception("findItemsByType() " + e.getMessage());
            }
        }
        return items;
    }

    @Override
    public String getItemsAsJSON() throws DAOexception {
        List<Item> items = findAllItems();
        Gson gson = new Gson();
        return gson.toJson(items);
    }

    @Override
    public String getItemAsJSON(int id) throws DAOexception {
        Item i = findItemById(id);
        Gson gson = new Gson();
        return gson.toJson(i);
    }

    @Override
    public String getItemsByTypeAsJSON(String type) throws DAOexception {
        List<Item> items = findItemsByType(type);
        Gson gson = new Gson();
        return gson.toJson(items);
    }


}

package com.dkit.gd2.dominikHampejs.DAO;

import com.dkit.gd2.dominikHampejs.DTO.Item;
import com.dkit.gd2.dominikHampejs.Exceptions.DAOexception;

import java.util.List;

public interface IItemDAO {
    List<Item> findAllItems() throws DAOexception;
    Item findItemById(int id) throws DAOexception;
    boolean deleteItem(int id) throws DAOexception;
    boolean insertItem(Item i) throws DAOexception;
    List<Item> findItemsByType(String type) throws DAOexception;

    String getItemsAsJSON() throws DAOexception;
    String getItemAsJSON(int id) throws DAOexception;
    String getItemsByTypeAsJSON(String type) throws DAOexception;

}

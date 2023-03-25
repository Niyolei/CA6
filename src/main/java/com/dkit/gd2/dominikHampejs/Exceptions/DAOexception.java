package com.dkit.gd2.dominikHampejs.Exceptions;

import java.sql.SQLException;

public class DAOexception extends SQLException {


    public DAOexception(){
    }

    public DAOexception(String message){
        super(message);
    }
}
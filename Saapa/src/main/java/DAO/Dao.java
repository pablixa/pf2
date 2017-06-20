/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * 
 */
public class Dao {

    PreparedStatement ps;
    Connection connection = ConnectionFactory.getConnection();
    ResultSet rs;
}

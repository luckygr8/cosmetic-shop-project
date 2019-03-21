/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import utility.Configration;

/**
 *
 * @author LAKSHAY DUTTA
 */
public class DataConnection {
    private static Connection con=null;

    private DataConnection() {
    }
    
    public static Connection getConnection() throws Exception
    {
        if(con==null)
        {
            Class.forName(Configration.DRIVER_NAME);
            con=DriverManager.getConnection(Configration.CONNECTION_URL, Configration.DB_USER,Configration.DB_PASS);
        }
        return con;
    }
    
    public static void closeConnection() throws Exception
    {
        if(con!=null)
            con.close();
    }
    
    public static PreparedStatement getStatement(String query) throws Exception{
        return getConnection().prepareStatement(query);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.dao.DataConnection;

/**
 *
 * @author LAKSHAY DUTTA
 */
public class testclass {

    public static void main(String[] args) {
        getlastid();
    }
    public static void getlastid() {
        try {
            String query = "select billid from billinfo order by billid desc limit 1";
            PreparedStatement stmt = DataConnection.getStatement(query);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
                System.out.println(rs.getInt("billid"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.to.Categoryinfo_TO;

/**
 *
 * @author LAKSHAY DUTTA
 */
public class Categoryinfo_DAO {
    private String errormessage="";

    public String getErrormessage() {
        return errormessage;
    }
    
    public boolean insertrecord(Categoryinfo_TO record) {
        boolean result;
        try {
            String query = "insert into categoryinfo (categoryid,categoryname) values(?,?)";
            PreparedStatement stmt = DataConnection.getStatement(query);
            stmt.setString(1, record.getCategoryid());
            stmt.setString(2, record.getCategoryname());
            result = stmt.executeUpdate() > 0;
            stmt.close();
            return result;
        } catch (Exception e) {
            errormessage = e.toString().trim();
            return false;
        }
    }
    
    public boolean deleterecord(String categoryid) {
        boolean result;
        try {
            String query = "delete from categoryinfo where categoryid=?";
            PreparedStatement stmt = DataConnection.getStatement(query);
            stmt.setString(1,categoryid);
            result = stmt.executeUpdate() > 0;
            stmt.close();
            return result;
        } catch (Exception e) {
            errormessage = e.toString().trim();
            return false;
        }
    }
    
    public boolean updaterecord(Categoryinfo_TO record) {
        boolean result;
        try {
            String query = "update categoryinfo set categoryname=? where categoryid=?";
            PreparedStatement stmt = DataConnection.getStatement(query);
            stmt.setString(1, record.getCategoryname());
            stmt.setString(2, record.getCategoryid());
            result = stmt.executeUpdate() > 0;
            stmt.close();
            return result;
        } catch (Exception e) {
            errormessage = e.toString().trim();
            return false;
        }
    }
    
    public List<Categoryinfo_TO> getallrecords() {
        List<Categoryinfo_TO> list = null;
        Categoryinfo_TO record;
        try {
            String query = "select categoryid,categoryname from categoryinfo";
            PreparedStatement stmt = DataConnection.getStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                list = new ArrayList<Categoryinfo_TO>();
                do {
                    record = new Categoryinfo_TO();
                    record.setCategoryid(rs.getString("categoryid"));
                    record.setCategoryname(rs.getString("categoryname"));
                    list.add(record);
                } while (rs.next());
            }
            stmt.close();
            return list;
        } catch (Exception e) {
            errormessage = e.toString().trim();
            return null;
        }
    }
}

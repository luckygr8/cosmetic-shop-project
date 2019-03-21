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
import model.to.Logininfo_TO;

/**
 *
 * @author LAKSHAY DUTTA
 */
public class Logininfo_DAO {

    private String errormessage = "";

    public String getErrormessage() {
        return errormessage;
    }

    public boolean insertrecord(Logininfo_TO record) {
        boolean result;
        try {
            String query = "insert into logininfo (uname,upass,rolename,lastlogin) values(?,?,?,sysdate())";
            PreparedStatement stmt = DataConnection.getStatement(query);
            stmt.setString(1, record.getUname());
            stmt.setString(2, record.getUpass());
            stmt.setString(3, record.getRolename());
            result = stmt.executeUpdate() > 0;
            stmt.close();
            return result;
        } catch (Exception e) {
            errormessage = e.toString().trim();
            return false;
        }
    }

    public boolean updaterecord(Logininfo_TO record) {
        boolean result;
        try {
            String query = "update logininfo set upass=?,rolename=?,lastlogin=sysdate() where uname=?";
            PreparedStatement stmt = DataConnection.getStatement(query);
            stmt.setString(1, record.getUpass());
            stmt.setString(2, record.getRolename());
            stmt.setString(3, record.getUname());
            result = stmt.executeUpdate() > 0;
            stmt.close();
            return result;
        } catch (Exception e) {
            errormessage = e.toString().trim();
            return false;
        }
    }

    public boolean deleterecord(String uname) {
        boolean result;
        try {
            String query = "delete from logininfo where uname=?";
            PreparedStatement stmt = DataConnection.getStatement(query);
            stmt.setString(1, uname);
            result = stmt.executeUpdate() > 0;
            stmt.close();
            return result;
        } catch (Exception e) {
            errormessage = e.toString().trim();
            return false;
        }
    }

    public Logininfo_TO getrecord(String uname) {
        Logininfo_TO record = null;
        try {
            String query = "select uname,upass,rolename,lastlogin from logininfo where uname=?";
            PreparedStatement stmt = DataConnection.getStatement(query);
            stmt.setString(1, uname);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                record = new Logininfo_TO();
                record.setUname(rs.getString("uname"));
                record.setUpass(rs.getString("upass"));
                record.setRolename(rs.getString("rolename"));
                record.setLastlogin(rs.getTimestamp("lastlogin"));
            }

            stmt.close();
            return record;
        } catch (Exception e) {
            errormessage = e.toString().trim();
            return null;
        }
    }

    public List<Logininfo_TO> getallrecords() {
        List<Logininfo_TO> list = null;
        Logininfo_TO record;
        try {
            String query = "select uname,upass,rolename,lastlogin from logininfo";
            PreparedStatement stmt = DataConnection.getStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                list = new ArrayList<Logininfo_TO>();
                do {
                    record = new Logininfo_TO();
                    record.setUname(rs.getString("uname"));
                    record.setUpass(rs.getString("upass"));
                    record.setRolename(rs.getString("rolename"));
                    record.setLastlogin(rs.getTimestamp("lastlogin"));
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

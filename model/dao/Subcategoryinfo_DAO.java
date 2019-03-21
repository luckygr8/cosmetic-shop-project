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
import model.to.Subcategoryinfo_TO;

/**
 *
 * @author LAKSHAY DUTTA
 */
public class Subcategoryinfo_DAO {

    private String errormessage = "";

    public String getErrormessage() {
        return errormessage;
    }

    public boolean insertrecord(Subcategoryinfo_TO record) {
        boolean result;
        try {
            String query = "insert into subcategoryinfo (subcategoryname,categoryid) values(?,?)";
            PreparedStatement stmt = DataConnection.getStatement(query);
            stmt.setString(1, record.getSubcategoryname());
            stmt.setString(2, record.getCategoryid());
            result = stmt.executeUpdate() > 0;
            stmt.close();
            return result;
        } catch (Exception e) {
            errormessage = e.toString().trim();
            return false;
        }
    }

    public boolean deleterecord(int subcategoryid) {
        boolean result;
        try {
            String query = "delete from subcategoryinfo where subcategoryid=?";
            PreparedStatement stmt = DataConnection.getStatement(query);
            stmt.setInt(1, subcategoryid);
            result = stmt.executeUpdate() > 0;
            stmt.close();
            return result;
        } catch (Exception e) {
            errormessage = e.toString().trim();
            return false;
        }
    }

    public boolean updaterecord(Subcategoryinfo_TO record) {
        boolean result;
        try {
            String query = "update subcategoryinfo set subcategoryname=?,categoryid=? where subcategoryid=?";
            PreparedStatement stmt = DataConnection.getStatement(query);
            stmt.setString(1, record.getSubcategoryname());
            stmt.setString(2, record.getCategoryid());
            stmt.setInt(3, record.getSubcategoryid());
            result = stmt.executeUpdate() > 0;
            stmt.close();
            return result;
        } catch (Exception e) {
            errormessage = e.toString().trim();
            return false;
        }
    }

    public List<Subcategoryinfo_TO> getallrecords() {
        List<Subcategoryinfo_TO> list = null;
        Subcategoryinfo_TO record;
        try {
            String query = "select categoryid,subcategoryid,c.categoryname,subcategoryname from subcategoryinfo left outer join categoryinfo c using(categoryid)";
            PreparedStatement stmt = DataConnection.getStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                list = new ArrayList<Subcategoryinfo_TO>();
                do {
                    record = new Subcategoryinfo_TO();
                    record.setCategoryid(rs.getString("categoryid"));
                    record.setSubcategoryid(rs.getInt("subcategoryid"));
                    record.setCategoryname(rs.getString("categoryname"));
                    record.setSubcategoryname(rs.getString("subcategoryname"));
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

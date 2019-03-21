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
import model.to.Productstock_TO;

/**
 *
 * @author LAKSHAY DUTTA
 */
public class Productstock_DAO {
    
    private String errormessage = "";
    
    public String getErrormessage() {
        return errormessage;
    }
    
    public boolean insertrecord(Productstock_TO record) {
        boolean result;
        try {
            String query = "insert into productstock(productid,quantity,price,expirydate,discount) values(?,?,?,?,?)";
            PreparedStatement stmt = DataConnection.getStatement(query);
            stmt.setInt(1, record.getProductid());
            stmt.setInt(2, record.getQuantity());
            stmt.setFloat(3, record.getPrice());
            stmt.setDate(4, record.getExpirydate());
            stmt.setFloat(5, record.getDiscount());
            result = stmt.executeUpdate() > 0;
            stmt.close();
            return result;
        } catch (Exception e) {
            errormessage = e.toString().toLowerCase().trim();
            return false;
        }
    }
    
    public boolean deleterecord(int stockid) {
        boolean result;
        try {
            String query = "delete from productstock where stockid=?";
            PreparedStatement stmt = DataConnection.getStatement(query);
            stmt.setInt(1, stockid);
            result = stmt.executeUpdate() > 0;
            stmt.close();
            return result;
        } catch (Exception e) {
            errormessage = e.toString().toLowerCase().trim();
            return false;
        }
    }
    
    public boolean updaterecord(Productstock_TO record) {
        boolean result;
        try {
            String query = "update productstock set productid=?,quantity=?,price=?,expirydate=?,discount=? where stockid=?";
            PreparedStatement stmt = DataConnection.getStatement(query);
            stmt.setInt(1, record.getProductid());
            stmt.setInt(2, record.getQuantity());
            stmt.setFloat(3, record.getPrice());
            stmt.setDate(4, record.getExpirydate());
            stmt.setFloat(5, record.getDiscount());
            stmt.setInt(6, record.getStockid());
            result = stmt.executeUpdate() > 0;
            stmt.close();
            return result;
        } catch (Exception e) {
            errormessage = e.toString().toLowerCase().trim();
            return false;
        }
    }
    
    public List<Productstock_TO> getallrecord() {
        List<Productstock_TO> list = null;
        Productstock_TO record;
        try {
            String query = "select stockid,pi.productname,sc.subcategoryname,bi.brandname,quantity,ps.price,expirydate,discount "
                    + " from productstock ps left outer join productinfo pi "
                    + " using(productid) "
                    + " left outer join brandinfo bi "
                    + " using(brandid) "
                    + " left outer join subcategoryinfo sc "
                    + " using(subcategoryid) ";
            PreparedStatement stmt = DataConnection.getStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                list = new ArrayList<Productstock_TO>();
                do {
                    record = new Productstock_TO();
                    record.setStockid(rs.getInt("stockid"));
                    //record.setProductid(rs.getInt("productid"));
                    record.setQuantity(rs.getInt("quantity"));
                    record.setPrice(rs.getFloat("price"));
                    record.setExpirydate(rs.getDate("expirydate"));
                    record.setDiscount(rs.getFloat("discount"));
                    record.setBrandname(rs.getString("brandname"));
                    record.setSubcategoryname(rs.getString("subcategoryname"));
                    record.setProductname(rs.getString("productname"));
                    list.add(record);
                } while (rs.next());
            }
            stmt.close();
            //System.out.println("dao list "+list);
            return list;
        } catch (Exception e) {
            errormessage = e.toString().trim().toLowerCase();
            return null;
        }
    }
}

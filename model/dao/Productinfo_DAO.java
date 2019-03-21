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
import model.to.Productinfo_TO;

/**
 *
 * @author LAKSHAY DUTTA
 */
public class Productinfo_DAO {

    private String errormessage = "";

    public String getErrormessage() {
        return errormessage;
    }

    public boolean insertrecord(Productinfo_TO record) {
        boolean result;
        try {
            String query = "insert into productinfo(productname,brandid,subcategoryid,price,availablequantity) values(?,?,?,?,?)";
            PreparedStatement stmt = DataConnection.getStatement(query);
            stmt.setString(1, record.getProductname());
            stmt.setString(2, record.getBrandid());
            stmt.setInt(3, record.getSubcategoryid());
            stmt.setFloat(4, record.getPrice());
            stmt.setInt(5, record.getAvailablequantity());
            result = stmt.executeUpdate() > 0;
            stmt.close();
            return result;
        } catch (Exception e) {
            errormessage = e.toString().trim();
            return false;
        }
    }

    public boolean deleterecord(int productid) {
        boolean result;
        try {
            String query = "delete from productinfo where productid=?";
            PreparedStatement stmt = DataConnection.getStatement(query);
            stmt.setInt(1, productid);
            result = stmt.executeUpdate() > 0;
            stmt.close();
            return result;
        } catch (Exception e) {
            errormessage = e.toString().trim();
            return false;
        }
    }
    
    public int getavailablequantity(Productinfo_TO record){
        int availablequantity=0;
        try{
            String query = "select availablequantity from productinfo where productid=?";
            PreparedStatement stmt = DataConnection.getStatement(query);
            stmt.setInt(1, record.getProductid());
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            {
                availablequantity=rs.getInt("availablequantity");
            }
        }catch(Exception e){
            
        }
        return availablequantity;
    }
    
    public boolean updaterecord(Productinfo_TO record) {
        boolean result;
        try {
            String query = "update productinfo set productname=?,brandid=?,subcategoryid=?,price=?,availablequantity=? where productid=?";
            PreparedStatement stmt = DataConnection.getStatement(query);
            stmt.setString(1, record.getProductname());
            stmt.setString(2, record.getBrandid());
            stmt.setInt(3, record.getSubcategoryid());
            stmt.setFloat(4, record.getPrice());
            stmt.setInt(5, record.getAvailablequantity());
            stmt.setInt(6, record.getProductid());
            result = stmt.executeUpdate() > 0;
            stmt.close();
            return result;
        } catch (Exception e) {
            errormessage = e.toString().trim();
            return false;
        }
    }

    public List<Productinfo_TO> getallrecords() {
        List<Productinfo_TO> list = null;
        Productinfo_TO record;
        try {
            //String query = "select productid,productname,brandid,subcategoryid,price,availablequantity from productinfo";
            String query="select productid,productname,bi.brandname,sc.subcategoryname,price,availablequantity\n" +
"from productinfo left outer join brandinfo bi\n" +
"using(brandid)\n" +
"left outer join subcategoryinfo sc\n" +
"using(subcategoryid)";
            PreparedStatement stmt = DataConnection.getStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                list = new ArrayList<Productinfo_TO>();
                do {
                    record = new Productinfo_TO();
                    record.setProductname(rs.getString("productname"));
                    record.setProductid(rs.getInt("productid"));
                    record.setBrandname(rs.getString("brandname"));
                    record.setSubcategoryname(rs.getString("subcategoryname"));
                    record.setPrice(rs.getFloat("price"));
                    record.setAvailablequantity(rs.getInt("availablequantity"));
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

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
import model.to.Brandsubcategory_TO;

/**
 *
 * @author LAKSHAY DUTTA
 */
public class Brandsubcategory_DAO {

    private String errormessage = "";

    public String getErrormessage() {
        return errormessage;
    }

    public boolean insertrecord(Brandsubcategory_TO record) {
        boolean result;
        try {
            String query = "insert into brandsubcategory (brandid,subcategoryid) values(?,?)";
            PreparedStatement stmt = DataConnection.getStatement(query);
            stmt.setString(1, record.getBrandid());
            stmt.setInt(2, record.getSubcategoryid());
            result = stmt.executeUpdate() > 0;
            stmt.close();
            return result;
        } catch (Exception e) {
            errormessage = e.toString().trim();
            return false;
        }
    }

    public boolean deleterecord(int srno) {
        boolean result;
        try {
            String query = "delete from brandsubcategory where srno=?";
            PreparedStatement stmt = DataConnection.getStatement(query);
            stmt.setInt(1, srno);
            result = stmt.executeUpdate() > 0;
            stmt.close();
            return result;
        } catch (Exception e) {
            errormessage = e.toString().trim();
            return false;
        }
    }

    
    public boolean updaterecord(Brandsubcategory_TO record) {
        boolean result;
        try {
            String query = "update brandsubcategory set brandid=?,subcategoryid=? where srno=?";
            PreparedStatement stmt = DataConnection.getStatement(query);
            stmt.setString(1, record.getBrandid());
            stmt.setInt(2, record.getSubcategoryid());
            stmt.setInt(3, record.getSrno());
            result = stmt.executeUpdate() > 0;
            stmt.close();
            return result;
        } catch (Exception e) {
            errormessage = e.toString().trim();
            return false;
        }
    }
    
    public List<Brandsubcategory_TO> getallrecords()
    {
        List<Brandsubcategory_TO> list=null;
        Brandsubcategory_TO record;
        try{
            //String query="select srno,brandid,subcategoryid from brandsubcategory";
            String query="select srno,brandid,sc.brandname\n" +
" from brandsubcategory bs left outer join brandinfo sc\n" +
"using(brandid)";
            PreparedStatement stmt = DataConnection.getStatement(query);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            {
                list = new ArrayList<Brandsubcategory_TO>();
                do{
                    record = new Brandsubcategory_TO();
                    record.setBrandid(rs.getString("brandid"));
                    record.setSrno(rs.getInt("srno"));
                    record.setBrandname(rs.getString("brandname"));
                    list.add(record);
                }while(rs.next());
            }
            stmt.close();
            return list;
        }catch(Exception e){
            errormessage = e.toString().trim();
            return null;
        }
    }
}

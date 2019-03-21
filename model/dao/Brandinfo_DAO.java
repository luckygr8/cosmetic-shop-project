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
import model.to.Brandinfo_TO;

/**
 *
 * @author LAKSHAY DUTTA
 */
public class Brandinfo_DAO {
    private String errormessage="";

    public String getErrormessage() {
        return errormessage;
    }
    
    public boolean insertrecord(Brandinfo_TO record)
    {
        boolean result;
        try{
        String query="insert into brandinfo (brandid,brandname) values(?,?)";
        PreparedStatement stmt=DataConnection.getStatement(query);
        stmt.setString(1, record.getBrandid());
        stmt.setString(2, record.getBrandname());
        result = stmt.executeUpdate() >0;
        stmt.close();
        return result;
        }catch(Exception e){
            errormessage=e.toString().trim();
            return false;
        }
    }
    
    public boolean deleterecord(String brandid)
    {
        boolean result;
        try{
        String query="delete from brandinfo where brandid=?";
        PreparedStatement stmt=DataConnection.getStatement(query);
        stmt.setString(1,brandid);
        result = stmt.executeUpdate() >0;
        stmt.close();
        return result;
        }catch(Exception e){
            errormessage=e.toString().trim();
            return false;
        }
    }
    
    public boolean updaterecord(Brandinfo_TO record)
    {
        boolean result;
        try{
        String query="update brandinfo set brandname=? where brandid=?";
        PreparedStatement stmt=DataConnection.getStatement(query);
        stmt.setString(1, record.getBrandname());
        stmt.setString(2, record.getBrandid());
        result = stmt.executeUpdate() >0;
        stmt.close();
        return result;
        }catch(Exception e){
            errormessage=e.toString().trim();
            return false;
        }
    }
    
    public List<Brandinfo_TO> getallrecords()
    {
        List<Brandinfo_TO> list=null;
        Brandinfo_TO record;
        try{
            String query = "select brandid,brandname from brandinfo";
            PreparedStatement stmt=DataConnection.getStatement(query);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            {
                list=new ArrayList<Brandinfo_TO>();
                do{
                    record=new Brandinfo_TO();
                    record.setBrandid(rs.getString("brandid"));
                    record.setBrandname(rs.getString("brandname"));
                    list.add(record);
                }while(rs.next());
            }
            stmt.close();
            return list;
        }catch(Exception e)
        {
            errormessage=e.toString().trim();
            return null;
        }
    }
}

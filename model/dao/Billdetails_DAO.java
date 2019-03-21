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
import model.to.Billdetails_TO;

/**
 *
 * @author LAKSHAY DUTTA
 */
public class Billdetails_DAO {
    private String errormessage="";

    public String getErrormessage() {
        return errormessage;
    }
    
    public boolean insertrecord(Billdetails_TO record)
    {
        boolean result;
        try{
        String query="insert into billdetails (billid,productid,quantity,price,discount) values(?,?,?,?,?)";
        PreparedStatement stmt=DataConnection.getStatement(query);
        stmt.setInt(1, record.getBillid());
        stmt.setInt(2, record.getProductid());
        stmt.setInt(3, record.getQuantity());
        stmt.setFloat(4, record.getPrice());
        stmt.setFloat(5, record.getDiscount());
        result = stmt.executeUpdate() >0;
        stmt.close();
        return result;
        }catch(Exception e){
            errormessage=e.toString().trim();
            return false;
        }
    }
    
    public boolean deleterecord(int detailid)
    {
        boolean result;
        try{
        String query="delete from billdetails where detailid =?";
        PreparedStatement stmt=DataConnection.getStatement(query);
        stmt.setInt(1,detailid);
        result = stmt.executeUpdate() >0;
        stmt.close();
        return result;
        }catch(Exception e){
            errormessage=e.toString().trim();
            return false;
        }
    }
    
    public boolean updaterecord(Billdetails_TO record)
    {
        boolean result;
        try{
        String query="update billdetails set billid=?,productid=?,quantity=?,price=?,discount=? where detailid=?";
        PreparedStatement stmt=DataConnection.getStatement(query);
        stmt.setInt(1, record.getBillid());
        stmt.setInt(2, record.getProductid());
        stmt.setInt(3, record.getQuantity());
        stmt.setFloat(4, record.getPrice());
        stmt.setFloat(5, record.getDiscount());
        stmt.setInt(6, record.getDetailid());
        result = stmt.executeUpdate() >0;
        stmt.close();
        return result;
        }catch(Exception e){
            errormessage=e.toString().trim();
            return false;
        }
    }
    
    public List<Billdetails_TO> getallrecords()
    {
        List<Billdetails_TO> list=null;
        Billdetails_TO record;
        try{
            String query = "select partyid,billdate,uname,billid from billinfo";
            PreparedStatement stmt=DataConnection.getStatement(query);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            {
                list=new ArrayList<Billdetails_TO>();
                do{
                    record=new Billdetails_TO();
                    record.setBillid(rs.getInt("billid"));
                    record.setProductid(rs.getInt("productid"));
                    record.setQuantity(rs.getInt("quantity"));
                    record.setPrice(rs.getFloat("price"));
                    record.setDiscount(rs.getFloat("discount"));
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

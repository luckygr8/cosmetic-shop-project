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
import model.to.Partyinfo_TO;

/**
 *
 * @author LAKSHAY DUTTA
 */
public class Partyinfo_DAO {

    private String errormessage;

    public String getErrormessage() {
        return errormessage;
    }

    public boolean insertrecord(Partyinfo_TO record) {
        boolean result;
        try {
            String query = "insert into partyinfo(partyname,contactno,adress,entrydate,uname) values(?,?,?,?,?)";
            PreparedStatement stmt = DataConnection.getStatement(query);
            stmt.setString(1, record.getPartyname());
            stmt.setString(2, record.getContactno());
            stmt.setString(3, record.getAdress());
            stmt.setDate(4, record.getEntrydate());
            stmt.setString(5, record.getUname());
            result = stmt.executeUpdate() > 0;
            stmt.close();
            return result;
        } catch (Exception e) {
            errormessage = e.toString().trim().toLowerCase();
            return false;
        }

    }

    public boolean deleterecord(int partyid) {
        boolean result;
        try {
            String query = "delete from partyinfo where partyid=?";
            PreparedStatement stmt = DataConnection.getStatement(query);
            stmt.setInt(1, partyid);
            result = stmt.executeUpdate() > 0;
            stmt.close();
            return result;
        } catch (Exception e) {
            errormessage = e.toString().trim().toLowerCase();
            return false;
        }

    }
    
    public boolean updaterecord(Partyinfo_TO record) {
        boolean result;
        try {
            String query = "update partyinfo set partyname=?,contactno=?,adress=?,entrydate=? where uname=? and partyid=?";
            PreparedStatement stmt = DataConnection.getStatement(query);
            stmt.setString(1, record.getPartyname());
            stmt.setString(2, record.getContactno());
            stmt.setString(3, record.getAdress());
            stmt.setDate(4, record.getEntrydate());
            stmt.setString(5, record.getUname());
            stmt.setInt(6, record.getPartyid());
            result = stmt.executeUpdate() > 0;
            stmt.close();
            return result;
        } catch (Exception e) {
            errormessage = e.toString().trim().toLowerCase();
            return false;
        }

    }
    
    public List<Partyinfo_TO> getallrecords( String uname)
    {
        List<Partyinfo_TO> list = null;
        Partyinfo_TO record;
        try{
            String query="select partyid,partyname,contactno,adress,entrydate from partyinfo where uname=?";
            PreparedStatement stmt = DataConnection.getStatement(query);
            stmt.setString(1, uname);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            {
                list = new ArrayList<Partyinfo_TO>();
                do{
                    record = new Partyinfo_TO();
                    record.setPartyid(rs.getInt("partyid"));
                    record.setPartyname(rs.getString("partyname"));
                    record.setAdress(rs.getString("adress"));
                    record.setContactno(rs.getString("contactno"));
                            //record.setUname(rs.getString("uname"));
                    record.setEntrydate(rs.getDate("entrydate"));
                    list.add(record);
                }while(rs.next());
            }
            stmt.close();
            return list;
        }catch(Exception e){
            errormessage = e.toString().trim().toLowerCase();
            return null;
        }
    }
}

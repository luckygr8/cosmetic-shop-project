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
import model.to.Billinfo_TO;

/**
 *
 * @author LAKSHAY DUTTA
 */
public class Billinfo_DAO {

    private String errormessage = "";

    public String getErrormessage() {
        return errormessage;
    }

    public boolean insertrecord(Billinfo_TO record) {
        boolean result;
        try {
            String query = "insert into billinfo (partyid,billdate,uname) values(?,sysdate(),?)";
            PreparedStatement stmt = DataConnection.getStatement(query);
            stmt.setInt(1, record.getPartyid());
            stmt.setString(2, record.getUname());
            result = stmt.executeUpdate() > 0;
            stmt.close();
            return result;
        } catch (Exception e) {
            errormessage = e.toString().trim();
            return false;
        }
    }

    public boolean deleterecord(int billid) {
        boolean result;
        try {
            String query = "delete from billinfo where billid =?";
            PreparedStatement stmt = DataConnection.getStatement(query);
            stmt.setInt(1, billid);
            result = stmt.executeUpdate() > 0;
            stmt.close();
            return result;
        } catch (Exception e) {
            errormessage = e.toString().trim();
            return false;
        }
    }

    public boolean updaterecord(Billinfo_TO record) {
        boolean result;
        try {
            String query = "update billinfo set partyid=?,billdate=sysdate(),uname=? where billid=?";
            PreparedStatement stmt = DataConnection.getStatement(query);
            stmt.setInt(1, record.getPartyid());
            stmt.setString(2, record.getUname());
            stmt.setInt(3, record.getBillid());
            result = stmt.executeUpdate() > 0;
            stmt.close();
            return result;
        } catch (Exception e) {
            errormessage = e.toString().trim();
            return false;
        }
    }

    public int getlastid() {
        int billid = 0;
        try {
            String query = "select billid from billinfo\n"
                    + "order by billid desc\n"
                    + "limit 1";
            PreparedStatement stmt = DataConnection.getStatement(query);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
                billid=rs.getInt("billid");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return billid;
    }

    public List<Billinfo_TO> getallrecords(String uname) {
        List<Billinfo_TO> list = null;
        Billinfo_TO record;
        try {
            String query = "select partyid,pi.partyname,billdate,b.uname,billid from billinfo b left outer join partyinfo pi using(partyid) where b.uname=?";
            PreparedStatement stmt = DataConnection.getStatement(query);
            stmt.setString(1, uname);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                list = new ArrayList<Billinfo_TO>();
                do {
                    record = new Billinfo_TO();
                    record.setPartyid(rs.getInt("partyid"));
                    record.setBilldate(rs.getTimestamp("billdate"));
                    record.setUname(rs.getString("uname"));
                    record.setBillid(rs.getInt("billid"));
                    record.setPartyname(rs.getString("partyname"));
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

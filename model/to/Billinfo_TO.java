/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.to;

import java.sql.Timestamp;

/**
 *
 * @author LAKSHAY DUTTA
 */
public class Billinfo_TO {
    private int billid;
    private int partyid;
    private String uname;
    private Timestamp billdate;
    private String partyname;

    public String getPartyname() {
        return partyname;
    }
    @Override
    public String toString()
    {
        return Integer.toString(billid) + " " + partyname + "( "+Integer.toString(partyid)+")";
    }
    public void setPartyname(String partyname) {
        this.partyname = partyname;
    }
    public int getBillid() {
        return billid;
    }
    
    public void setBillid(int billid) {
        this.billid = billid;
    }

    public int getPartyid() {
        return partyid;
    }

    public void setPartyid(int partyid) {
        this.partyid = partyid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Timestamp getBilldate() {
        return billdate;
    }

    public void setBilldate(Timestamp billdate) {
        this.billdate = billdate;
    }
    
}

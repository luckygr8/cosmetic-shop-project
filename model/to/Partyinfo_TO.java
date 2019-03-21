/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.to;

import java.sql.Date;

/**
 *
 * @author LAKSHAY DUTTA
 */
public class Partyinfo_TO {
    private String partyname;
    private String adress;
    private String contactno;
    private String uname;
    private Date entrydate;
    private int partyid;

    public String getPartyname() {
        return partyname;
    }
    public String toString()
    {
        return partyname + " ("+Integer.toString(partyid)+")";
    }
    public void setPartyname(String partyname) {
        this.partyname = partyname;
    }

    public String getAdress() {
        return adress;
    }
    

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Date getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(Date entrydate) {
        this.entrydate = entrydate;
    }

    public int getPartyid() {
        return partyid;
    }

    public void setPartyid(int partyid) {
        this.partyid = partyid;
    }
    
}

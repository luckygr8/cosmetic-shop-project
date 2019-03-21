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
public class Logininfo_TO {
    private String uname;
    private String upass;
    private String rolename;
    private Timestamp lastlogin;
    public String toString()
    {
        return uname + "("+rolename+")";
    }
    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public void setLastlogin(Timestamp lastlogin) {
        this.lastlogin = lastlogin;
    }

    public String getUname() {
        return uname;
    }

    public String getUpass() {
        return upass;
    }

    public String getRolename() {
        return rolename;
    }

    public Timestamp getLastlogin() {
        return lastlogin;
    }
    
}

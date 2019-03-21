/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.beans.PropertyVetoException;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author LAKSHAY DUTTA
 */
public class Checks {

    public static boolean isempty(String str) {
        if (str.trim().isEmpty()) {
            return true;
        }
        return false;
    }

    public static void openInternalFrame(JDesktopPane deskpane, JInternalFrame jif) {
        jif.setVisible(true);
        deskpane.add(jif);
        try {
            jif.setSelected(true);
        } catch (PropertyVetoException ex) {
        }
    }

    public static boolean keytype_int(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if (c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == '0') {
            return true;
        }
        return false;
    }

    public static boolean keytype_float(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if (c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == '.' || c == '0') {
            return true;
        }
        return false;
    }
    public static String month_names[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private static  int month_number;
    public static int month_number(String monthname) {
        switch (monthname) {           
            case "January":
                month_number=1;
                break;
            case "February":
                month_number=2;
                break;
            case "March":
                month_number=3;
                break;
            case "April":
                month_number=4;
                break;
            case "May":
                month_number=5;
                break;
            case "June":
                month_number=6;
                break;
            case "july":
                month_number=7;
                break;
            case "August":
               month_number=8;
                break;
            case "September":
               month_number=9;
                break;
            case "October":
               month_number=10;
                break;
            case "November":
               month_number=11;
                break;
            case "December":
               month_number=12;
                break;
        }
        return month_number;
    }
    public static boolean isLeap(int year)
    {
        if(year%4==0)
        {
            if(year%100==0)
            {
                if(year%400==0)
                    return true;
                else
                    return false;
            }else
                return true;
        }
        else
            return false;
    }
    public static boolean date_check(int date , int month , int year)
    {
        boolean valid=true;
        int date_limit_for_feb=28;
        if(isLeap(year))
            date_limit_for_feb=29;
        switch(month)
        {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                if(!(date<=31))
                    valid=false;
                break;
            case 2:
                if(!(date<=date_limit_for_feb))
                    valid=false;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                if(!(date<=30))
                    valid=false;
                break;
        }
        return valid;
    }
}

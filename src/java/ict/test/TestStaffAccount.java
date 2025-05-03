/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.test;

import ict.db.bookingDB;
import ict.db.guestDB;
import ict.db.memberDB;
import ict.db.staffDB;
import ict.db.venueDB;

/**
 *
 * @author user
 */
public class TestStaffAccount {
    public static void main(String[] arg){
        String url = "jdbc:mysql://localhost:3307/itp4511_assignment";
        String username = "root";
        String password = "";
        staffDB staff = new staffDB(url,username,password);
        boolean createAccount = staff.CreateStaffAccount("tom","123456","Senior");
        boolean createAccount2 = staff.CreateStaffAccount("mary","123456","Staff");
    }
}

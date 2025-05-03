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
import ict.db.userDB;

/**
 *
 * @author user
 */
public class TestRegister {
    public static void main(String[] arg){
        String url = "jdbc:mysql://localhost:3306/itp4511_assignment";
        String username = "root";
        String password = "";
        userDB user = new userDB(url,username,password);
        boolean register = user.CreateAccount("sky","123456","senior",false);
        boolean register1 = user.CreateAccount("mary","123456","staff",false);
        boolean register2 = user.CreateAccount("tom","123456","member",false);
    }
}

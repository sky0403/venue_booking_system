/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.test;

import ict.db.bookingDB;
import ict.db.staffDB;
import ict.db.venueDB;

/**
 *
 * @author user
 */
public class TestQuery {
    public static void main(String[] arg){
        String url = "jdbc:mysql://localhost:3306/itp4511_assignment";
        String username = "root";
        String password = "";
        bookingDB booking = new bookingDB(url,username,password);
        
        
        //Tuen Mun, Sha Tin, Tsing Yi, Lee Wai Lee, and Chai Wan
    }
}

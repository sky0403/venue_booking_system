/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.test;

import ict.db.staffDB;
import ict.db.venueDB;

/**
 *
 * @author user
 */
public class TestAddVenue {
    public static void main(String[] arg){
        String url = "jdbc:mysql://localhost:3306/itp4511_assignment";
        String username = "root";
        String password = "";
        venueDB venue = new venueDB(url,username,password);
        boolean addvenue = venue.AddVenue("Happy", "event", 100,"Tuen Mun", "venue for guest to organazation event", "mary",300,true);
        boolean addvenue1 = venue.AddVenue("Happy Hour", "event", 200,"Sha Tin", "dinner ball", "mary",200,true);
        boolean addvenue2 = venue.AddVenue("Happy night", "event", 300,"Tsing Yi", "big place for night party", "mary",150,true);
        boolean addvenue3 = venue.AddVenue("Blue Island", "event", 50,"Lee Wai Lee", "party for single", "mary",100,true);
        boolean addvenue4 = venue.AddVenue("Colorful Life", "event", 150,"Chai Wan", "party for friends", "mary",400,true);
        
        
        //Tuen Mun, Sha Tin, Tsing Yi, Lee Wai Lee, and Chai Wan
    }
}

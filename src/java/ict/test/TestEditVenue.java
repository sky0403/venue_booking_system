/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.test;

import ict.db.venueDB;
import ict.bean.Venue;

/**
 *
 * @author user
 */
public class TestEditVenue {
    public static void main(String[] arg){
        String url = "jdbc:mysql://localhost:3307/itp4511_assignment";
        String username = "root";
        String password = "";
        Venue v = new Venue();
        v.setId(6);
        v.setName("asdasd");
        v.setType("asdsaasd");
        v.setCapacity(100);
        v.setLocation("adsasdg");
        v.setDescription("asdfsadfsadfadsf");
        v.setInchargePerson("tom");
        v.setFee(150);
        v.setEnabled(false);
        venueDB venue = new venueDB(url,username,password);
        boolean editvenue = venue.editVenue(v);
        //boolean addvenue = venue.AddVenue("Happy", "event", 100,"Tuen Mun", "venue for guest to organazation event", "mary",);
        //boolean addvenue = venue.AddVenue("Happy Hour", "event", 200,"Sha Tin", "dinner ball", 2);
        //boolean addvenue2 = venue.AddVenue("Happy night", "event", 300,"Tsing Yi", "big place for night party", 2);
        //boolean addvenue3 = venue.AddVenue("Blue Island", "event", 50,"Lee Wai Lee", "party for single", 2);
        //boolean addvenue4 = venue.AddVenue("Colorful Life", "event", 150,"Chai Wan", "party for friends", 2);
        
        
        //Tuen Mun, Sha Tin, Tsing Yi, Lee Wai Lee, and Chai Wan
    }
}

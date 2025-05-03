/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.bean;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class Guest implements Serializable{
    private int guestlist_id;
    private String name;
    private String email;
    
    public Guest(){
        guestlist_id = 0;
        this.name = "";
        this.email = "";
    }
    
    public int getGuestList_Id(){
        return this.guestlist_id;
    }
    public String getName(){
        return this.name;
    }
    public String getEmail(){
        return this.email;
    }
    
    public void setGuestList_ID(int id){
        this.guestlist_id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        this.email = email;
    }
}

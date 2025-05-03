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
public class GuestList implements Serializable{
    private int id;
    private int user_id;
    private String list_name;
    
    public GuestList(){
        this.id = 0;
        this.user_id = 0;
        this.list_name = "";
    }
    
    public int getId(){
        return this.id;
    }
    public int getUserid(){
        return user_id;
    }
    public String getListname(){
        return list_name;
    }
    
    public void setId(int id){
        this.id = id;
    }
    public void setUserid(int id){
        this.user_id = id; 
    }
    public void setListname(String name){
        this.list_name = name;
    }
}

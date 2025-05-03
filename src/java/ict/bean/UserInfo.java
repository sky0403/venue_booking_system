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
public class UserInfo implements Serializable{
    private int id;
    private String username;
    private String password;
    private String position;
    private boolean blacklist;
    
    public UserInfo(){
        this.id = 0;
        this.username = "";
        this.password = "";
        this.position = "";
        this.blacklist = false;
    }
    
    public int getId(){
        return this.id;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getPosition(){
        return position;
    }
    public boolean getBlackList(){
        return this.blacklist;
    }
    
    public void setId(int id){
        this.id = id;
    }
    public void setUsername(String name){
        this.username = name;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setPosition(String post){
        this.position = post;
    }
    public void setBlackList(boolean bl){
        this.blacklist = bl;
    }
}

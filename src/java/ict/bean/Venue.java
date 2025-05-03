/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.bean;

/**
 *
 * @author user
 */
public class Venue {
    private int id;
    private String name;
    private String type;
    private int capacity;
    private String location;
    private String description;
    private String inchargeperson;
    private int fee;
    private boolean enabled;
    
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getType(){
        return type;
    }
    public int getCapacity(){
        return capacity;
    }
    public String getLocation(){
        return location;
    }
    public String getDescription(){
        return description;
    }
    public String getInchargePerson(){
        return inchargeperson;
    }
    public int getFee(){
        return fee;
    }
    public boolean getEnabled(){
        return enabled;
    }
    
    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setType(String type){
        this.type = type;
    }
    public void setCapacity(int cap){
        this.capacity = cap;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public void setDescription(String desc){
        this.description = desc;
    }
    public void setInchargePerson(String person){
        this.inchargeperson = person;
    }
    public void setFee(int fee){
        this.fee = fee;
    }
    public void setEnabled(boolean enable){
        this.enabled = enable;
    }
}

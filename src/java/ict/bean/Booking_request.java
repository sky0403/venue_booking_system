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
public class Booking_request implements Serializable{
    private int booking_id;
    private String incharge_person;
    private String booking_accept;

    public Booking_request() {
        this.booking_id = 0;
        this.incharge_person = "";
        this.booking_accept = "";
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public String getIncharge_person() {
        return incharge_person;
    }

    public void setIncharge_person(String incharge_person) {
        this.incharge_person = incharge_person;
    }

    public String getBooking_accept() {
        return booking_accept;
    }

    public void setBooking_accept(String booking_accept) {
        this.booking_accept = booking_accept;
    }

}

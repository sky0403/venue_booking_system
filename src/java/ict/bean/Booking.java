/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.bean;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author user
 */
public class Booking implements Serializable{
    private int booking_id;
    private int user_id;
    private int guestList_id;
    private int venue_id;
    private Date booking_date;
    private Time booking_StartTime;
    private Time booking_EndTime;
    private String guest_invitations;
    private String member_comment;
    private String staff_comment;
    private Time checkin_time;
    private Time checkout_time;
    private int amount;
    
    
    public Booking(){
        booking_id = 0;
        user_id = 0;
        guestList_id = 0;
        venue_id = 0;
        booking_date = null;
        booking_StartTime = null;
        guest_invitations = "";
        member_comment = "";
        staff_comment = "";
        checkin_time = null;
        checkout_time = null;
        amount = 0;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getGuestList_id() {
        return guestList_id;
    }

    public void setGuestList_id(int guestList_id) {
        this.guestList_id = guestList_id;
    }

    public int getVenue_id() {
        return venue_id;
    }

    public void setVenue_id(int venue_id) {
        this.venue_id = venue_id;
    }

    public Date getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(Date booking_date) {
        this.booking_date = booking_date;
    }

    public Time getBooking_StartTime() {
        return booking_StartTime;
    }

    public void setBooking_StartTime(Time booking_StartTime) {
        this.booking_StartTime = booking_StartTime;
    }

    public Time getBooking_EndTime() {
        return booking_EndTime;
    }

    public void setBooking_EndTime(Time booking_EndTime) {
        this.booking_EndTime = booking_EndTime;
    }

    public String getGuest_invitations() {
        return guest_invitations;
    }

    public void setGuest_invitations(String guest_invitations) {
        this.guest_invitations = guest_invitations;
    }

    public String getMember_comment() {
        return member_comment;
    }

    public void setMember_comment(String member_comment) {
        this.member_comment = member_comment;
    }

    public String getStaff_comment() {
        return staff_comment;
    }

    public void setStaff_comment(String staff_comment) {
        this.staff_comment = staff_comment;
    }

    public Time getCheckin_time() {
        return checkin_time;
    }

    public void setCheckin_time(Time checkin_time) {
        this.checkin_time = checkin_time;
    }

    public Time getCheckout_time() {
        return checkout_time;
    }

    public void setCheckout_time(Time checkout_time) {
        this.checkout_time = checkout_time;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    
    
    
}

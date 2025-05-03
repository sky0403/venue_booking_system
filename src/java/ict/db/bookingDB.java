/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.bean.Booking;
import ict.bean.Booking_request;
import ict.bean.GuestList;
import ict.bean.Report;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class bookingDB {
    private String url = "";
    private String username = "";
    private String password = "";
    
    public bookingDB(String url, String username, String password){
        this.url = url;
        this.username = username;
        this.password = password;
    }
    
    public Connection getConnection() throws SQLException, IOException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return DriverManager.getConnection(url,username,password);
    }
    
    public void createBookingTable(){
        Connection cnnct = null;
        Statement stmnt = null;
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            String sql
                    = "CREATE TABLE IF NOT EXISTS booking (" 
                    + "booking_id int NOT NULL AUTO_INCREMENT,"
                    + "user_id int NOT NULL,"
                    + "guestList_id int NOT NULL,"
                    + "venue_id int NOT NULL,"
                    + "booking_date date NOT NULL,"
                    + "booking_StartTime time NOT NULL,"
                    + "booking_EndTime time not null,"
                    + "guest_invitations varchar(255) NOT NULL,"
                    + "member_comment varchar(255) NULL,"
                    + "staff_comment varchar(255) NULL,"
                    + "Checkin_time time NULL,"
                    + "Checkout_time time NULL,"
                    + "amount int not null,"
                    + "PRIMARY KEY(booking_id),"
                    + "foreign key(user_id) references user(user_id),"
                    + "foreign key(guestList_id) references guestList(guestList_id),"
                    + "foreign key(venue_id) references venue(venue_id)"
                    + ")";
            stmnt.execute(sql);

            stmnt.close();
            cnnct.close();
        } catch (SQLException ex){
            while (ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
    public void createBookingRequestTable(){
        Connection cnnct = null;
        Statement stmnt = null;
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            String sql
                    = "CREATE TABLE IF NOT EXISTS booking_request (" 
                    + "booking_id int NOT NULL,"
                    + "incharge_person varchar(30) not null,"
                    + "booking_accept varchar(11) not null"
                    + "foreign key(booking_id) references booking(booking_id)"
                    + ")";
            stmnt.execute(sql);

            stmnt.close();
            cnnct.close();
        } catch (SQLException ex){
            while (ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
    public ArrayList queryBooking(){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<Booking> list = new ArrayList<Booking>();
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM booking";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = pStmnt.executeQuery();
            
            while(rs.next()){
                Booking bk = new Booking();
                bk.setBooking_id(rs.getInt(1));
                bk.setUser_id(rs.getInt(2));
                bk.setGuestList_id(rs.getInt(3));
                bk.setVenue_id(rs.getInt(4));
                bk.setBooking_date(rs.getDate(5));
                bk.setBooking_StartTime(rs.getTime(6));
                bk.setBooking_EndTime(rs.getTime(7));
                bk.setGuest_invitations(rs.getString(8));
                bk.setMember_comment(rs.getString(9));
                bk.setStaff_comment(rs.getString(10));
                bk.setCheckin_time(rs.getTime(11));
                bk.setCheckout_time(rs.getTime(12));
                bk.setAmount(rs.getInt(13));
                list.add(bk);
            }
            return list;
        } catch(SQLException ex){
            while (ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }finally{
            if(pStmnt != null){
                try{
                    pStmnt.close();
                }catch (SQLException e){
                }
            }
            if(cnnct != null){
                try{
                    cnnct.close();
                }catch (SQLException sqlEx){
                }
            }
        }
        return null;
    }
    public ArrayList queryBookingByUser(int id){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<Booking> list = new ArrayList<Booking>();
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM booking where user_id = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);
            ResultSet rs = pStmnt.executeQuery();
            
            while(rs.next()){
                Booking bk = new Booking();
                bk.setBooking_id(rs.getInt(1));
                bk.setUser_id(rs.getInt(2));
                bk.setGuestList_id(rs.getInt(3));
                bk.setVenue_id(rs.getInt(4));
                bk.setBooking_date(rs.getDate(5));
                bk.setBooking_StartTime(rs.getTime(6));
                bk.setBooking_EndTime(rs.getTime(7));
                bk.setGuest_invitations(rs.getString(8));
                bk.setMember_comment(rs.getString(9));
                bk.setStaff_comment(rs.getString(10));
                bk.setCheckin_time(rs.getTime(11));
                bk.setCheckout_time(rs.getTime(12));
                bk.setAmount(rs.getInt(13));
                list.add(bk);
            }
            return list;
        } catch(SQLException ex){
            while (ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }finally{
            if(pStmnt != null){
                try{
                    pStmnt.close();
                }catch (SQLException e){
                }
            }
            if(cnnct != null){
                try{
                    cnnct.close();
                }catch (SQLException sqlEx){
                }
            }
        }
        return null;
    }
    public ArrayList queryBookingByVenue(int id){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<Booking> list = new ArrayList<Booking>();
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM booking where venue_id = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);
            ResultSet rs = pStmnt.executeQuery();
            
            while(rs.next()){
                Booking bk = new Booking();
                bk.setBooking_id(rs.getInt(1));
                bk.setUser_id(rs.getInt(2));
                bk.setGuestList_id(rs.getInt(3));
                bk.setVenue_id(rs.getInt(4));
                bk.setBooking_date(rs.getDate(5));
                bk.setBooking_StartTime(rs.getTime(6));
                bk.setBooking_EndTime(rs.getTime(7));
                bk.setGuest_invitations(rs.getString(8));
                bk.setMember_comment(rs.getString(9));
                bk.setStaff_comment(rs.getString(10));
                bk.setCheckin_time(rs.getTime(11));
                bk.setCheckout_time(rs.getTime(12));
                bk.setAmount(rs.getInt(13));
                list.add(bk);
            }
            return list;
        } catch(SQLException ex){
            while (ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }finally{
            if(pStmnt != null){
                try{
                    pStmnt.close();
                }catch (SQLException e){
                }
            }
            if(cnnct != null){
                try{
                    cnnct.close();
                }catch (SQLException sqlEx){
                }
            }
        }
        return null;
    }
    public Report queryAmount(int id,String startdate, String enddate){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        Report rp = null;
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT SUM(amount) FROM booking WHERE venue_id = ? and booking_date >= ? and booking_date <= ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);
            pStmnt.setString(2, startdate);
            pStmnt.setString(3, enddate);
            ResultSet rs = pStmnt.executeQuery();
            
            rp.setAmount(rs.getInt(1));
            rp.setVenue_id(id);
            
        } catch(SQLException ex){
            while (ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }finally{
            if(pStmnt != null){
                try{
                    pStmnt.close();
                }catch (SQLException e){
                }
            }
            if(cnnct != null){
                try{
                    cnnct.close();
                }catch (SQLException sqlEx){
                }
            }
        }
        return rp;
    }
    public Booking queryBookingRecordById(int id){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        Booking bk = null;
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM booking where booking_id = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);
            ResultSet rs = pStmnt.executeQuery();
                
                bk.setBooking_id(rs.getInt(1));
                bk.setUser_id(rs.getInt(2));
                bk.setGuestList_id(rs.getInt(3));
                bk.setVenue_id(rs.getInt(4));
                bk.setBooking_date(rs.getDate(5));
                bk.setBooking_StartTime(rs.getTime(6));
                bk.setBooking_EndTime(rs.getTime(7));
                bk.setGuest_invitations(rs.getString(8));
                bk.setMember_comment(rs.getString(9));
                bk.setStaff_comment(rs.getString(10));
                bk.setCheckin_time(rs.getTime(11));
                bk.setCheckout_time(rs.getTime(12));
                bk.setAmount(rs.getInt(13));
            
        } catch(SQLException ex){
            while (ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }finally{
            if(pStmnt != null){
                try{
                    pStmnt.close();
                }catch (SQLException e){
                }
            }
            if(cnnct != null){
                try{
                    cnnct.close();
                }catch (SQLException sqlEx){
                }
            }
        }
        return bk;
    }
    public ArrayList queryBookingByIncharge(String name){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<Booking_request> list = new ArrayList<Booking_request>();
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM booking_request where incharge_person = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1,name);
            ResultSet rs = pStmnt.executeQuery();
            
            while(rs.next()){
                Booking_request bq = new Booking_request();
                bq.setBooking_id(rs.getInt(1));
                bq.setIncharge_person(rs.getString(2));
                bq.setBooking_accept(rs.getString(3));
                list.add(bq);
            }
            return list;
        } catch(SQLException ex){
            while (ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }finally{
            if(pStmnt != null){
                try{
                    pStmnt.close();
                }catch (SQLException e){
                }
            }
            if(cnnct != null){
                try{
                    cnnct.close();
                }catch (SQLException sqlEx){
                }
            }
        }
        return null;
    }
    public Booking_request queryBookingRequestById(int id){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<Booking_request> list = new ArrayList<Booking_request>();
        Booking_request bq = new Booking_request();
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM booking_request where booking_id = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);
            ResultSet rs = pStmnt.executeQuery();
            
            bq.setBooking_id(rs.getInt(1));
            bq.setIncharge_person(rs.getString(2));
            bq.setBooking_accept(rs.getString(3));
            
        } catch(SQLException ex){
            while (ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }finally{
            if(pStmnt != null){
                try{
                    pStmnt.close();
                }catch (SQLException e){
                }
            }
            if(cnnct != null){
                try{
                    cnnct.close();
                }catch (SQLException sqlEx){
                }
            }
        }
        return bq;
    }
    public boolean createBooking(int user_id,int guestList_id,int venue_id, String booking_date, String booking_start, String booking_end, String guest_invitations,int amount){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO booking (user_id,guestList_id,venue_id,booking_date,booking_StartTime,booking_EndTime,guest_invitations,amount) VALUES (?,?,?,?,?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, user_id);
            pStmnt.setInt(2,guestList_id);
            pStmnt.setInt(3,venue_id);
            pStmnt.setString(4,booking_date);
            pStmnt.setString(5,booking_start);
            pStmnt.setString(6,booking_end);
            pStmnt.setString(7, guest_invitations);
            pStmnt.setInt(8,amount);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1){
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close(); 
        } catch(SQLException ex){
            while (ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return isSuccess;
    }
    public boolean createBookingRequest(int booking_id,String name){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO booking_request VALUES (?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1,booking_id);
            pStmnt.setString(2,name);
            pStmnt.setString(3, "unprocessed");
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1){
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close(); 
        } catch(SQLException ex){
            while (ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return isSuccess;
    }
    public boolean confirmbooking(int id){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "UPDATE booking_request SET booking_accept = ?"
                                     + "WHERE booking_id = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, "accept");
            pStmnt.setInt(2,id);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1){
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close(); 
        } catch(SQLException ex){
            while (ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return isSuccess;
    }
    public boolean rejectbooking(int id){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "UPDATE booking_request SET booking_accept = ?"
                                     + "WHERE booking_id = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, "reject");
            pStmnt.setInt(2,id);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1){
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close(); 
        } catch(SQLException ex){
            while (ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return isSuccess;
    }
    public boolean checkin(int id, String Checkin_time){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "UPDATE booking SET Checkin_time = ?"
                                     + "WHERE booking_id = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, Checkin_time);
            pStmnt.setInt(2,id);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1){
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close(); 
        } catch(SQLException ex){
            while (ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return isSuccess;
    }
    public boolean checkout(int id, String Checkout_time){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "UPDATE booking SET Checkout_time = ?"
                                     + "WHERE booking_id = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, Checkout_time);
            pStmnt.setInt(2,id);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1){
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close(); 
        } catch(SQLException ex){
            while (ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return isSuccess;
    }
}

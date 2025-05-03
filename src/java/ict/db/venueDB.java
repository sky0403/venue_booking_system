/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.bean.Venue;
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
public class venueDB {
    private String url = "";
    private String username = "";
    private String password = "";
    
    public venueDB(String url, String username, String password){
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
    
    public void createVenueTable(){
        Connection cnnct = null;
        Statement stmnt = null;
        
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            String sql
                    = "CREATE TABLE IF NOT EXISTS venue (" 
                    + "venue_id int NOT NULL AUTO_INCREMENT,"
                    + "venue_name varchar(25) NOT NULL,"
                    + "venue_type varchar(10) NOT NULL,"
                    + "capacity int NOT NULL,"
                    + "location varchar(15) NOT NULL,"
                    + "Description varchar(255) NULL,"
                    + "incharge_person varchar(20) NOT NULL,"
                    + "fee int not null,"
                    + "Enable boolean not null,"
                    + "PRIMARY KEY(venue_id)"
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
    public void createVenuefeeTable(){
        Connection cnnct = null;
        Statement stmnt = null;
        
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            String sql
                    = "CREATE TABLE IF NOT EXISTS venue_fee (" 
                    + "venue_id int NOT NULL,"
                    + "year int NOT NULL,"
                    + "fee int not null"
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
    public boolean AddVenue(String name, String type,int capacity,String location, String Description,String incharge_person,int fee,boolean enable){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO venue (venue_name,venue_type,capacity,location,Description,incharge_person,fee,Enable) VALUES (?,?,?,?,?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1,name);
            pStmnt.setString(2,type);
            pStmnt.setInt(3,capacity);
            pStmnt.setString(4,location);
            pStmnt.setString(5,Description);
            pStmnt.setString(6,incharge_person);
            pStmnt.setInt(7, fee);
            pStmnt.setBoolean(8,enable);
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
    public boolean delvenue(int venue_id){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "DELETE FROM venue WHERE venue_id = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1,venue_id);
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
    
    public boolean AddVenueFee(int id,int year,int fee){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO venue_fee (venue_id,year,fee) VALUES (?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1,id);
            pStmnt.setInt(2,year);
            pStmnt.setInt(3, fee);
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
    
    public boolean editVenue(Venue v){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "UPDATE venue SET venue_name = ?,venue_type = ?,capacity = ?,"
                                     + "location = ?, Description = ?, incharge_person = ?, fee = ?, Enable = ? "
                                     + "WHERE venue_id = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1,v.getName());
            pStmnt.setString(2,v.getType());
            pStmnt.setInt(3,v.getCapacity());
            pStmnt.setString(4, v.getLocation());
            pStmnt.setString(5,v.getDescription());
            pStmnt.setString(6,v.getInchargePerson());
            pStmnt.setInt(7,v.getFee());
            pStmnt.setBoolean(8,v.getEnabled());
            pStmnt.setInt(9, v.getId());
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
    
    public ArrayList queryVenue(){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<Venue> list = new ArrayList<Venue>();
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM venue";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = pStmnt.executeQuery();
            rs = pStmnt.executeQuery();
            
            while(rs.next()){
                Venue v = new Venue();
                v.setId(rs.getInt(1));
                v.setName(rs.getString(2));
                v.setType(rs.getString(3));
                v.setCapacity(rs.getInt(4));
                v.setLocation(rs.getString(5));
                v.setDescription(rs.getString(6));
                v.setInchargePerson(rs.getString(7));
                v.setFee(rs.getInt(8));
                v.setEnabled(rs.getBoolean(9));
                list.add(v);
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
    public ArrayList queryVenueByEnabled(){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<Venue> list = new ArrayList<Venue>();
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM venue where Enable = true";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = pStmnt.executeQuery();
            rs = pStmnt.executeQuery();
            
            while(rs.next()){
                Venue v = new Venue();
                v.setId(rs.getInt(1));
                v.setName(rs.getString(2));
                v.setType(rs.getString(3));
                v.setCapacity(rs.getInt(4));
                v.setLocation(rs.getString(5));
                v.setDescription(rs.getString(6));
                v.setInchargePerson(rs.getString(7));
                v.setFee(rs.getInt(8));
                v.setEnabled(rs.getBoolean(9));
                list.add(v);
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
    
    public Venue queryVenueByID(int id){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        Venue v = null;
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM venue WHERE venue_id = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            if (rs.next()){
                v = new Venue();
                v.setId(rs.getInt(1));
                v.setName(rs.getString(2));
                v.setType(rs.getString(3));
                v.setCapacity(rs.getInt(4));
                v.setLocation(rs.getString(5));
                v.setDescription(rs.getString(6));
                v.setInchargePerson(rs.getString(7));
                v.setFee(rs.getInt(8));
                v.setEnabled(rs.getBoolean(9));
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
        return v;
    }
}

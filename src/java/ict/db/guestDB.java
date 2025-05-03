/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.bean.Guest;
import ict.bean.GuestList;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
public class guestDB {
    private String url = "";
    private String username = "";
    private String password = "";
    
    public guestDB(String url, String username, String password){
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
    
    public void createGuestListTable(){
        Connection cnnct = null;
        Statement stmnt = null;
        
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            
            String sql
                    = "CREATE TABLE IF NOT EXISTS guestList(" 
                    + "guestList_id int NOT NULL AUTO_INCREMENT,"
                    + "name varchar(50) not null,"
                    + "user_id int not null,"
                    + "PRIMARY KEY(guestList_id),"
                    + "foreign key(user_id) references user(user_id)"
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
    public void createGuestTable(){
        Connection cnnct = null;
        Statement stmnt = null;
        
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            
            String sql
                    = "CREATE TABLE IF NOT EXISTS guest (" 
                    + "guestList_id int NOT NULL,"
                    + "name varchar(25) NOT NULL,"
                    + "email varchar(255) NOT NULL"
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
    public boolean CreateGuestList(int userid,String name){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO guestlist (user_id,guestlist_name) VALUES (?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1,userid);
            pStmnt.setString(2,name);
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
    public boolean AddGuest(int guestlist_id,String name,String email){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO guest (guestList_id,name,email) VALUES (?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1,guestlist_id);
            pStmnt.setString(2,name);
            pStmnt.setString(3,email);
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
    public boolean delGuestList(int guestlist_id){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "DELETE FROM guestlist WHERE guestlist_id = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1,guestlist_id);
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
    
    public boolean delGuest(int guestlist_id,String email){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "DELETE FROM guest WHERE guestList_id = ? and email = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1,guestlist_id);
            pStmnt.setString(2,email);
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
    public ArrayList queryGuestList(int id){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<GuestList> list = new ArrayList<GuestList>();
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM guestlist where user_id = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);
            ResultSet rs = pStmnt.executeQuery();
            
            while(rs.next()){
                GuestList gl = new GuestList();
                gl.setId(rs.getInt(1));
                gl.setUserid(rs.getInt(2));
                gl.setListname(rs.getString(3));
                list.add(gl);
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
    public GuestList queryGuestListByID(int id){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        GuestList gl = null;
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM guestlist WHERE guestList_id= ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            if (rs.next()){
                gl = new GuestList();
                gl.setId(rs.getInt(1));
                gl.setUserid(rs.getInt(2));
                gl.setListname(rs.getString(3));
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
        return gl;
    }
    public ArrayList queryGuest(int id){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<Guest> list = new ArrayList<Guest>();
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM guest where guestList_id = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);
            ResultSet rs = pStmnt.executeQuery();
            
            while(rs.next()){
                Guest g = new Guest();
                g.setGuestList_ID(rs.getInt(1));
                g.setName(rs.getString(2));
                g.setEmail(rs.getString(3));
                list.add(g);
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
}

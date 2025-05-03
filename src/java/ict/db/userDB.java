/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.bean.UserInfo;
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
public class userDB {
    private String url = "";
    private String username = "";
    private String password = "";
    
    public userDB(String url, String username, String password){
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
    
    public void createUserTable(){
        Connection cnnct = null;
        Statement stmnt = null;
        
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            String sql
                    = "CREATE TABLE IF NOT EXISTS user (" 
                    + "user_id int NOT NULL AUTO_INCREMENT,"
                    + "user_name varchar(25) NOT NULL,"
                    + "password varchar(10) NOT NULL,"
                    + "role varchar(11) NOT NULL,"
                    + "blacklist boolean not null,"
                    + "PRIMARY KEY(user_id)"
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
    public boolean CreateAccount(String name, String password,String role,boolean blacklist){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO user (user_name,password,role,blacklist) VALUES (?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1,name);
            pStmnt.setString(2,password);
            pStmnt.setString(3,role);
            pStmnt.setBoolean(4, blacklist);
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
    public boolean isMember(String name, String pwd){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isValid;
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM user WHERE user_name=? and password=? and role = ?";         
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, name);
            pStmnt.setString(2, pwd);
            pStmnt.setString(3, "member");
            ResultSet rs = rs = pStmnt.executeQuery();
            if(rs.next()){
                isValid = true;
            }else{
                isValid = false;
            }
            pStmnt.close();
            cnnct.close();
        }catch (SQLException ex){
            while (ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
            isValid = false;
        }catch (IOException ex){
            ex.printStackTrace();
            isValid = false;
        }
        return isValid;
    }
    public boolean isStaff(String name, String pwd){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isValid;
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM user WHERE user_name=? and password=? and role = ?";         
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, name);
            pStmnt.setString(2, pwd);
            pStmnt.setString(3, "staff");
            ResultSet rs = rs = pStmnt.executeQuery();
            if(rs.next()){
                isValid = true;
            }else{
                isValid = false;
            }
            pStmnt.close();
            cnnct.close();
        }catch (SQLException ex){
            while (ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
            isValid = false;
        }catch (IOException ex){
            ex.printStackTrace();
            isValid = false;
        }
        return isValid;
    }
    public boolean isSenior(String name, String pwd){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isValid;
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM user WHERE user_name=? and password=? and role = ?";         
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, name);
            pStmnt.setString(2, pwd);
            pStmnt.setString(3, "senior");
            ResultSet rs = rs = pStmnt.executeQuery();
            if(rs.next()){
                isValid = true;
            }else{
                isValid = false;
            }
            pStmnt.close();
            cnnct.close();
        }catch (SQLException ex){
            while (ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
            isValid = false;
        }catch (IOException ex){
            ex.printStackTrace();
            isValid = false;
        }
        return isValid;
    }
    
    
    public UserInfo isVaildUser(String name, String pwd){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        UserInfo user = new UserInfo();
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM user WHERE user_name=? and password=?";         
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, name);
            pStmnt.setString(2, pwd);
            ResultSet rs = rs = pStmnt.executeQuery();
            if(rs.next()){
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setPosition(rs.getString(4));
                user.setBlackList(rs.getBoolean(5));
            }
            /*if(rs.next()){
                isValid = true;
            }else{
                isValid = false;
            }*/
            pStmnt.close();
            cnnct.close();
        }catch (SQLException ex){
            while (ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return user;
    }
    
    public ArrayList queryStaff(){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<UserInfo> list = new ArrayList<UserInfo>();
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM user where role = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, "staff");
            ResultSet rs = pStmnt.executeQuery();
            
            while(rs.next()){
                UserInfo ui = new UserInfo();
                ui.setId(rs.getInt(1));
                ui.setUsername(rs.getString(2));
                list.add(ui);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

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
public class memberDB {
    private String url = "";
    private String username = "";
    private String password = "";
    
    public memberDB(String url, String username, String password){
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
    
    public void createMemberTable(){
        Connection cnnct = null;
        Statement stmnt = null;
        
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            String sql
                    = "CREATE TABLE IF NOT EXISTS member (" 
                    + "member_id int NOT NULL AUTO_INCREMENT,"
                    + "member_name varchar(25) NOT NULL,"
                    + "password varchar(10) NOT NULL,"
                    + "blacklist varchar(11) NOT NULL,"
                    + "PRIMARY KEY(member_id)"
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
    public boolean Register(String name, String password, boolean blacklist){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO member (member_name,password,blacklist) VALUES (?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1,name);
            pStmnt.setString(2,password);
            pStmnt.setBoolean(3,blacklist);
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
     public boolean isValidUser(String name, String pwd){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isValid;
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM member WHERE member_name=? and password=?";         
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, name);
            pStmnt.setString(2, pwd);
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
}

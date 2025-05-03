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
public class staffDB {
    private String url = "";
    private String username = "";
    private String password = "";
    
    public staffDB(String url, String username, String password){
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
    
    public void createStaffTable(){
        Connection cnnct = null;
        Statement stmnt = null;
        
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            String sql
                    = "CREATE TABLE IF NOT EXISTS staff (" 
                    + "staff_id int NOT NULL AUTO_INCREMENT,"
                    + "staff_name varchar(25) NOT NULL,"
                    + "password varchar(10) NOT NULL,"
                    + "position varchar(11) NOT NULL,"
                    + "PRIMARY KEY(staff_id)"
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
    public boolean CreateStaffAccount(String name, String password,String post){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO staff (staff_name,password,position) VALUES (?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1,name);
            pStmnt.setString(2,password);
            pStmnt.setString(3,post);
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
    public boolean isValidUser(String name, String pwd,String post){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isValid;
        try{
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM staff WHERE staff_name=? and password=? and position = ?";         
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, name);
            pStmnt.setString(2, pwd);
            pStmnt.setString(3,post);
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

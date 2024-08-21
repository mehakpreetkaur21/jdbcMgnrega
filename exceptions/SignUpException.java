/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import classes.UserDAO;
import models.UserModel;

/**
 *
 * @author mehak
 */
public class SignUpException extends Exception {

    Connection con=null;
    PreparedStatement ppst=null;
    UserDAO u=new UserDAO();
    String query=null;
    ResultSet rs=null;
    UserModel um=new UserModel();
    
    public SignUpException(String message) {
        super(message);
    }
   public boolean checkAvailUsername(String username) {
    boolean isAvailable = true;
    Connection con = null;
    PreparedStatement ppst = null;
    ResultSet rs = null;
    
    try {
        con = u.doConnect();
        query = "SELECT * FROM users WHERE username = ?;";
        ppst = con.prepareStatement(query);
        ppst.setString(1, username);
        rs = ppst.executeQuery();
        if (rs.next()) {
            // Username exists
            isAvailable = false;
        }
    } catch (SQLException e) {
        
    } finally {
        // Close resources to prevent leaks
        try {
            if (rs != null) rs.close();
            if (ppst != null) ppst.close();
            if (con != null) con.close();
        } catch (SQLException e) {
        }
    }
    
    return isAvailable;
}

}

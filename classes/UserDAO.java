
package classes;

import Interfaces.UserInterface;
import conn.DbConnection;
import exceptions.LoginException;
import exceptions.SignUpException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import models.UserModel;
import java.sql.ResultSet;
public class UserDAO implements UserInterface {

    Connection con = null;
    PreparedStatement ppst = null;
    String query = null;
    ResultSet rs=null;

    // Method for getting connection
    public Connection doConnect() throws SQLException {
        con = DbConnection.getInstance().getConnection();
        return con;
    }

    public void signUp(UserModel um) throws SignUpException {
        try {
            con = doConnect();
            query = "INSERT INTO users (username, password, phone_no, email, address, role) VALUES (?,?,?,?,?,?);";
            ppst = con.prepareStatement(query);
            ppst.setString(1, um.getUsername());
            ppst.setString(2, um.getPassword());
            ppst.setString(3, um.getPhoneNo());
            ppst.setString(4, um.getEmail());
            ppst.setString(5, um.getAddress());
            ppst.setString(6, um.getRole());

            int result = ppst.executeUpdate(); 

            if (result > 0) {
                System.out.println("User signed up successfully!");
            } else {
                throw new SignUpException("Sign-up failed. Please try again.");
            }
        } catch (SQLException e) {
            throw new SignUpException("Database error: " + e.getMessage());
        } finally {
            try {
                if (ppst != null) ppst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
            }
        }
    }

    @Override
   public UserModel login(String username, String password) throws LoginException {
    UserModel user = null;

    try {
        con = doConnect();
        query = "SELECT * FROM users WHERE username = ?;";
        ppst = con.prepareStatement(query);
        ppst.setString(1, username);

        rs = ppst.executeQuery();

        if (rs.next()) {
            String retrievedPassword = rs.getString("password");
            if (retrievedPassword.equals(password)) {
                user = new UserModel();
                user.setUsername(rs.getString("username"));
                user.setPhoneNo(rs.getString("phone_no"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("address"));
                user.setRole(rs.getString("role"));  // Get the user's role
                System.out.println("Login successful as " + user.getRole() + "!");
            } else {
                throw new LoginException("Incorrect password. Please try again.");
            }
        } else {
            throw new LoginException("Username not found. Please check your username.");
        }

    } catch (SQLException e) {
        throw new LoginException("Database error occurred: " + e.getMessage());
    } finally {
        // Close resources
        try {
            if (rs != null) rs.close();
            if (ppst != null) ppst.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            // Log or handle the exception
        }
    }

    return user;
}

}

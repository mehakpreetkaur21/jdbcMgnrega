/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import Interfaces.BDOInterface;
import conn.DbConnection;
import exceptions.LoginException;
import exceptions.ProjectException;
import java.util.List;
import models.UserModel;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author mehak
 */
public class BDODAO implements BDOInterface{
    private Connection con;
    private PreparedStatement ppst;
    private ResultSet rs;
    private String query;
    UserDAO ud=new UserDAO();

    @Override
    public void createNewGPM(UserModel gpm) throws LoginException {
        throw new LoginException("Not supported yet."); 
    }

  

    @Override
    public List<UserModel> viewAllGPMs() throws LoginException {
        List<UserModel> gpmList = new ArrayList<>();
        try {
            con = DbConnection.getInstance().getConnection();
            query = "SELECT * FROM users WHERE role = 'GPM';";
            ppst = con.prepareStatement(query);
            rs = ppst.executeQuery();
            while (rs.next()) {
                UserModel gpm = new UserModel();
                gpm.setUsername(rs.getString("username"));
                gpm.setPassword(rs.getString("password")); 
                gpm.setPhoneNo(rs.getString("phone_no"));
                gpm.setEmail(rs.getString("email"));
                gpm.setAddress(rs.getString("address"));
                gpm.setRole(rs.getString("role"));
                gpmList.add(gpm);
            }
        } catch (SQLException e) {
            throw new LoginException("Database error: " + e.getMessage());
        } finally {
            closeResources();
        }
        return gpmList;
    }

    private void closeResources() {
        try {
            if (rs != null) rs.close();
            if (ppst != null) ppst.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            // Log or handle the exception
        }
    }


  @Override
public void allocateProjectToGPM(int projectId, String gpmUsername) throws ProjectException, LoginException {
    Connection con = null;
    PreparedStatement ppst = null;
    ResultSet rs = null;
    String query;

    try {
        con = ud.doConnect();

        // Check if the GPM exists
        query = "SELECT COUNT(*) FROM users WHERE username = ? AND role = 'GPM'";
        ppst = con.prepareStatement(query);
        ppst.setString(1, gpmUsername);
        rs = ppst.executeQuery();
        if (rs.next() && rs.getInt(1) == 0) {
            throw new LoginException("GPM username not found or not a GPM.");
        }

        // Check if the project exists
        query = "SELECT COUNT(*) FROM projects WHERE project_id = ?";
        ppst = con.prepareStatement(query);
        ppst.setInt(1, projectId);
        rs = ppst.executeQuery();
        if (rs.next() && rs.getInt(1) == 0) {
            throw new ProjectException("Project ID not found.");
        }

        // Allocate the project to the GPM
        query = "UPDATE projects SET allocated_to = ? WHERE project_id = ?";
        ppst = con.prepareStatement(query);
        ppst.setString(1, gpmUsername);
        ppst.setInt(2, projectId);

        int result = ppst.executeUpdate();
        if (result > 0) {
            System.out.println("Project allocated to GPM successfully!");
        } else {
            throw new ProjectException("Failed to allocate project. Please try again.");
        }

    } catch (SQLException e) {
        throw new ProjectException("Database error: " + e.getMessage());
    } finally {
        // Clean up resources
        try {
            if (rs != null) rs.close();
            if (ppst != null) ppst.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            // Log or handle exception
        }
    }
}

    @Override
    public List<UserModel> getAllEmployees() {
    List<UserModel> employees = new ArrayList<>();
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    try {
        con = ud.doConnect();
        String query = "SELECT * FROM users WHERE role = 'Employee';";
        pst = con.prepareStatement(query);
        rs = pst.executeQuery();
        
        while (rs.next()) {
            UserModel employee = new UserModel();
            employee.setUsername(rs.getString("username"));
            employee.setPassword(rs.getString("password")); // or omit if not needed
            employee.setPhoneNo(rs.getString("phone_no"));
            employee.setEmail(rs.getString("email"));
            employee.setAddress(rs.getString("address"));
            employee.setRole(rs.getString("role"));
            employees.add(employee);
        }
    } catch (SQLException e) {
        try {
            throw new SQLException("Database error: " + e.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(BDODAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    } finally {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            // Log or handle exception
        }
    }
    return employees;
}

    public UserModel searchGPM(String username) throws SQLException, LoginException {
    Connection con = null;
    PreparedStatement ppst = null;
    ResultSet rs = null;
    UserModel gpm = null;

    try {
        con = ud.doConnect();

        // Search for the GPM by username
        String searchQuery = "SELECT * FROM users WHERE username = ? AND role = 'GPM'";
        ppst = con.prepareStatement(searchQuery);
        ppst.setString(1, username);

        rs = ppst.executeQuery();

        if (rs.next()) {
            // If GPM is found, create a UserModel object and populate it with the data
            gpm = new UserModel();
            gpm.setUsername(rs.getString("username"));
            gpm.setPhoneNo(rs.getString("phoneNo"));
            gpm.setEmail(rs.getString("email"));
            gpm.setAddress(rs.getString("address"));
            gpm.setRole(rs.getString("role"));
        } else {
            throw new LoginException("GPM with username '" + username + "' not found.");
        }

    } catch (SQLException e) {
        throw new SQLException("Database error: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (ppst != null) ppst.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            // Handle exception
        }
    }

    return gpm;
}
public void deleteGPM(String username) throws SQLException, LoginException {
    Connection con = null;
    PreparedStatement ppst = null;

    try {
        con = ud.doConnect();

        // Check if the GPM exists and has the role of GPM
        String checkQuery = "SELECT username FROM users WHERE username = ? AND role = 'GPM'";
        ppst = con.prepareStatement(checkQuery);
        ppst.setString(1, username);
        ResultSet rs = ppst.executeQuery();

        if (!rs.next()) {
            throw new LoginException("GPM with username " + username + " does not exist.");
        }

        // Delete the GPM
        String deleteQuery = "DELETE FROM users WHERE username = ?";
        ppst = con.prepareStatement(deleteQuery);
        ppst.setString(1, username);

        int rowsAffected = ppst.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("GPM deleted successfully!");
        } else {
            throw new LoginException("Failed to delete the GPM. Please try again.");
        }

    } catch (SQLException e) {
        throw new SQLException("Database error: " + e.getMessage());
    } finally {
        try {
            if (ppst != null) ppst.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            // Handle exception
        }
    }
}

public void updateGPM(UserModel gpm) throws SQLException, LoginException {
    Connection con = null;
    PreparedStatement ppst = null;

    try {
        con = ud.doConnect();

        // Check if the GPM exists and has the role of GPM
        String checkQuery = "SELECT username FROM users WHERE username = ? AND role = 'GPM'";
        ppst = con.prepareStatement(checkQuery);
        ppst.setString(1, gpm.getUsername());
        ResultSet rs = ppst.executeQuery();

        if (!rs.next()) {
            throw new LoginException("GPM with username " + gpm.getUsername() + " does not exist.");
        }

        // Update the GPM details
        String updateQuery = "UPDATE users SET phoneNo = ?, email = ?, address = ? WHERE username = ?";
        ppst = con.prepareStatement(updateQuery);
        ppst.setString(1, gpm.getPhoneNo());
        ppst.setString(2, gpm.getEmail());
        ppst.setString(3, gpm.getAddress());
        ppst.setString(4, gpm.getUsername());

        int rowsAffected = ppst.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("GPM updated successfully!");
        } else {
            throw new LoginException("Failed to update the GPM. Please try again.");
        }

    } catch (SQLException e) {
        throw new SQLException("Database error: " + e.getMessage());
    } finally {
        try {
            if (ppst != null) ppst.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            // Handle exception
        }
    }
}

}

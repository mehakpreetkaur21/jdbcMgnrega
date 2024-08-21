/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import Interfaces.GPMInterface;
import exceptions.LoginException;
import java.util.List;
import models.UserModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author mehak
 */
public class GPMDAO implements GPMInterface {
    UserDAO ud=new UserDAO();
    Connection con = null;
    PreparedStatement ppst = null;

    @Override
   public void createEmployee(UserModel employee) throws LoginException {
        try {
            con = ud.doConnect();
            String empQuery = "INSERT INTO employees (username, project_id, days_worked) VALUES (?, 1, 0)"; // Assuming default days_worked is 0
            ppst = con.prepareStatement(empQuery);
            ppst.setString(1, employee.getUsername());
            
            int result = ppst.executeUpdate();
            
            if (result > 0) {
                System.out.println("Employee created successfully!");
            } else {
                throw new LoginException("Failed to create employee.");
            }
            
        } catch (SQLException e) {
            throw new LoginException("Database error: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (ppst != null) ppst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                // Handle exception
            }
        }
    }

    @Override
    public UserModel viewEmployeeDetails(String username) throws LoginException {
        Connection con = null;
        PreparedStatement ppst = null;
        ResultSet rs = null;
        UserModel employee = null;

        try {
            con = ud.doConnect();
            String query = "SELECT * FROM users WHERE username = ? AND role = 'Employee';";
            ppst = con.prepareStatement(query);
            ppst.setString(1, username);

            rs = ppst.executeQuery();

            if (rs.next()) {
                employee = new UserModel();
                employee.setUsername(rs.getString("username"));
                employee.setPassword(rs.getString("password"));
                employee.setPhoneNo(rs.getString("phone_no"));
                employee.setEmail(rs.getString("email"));
                employee.setAddress(rs.getString("address"));
                employee.setRole(rs.getString("role"));
            } else {
                throw new LoginException("Employee with username " + username + " not found.");
            }
        } catch (SQLException e) {
            throw new LoginException("Database error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ppst != null) ppst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                // Handle exception
            }
        }

        return employee;
    }

    @Override
    public void allocateEmployeeToProject(String username, int projectId) throws LoginException{
    Connection con = null;
    PreparedStatement ppst = null;

    try {
        con = ud.doConnect();
        String checkEmployeeQuery = "SELECT COUNT(*) FROM employees WHERE username = ?";
        ppst = con.prepareStatement(checkEmployeeQuery);
        ppst.setString(1, username);
        ResultSet rs = ppst.executeQuery();
        if (rs.next() && rs.getInt(1) == 0) {
            throw new SQLException("Employee not found.");
        }

        // Allocate project to employee
        String allocateQuery = "UPDATE employees SET project_id = ? WHERE username = ?";
        ppst = con.prepareStatement(allocateQuery);
        ppst.setInt(1, projectId);
        ppst.setString(2, username);

        int result = ppst.executeUpdate();

        if (result > 0) {
            System.out.println("Project allocated successfully!");
        } else {
            throw new SQLException("Failed to allocate project. Please try again.");
        }

    } catch (SQLException e) {
        try {
            throw new SQLException("Database error: " + e.getMessage());
        } catch (SQLException ex) {
            
        }
    } finally {
        try {
            if (ppst != null) ppst.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            // Handle exception
        }
    }
}


    @Override
    public List<String> viewEmployeeWork(String employeeUsername) throws LoginException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}

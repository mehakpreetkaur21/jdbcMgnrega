///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package classes;
//
///**
// *
// * @author mehak
// */
package classes;

import Interfaces.ProjectInterface;
import conn.DbConnection;
import exceptions.ProjectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.ProjectModel;

public class ProjectDAO implements ProjectInterface {
    Connection con = null;
    PreparedStatement ppst = null;
    ResultSet rs = null;
    String query = null;

    // Method for getting connection
    public Connection doConnect() throws SQLException {
        con = DbConnection.getInstance().getConnection();
        return con;
    }

@Override
public void createProject(ProjectModel project) throws ProjectException {
    try {
        con = doConnect();
        query = "INSERT INTO projects (project_name, description, status) VALUES (?, ?, ?);";
        ppst = con.prepareStatement(query);
        ppst.setString(1, project.getProjectName());
        ppst.setString(2, project.getDescription());
        ppst.setString(3, project.getStatus());  // Ensure this line is correct

        int result = ppst.executeUpdate();

        if (result > 0) {
            System.out.println("Project created successfully!");
        } else {
            throw new ProjectException("Failed to create project. Please try again.");
        }
    } catch (SQLException e) {
        throw new ProjectException("Database error: " + e.getMessage());
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
    public ProjectModel viewProject(int projectId) throws ProjectException {
        ProjectModel project = null;
        try {
            con = doConnect();
            query = "SELECT * FROM projects WHERE project_id = ?;";
            ppst = con.prepareStatement(query);
            ppst.setInt(1, projectId);
            rs = ppst.executeQuery();

            if (rs.next()) {
                project = new ProjectModel();
                project.setProjectId(rs.getInt("project_id"));
                project.setProjectName(rs.getString("project_name"));
                project.setDescription(rs.getString("description"));
                project.setStatus(rs.getString("status"));
            } else {
                throw new ProjectException("Project not found.");
            }
        } catch (SQLException e) {
            throw new ProjectException("Database error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ppst != null) ppst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                // Log or handle exception
            }
        }
        return project;
    }
   @Override
    public void updateProject(int projectId, String newName, String newDescription, String newStatus) throws ProjectException {
    Connection con = null;
    PreparedStatement ppst = null;

    try {
        con = doConnect();

        // Check if the project exists
        String checkProjectQuery = "SELECT COUNT(*) FROM projects WHERE project_id = ?";
        ppst = con.prepareStatement(checkProjectQuery);
        ppst.setInt(1, projectId);
        ResultSet rs = ppst.executeQuery();
        if (rs.next() && rs.getInt(1) == 0) {
            try {
                throw new ProjectException("Project not found.");
            } catch (ProjectException ex) {
                Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Update the project details
        String updateQuery = "UPDATE projects SET project_name = ?, description = ?, status = ? WHERE project_id = ?";
        ppst = con.prepareStatement(updateQuery);
        ppst.setString(1, newName);
        ppst.setString(2, newDescription);
        ppst.setString(3, newStatus);
        ppst.setInt(4, projectId);

        int result = ppst.executeUpdate();

        if (result > 0) {
            System.out.println("Project updated successfully!");
        } else {
            throw new SQLException("Failed to update the project. Please try again.");
        }

    } catch (SQLException e) {
        try {
            throw new SQLException("Database error: " + e.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    public void deleteProject(int projectId) throws ProjectException {
        try {
            con = doConnect();
            query = "DELETE FROM projects WHERE project_id = ?;";
            ppst = con.prepareStatement(query);
            ppst.setInt(1, projectId);

            int result = ppst.executeUpdate();

            if (result > 0) {
                System.out.println("Project deleted successfully!");
            } else {
                throw new ProjectException("Failed to delete project. Please try again.");
            }
        } catch (SQLException e) {
            throw new ProjectException("Database error: " + e.getMessage());
        } finally {
            try {
                if (ppst != null) ppst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                // Log or handle exception
            }
        }
    }

 
    @Override
    public List<ProjectModel> getAllProjects() throws ProjectException {
        List<ProjectModel> projects = new ArrayList<>();
        try {
            con = doConnect();
            query = "SELECT * FROM projects;";
            ppst = con.prepareStatement(query);
            rs = ppst.executeQuery();

            while (rs.next()) {
                ProjectModel project = new ProjectModel();
                project.setProjectId(rs.getInt("project_id"));
                project.setProjectName(rs.getString("project_name"));
                project.setDescription(rs.getString("description"));
                project.setStatus(rs.getString("status"));
                projects.add(project);
            }
        } catch (SQLException e) {
            throw new ProjectException("Database error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ppst != null) ppst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                // Log or handle exception
            }
        }
        return projects;
    }

    @Override
    public ProjectModel getProjectById(int projectId) throws ProjectException {
        ProjectModel project = null;
        try {
            con = doConnect();
            query = "SELECT * FROM projects WHERE project_id = ?;";
            ppst = con.prepareStatement(query);
            ppst.setInt(1, projectId);
            rs = ppst.executeQuery();

            if (rs.next()) {
                project = new ProjectModel();
                project.setProjectId(rs.getInt("project_id"));
                project.setProjectName(rs.getString("project_name"));
                project.setDescription(rs.getString("description"));
                project.setStatus(rs.getString("status"));
            } else {
                throw new ProjectException("Project not found.");
            }
        } catch (SQLException e) {
            throw new ProjectException("Database error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ppst != null) ppst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                // Log or handle exception
            }
        }
        return project;
        }
}


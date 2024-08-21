/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author mehak
 */

public class ProjectModel {
    private int projectId;
    private String projectName;
    private String description;
    private String status;

    // Default constructor
    public ProjectModel() {
    }

    // Parameterized constructor
    public ProjectModel(int projectId, String projectName, String description, String status) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.description = description;
        this.status = status;
    }

    // Getters and Setters
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   @Override
public String toString() {
    return """
           Project Details:
           -----------------
           Project ID   : """ + projectId + "\n" +
           "Project Name : " + projectName + "\n" +
           "Description  : " + description + "\n" +
           "Status       : " + (status != null ? status : "Not Set") + "\n";
}

 
}

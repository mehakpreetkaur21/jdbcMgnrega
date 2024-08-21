/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

/**
 *
 * @author mehak
 */

import models.ProjectModel;
import exceptions.ProjectException;
import java.util.List;

public interface ProjectInterface {
    
    // Method to create a new project
    void createProject(ProjectModel project) throws ProjectException;
    
    // Method to view a project by ID
    ProjectModel viewProject(int projectId) throws ProjectException;
    
    // Method to update an existing project
    void updateProject(ProjectModel project) throws ProjectException;
    
    // Method to delete a project by ID
    void deleteProject(int projectId) throws ProjectException;
    
    ProjectModel getProjectById(int projectId) throws ProjectException;
    // Method to get all projects
    List<ProjectModel> getAllProjects() throws ProjectException;
}


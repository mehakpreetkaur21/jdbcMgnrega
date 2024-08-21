/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

/**
 *
 * @author mehak
 */
import models.UserModel;
import exceptions.LoginException;
import java.util.List;
public interface GPMInterface {
    void createEmployee(UserModel employee) throws LoginException;
    UserModel viewEmployeeDetails(String username) throws LoginException;
    void allocateEmployeeToProject(String employeeUsername, int projectId) throws LoginException;
    List<String> viewEmployeeWork(String employeeUsername) throws LoginException;
}

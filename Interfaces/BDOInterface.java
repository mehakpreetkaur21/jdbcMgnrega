/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

/**
 *
 * @author mehak
 */

import models.ProjectModel;
import models.UserModel;
import exceptions.ProjectException;
import exceptions.LoginException;

import java.util.List;

public interface BDOInterface {

    // GPM-related methods
    void createNewGPM(UserModel gpm) throws LoginException;
    List<UserModel> viewAllGPMs() throws LoginException;

    // Project allocation and employee methods
    void allocateProjectToGPM(int projectId, String gpmUsername) throws ProjectException, LoginException;
    List<UserModel> getAllEmployees() throws LoginException;
}


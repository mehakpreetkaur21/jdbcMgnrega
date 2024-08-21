/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import exceptions.LoginException;
import exceptions.SignUpException;
import models.UserModel;

/**
 *
 * @author mehak
 */
public interface UserInterface {
    public void signUp(UserModel um) throws SignUpException;
    public UserModel login(String username, String password) throws LoginException;

}

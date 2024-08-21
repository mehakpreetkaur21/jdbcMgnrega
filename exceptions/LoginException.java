/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 *
 * @author mehak
 */
public class LoginException extends Exception {
 
    public LoginException(String message) {
        super(message);
    }
    public boolean checkUserExistence(String username) throws LoginException{
        boolean doExists=new SignUpException("").checkAvailUsername(username);
        
        return doExists;
    }
    }


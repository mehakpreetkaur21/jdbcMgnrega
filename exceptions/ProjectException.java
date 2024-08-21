/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 *
 * @author mehak
 */

public class ProjectException extends Exception {
    
    public ProjectException(String message) {
        super(message);
    }
    
    public ProjectException(String message, Throwable cause) {
        super(message, cause);
    }
}


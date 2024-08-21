///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */


package mgneraapp;

import classes.GPMDAO;
import classes.UserDAO;
import exceptions.LoginException;
import exceptions.SignUpException;
import java.util.Scanner;
import models.UserModel;

public class GPMMenu {
    private final UserModel um = new UserModel();
    private final Scanner sc = new Scanner(System.in);
    private final UserDAO ud = new UserDAO();
    private final GPMDAO gd = new GPMDAO();
    private final SignUpException se = new SignUpException("");
    private final LoginException le = new LoginException("");

//    public void signup() {
//        boolean isValidUsername = false;
//
//        while (!isValidUsername) {
//            System.out.println("Enter the username:");
//            String username = sc.nextLine();
//            if (se.checkAvailUsername(username)) {
//                um.setUsername(username);
//                isValidUsername = true;
//            } else {
//                System.out.println("Username " + username + " is already assigned. Please try with a new username.");
//            }
//        }
//
//        System.out.println("Enter the password:");
//        um.setPassword(sc.nextLine());
//
//        System.out.println("Enter the phone Number:");
//        um.setPhoneNo(sc.nextLine());
//
//        System.out.println("Enter the email id:");
//        um.setEmail(sc.nextLine());
//
//        System.out.println("Enter the address:");
//        um.setAddress(sc.nextLine());
//
//        um.setRole("GPM");
//
//        try {
//            ud.signUp(um);
//        } catch (SignUpException se) {
//            System.out.println(se.getMessage());
//        }
//    }

    
    
    public void login() throws LoginException {
        System.out.println("Enter the username:");
        String username = sc.nextLine();

        System.out.println("Enter the password:");
        String password = sc.nextLine();

        ud.login(username, password);
    }
    
    public void createEmployee() throws LoginException, SignUpException {
    UserModel um = new UserModel();
    boolean isValidUsername = false;

    while (!isValidUsername) {
        System.out.println("Enter the username:");
        String username = sc.nextLine();
        if (se.checkAvailUsername(username)) {
            um.setUsername(username);
            isValidUsername = true;
        } else {
            System.out.println("Username " + username + " is already assigned. Please try with a new username.");
        }
    }

    System.out.println("Enter the password:");
    um.setPassword(sc.nextLine());

    System.out.println("Enter the phone Number:");
    um.setPhoneNo(sc.nextLine());

    System.out.println("Enter the email id:");
    um.setEmail(sc.nextLine());

    System.out.println("Enter the address:");
    um.setAddress(sc.nextLine());

    um.setRole("Employee");
    
    try {
        ud.signUp(um);
        gd.createEmployee(um); 
        System.out.println("Employee created successfully!");
    } catch (LoginException se) {
        System.out.println(se.getMessage());
    }
}
  public void viewEmployeeDetails() {
    System.out.println("Enter the username of the employee to view details:");
    String username = sc.nextLine();

    try {
        UserModel employee = gd.viewEmployeeDetails(username); 
        // Print details in a readable format
        System.out.println("Employee Details:");
        System.out.println("Username: " + employee.getUsername());
        System.out.println("Phone Number: " + employee.getPhoneNo());
        System.out.println("Email: " + employee.getEmail());
        System.out.println("Address: " + employee.getAddress());
        System.out.println("Role: " + employee.getRole());
//         System.out.println("Project ID: " + employee.getProjectId());
//         System.out.println("Days Worked: " + employee.getDaysWorked());
    } catch (LoginException e) {
        System.out.println("Error: " + e.getMessage());
    }
}


}

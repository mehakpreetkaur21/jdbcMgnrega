///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
// */
///**
// *
// * @author mehak
// */


package mgneraapp;

import exceptions.LoginException;
import exceptions.ProjectException;
import exceptions.SignUpException;
import java.util.Scanner;

public class MgneraApp {
    private int choice = 0;
    private final BDOMenu bdo = new BDOMenu();
    private final GPMMenu gpm = new GPMMenu();

    public int getChoice() {
        return choice;
    }

    public void mainMenu(Scanner sc) {
        System.out.println("""
                           
            ------------- Welcome to MGNREGA Management System ----------------
                           
            1. BDO (Block Development Officer)
            2. GPM (Gram Panchayat Member)
            3. Exit

            Select one option:""");
        choice = sc.nextInt();
        sc.nextLine();  // clear the buffer
    }

    public void bdoMenu(Scanner sc) {
        System.out.println("""
            You selected option 1  
                        
            -------------- Welcome to  BDO Menu-----------------
            1. Signup
            2. Login
            3. Exit to Main Menu

            Select one option:""");
        choice = sc.nextInt();
        sc.nextLine();  
    }

    public void bdoFunctions(Scanner sc) throws ProjectException {
        switch (choice) {
            case 1 -> bdo.signup();
            case 2 -> {
                try {
                    bdo.login();
                    handleBDOMenuOptions(sc);
                } catch (LoginException e) {
                    System.out.println(e.getMessage());
                }
            }
            case 3 -> { /* Return to Main Menu */ }
            default -> {
                System.out.println("Invalid choice! Please select a valid option.");
                bdoMenu(sc);
            }
        }
    }

    private void handleBDOMenuOptions(Scanner sc) throws ProjectException {
        System.out.println("""
                           
            ----------------- Welcome to BDO Functions -----------------
                           
            1. Manage GPM(Gram Panchayat Member)
            2. Manage Projects
            3. Exit to BDO Menu

            Select one option:""");
        choice = sc.nextInt();
        sc.nextLine();  // clear the buffer
        switch (choice) {
            case 1 -> 
                 manageGPM(sc);
            case 2 -> 
                manageProjects(sc);
            case 3 -> 
            {
                System.out.println("Exiting to BDO Menu ....");
                    try{
                   Thread.sleep(2000);
                   bdoMenu(sc);
                   }catch(InterruptedException e){ 
                   }
            }
            default -> {
                System.out.println("Invalid choice! Please select a valid option.");
                handleBDOMenuOptions(sc);
            }
        }
    }

    //manage gpm
    public void manageGPM(Scanner sc) throws ProjectException{
        while(true){
        System.out.println("""
            You selected option 1
                           
            -----------------Manage the Gram Panchayat Member(GPM) -----------------
                           
            1. Create GPM
            2. Search GPM by id
            3. Update GPM
            4. Delete GPM
            5. View All GPM
            6. Exit to BDO Functions Menu
            7. Exit from application

            Select one option:""");
          choice = sc.nextInt();
          switch(choice){
              case 1->
                  bdo.createNewGPM();
              case 2 -> 
                  bdo.searchGPM();
              case 3 -> 
                  bdo.updateGPM();
              case 4 ->
                  bdo.deleteGPM();
               case 5 -> 
                  bdo.viewAllGPMs();
               case 6 -> 
               {   
                   System.out.println("Exiting to BDO Functions  Menu ....");
                    try{
                   Thread.sleep(2000);
                   handleBDOMenuOptions(sc);
                   }catch(InterruptedException e){
                       
                   }
               }
               case 7 ->
               {
                   System.out.println("Thank you for using this app. Goodbye!");
                   System.exit(0);
               }
               default ->{
                    System.out.println("Invalid choice! Please select a valid option.");
                    manageGPM(sc);
               }
          }
        }
         
    }
    
    // manage projects
        public void manageProjects(Scanner sc) throws ProjectException{
            while(true){
        System.out.println("""
                           
            -----------------Manage the Gram Panchayat Member(GPM) -----------------
                           
            1. Create Project
            2. Search Project by id
            3. Update Project
            4. Delete Project
            5. View All Projects
            6. Allocate Project to GPM
            7. Exit to BDO Functions Menu
            8. Exit from the application

            Select one option:""");
          choice = sc.nextInt();
          switch(choice){
              case 1->
                  bdo.createProject();
              case 2 -> 
                  bdo.searchProjectById();
              case 3 -> 
                  bdo.updateProject();
              case 4 ->
                  bdo.deleteProject();
               case 5 -> 
                  bdo.viewAllGPMs();
               case 6 ->
                   bdo.allocateProjectToGPM();
               case 7 ->
               {
                   System.out.println("Exiting to BDO Functions Menu ....");
                   try{
                   Thread.sleep(2000);
                   handleBDOMenuOptions(sc);
                   }catch(InterruptedException e){
                       
                   }
               }
                   
          }
    }
        }
    public void gpmMenu(Scanner sc) {
        System.out.println("""
                           
            ----------------- Welcome to GPM Menu ----------------
                           
            1. Login
            2. Exit to Main Menu

            Select one option:""");
        choice = sc.nextInt();
        sc.nextLine();  // clear the buffer
    }

    public void gpmFunctions(Scanner sc) throws SignUpException {
        switch (choice) {
            case 1 -> {
                try {
                    gpm.login();
                    handleGPMMenuOptions(sc);
                } catch (LoginException e) {
                    System.out.println(e.getMessage());
                }
            }
            case 2 -> { /* Return to Main Menu */ }
            default -> {
                System.out.println("Invalid choice! Please select a valid option.");
                gpmMenu(sc);
            }
        }
    }

    private void handleGPMMenuOptions(Scanner sc) throws LoginException, SignUpException {
        System.out.println("""
                           
            ------------ Manage the GPM Functions -------------------------
                           
            1. Create Employee
            2. View Employee Details
            3. Allocate Employee to a Project
            4. View Employee Work
            5. Exit to GPM Menu

            Select one option:""");
        choice = sc.nextInt();
        sc.nextLine();  // clear the buffer
        switch (choice) {
            case 1 -> 
                gpm.createEmployee();
            case 2 -> 
                gpm.viewEmployeeDetails();
            case 3 -> System.out.println("Allocate Employee to a Project functionality");
            case 4 -> System.out.println("View Employee Work functionality");
            case 5 -> gpmMenu(sc);
            default -> {
                System.out.println("Invalid choice! Please select a valid option.");
                handleGPMMenuOptions(sc);
            }
        }
    }
}

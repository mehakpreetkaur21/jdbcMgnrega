/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mgneraapp;

import exceptions.ProjectException;
import exceptions.SignUpException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ProjectException, SignUpException  {
        Scanner sc = new Scanner(System.in);
        MgneraApp mg = new MgneraApp();

        int choice = 0;
        while (choice != 3) {
            mg.mainMenu(sc);
            choice = mg.getChoice();
            switch (choice) {
                case 1 -> {
                    mg.bdoMenu(sc);
                    mg.bdoFunctions(sc);
                }
                case 2 -> {
                    mg.gpmMenu(sc);
                    mg.gpmFunctions(sc);
                }
                case 3 -> System.out.println("Thank you for using this app. Goodbye!");
                default -> System.out.println("Invalid choice! Please select a valid option.");
            }
        }
        sc.close();
    }
}

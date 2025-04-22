/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poe;

import static com.mycompany.poe.Login.registerCellPhoneNumber;
import static com.mycompany.poe.Login.registerPassword;
import static com.mycompany.poe.Login.registerUserName;
import javax.swing.JOptionPane;

/**
 *
 * @author lab_services_student
 */
public class POE {

    public static void main(String[] args) {
        
        //prompt user to enter their first name and last name
        String firstName = JOptionPane.showInputDialog(null,"Enter your first name","FirstName",
                JOptionPane.QUESTION_MESSAGE);
        
        String lastName = JOptionPane.showInputDialog(null,"Enter your last name","Last Name",
                JOptionPane.QUESTION_MESSAGE);
        
        // Login class calls 
        Login.registerUser();
        Login.checkUserName (registerUserName);
        Login.checkPasswordComplexity (registerPassword);
        Login.checkCellPhoneNumber (registerCellPhoneNumber);
        Login.returningLoginStatus();
        Login.loginUser();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poe;

import static com.mycompany.poe.Login.returningLoginStatus;
import javax.swing.JOptionPane;

/**
 *
 * @author lab_services_student
 */
public class Login {
   
   public static String messages;
   public static String firstName;
   public static String lastName;
   public static String registerUserName;
   public static String registerPassword;
   public static String registerCellPhoneNumber;
   public static String loginUserName;
   public static String loginPassword;
   public static String LoginStatus;
   public static String returningLoginStatus;

   
   public static boolean checkUserName(String registerUserName){
       //returning conditions for username
       return registerUserName != null && 
               !registerUserName.isEmpty()&&
               registerUserName.contains("_")&&
               registerUserName.length() <= 5;
   }
   
   public static boolean checkPasswordComplexity(String registerPassword){
       //returning conditions for password 
       return registerPassword != null &&
               !registerPassword.isEmpty()&&
               registerPassword.matches(".*\\w.*")&&
               registerPassword.matches(".*[a-z].*")&&
               registerPassword.matches(".*[A-Z].*")&&
               registerPassword.matches(".*[0-9].*")&&
               registerPassword.length() >=8;
   }
   
  public static boolean checkCellPhoneNumber(String registerCellPhoneNumber){
      //returning conditions for Number
      return registerCellPhoneNumber != null &&
              !registerCellPhoneNumber.isEmpty()&&
              registerCellPhoneNumber.matches("\\+27\\d{9}")&&
              registerCellPhoneNumber.length() ==12;

  }
  public static void registerNumber (){
      //prompt user to create number to register with 
       registerCellPhoneNumber  = JOptionPane.showInputDialog(null,"Enter SA phone number (+27XXXXXXXXX):",
               "Register Number" , JOptionPane.QUESTION_MESSAGE);
       //IF statement 
       if(checkCellPhoneNumber(registerCellPhoneNumber)) {
            JOptionPane.showMessageDialog(null, "Valid SA number!");
        }else{
            JOptionPane.showMessageDialog(null, "Invalid phone number.It must start with +27 and contain 9 digits");
        return;
        
       }
  }
  public static void registerUser(){
     //Prompt user to create username to register with 
     registerUserName = JOptionPane.showInputDialog(null, "Please create your Username", "Register Username");
     //IF statement
     if(checkUserName(registerUserName)){
         JOptionPane.showMessageDialog(null,"Username successfully captured");
     }else{
         JOptionPane.showMessageDialog(null,"Username not correctly formatted, please ensure that your username contains '_' and be 5 characters or less.");
     return;
       }
     
     //Do while Loop 
     do{ //
     // prompt user to create password and register with it 
         registerPassword = JOptionPane.showInputDialog(null, "Please create your password", "Register Password");
    if(checkPasswordComplexity(registerPassword)) {
         JOptionPane.showMessageDialog(null,"Password succesfully captured");
   }else{
        JOptionPane.showMessageDialog(null,"Password is not correctly formatted, please ensure that the password contains a capital letter , number ,special character and be 8 characters or more.");
    }
  }
   // WHILE the conditions are not met it loops back 
   while( checkPasswordComplexity(registerPassword));
   // message displaying that the conditons have not been met 
       JOptionPane.showMessageDialog(null, "The two above conditions have been met and the user has been registered");
       return;
 }
  
  public static boolean loginUser(){
    //returning the conditions 
    return registerUserName.equals(loginUserName)&&
            registerPassword.equals(loginPassword);
  }
  
  public static String returningLoginStatus() {
      //prompt user to enter username 
      loginUserName = JOptionPane.showInputDialog(null,"Enter your Username",
              "Login Username" , JOptionPane.QUESTION_MESSAGE);
      //prompt user to enter password 
      loginPassword = JOptionPane.showInputDialog(null, "Enter your password",
              "Login Password" , JOptionPane.QUESTION_MESSAGE);
      
      //IF statement 
      if(loginUser()) {
          String message = "Welcome" + firstName +" "+ lastName + " , it's great to see you again.";
          JOptionPane.showMessageDialog(null, message , "Login Succesful" , JOptionPane.INFORMATION_MESSAGE);
          return message;
      }else{
          String message = "Username or password incorrect, please try again.";
          JOptionPane.showMessageDialog(null, message , "Login Failed", JOptionPane.INFORMATION_MESSAGE);
return returningLoginStatus;
   }
  }
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poe;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lab_services_student
 */
public class Message {
    private String messageID;
    private String recipient;
    private String content;
    private String messageHash;
    private static int totalSent = 0;
    private static List<Message> messages = new ArrayList<>();

    public boolean checkMessageID(String id) {
        return id.length() == 10;
    }

    public int checkRecipientCell(String number) {
        return (number.startsWith("+") && number.length() == 10) ? 1 : 0;
    }

    public String createMessageHash() {
        String[] words = this.content.split(" ");
        String first = words[0].toUpperCase();
        String last = words[words.length-1].toUpperCase();
        return this.messageID.substring(0, 2) + ":" + totalSent + "-" + first + last;
    }

    public String sentMessage(int choice) {
        switch(choice) {
            case 1: 
                totalSent++;
                return "Message successfully sent.";
            case 2: 
                return "Press 0 to delete message.";
            case 3: 
                storeMessage();
                return "Message successfully stored.";
            default: return "Invalid choice";
        }
    }

    private void storeMessage() {
        messages.add(this);
        new Gson().toJson(messages); // Save to JSON file (add file handling)
    }

    public String getMessageID() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setMessageID(String format) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Getters and setters

    private static class Gson {

        public Gson() {
        }

        private void toJson(List<Message> messages) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
}

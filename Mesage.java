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
public class Mesage {
    
  public class Message {
    private String recipient;
    private String text;
    private String id;
    private String hash;

    public Message(String recipient, String text, String id, String hash) {
        this.recipient = recipient;
        this.text = text;
        this.id = id;
        this.hash = hash;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getText() {
        return text;
    }

    public String getId() {
        return id;
    }

    public String getHash() {
        return hash;
    }
}
 
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ros2.rcljava.havelsanros2.models;

/**
 *
 * @author asimkaymak
 */
public class Action {

    private String sender_id;
    private String action_name;
    private String action;
    private String action_properties;
    private long date;

    public Action(String sender_id, String action_name, String action, String action_properties, long date) {
        this.sender_id = sender_id;
        this.action_name = action_name;
        this.action = action;
        this.action_properties = action_properties;
        this.date = date;
    }
    
    public Action() {};

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public String getAction_name() {
        return action_name;
    }

    public void setAction_name(String action_name) {
        this.action_name = action_name;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAction_properties() {
        return action_properties;
    }

    public void setAction_properties(String action_properties) {
        this.action_properties = action_properties;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
    
    

}

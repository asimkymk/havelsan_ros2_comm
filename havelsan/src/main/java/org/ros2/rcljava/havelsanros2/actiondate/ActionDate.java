/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ros2.rcljava.havelsanros2.actiondate;

/**
 *
 * @author asimkaymak
 */
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Date;

public class ActionDate {

    public static long dateToLong() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmmddMMyyyy");
        LocalDateTime now = LocalDateTime.now();
        return Long.parseLong(dtf.format(now));
    }
    
    public static String longToStringDate(long longDate){
        String converted_date = String.valueOf(longDate);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime custom_date;
        if(converted_date.length() == 12){
            custom_date = LocalDateTime.of(Integer.parseInt(converted_date.substring(8,12)),
                                            Integer.parseInt(converted_date.substring(6, 8)),
                                             Integer.parseInt(converted_date.substring(4, 6)),
                                             Integer.parseInt(converted_date.substring(0, 2)),
                                             Integer.parseInt(converted_date.substring(2,4)));
        }
        else{
            custom_date = LocalDateTime.of(Integer.parseInt(converted_date.substring(7,11)),
                                            Integer.parseInt(converted_date.substring(5, 7)),
                                             Integer.parseInt(converted_date.substring(3, 5)),
                                             Integer.parseInt(converted_date.substring(0, 1)),
                                             Integer.parseInt(converted_date.substring(1,3)));
        }
        return dtf.format(custom_date);
    }
    

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package org.ros2.rcljava.havelsanros2.actionpub;

import org.ros2.rcljava.havelsanros2.actiondate.ActionDate;

import java.util.concurrent.TimeUnit;
import java.util.Scanner;

import org.ros2.rcljava.RCLJava;
import org.ros2.rcljava.concurrent.Callback;
import org.ros2.rcljava.node.BaseComposableNode;
import org.ros2.rcljava.publisher.Publisher;
import org.ros2.rcljava.timer.WallTimer;
/**
 *
 * @author asimkaymak
 */
public class ActionPublisher extends BaseComposableNode {



    private Publisher<havelsan_msgs.msg.Action> publisher;

    private WallTimer timer;
    private String identity;

    public ActionPublisher() {
        super("minimal_publisher");
        this.identity = "client_1";
        // Publishers are type safe, make sure to pass the message type
        this.publisher = node.<havelsan_msgs.msg.Action>createPublisher(havelsan_msgs.msg.Action.class, "action");
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        
        
        Callback timerCallback = () -> {
            havelsan_msgs.msg.Action message = new havelsan_msgs.msg.Action();
            int record_type  = 0;
            int command_type = 0;
            while(record_type != 1 && record_type != 2){
                System.out.println("Lutfen kayit tipi seciniz :\n1- Konsol Kayıt\n2- Monitör Kayıt\n");
                record_type = scanner.nextInt();
            }

            while(command_type != 1 && command_type != 2 && command_type != 3){
                System.out.println("Lutfen islemi seciniz :\n1- Kayit Baslat\n2- Kayit Durdur\n3- Kayit Kaydet\n");
                command_type = scanner.nextInt();
            }
            

            if(record_type == 1){
                
                message.setActionName("console");
            }
            else if (record_type == 2){
                
                message.setActionName("display");
            }
            
            if(command_type == 1){
                message.setAction("start");
                
            }
            else if (command_type == 2){
                
                message.setAction("stop");
            }
            else if(command_type == 3){
                message.setAction("save");
                
            }
            System.out.println("Kayit özelliklerinizi giriniz : \n");
            String action_properties;
            scanner.nextLine();
            action_properties = scanner.nextLine();
            message.setSenderId(this.identity);
            message.setDate(ActionDate.dateToLong());
            message.setActionProperties(action_properties);
            
            
            //System.out.println("Mesaj : [" + message.getData() + "]");
            this.publisher.publish(message);
        };
        this.timer = node.createWallTimer(500, TimeUnit.MILLISECONDS, timerCallback);
    }

    public static void main(String[] args) throws InterruptedException {
        // Initialize RCL
        RCLJava.rclJavaInit();

        RCLJava.spin(new ActionPublisher());
    }
}

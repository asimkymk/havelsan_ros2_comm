/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package org.ros2.rcljava.havelsanros2.havelsanpub;

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
public class HavelsanPublisher extends BaseComposableNode {



    private Publisher<std_msgs.msg.String> publisher;

    private WallTimer timer;

    public HavelsanPublisher() {
        super("minmal_publisher");
        // Publishers are type safe, make sure to pass the message type
        this.publisher = node.<std_msgs.msg.String>createPublisher(std_msgs.msg.String.class, "topic");
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        
        Callback timerCallback = () -> {
            std_msgs.msg.String message = new std_msgs.msg.String();
            System.out.println("Lutfen islemi seciniz :\n1- Kayit Baslat\n2- Kayit Durdur\n3- Çık\n");
            
            String input = scanner.nextLine();

            if(input == "3"){
                System.out.println("ok");
            }
            else if (input == "2"){
                message.setData("dur");
            }
            else if(input == "1"){
                message.setData("baslat");
            }
            
            
            System.out.println("Mesaj : [" + message.getData() + "]");
            this.publisher.publish(message);
        };
        this.timer = node.createWallTimer(500, TimeUnit.MILLISECONDS, timerCallback);
    }

    public static void main(String[] args) throws InterruptedException {
        // Initialize RCL
        RCLJava.rclJavaInit();

        RCLJava.spin(new HavelsanPublisher());
    }
}

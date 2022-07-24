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

    private int count;

    private Publisher<std_msgs.msg.String> publisher;

    private WallTimer timer;

    public HavelsanPublisher() {
        super("minmal_publisher");
        this.count = 0;
        // Publishers are type safe, make sure to pass the message type
        this.publisher = node.<std_msgs.msg.String>createPublisher(std_msgs.msg.String.class, "topic");
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        
        Callback timerCallback = () -> {
            std_msgs.msg.String message = new std_msgs.msg.String();

            String userName = myObj.nextLine();
            message.setData("Havelsan Test " + userName);
            this.count++;
            System.out.println("Publishing: [" + message.getData() + "]");
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

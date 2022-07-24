/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package org.ros2.rcljava.havelsanros2.storagepub;

import java.util.concurrent.TimeUnit;
import java.io.File;

import org.ros2.rcljava.RCLJava;
import org.ros2.rcljava.concurrent.Callback;
import org.ros2.rcljava.node.BaseComposableNode;
import org.ros2.rcljava.publisher.Publisher;
import org.ros2.rcljava.timer.WallTimer;
/**
 *
 * @author asimkaymak
 */
public class StoragePublisher extends BaseComposableNode {
    private Publisher<std_msgs.msg.UInt64> publisher;

    private WallTimer timer;
    private File diskPartition;

    public StoragePublisher() {
        super("minmal_publisher");
        // Publishers are type safe, make sure to pass the message type
        this.publisher = node.<std_msgs.msg.UInt64>createPublisher(std_msgs.msg.UInt64.class, "storage");
        this.diskPartition = new File("/"); //windows C:
        Callback timerCallback = () -> {
            
 
            //long totalCapacity = diskPartition.getTotalSpace(); 
 
            //long freePartitionSpace = diskPartition.getFreeSpace(); 
            long usablePatitionSpace = diskPartition.getUsableSpace(); 
            //String s =String.valueOf(usablePatitionSpace);
            std_msgs.msg.UInt64 message = new std_msgs.msg.UInt64();

            
            message.setData(usablePatitionSpace);
            System.out.println("Kalan kullanılabilir boş alan (pub) : [" + message.getData() + "]");
            this.publisher.publish(message);
        };
        this.timer = node.createWallTimer(500, TimeUnit.MILLISECONDS, timerCallback);
    }

    public static void main(String[] args) throws InterruptedException {
        // Initialize RCL
        RCLJava.rclJavaInit();

        RCLJava.spin(new StoragePublisher());
    }
}

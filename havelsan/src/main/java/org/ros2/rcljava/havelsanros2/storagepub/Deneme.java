/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package org.ros2.rcljava.havelsanros2.storagepub;

import java.util.concurrent.TimeUnit;
import java.io.File;
import java.util.List;
import java.util.Arrays;

import org.ros2.rcljava.RCLJava;
import org.ros2.rcljava.concurrent.Callback;
import org.ros2.rcljava.node.BaseComposableNode;
import org.ros2.rcljava.publisher.Publisher;
import org.ros2.rcljava.timer.WallTimer;
/**
 *
 * @author asimkaymak
 */
public class Deneme extends BaseComposableNode {
    private Publisher<havelsan_msgs.msg.Status> publisher;

    private WallTimer timer;
    private File diskPartition;

    public Deneme() {
        super("minmal_publisher");
        // Publishers are type safe, make sure to pass the message type
        this.publisher = node.<havelsan_msgs.msg.Status>createPublisher(havelsan_msgs.msg.Status.class, "storage");
        this.diskPartition = new File("/"); //windows C:
        Callback timerCallback = () -> {
            
 
            long totalCapacity = diskPartition.getTotalSpace(); 
 
            long freePartitionSpace = diskPartition.getFreeSpace(); 
            long usablePatitionSpace = diskPartition.getUsableSpace(); 
            //String s =String.valueOf(usablePatitionSpace);
            havelsan_msgs.msg.Status message = new havelsan_msgs.msg.Status();

            message.setTotalCapacity(totalCapacity);
            message.setFreePartitionSpace(freePartitionSpace);
            message.setUsablePartitionSpace(usablePatitionSpace);
            //List<Integer> arrays = new ArrayList<Byte>(1,0,1,1,1);
            List<Boolean> boolValues = Arrays.asList(new Boolean[] {true, false, true});
            message.setSystemLiveStatus(boolValues);
            //message.setTotalcapacity(totalCapacity);
            //message.setTotalcapacity(totalCapacity);
            //System.out.println("Kalan kullanılabilir boş alan (pub) : [" + message.getData() + "]");
            this.publisher.publish(message);
        };
        this.timer = node.createWallTimer(1000, TimeUnit.MILLISECONDS, timerCallback);
    }

    public static void main(String[] args) throws InterruptedException {
        // Initialize RCL
        RCLJava.rclJavaInit();

        RCLJava.spin(new Deneme());
    }
}

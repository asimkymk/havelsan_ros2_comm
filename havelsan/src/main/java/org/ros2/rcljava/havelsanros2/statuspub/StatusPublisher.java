/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package org.ros2.rcljava.havelsanros2.statuspub;

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
public class StatusPublisher extends BaseComposableNode {
    private Publisher<havelsan_msgs.msg.Status> publisher;

    private WallTimer timer;
    private File diskPartition;

    public StatusPublisher() {
        super("minmal_publisher");
        // Publishers are type safe, make sure to pass the message type
        this.publisher = node.<havelsan_msgs.msg.Status>createPublisher(havelsan_msgs.msg.Status.class, "status");
        this.diskPartition = new File("/"); //windows C:
        Callback timerCallback = () -> {
            havelsan_msgs.msg.Status message = new havelsan_msgs.msg.Status();
            long totalCapacity = diskPartition.getTotalSpace(); 
            long freePartitionSpace = diskPartition.getFreeSpace(); 
            long usablePatitionSpace = diskPartition.getUsableSpace();

            message.setTotalCapacity(totalCapacity);
            message.setFreePartitionSpace(freePartitionSpace);
            message.setUsablePartitionSpace(usablePatitionSpace);
            List<Boolean> systemLiveStatus = Arrays.asList(new Boolean[] {true, true, true}); // sistem canlılık
            List<Boolean> consoleRecordStatus = Arrays.asList(new Boolean[] {true, false, true}); // konsol durum
            List<Boolean> displayRecordStatus = Arrays.asList(new Boolean[] {true, false, false}); // display durum
            message.setSystemLiveStatus(systemLiveStatus);
            message.setConsoleRecordStatus(consoleRecordStatus);
            message.setDisplayRecordStatus(displayRecordStatus);
            this.publisher.publish(message);
        };
        this.timer = node.createWallTimer(1000, TimeUnit.MILLISECONDS, timerCallback);
    }

    public static void main(String[] args) throws InterruptedException {
        // Initialize RCL
        RCLJava.rclJavaInit();

        RCLJava.spin(new StatusPublisher());
    }
}

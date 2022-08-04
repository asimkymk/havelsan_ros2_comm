/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package org.ros2.rcljava.havelsanros2.testCases;

import java.util.concurrent.TimeUnit;
import java.io.File;
import java.util.List;
import java.util.Arrays;
import org.ros2.rcljava.havelsanros2.statuspub.StatusPublisher;
import org.ros2.rcljava.RCLJava;
import org.ros2.rcljava.concurrent.Callback;
import org.ros2.rcljava.publisher.Publisher;
import org.ros2.rcljava.node.Node;
/**
 *
 * @author asimkaymak
 */
public class TestCases {
    
    public static publishTest(){
        File diskPartition = new File("/");
        RCLJava.rclJavaInit();
        Node node = RCLJava.createNode("test_node");
        // Initialize RCL
        Publisher<havelsan_msgs.msg.Status> publisher = node.<havelsan_msgs.msg.Status>createPublisher(havelsan_msgs.msg.Status.class, "status");
        long totalCapacity = diskPartition.getTotalSpace(); 
        long freePartitionSpace = diskPartition.getFreeSpace(); 
        long usablePatitionSpace = diskPartition.getUsableSpace();
        havelsan_msgs.msg.Status message = new havelsan_msgs.msg.Status();
        message.setTotalCapacity(totalCapacity);
        message.setFreePartitionSpace(freePartitionSpace);
        message.setUsablePartitionSpace(usablePatitionSpace);
        List<Boolean> systemLiveStatus = Arrays.asList(new Boolean[] {true, true, true}); // sistem canlılık
        List<Boolean> consoleRecordStatus = Arrays.asList(new Boolean[] {true, false, true}); // konsol durum
        List<Boolean> displayRecordStatus = Arrays.asList(new Boolean[] {true, false, false}); // display durum
        message.setSystemLiveStatus(systemLiveStatus);
        message.setConsoleRecordStatus(consoleRecordStatus);
        message.setDisplayRecordStatus(displayRecordStatus);
     
        publisher.publish(message);
        publisher.dispose();
    }
    public static void main(String[] args) throws InterruptedException {
        
        File diskPartition = new File("/");
        RCLJava.rclJavaInit();
        Node node = RCLJava.createNode("test_node");
        // Initialize RCL
        Publisher<havelsan_msgs.msg.Status> publisher = node.<havelsan_msgs.msg.Status>createPublisher(havelsan_msgs.msg.Status.class, "status");
        long totalCapacity = diskPartition.getTotalSpace(); 
        long freePartitionSpace = diskPartition.getFreeSpace(); 
        long usablePatitionSpace = diskPartition.getUsableSpace();
        havelsan_msgs.msg.Status message = new havelsan_msgs.msg.Status();
        message.setTotalCapacity(totalCapacity);
        message.setFreePartitionSpace(freePartitionSpace);
        message.setUsablePartitionSpace(usablePatitionSpace);
        List<Boolean> systemLiveStatus = Arrays.asList(new Boolean[] {true, true, true}); // sistem canlılık
        List<Boolean> consoleRecordStatus = Arrays.asList(new Boolean[] {true, false, true}); // konsol durum
        List<Boolean> displayRecordStatus = Arrays.asList(new Boolean[] {true, false, false}); // display durum
        message.setSystemLiveStatus(systemLiveStatus);
        message.setConsoleRecordStatus(consoleRecordStatus);
        message.setDisplayRecordStatus(displayRecordStatus);
     
        publisher.publish(message);
        publisher.dispose();
            

    }
}

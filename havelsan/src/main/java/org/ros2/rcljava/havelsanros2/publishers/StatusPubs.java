/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package org.ros2.rcljava.havelsanros2.publishers;

import org.ros2.rcljava.havelsanros2.models.Status;
import java.io.File;
import java.util.List;
import java.util.Arrays;
import org.ros2.rcljava.RCLJava;
import org.ros2.rcljava.concurrent.Callback;
import org.ros2.rcljava.publisher.Publisher;
import org.ros2.rcljava.node.Node;

/**
 *
 * @author asimkaymak
 */
public class StatusPubs {
    
    public void publishTest(Status status){
        
        RCLJava.rclJavaInit();
        Node node = RCLJava.createNode("test_node");
        // Initialize RCL
        Publisher<havelsan_msgs.msg.Status> publisher = node.<havelsan_msgs.msg.Status>createPublisher(havelsan_msgs.msg.Status.class, "status");
        
        havelsan_msgs.msg.Status message = new havelsan_msgs.msg.Status();
        message.setTotalCapacity(status.getTotal_capacity());
        message.setFreePartitionSpace(status.getFree_partition_space());
        message.setUsablePartitionSpace(status.getUsable_partition_space());
        message.setSystemLiveStatus(status.getSystem_live_status());
        message.setConsoleRecordStatus(status.getConsole_record_status());
        message.setDisplayRecordStatus(status.getDisplay_record_status());
     
        publisher.publish(message);
        publisher.dispose();
    }
    
}

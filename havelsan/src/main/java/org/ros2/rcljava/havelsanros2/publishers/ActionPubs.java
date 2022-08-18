package org.ros2.rcljava.havelsanros2.publishers;

import org.ros2.rcljava.havelsanros2.models.Action;
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
public class ActionPubs {
    
    public void publishTesst(Action action){
        System.out.println("ok");
        /*
        RCLJava.rclJavaInit();
        Node node = RCLJava.createNode("test_node");
        // Initialize RCL
        Publisher<havelsan_msgs.msg.Action> publisher = node.<havelsan_msgs.msg.Action>createPublisher(havelsan_msgs.msg.Action.class, "action");
        
        havelsan_msgs.msg.Action message = new havelsan_msgs.msg.Action();
        message.setActionName(action.getAction_name());
        message.setAction(action.getAction());
        message.setSenderId(action.getSender_id());
        message.setDate(action.getDate());
        message.setActionProperties(action.getAction_properties());
        //message.setEmptyArea(action.getEmpty_area());
        publisher.publish(message);
        publisher.dispose();*/
    }
    
}

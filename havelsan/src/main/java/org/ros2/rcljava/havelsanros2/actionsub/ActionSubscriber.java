package org.ros2.rcljava.havelsanros2.actionsub;

import org.ros2.rcljava.havelsanros2.actiondate.ActionDate;

import org.ros2.rcljava.RCLJava;
import org.ros2.rcljava.consumers.Consumer;
import org.ros2.rcljava.node.BaseComposableNode;
import org.ros2.rcljava.subscription.Subscription;
/**
 *
 * @author asimkaymak
 */
public class ActionSubscriber extends BaseComposableNode {
  private Subscription<havelsan_msgs.msg.Action> subscription;
  public ActionSubscriber() {
    super("minimal_subscriber");
    subscription = node.<havelsan_msgs.msg.Action>createSubscription(havelsan_msgs.msg.Action.class, "action",
        msg -> {
            System.out.println("Gönderen          : " + msg.getSenderId());
            System.out.println("İşlem İsmi        : " + msg.getActionName());
            System.out.println("İşlem             : " + msg.getAction());
            System.out.println("İşlem Özellikleri : " + msg.getActionProperties());
            System.out.println("İşlem Tarih       : " + ActionDate.longToStringDate(msg.getDate()));
            System.out.println("------------------------------------------------");
            
        });
  }

  public static void main(final String[] args) throws InterruptedException, Exception {
    // Initialize RCL
    RCLJava.rclJavaInit();

    RCLJava.spin(new ActionSubscriber());
  }
}


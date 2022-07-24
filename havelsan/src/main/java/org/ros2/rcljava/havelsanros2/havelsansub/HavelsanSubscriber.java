package org.ros2.rcljava.havelsanros2.havelsansub;

import org.ros2.rcljava.RCLJava;
import org.ros2.rcljava.consumers.Consumer;
import org.ros2.rcljava.node.BaseComposableNode;
import org.ros2.rcljava.subscription.Subscription;
/**
 *
 * @author asimkaymak
 */
public class HavelsanSubscriber extends BaseComposableNode {
  private Subscription<std_msgs.msg.String> subscription;

  public HavelsanSubscriber() {
    super("minimal_subscriber");
    subscription = node.<std_msgs.msg.String>createSubscription(std_msgs.msg.String.class, "topic",
        msg -> System.out.println("Bilgi alındı : [" + msg.getData() + "]"));
  }

  public static void main(final String[] args) throws InterruptedException, Exception {
    // Initialize RCL
    RCLJava.rclJavaInit();

    RCLJava.spin(new HavelsanSubscriber());
  }
}


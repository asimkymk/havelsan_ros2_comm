package org.ros2.rcljava.havelsanros2.storagesub;

import org.ros2.rcljava.RCLJava;
import org.ros2.rcljava.consumers.Consumer;
import org.ros2.rcljava.node.BaseComposableNode;
import org.ros2.rcljava.subscription.Subscription;
/**
 *
 * @author asimkaymak
 */
public class StorageSubscriber extends BaseComposableNode {
  private Subscription<std_msgs.msg.UInt64> subscription;

  public StorageSubscriber() {
    super("minimal_subscriber");
    subscription = node.<std_msgs.msg.UInt64>createSubscription(std_msgs.msg.UInt64.class, "storage",
        msg -> System.out.println("Diskte : [" + msg.getData() + "] byte yer kaldı." ));
  }

  public static void main(final String[] args) throws InterruptedException, Exception {
    // Initialize RCL
    RCLJava.rclJavaInit();

    RCLJava.spin(new StorageSubscriber());
  }
}


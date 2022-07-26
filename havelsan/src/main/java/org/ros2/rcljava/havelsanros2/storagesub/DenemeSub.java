package org.ros2.rcljava.havelsanros2.storagesub;

import java.util.List;
import java.util.Arrays;
import java.lang.*;

import org.ros2.rcljava.RCLJava;
import org.ros2.rcljava.consumers.Consumer;
import org.ros2.rcljava.node.BaseComposableNode;
import org.ros2.rcljava.subscription.Subscription;
/**
 *
 * @author asimkaymak
 */
public class DenemeSub extends BaseComposableNode {
  private Subscription<havelsan_msgs.msg.Status> subscription;

  public DenemeSub() {
    super("minimal_subscriber");
    subscription = node.<havelsan_msgs.msg.Status>createSubscription(havelsan_msgs.msg.Status.class, "storage",
        msg -> {
          List<Boolean> boolValues = msg.getSystemLiveStatus();
            System.out.println("Toplam Disk Alanı              : [" + msg.getTotalCapacity() + "] byte." );
            System.out.println("Toplam Boş Alan                : [" + msg.getFreePartitionSpace() + "] byte." );
            System.out.println("Toplam Kullanılabilir Boş Alan : [" + msg.getUsablePartitionSpace() + "] byte." );
            System.out.println("Canlı Sistem Durumu            : " +  Arrays.toString(boolValues.toArray()));
            System.out.println("-------------------------------------------------------------------------------------------");
            }
            
            );
  }

  public static void main(final String[] args) throws InterruptedException, Exception {
    // Initialize RCL
    RCLJava.rclJavaInit();

    RCLJava.spin(new DenemeSub());
  }
}


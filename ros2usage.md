# ROS2 Paket Yüklenmesi


  
## Kurulum

*  [ROS 2 Galactic Kurulum](https://docs.ros.org/en/galactic/Installation.html)

*  [Java Custom Paketi Kurulumu ](https://github.com/ros2-java/ros2_java)

* Havelsan Paketinin Yüklenmesi
    Çalışma alanı içerisinde src klasöründe terminali açınız.
    ```sh
    git clone https://github.com/asimkymk/havelsan_ros2_comm.git
    ```

* Paketlerin Derlenmesi
    Paketleri derlemeden önce klasör yapısını düzeltmemiz gerekiyor. havelsan_ros2_comms klsaörü içerisindeki klasörler /src klasörüne taşımamız gerekiyor. 
    ```sh
    mv ./havelsan_ros2_comm/havelsan ./
    mv ./havelsan_ros2_comm/havelsan_msgs ./
    ```
    Terminali kapatıp çalışma alanı içerisinde yeni bir terminal açınız.
    ```sh
    rosdep install --from-paths src -y -i --skip-keys "ament_tools"
    colcon build --symlink-install --packages-select havelsan_msgs
    colcon build --symlink-install --packages-select havelsan
    ```



## Kullanım
* Mesaj yayınlamak için ui klasöründeki örnek incelenebilir. Açıklamak gerekirse;
Kod içerisinde publishers ve models paketlerinde uygun modeller import edilmeli.

    ```java
    import org.ros2.rcljava.havelsanros2.publishers.StatusPubs;
    import org.ros2.rcljava.havelsanros2.models.Status;
    ```
    Örnek mesaj publish ise şu şekilde:
    ```java
    File diskPartition = new File("/");
    long totalCapacity = diskPartition.getTotalSpace(); 
    long freePartitionSpace = diskPartition.getFreeSpace(); 
    long usablePatitionSpace = diskPartition.getUsableSpace();
    Boolean[] systemLiveStatus = new Boolean[] {true, true, true};
    Boolean[] consoleRecordStatus = new Boolean[] {true, false, true}; // konsol durum
    Boolean[] displayRecordStatus = new Boolean[] {true, true, true};
    Status status = new Status(totalCapacity,freePartitionSpace,usablePatitionSpace,systemLiveStatus,consoleRecordStatus,displayRecordStatus);
    StatusPubs testCases = new StatusPubs();
    testCases.publishTest(status);
    ```
## Test
Çalışma alanı içerisinde;
```sh
ros2 run havelsan status_sub
```
Yeni terminalde
```sh
javac org.ros2.rcljava.havelsanros2.ui.testgui
```
Açılan ekranda butona tıklandığında ilk terminalde mesaj takibi yapılabilir.

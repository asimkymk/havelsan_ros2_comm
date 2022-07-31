# Modüller Arası Haberleşme

ROS2 ve Java ile özel mesaj tipleriyle subscriber ve publisher yazma ve ilgili haberleşmeyi sağlama.



## Hakkında

*   Ros 2 den önce ROS’un temelde ne olduğunu bilmekte fayda var. Açılımı Robot Operating System olan ROS, açılımından da anlayacağımız üzere robot kontrol işlemleri için kullanılan bir yazılımdır. Her ne kadar bir işletim sistemi ifadesi geçse bile aslında ROS robotik işlemler için genel bir framework gibi çalışır. Hazır yapılar ile robot üzerindeki yapılabilecek işlemleri kolaylaştırır. ROS’un tercih edilmesindeki bir diğer neden ise simülasyon ortamıdır. Gazebo ve Rviz gibi arayüz simülasyon programları ile gerçek zamanlı robot hareketleri simüle edilip test edilebilir. Bu sayede de ciddi bir maaliyet yükü azaltılmış olur. ROS, programlama dillerinden bağımsız olarak geliştirilmiştir. Yani geliştirme yapılacak algoritma için programlama dili zorunluluğu yoktur. Fakat resmi olarak yayımlanan dökümantasyonda C++ ve Python için destek verilmiştir. 

*   ROS 2’ye geçecek olursak ROS’daki bazı eksiklikler ROS 2’nin doğmasına sebep olmuştur. ROS’da haberleşme sisteminde herhangi bir güvenlik katmanı bulunmamaktadır. Tüm iletişim düz plain text olarak yani hiç şifrelenmeden düz metin halinde yapılmaktadır. Ağı dinleyen birisi bu iletişimi rahatlıkla takip edebilir. ROS 2’de buna çözüm getirilmiş ve iletişim için bir şifreleme katmanı da eklenmiştir. ROS resmi olarak sadece Linux ve MacOS işletim sistemlerini desteklemektedir. ROS 2 ile birlikte Windows ve RTOS (gerçek zamanlı işletim sistemleri) işletim sistemlerine de kurulum yapılabilmektedir.

*   ROS 2 üzerinde modüller arası haberleşme Publisher-subscriber yaklaşımı ile yapılmaktadır. Ağ üzerinde sayısız topic (konu) olabilir. Bu topiclere canlı mesajlar Publisher aracılığı ile atılır. Yine aynı topiğe subscribe olmuş başka bir modül ise bu mesajları dinler ve gerekli aksiyonu uygular. Örneğin bir şerit takip algoritması yazmak isteyelim. Bunun için araç üzerindeki kameralardaki görüntüye ihtiyaç duyarız. Kameradaki anlık görüntü istenilen topiğe sürekli publish edilir. Başka bir modül ise buradaki görüntüyü izler ve gerekli aksiyonları (sağa dön- sola dön- düz ilerle) uygular.

*   ROS 2’de haberleşme Fast DDS katmanı üzerinden yapılmaktadır. DDS (Data Distrubition System) dağıtık ortamda çalışan gerçek zamanlı sistemlerde veri iletimi için kullanılır. Fast DDS ise DDS üzerine kurulan daha hızlı çalışan bir veri iletimi yaklaşımıdır.

## Gereksinimler

| Platform                                  | Durum         |
|-------------------------------------------|---------------|
| **ROS Galactic - Ubuntu Focal (OpenJDK)** | ![Build Status](https://github.com/ros2-java/ros2_java/workflows/CI/badge.svg?branch=main) |
  
## Kurulum

*  ROS2 Kurulumu
    
    İşlemlerimize öncelikle ROS 2 yi kurmakla başlayalım. ROS 2 için belli dönemlerde farklı dağıtım sürümleri çıkmaktadır ve bu dağıtımlar sürekli olarak işletim sistemlerine uygun olarak güncellenmektedir.
    
    Son sürüm olan Humble Hawksbill dağıtımı Mayıs 2022’de çıkmış olsa bile paketleri Java ile yazacağımızdan ilgili paketin henüz uygun olduğu dağıtımı kullanmak daha doğru olacaktır. Bu nedenle kuruluma ROS 2 Galactic Geochelone dağıtımıyla devam edelim. Bu dağıtım için Linux kullanılıyorsa Ubuntu 20.04 işletim sisteminin kurulu olması gerekiyor.

    * UTF-8 ayarlama

        ```sh
        sudo apt update && sudo apt install locales
        sudo locale-gen en_US en_US.UTF-8
        sudo update-locale LC_ALL=en_US.UTF-8 LANG=en_US.UTF-8
        export LANG=en_US.UTF-8

        locale
        ```

    * Ubuntu Universe Repository aktiflik kontrolü
    
        ```sh
        apt-cache policy | grep universe
        ```


        > Eğer repository hata verirse aşağıdaki paketler yüklenerek devam edilebilir.
        >```sh
        >sudo apt install software-properties-common
        >sudo add-apt-repository universe
        >```


    * ROS2 Apt repository paketinin eklenmesi
        ```sh
        sudo apt update && sudo apt install curl gnupg lsb-release
        sudo curl -sSL https://raw.githubusercontent.com/ros/rosdistro/master/ros.key -o /usr/share/keyrings/ros-archive-keyring.gpg
        echo "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/ros-archive-keyring.gpg] http://packages.ros.org/ros2/ubuntu $(source /etc/os-release && echo $UBUNTU_CODENAME) main" | sudo tee /etc/apt/sources.list.d/ros2.list > /dev/null
        ```

    * Sistem paketlerinin güncellenmesi
        ```sh
        sudo apt update
        sudo apt upgrade
        ```


    * ROS2 Galactic paketinin kurulumu
        > NOT: 29.07.2022 tarihi için kullanacağımız ROS2 Java paketi ROS2 Galactic paketini destekliyor. Bu sebeple sorun yaşamamak için Galactic ile devam ediyoruz.
        ```sh
        sudo apt install ros-galactic-desktop
        ```

* Java JDK kurulumu

    ```sh
    sudo apt install default-jdk
    ```

* Derleme için Gradle kurulumu
    ```sh
    sudo apt install gradle
    ```
* ROS2 çalışma alanı derlemeleri için gerekli paketlerin kurulumu

    ```sh
    sudo apt install curl python3-colcon-common-extensions python3-pip python3-vcstool
    ```

* Colcon için gradle paketlerinin eklentilerinin kurulması
    ```sh
    python3 -m pip install -U git+https://github.com/colcon/colcon-gradle
    python3 -m pip install --no-deps -U git+https://github.com/colcon/colcon-ros-gradle

    ```

## Çalışma Ortamını (Workspace) Oluşturma
* ROS2 paket kaynağının kolaylık olması için .bashrc dosyasına eklenmesi
    > Her terminal açıldığında kaynak eklenmesi yapılmaması için .bashrc dosyasına ilgili komut eklenebilir. Bashrc dosyası her yeni terminalde içeriğindeki kaynakları otomatik olarak ekler ve böylece her terminal için bu komutun yazılmasına gerek kalmaz.
    ```sh
    gedit ~/.bashrc
    ```
    Aşağıdaki komutu açılan düzenleme penceresinde ekleyip kaydedin.
    ```sh
    source /opt/ros/galactic/setup.bash
    ```
    Artık yeni terminal açıp ros2 komutunu doğrudan çalıştırabilirsiniz.


* Çalışma ortamı için gerekli klasörlerin oluşturulması ve ROS2 Java paketinin yüklenmesi

    ```sh
    mkdir -p ros2_java_ws/src
    cd ros2_java_ws
    curl -skL https://raw.githubusercontent.com/ros2-java/ros2_java/main/ros2_java_desktop.repos | vcs import src
    ```
* ROS gereksinimlerinin yüklenmesi
    ```sh
    rosdep install --from-paths src -y -i --skip-keys "ament_tools"
    ```
* Paketlerin derlenmesi
    ```sh
    colcon build --symlink-install
    ```
    * Bu aşamada tekrardan .bashrc dosyasına bu sefer derleme sonucu oluşan yeni setup.bash dosyamızı ekliyoruz. Böylece bu çalışma alanını da her terminal açtığımızda otomatik olarak yüklemiş oluyoruz.
    
     ```sh
    gedit ~/.bashrc
    ```
    Aşağıdaki komutu açılan düzenleme penceresinde ekleyip kaydedin.
    ```sh
    source /home/asimkaymak/ros2_java_ws/install/setup.bash
    ```
    > Kullanıcı adını değiştirmeyi unutmayın.

* Paketlerin testi
    ```sh
    ros2 run rcljava_examples subscriber_lambda
    ```
    Yeni terminalde:
    ```sh
    ros2 run rcljava_examples publisher_lambda
    ```

    Herhangi bir hata almıyor ve mesajları başarılı bir şekilde görüntüleyebiliyorsanız tüm işlemler başarılı bir şekilde tamamlanmış demektir.

## Özel Mesaj Tipi (Custom Message Oluşturma)

Proje için iki farklı mesaj tipine ihtiyaç duyuyoruz. Bunlardan birisi sistem durumlarının inceleneceği durum mesajları, bir diğeri ise aksiyon bilgilerinin iletiminin yapılacağı aksiyon mesajları. Öncelikle oluşturduğumuz çalışma ortamı içerisinde src klasörü içine özel mesaj tiplerinin yer alacağı paketimizi oluşturalım.

* Paket oluşturma
    ```sh
    cd ~/ros2_java_ws/src/
    ros2 pkg create havelsan_msgs
    ```
* Gereksiz klasörleri silme ve msg klasörü oluşturma
    ```sh
    cd ~/ros2_ws/src/my_robot_interfaces/
    rm -rf include/
    rm -rf src/
    mkdir msg
    ```

* Build için package.xml ayarlaması
    Aşağıdaki komutları package.xml e ekleyelim.
    ```xml
    <buildtool_depend>rosidl_default_generators</buildtool_depend>
    <exec_depend>rosidl_default_runtime</exec_depend>
    <member_of_group>rosidl_interface_packages</member_of_group>
    ```

* Durum mesajları için Status.msg ve aksiyon mesajları için Action.msg oluşturma

    ```sh
    cd ~/ros2_ws/src/my_robot_interfaces/msg/
    touch Status.msg
    touch Action.msg
    ```

* Status.msg
Burada disk bilgileri çok büyük sayılar olabileceğinden bu bilgiler için long veri tipi seçiyoruz. Sistem canlılık, konsol ve monitor kayıt durumları için de Boolean tipinde array veri tipi seçilebilir.
    
    ```
    uint64 total_capacity
    uint64 free_partition_space
    uint64 usable_partition_space
    bool[] system_live_status
    bool[] console_record_status
    bool[] display_record_status

    ```

* Action.msg
Her bir aksiyon için aksiyonu yapan kişi, aksiyonun türü, işlem bilgisi, işlem parametresi ve işlem tarihi yer almalı. Tarih bilgisi için saatdakikagünayyıl bilgisi long veri tipiyle kontrol edilebilir.
    
    ```
    string sender_id
    string action_name
    string action
    string action_properties
    uint64 date
    ```

* Derleme öncesi Cmakelist.txt düzenleme
    ```
    ...
    find_package(rosidl_default_generators REQUIRED)
    rosidl_generate_interfaces(${PROJECT_NAME}
    "msg/Status.msg"
    "msg/Action.msg"
    )
    ament_export_dependencies(rosidl_default_runtime)
    ...
    ```
* Paketi build etme
    ```sh
    cd ~/ros2_ws/
    colcon build --packages-select havelsan_msgs
    ```

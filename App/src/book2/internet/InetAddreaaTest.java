package src.book2.internet;

import java.net.InetAddress;

public class InetAddreaaTest {
    public static void main(String[] args) {

        String host = "www.baidu.com";
        System.out.println(host + " address");
        try {
            InetAddress[] addresses = InetAddress.getAllByName(host);
            for (InetAddress address : addresses) {
                System.out.println(address);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("localhost address");
        try {
            InetAddress localHostAddress = InetAddress.getLocalHost();
            System.out.println(localHostAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

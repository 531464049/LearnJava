package src.book2.internet;

import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SocketTest {
    public static void main(String[] args) {
        System.out.println("start");
        try (Socket s = new Socket("time-a.nist.gov", 13);
                Scanner in = new Scanner(s.getInputStream(), StandardCharsets.UTF_8)) {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

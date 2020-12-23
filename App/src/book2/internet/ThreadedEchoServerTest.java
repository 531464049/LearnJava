package src.book2.internet;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ThreadedEchoServerTest {
    public static void main(String[] args) {
        try (ServerSocket s = new ServerSocket(8189)) {
            int i = 1;
            while (i < 1000) {
                Socket incoming = s.accept();
                System.out.println("spawing " + i);
                Runnable r = new ThreadedEchoHandle(incoming);
                Thread t = new Thread(r);
                t.start();
                i++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

/**
 * ThreadedEchoHandle
 */
class ThreadedEchoHandle implements Runnable {

    private Socket incoming;

    public ThreadedEchoHandle(Socket inSocket) {
        this.incoming = inSocket;
    }

    @Override
    public void run() {
        try (InputStream inStream = this.incoming.getInputStream();
                OutputStream outStream = this.incoming.getOutputStream()) {
            Scanner in = new Scanner(inStream, "UTF-8");

            boolean done = false;
            while (!done && in.hasNextLine()) {
                String line = in.nextLine();
                String addStr = "Echo: ";
                StringBuilder strBuilder = new StringBuilder(addStr);
                strBuilder.append(line);
                strBuilder.append("\n");
                outStream.write(strBuilder.toString().getBytes("UTF-8"));

                String lowerLine = line.trim().toLowerCase();
                if (lowerLine.equals("bye")) {
                    done = true;
                    in.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
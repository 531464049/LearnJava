package src.book2.internet;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class InterruptibleSocketTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new InterruptibleSocketFrame();

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

/**
 * KFrameLayout
 */
class KFrameLayout {
    public static final int K_WIDTH = 500;
    public static final int K_HIEGHT = 500;

    public static final int K_BAR_HEIGHT = 50;

    public static final int K_PORT = 8190;
}

/**
 * InterruptibleSocketFrame
 */
class InterruptibleSocketFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private Scanner inScanner;
    private JButton interruptBtn;
    private JButton blockingBtn;
    private JButton cancleBtn;
    private JTextArea msgArea;
    private Thread connectThread;

    public InterruptibleSocketFrame() {
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(KFrameLayout.K_WIDTH, KFrameLayout.K_HIEGHT);
        this.createUI();
    }

    private void createUI() {
        JPanel barPanel = new JPanel();
        barPanel.setLayout(null);
        barPanel.setBounds(0, 0, KFrameLayout.K_WIDTH, KFrameLayout.K_BAR_HEIGHT);
        barPanel.setBackground(Color.CYAN);
        this.add(barPanel);

        this.interruptBtn = new JButton("interruptible");
        this.interruptBtn.setBounds(0, 0, KFrameLayout.K_WIDTH / 3, KFrameLayout.K_BAR_HEIGHT);
        barPanel.add(this.interruptBtn);

        this.blockingBtn = new JButton("blocking");
        this.blockingBtn.setBounds(KFrameLayout.K_WIDTH / 3, 0, KFrameLayout.K_WIDTH / 3, KFrameLayout.K_BAR_HEIGHT);
        barPanel.add(this.blockingBtn);

        this.cancleBtn = new JButton("cancle");
        cancleBtn.setBounds(KFrameLayout.K_WIDTH / 3 * 2, 0, KFrameLayout.K_WIDTH / 3, KFrameLayout.K_BAR_HEIGHT);
        this.cancleBtn.setEnabled(false);
        barPanel.add(this.cancleBtn);

        this.msgArea = new JTextArea(20, 60);
        this.msgArea.setBounds(0, KFrameLayout.K_BAR_HEIGHT, KFrameLayout.K_WIDTH,
                KFrameLayout.K_HIEGHT - KFrameLayout.K_BAR_HEIGHT);
        this.add(this.msgArea);

        this.interruptBtn.addActionListener(event -> {
            this.interruptAction();
        });
        this.blockingBtn.addActionListener(event -> {
            this.blockingAction();
        });
        this.cancleBtn.addActionListener(event -> {
            this.cancleAction();
        });

        TestServer server = new TestServer();
        Thread serverThread = new Thread(server);
        serverThread.start();
    }

    private void interruptAction() {
        this.interruptBtn.setEnabled(false);
        this.blockingBtn.setEnabled(false);
        this.cancleBtn.setEnabled(true);

        this.connectThread = new Thread(() -> {
            this.connectInterruptibly();
        });
        this.connectThread.start();
    }

    private void blockingAction() {
        this.interruptBtn.setEnabled(false);
        this.blockingBtn.setEnabled(false);
        this.cancleBtn.setEnabled(true);

        this.connectThread = new Thread(() -> {
            this.connectBlocking();
        });
        this.connectThread.start();
    }

    private void cancleAction() {
        this.connectThread.interrupt();
        this.msgArea.append("Channel closed \n");
        this.cancleBtn.setEnabled(false);
        this.interruptBtn.setEnabled(true);
        this.blockingBtn.setEnabled(true);
    }

    private void connectInterruptibly() {
        this.msgArea.append("interruptible:\n");

        try (SocketChannel channel = SocketChannel.open(new InetSocketAddress("172.0.0.1", KFrameLayout.K_PORT))) {
            this.inScanner = new Scanner(channel, "UTF-8");
            while (!Thread.currentThread().isInterrupted()) {
                this.msgArea.append("Reading ");
                if (this.inScanner.hasNextLine()) {
                    String line = this.inScanner.nextLine();
                    this.msgArea.append(line);
                    this.msgArea.append("\n");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            this.msgArea.append(e.getMessage());
        }
    }

    private void connectBlocking() {
        this.msgArea.append("Blocking:\n");

        try (Socket sock = new Socket("172.0.0.1", KFrameLayout.K_PORT)) {

            this.inScanner = new Scanner(sock.getInputStream(), "UTF-8");
            while (!Thread.currentThread().isInterrupted()) {
                this.msgArea.append("Reading ");
                if (inScanner.hasNextLine()) {
                    String line = inScanner.nextLine();
                    this.msgArea.append(line);
                    this.msgArea.append("\n");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            this.msgArea.append(e.getMessage());
        }
    }

    class TestServer implements Runnable {
        @Override
        public void run() {
            try (ServerSocket s = new ServerSocket(KFrameLayout.K_PORT)) {
                while (true) {
                    Socket incoming = s.accept();
                    Thread t = new Thread(new TestServerHandle(incoming));
                    t.start();
                }
            } catch (Exception e) {
                InterruptibleSocketFrame.this.msgArea.append("TestServer.run: " + e);
            }
        }
    }

    class TestServerHandle implements Runnable {
        private Socket incoming;
        private int count;

        public TestServerHandle(Socket incoming) {
            this.incoming = incoming;
            this.count = 0;
        }

        @Override
        public void run() {
            try {
                OutputStream outStream = this.incoming.getOutputStream();

                while (this.count < 100) {
                    this.count += 1;
                    if (this.count <= 10) {
                        String str = "" + this.count;
                        outStream.write(str.getBytes("UTF-8"));
                    }
                    Thread.sleep(100);
                }
            } catch (Exception e) {
                InterruptibleSocketFrame.this.msgArea.append("TestServerHandle.run: " + e);
            } finally {
                try {
                    this.incoming.close();
                    InterruptibleSocketFrame.this.msgArea.append("Closing server\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}

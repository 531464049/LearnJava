package src.book1.bounce;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Bounce {
    public static void main(String[] args) {
        System.out.println("start");
        EventQueue.invokeLater(() -> {
            JFrame frame = new BounceFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

/**
 * BounceFrame
 */
class BounceFrame extends JFrame {

    private static final long serialVersionUID = 6261213227553770763L;
    private BallComponent comp;
    public static final int DELAY = 100;
    private boolean stop = false;

    public BounceFrame() {
        this.setTitle("bounce");
        this.comp = new BallComponent();
        this.add(comp, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        this.addButton(buttonPanel, "add", event -> addBall());
        this.addButton(buttonPanel, "start", event -> ballStart());
        this.addButton(buttonPanel, "stop", event -> close());
        this.add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }

    private void addButton(Container c, String title, ActionListener listener) {
        JButton button = new JButton(title);
        c.add(button);
        button.addActionListener(listener);
    }

    public void addBall() {
        Ball ball = new Ball();
        this.comp.addBall(ball);
    }

    public void ballStart() {
        this.stop = false;
        Runnable r = () -> {
            try {
                while (!this.stop) {
                    for (Ball b : this.comp.getBalls()) {
                        b.move(this.comp.getBounds());
                        this.comp.paint(this.comp.getGraphics());
                    }
                    Thread.sleep(DELAY);
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        };
        Thread t = new Thread(r);
        t.start();
    }

    public void close() {
        this.stop = true;
    }
}

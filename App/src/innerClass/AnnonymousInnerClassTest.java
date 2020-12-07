package src.innerClass;

import java.awt.Toolkit;
import java.awt.event.*;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class AnnonymousInnerClassTest {
    public static void main(String[] args) {
        ATalkClock clock = new ATalkClock();
        clock.start(1000, true);

        JOptionPane.showMessageDialog(null, "exit?");
        System.out.println("**");
        System.exit(0);
    }
}

/**
 * ATalkClock
 */
class ATalkClock {
    public void start(int interval, boolean beep) {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("time is " + new Date());
                if (beep) {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        };
        Timer t = new Timer(interval, listener);
        t.start();
    }

}
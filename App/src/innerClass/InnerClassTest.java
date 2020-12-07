package src.innerClass;

import java.awt.Toolkit;
import java.awt.event.*;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class InnerClassTest {
    public static void main(String[] args) {
        TalkClock clock = new TalkClock(1000, true);
        clock.start();

        JOptionPane.showMessageDialog(null, "exit?");
        System.exit(0);
    }
}

/**
 * TalkClock
 */
class TalkClock {
    private int interval;
    private boolean beep;

    public TalkClock(int aInterval, boolean aBeep) {
        this.interval = aInterval;
        this.beep = aBeep;
    }

    public void start() {
        ActionListener listener = new TimePrinter();
        Timer t = new Timer(interval, listener);
        t.start();
    }

    public class TimePrinter implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("at the tone the time is" + new Date());
            if (TalkClock.this.beep) {
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }
}
package src.lambda;

import java.util.Arrays;
import java.util.Date;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class LambdaTest {
    public static void main(String[] args) {
        String[] planets = new String[] { "sb", "fuck", "loser", "shit" };
        System.out.println(Arrays.toString(planets));

        System.out.println("sorted in dictionary order:");
        Arrays.sort(planets);
        System.out.println(Arrays.toString(planets));

        System.out.println("sorted by length:");
        Arrays.sort(planets, (first, second) -> first.length() - second.length());
        System.out.println(Arrays.toString(planets));

        // Timer t = new Timer(1000, event -> {
        // System.out.println("the time is " + new Date());
        // });
        // t.start();

        repeatMessage("test", 1000);

        JOptionPane.showMessageDialog(null, "quit");
        System.exit(0);
    }

    public static void repeatMessage(String text, int dalay) {
        ActionListener listener = event -> {
            System.out.println(text);
            Toolkit.getDefaultToolkit().beep();
        };

        new Timer(dalay, listener).start();
    }
}

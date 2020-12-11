import java.awt.*;
import javax.swing.*;

/// pdf-page-1-5-88
public class App {
    public static void main(String[] args) {
        System.out.println("app run");
        testFrame();
    }

    public static void testFrame() {
        EventQueue.invokeLater(() -> {
            SimpleFrame frame = new SimpleFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

/**
 * SimpleFrame
 */
class SimpleFrame extends JFrame {
    public SimpleFrame() {
        // get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width / 2;
        int height = screenSize.height / 2;
        this.setSize(width, height);
        this.setLocationByPlatform(true);
        // why????
        Image icon = new ImageIcon("img/app_icon.png").getImage();
        this.setIconImage(icon);
        this.setTitle("sb");
    }
}

package src.draw;

import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class DrawTest {
    public static void main(String[] args) {
        System.out.println("draw");
        EventQueue.invokeLater(() -> {
            JFrame frame = new DrawFrame();
            frame.setVisible(true);
        });
    }
}

/**
 * DrawFrame
 */
class DrawFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    public DrawFrame() {
        this.setTitle("draw");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new DrawComponent());
        this.pack();
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        return new Dimension(screensize.width / 2, screensize.height / 2);
    }
}

/**
 * DrawComponent
 */
class DrawComponent extends JComponent {

    private static final long serialVersionUID = 1L;

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        String msg = "test str";
        Font f = new Font(null, Font.BOLD, 36);
        g2.setFont(f);
        getWidth();
        FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D bounds = f.getStringBounds(msg, context);
        double frameWidth = this.getWidth();
        double frameheight = this.getHeight();
        double fw = bounds.getWidth();
        double fh = bounds.getHeight();
        double textX = (frameWidth - fw) / 2;
        double textY = frameheight / 2 + fh / 2;
        System.out.println("msg width = " + fw + " height = " + fh);
        // drawString的Y不是centerY而是绘制的y+h（组件bottom_y）
        g2.drawString(msg, (int) textX, (int) textY);
        g2.setPaint(Color.RED);

        // 画线
        Point2D lineP1 = new Point2D.Double(textX, textY);
        Point2D lineP2 = new Point2D.Double(textX + fw, textY);
        Line2D line = new Line2D.Double(lineP1, lineP2);
        g2.draw(line);

        // 画矩形
        Rectangle2D rect = new Rectangle2D.Double(textX, textY - fh, fw, fh);
        g2.draw(rect);

        // System.out.println(System.getProperty("user.dir"));
        // 绘制图片
        int imgStartX = (int) (textX + fw) + 1;
        int imgStartY = (int) textY + 1;
        Image icon = new ImageIcon("App/images/app_icon.png").getImage();
        int imgWidth = icon.getWidth(this);
        int imgHeight = icon.getHeight(this);
        // System.out.println("img width = " + imgWidth + " height = " + imgHeight);
        // g2.drawImage(icon, imgStartX, imgStartY, null);
        g2.drawImage(icon, imgStartX, imgStartY, imgWidth, imgHeight, null);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int dx = imgWidth * i;
                int dy = imgHeight * j;
                g2.copyArea(imgStartX, imgStartY, imgWidth, imgHeight, dx, dy);
            }
        }
    }
}
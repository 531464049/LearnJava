package src.book1.draw;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class ButtonFrame extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = -1971838820671486615L;
    private JPanel buttonPanel;
    private int tapIndex = 0;
    private static final int K_WIDTH = 300;
    private static final int K_HEIGHT = 200;

    public static void main(String[] args) {
        System.out.println("draw");
        EventQueue.invokeLater(() -> {
            JFrame frame = new ButtonFrame();
            frame.setVisible(true);
        });
    }

    public ButtonFrame() {
        this.setSize(K_WIDTH, K_HEIGHT);
        JButton yellowBtn = new JButton("yellow");
        JButton redBtn = new JButton("red");
        JButton blackBtn = new JButton("black");

        buttonPanel = new JPanel();
        buttonPanel.add(yellowBtn);
        buttonPanel.add(redBtn);
        buttonPanel.add(blackBtn);

        this.add(buttonPanel);

        yellowBtn.addActionListener(new ColorAction(Color.YELLOW));
        redBtn.addActionListener(new ColorAction(Color.RED));
        // blackBtn.addActionListener(new ColorAction(Color.BLACK));
        blackBtn.addActionListener(event -> {
            int r = (int) (Math.random() * 255);
            int g = (int) (Math.random() * 255);
            int b = (int) (Math.random() * 255);
            this.buttonPanel.setBackground(new Color(r, g, b));

            try {
                UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
                UIManager.LookAndFeelInfo nextInfo = infos[this.tapIndex];
                UIManager.setLookAndFeel(nextInfo.getClassName());
                SwingUtilities.updateComponentTreeUI(this);
                this.tapIndex += 1;
                if (this.tapIndex >= infos.length) {
                    this.tapIndex = 0;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }

    /**
     * ColorAction
     */
    private class ColorAction implements ActionListener {
        private Color bgColor;

        public ColorAction(Color c) {
            this.bgColor = c;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(e);
            ButtonFrame.this.buttonPanel.setBackground(this.bgColor);
        }
    }
}

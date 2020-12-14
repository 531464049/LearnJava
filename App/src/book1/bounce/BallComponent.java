package src.book1.bounce;

import java.awt.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

public class BallComponent extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int DEFAULT_WIDTH = 450;
    private static final int DEFAULT_HEIGHT = 350;

    private List<Ball> balls = new ArrayList<>();

    public void addBall(Ball b) {
        this.balls.add(b);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Ball b : balls) {
            g2.fill(b.getShape());
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    /**
     * @return the balls
     */
    public List<Ball> getBalls() {
        return balls;
    }
}

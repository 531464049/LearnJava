package src.draw;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator extends JFrame {

    public static void main(String[] args) {
        System.out.println("Calculator");
        EventQueue.invokeLater(() -> {
            JFrame frame = new Calculator();
            frame.setResizable(false);
            frame.setVisible(true);
        });
    }

    public Calculator() {
        this.setSize(CalculatorPanel.K_WIDTH, CalculatorPanel.K_HEIGHT);
        CalculatorPanel calculatorPanel = new CalculatorPanel();
        this.add(calculatorPanel);
        // this.pack();
    }
}

/**
 * CalculatorPanel
 */
class CalculatorPanel extends JPanel {
    public static final int K_WIDTH = 200;
    public static final int K_HEIGHT = 300;

    private JButton display;
    private JPanel panel;
    private double result;
    private String lastCommand;
    private boolean start;

    public CalculatorPanel() {
        this.setSize(K_WIDTH, K_HEIGHT);
        this.setLayout(null);
        this.result = 0;
        this.lastCommand = "";
        this.start = true;

        this.panel = new JPanel();
        this.panel.setSize(K_WIDTH, K_HEIGHT);
        // this.panel.setLayout(null);

        int displayWidth = this.getWidth() > 0 ? this.getWidth() : K_WIDTH;
        int displayHeight = this.getHeight() > 0 ? this.getHeight() / 5 : K_HEIGHT / 5;
        this.display = new JButton("0");
        this.display.setBounds(0, 0, displayWidth, displayHeight);
        this.display.setEnabled(false);
        this.panel.add(this.display);

        ActionListener insert = new InsetAction();
        ActionListener command = new CommandAction();
        String[] labs = { "7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "=", "+" };
        int itemWidth = this.getWidth() / 4;
        if (itemWidth == 0) {
            itemWidth = K_WIDTH / 4;
        }
        int itemHeight = this.getHeight() / 5;
        if (itemHeight == 0) {
            itemHeight = K_HEIGHT / 5;
        }
        for (int i = 0; i < labs.length; i++) {
            String lab = labs[i];
            JButton btn = new JButton(lab);
            if (i % 4 == 3 || i == 14) {
                btn.addActionListener(command);
            } else {
                btn.addActionListener(insert);
            }
            int x = 20;
            int y = 30;
            btn.setBounds(x, y, 20, 20);
            this.panel.add(btn);
        }

        this.add(this.panel);
    }

    public void calculate(double x) {
        if (this.lastCommand.equals("+")) {
            this.result += x;
        } else if (this.lastCommand.equals("-")) {
            this.result -= x;
        } else if (this.lastCommand.equals("*")) {
            this.result *= x;
        } else if (this.lastCommand.equals("/")) {
            this.result /= x;
        } else if (this.lastCommand.equals("=")) {
            this.result = x;
        }
        display.setText("" + result);
    }

    /**
     * InsetAction
     */
    private class InsetAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String input = e.getActionCommand();
            if (start) {
                display.setText("");
                start = false;
            }
            display.setText(display.getText() + input);
        }
    }

    /**
     * CommandAction
     */
    private class CommandAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            if (start) {
                if (cmd.equals("-")) {
                    display.setText(cmd);
                    start = false;
                } else {
                    lastCommand = cmd;
                }
            } else {
                calculate(Double.parseDouble(display.getText()));
                lastCommand = cmd;
                start = true;
            }
        }
    }
}
package src.book2.localetest;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class NumberFormatTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frmae = new FormatFrmae();
            frmae.setResizable(false);
            frmae.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frmae.setVisible(true);
        });
    }
}

/**
 * FormatFrmae
 */
class FormatFrmae extends JFrame {
    private static final long serialVersionUID = 1L;
    private Locale[] locales;
    private double curentNumber;
    private JComboBox<String> localCombo = new JComboBox<>();
    private JButton parseBtn = new JButton("parse");
    private JTextField numberText = new JTextField(30);
    private JRadioButton numberBtn = new JRadioButton("number");
    private JRadioButton currencyBtn = new JRadioButton("currency");
    private JRadioButton percentBtn = new JRadioButton("percent");
    private NumberFormat curentFormat;

    public FormatFrmae() {
        this.setLayout(null);
        this.setTitle("test");
        this.setSize(500, 250);

        JLabel lab = new JLabel("locale:");
        lab.setBounds(20, 0, 80, 50);
        this.add(lab);

        this.localCombo.setBounds(100, 0, 400, 50);
        this.add(this.localCombo);

        int x = 100;
        int y = 75;
        int width = 100;
        int height = 50;
        this.numberBtn.setBounds(x, y, width, height);
        this.numberBtn.addActionListener(e -> {
            this.updateBtnState(0);
        });
        this.numberBtn.setSelected(true);
        this.add(this.numberBtn);
        x += 100;
        this.currencyBtn.setBounds(x, y, width, height);
        this.currencyBtn.addActionListener(e -> {
            this.updateBtnState(1);
        });
        this.add(this.currencyBtn);
        x += 100;
        this.percentBtn.setBounds(x, y, width, height);
        this.percentBtn.addActionListener(e -> {
            this.updateBtnState(2);
        });
        this.add(this.percentBtn);

        this.parseBtn.setBounds(25, 160, 75, 30);
        this.parseBtn.addActionListener(e -> {
            this.parseInputText();
        });
        this.add(this.parseBtn);

        this.numberText.setBounds(120, 160, 300, 30);
        this.add(this.numberText);

        this.locales = NumberFormat.getAvailableLocales().clone();
        Arrays.sort(this.locales, Comparator.comparing(Locale::getDisplayName));
        for (Locale loc : locales) {
            this.localCombo.addItem(loc.getDisplayName());
        }
        this.localCombo.setSelectedItem(Locale.getDefault().getDisplayName());
        this.localCombo.addActionListener(e -> {
            this.updateDisplay();
        });
        this.curentNumber = 123456.78;
        this.curentFormat = NumberFormat.getNumberInstance(Locale.getDefault());
        this.updateDisplay();
    }

    private void parseInputText() {
        String s = this.numberText.getText().trim();
        try {
            Number n = this.curentFormat.parse(s);
            if (n != null) {
                this.curentNumber = n.doubleValue();
                this.updateDisplay();
            } else {
                this.numberText.setText("parse error: " + s);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void updateBtnState(int idx) {
        // System.out.println(idx);
        this.numberBtn.setSelected(idx == 0);
        this.currencyBtn.setSelected(idx == 1);
        this.percentBtn.setSelected(idx == 2);

        this.updateDisplay();
    }

    private void updateDisplay() {
        Locale loc = this.locales[this.localCombo.getSelectedIndex()];
        if (this.numberBtn.isSelected()) {
            this.curentFormat = NumberFormat.getNumberInstance(loc);
        } else if (this.currencyBtn.isSelected()) {
            this.curentFormat = NumberFormat.getCurrencyInstance(loc);
        } else if (this.percentBtn.isSelected()) {
            this.curentFormat = NumberFormat.getPercentInstance(loc);
        }
        String formatedStr = this.curentFormat.format(this.curentNumber);
        this.numberText.setText(formatedStr);
    }
}
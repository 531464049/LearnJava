package src.book2.swing;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.AbstractListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class LongListTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new TestFrame();
            frame.setTitle("test");
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class TestFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JList<String> wordlist;
    private JLabel lable;

    public TestFrame() {
        this.setLayout(null);
        this.setSize(300, 300);

        this.wordlist = new JList<>(new WordListModel(3));
        this.wordlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.wordlist.setFixedCellWidth(150);
        this.wordlist.setFixedCellHeight(30);

        JScrollPane scroll = new JScrollPane(this.wordlist);
        scroll.setBounds(50, 0, 150, 200);
        this.add(scroll);

        this.lable = new JLabel();
        this.lable.setBounds(0, 210, 300, 40);
        this.lable.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(this.lable);

        this.setSubject(null);

        this.wordlist.addListSelectionListener(event -> {
            this.setSubject(this.wordlist.getSelectedValue());
        });
    }

    private void setSubject(String txt) {
        StringBuilder buider = new StringBuilder("---- ");
        if (txt != null) {
            buider.append(txt);
        }
        buider.append(" ----");
        this.lable.setText(buider.toString());
    }
}

class WordListModel extends AbstractListModel<String> {

    private static final long serialVersionUID = 1L;
    private int length;
    public static final char FIRST = 'a';
    public static final char LAST = 'z';

    public WordListModel(int n) {
        this.length = n;
    }

    @Override
    public int getSize() {
        return (int) Math.pow(LAST - FIRST + 1, length);
    }

    @Override
    public String getElementAt(int index) {
        StringBuilder builder = new StringBuilder();
        int p = LAST - FIRST + 1;
        for (int i = 0; i < this.length; i++) {
            char c = (char) (FIRST + index % p);
            builder.insert(0, c);
            index = index / p;
        }
        return builder.toString();
    }

}
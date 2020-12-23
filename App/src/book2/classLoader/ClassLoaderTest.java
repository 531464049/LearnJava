package src.book2.classLoader;

import java.awt.EventQueue;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ClassLoaderTest {
    public static void main(String[] args) {
        Runnable r = () -> {
            System.out.println("start");
            JFrame frame = new ClassLoaderFrame();
            frame.setTitle("class loader");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setVisible(true);
        };
        EventQueue.invokeLater(r);
    }
}

class ClassLoaderFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField keyField;
    private JTextField nameField;
    private static final int K_WIDTH = 300;
    private static final int K_HEIGHT = 200;

    public ClassLoaderFrame() {
        this.setLayout(null);
        this.setSize(K_WIDTH, K_HEIGHT);

        JLabel lab1 = new JLabel("Class", SwingConstants.CENTER);
        lab1.setBounds(20, 0, 40, 50);
        this.add(lab1);

        this.nameField = new JTextField("Calculator", 30);
        this.nameField.setBounds(60, 10, 180, 30);
        this.add(this.nameField);

        JLabel lab2 = new JLabel("Key", SwingConstants.CENTER);
        lab2.setBounds(20, 50, 40, 50);
        this.add(lab2);

        this.keyField = new JTextField("3", 4);
        this.keyField.setBounds(60, 60, 180, 30);
        this.add(this.keyField);

        JButton loadBtn = new JButton("load");
        loadBtn.setBounds(K_WIDTH / 2 - 40, 100, 80, 40);
        loadBtn.addActionListener(e -> {
            this.loadClass();
        });
        this.add(loadBtn);
    }

    private void loadClass() {
        String name = this.nameField.getText();
        String key = this.keyField.getText();
        if (name.length() == 0 || key.length() == 0) {
            return;
        }
        System.out.printf("Try to load class:%s key:%s%n", name, key);
        try {
            ClassLoader loader = new CtyptoClassLoader(Integer.parseInt(key));
            Class<?> c = loader.loadClass(name);
            Method m = c.getMethod("main", String[].class);
            m.invoke(null, (Object) new String[] {});
        } catch (Exception e) {
            // System.out.println(e);
            JOptionPane.showMessageDialog(null, e);
        }
    }
}

class CtyptoClassLoader extends ClassLoader {
    private int key;

    public CtyptoClassLoader(int k) {
        this.key = k;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] classBytes = null;
            classBytes = this.loadClassBytes(name);
            Class<?> cl = this.defineClass(name, classBytes, 0, classBytes.length);
            if (cl == null) {
                throw new ClassNotFoundException(name);
            }
            return cl;
        } catch (Exception e) {
            throw new ClassNotFoundException(name);
        }
    }

    private byte[] loadClassBytes(String name) throws IOException {
        String cname = name.replace(".", "/") + ".caesar";
        byte[] bytes = Files.readAllBytes(Paths.get(cname));
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (bytes[i] - this.key);
        }
        return bytes;
    }
}
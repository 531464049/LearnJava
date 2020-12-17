package src.book2.xml;

import java.awt.Component;
import java.awt.EventQueue;
import java.io.File;

import javax.swing.*;
import javax.swing.event.TreeModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.*;
import javax.xml.parsers.*;

import org.w3c.dom.*;

public class Treeviewer {
    public static void main(String[] args) {
        System.out.println("start");
        Treeviewer.printDoc();
        EventQueue.invokeLater(() -> {
            JFrame frame = new DomTreeFrame();
            frame.setTitle("tree viewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    public static void printDoc() {
        System.out.println("test");
        try {
            File file = new File("App/src/book2/xml/test.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            Element root = doc.getDocumentElement();
            Treeviewer.printElement(root, 0);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void printElement(Element element, int space) {
        Treeviewer.printElementAttr(element, space);
        NodeList list = element.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node instanceof Element) {
                Element childELement = (Element) node;
                Treeviewer.printElement(childELement, space + 1);
            } else if ((node instanceof CharacterData)) {
                CharacterData charData = (CharacterData) node;
                StringBuilder builder = new StringBuilder(charData.getData());
                for (int j = 0; j < builder.length(); j++) {
                    char ic = builder.charAt(j);
                    if (ic == '\r' || ic == '\n' || ic == '\t') {
                        builder.replace(j, j + 1, "");
                        j++;
                    }
                }
                String ss = builder.toString().trim();
                if (ss.length() > 0) {
                    Treeviewer.printElementString(ss, space + 1);
                }
            }
        }
    }

    public static void printElementAttr(Element element, int space) {
        String str = element.getNodeName();
        NamedNodeMap map = element.getAttributes();
        if (map.getLength() > 0) {
            str = str + " - ";
        }
        for (int i = 0; i < map.getLength(); i++) {
            str = str + map.item(i).getNodeName() + " : " + map.item(i).getNodeValue();
        }
        if (str.trim().length() > 0) {
            Treeviewer.printElementString(str.trim(), space);
        }
    }

    public static void printElementString(String str, int space) {
        for (int i = 0; i < space; i++) {
            str = "__" + str;
        }
        System.out.println(str);
    }
}

/**
 * DomTreeFrame
 */
class DomTreeFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 400;
    private DocumentBuilder builder;

    public DomTreeFrame() {
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        JMenu fileMenu = new JMenu("file");
        JMenuItem openItem = new JMenuItem("open");
        openItem.addActionListener(event -> {
            this.openFile();
        });
        fileMenu.add(openItem);

        JMenuItem exitItem = new JMenuItem("exit");
        exitItem.addActionListener(event -> {
            System.exit(0);
        });
        fileMenu.add(exitItem);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);
    }

    public void openFile() {
        System.out.println("open file");
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        chooser.setFileFilter(new FileNameExtensionFilter("XML files", "xml"));
        int r = chooser.showOpenDialog(this);
        if (r != JFileChooser.APPROVE_OPTION) {
            return;
        }
        final File file = chooser.getSelectedFile();

        try {
            if (this.builder == null) {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                builder = factory.newDocumentBuilder();
            }
            Document doc = builder.parse(file);

            JTree tree = new JTree(new DOMTreeModel(doc));
            tree.setCellRenderer(new DOMTreeCellRender());
            this.setContentPane(new JScrollPane(tree));
            this.validate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);

        }
    }
}

/**
 * DOMTreeModel
 */
class DOMTreeModel implements TreeModel {

    private Document doc;

    public DOMTreeModel(Document doc) {
        this.doc = doc;
    }

    @Override
    public Object getRoot() {
        return this.doc.getDocumentElement();
    }

    private NodeList getNodeList(Object parent) {
        Node node = (Node) parent;
        return node.getChildNodes();
    }

    @Override
    public Object getChild(Object parent, int index) {
        NodeList list = this.getNodeList(parent);
        return list.item(index);
    }

    @Override
    public int getChildCount(Object parent) {
        NodeList list = this.getNodeList(parent);
        return list.getLength();
    }

    @Override
    public boolean isLeaf(Object node) {
        return this.getChildCount(node) == 0;
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        NodeList list = this.getNodeList(parent);
        for (int i = 0; i < list.getLength(); i++) {
            if (this.getChild(parent, i) == child) {
                return i;
            }
        }
        return -1;
    }

    public void valueForPathChanged(TreePath path, Object newValue) {
    }

    public void addTreeModelListener(TreeModelListener l) {
    }

    public void removeTreeModelListener(TreeModelListener l) {
    }
}

/**
 * DOMTreeCellRender
 */
class DOMTreeCellRender extends DefaultTreeCellRenderer {

    private static final long serialVersionUID = 1L;

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,
            int row, boolean hasFocus) {

        Node node = (Node) value;
        if (node instanceof Element) {
            return this.elementPanel((Element) node);
        }
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        if (node instanceof CharacterData) {
            this.setText(this.characterString((CharacterData) node));
        } else {
            this.setText(node.getClass() + ": " + node.toString());
        }
        return this;
    }

    public JPanel elementPanel(Element e) {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Element: " + e.getTagName()));
        final NamedNodeMap map = e.getAttributes();

        AbstractTableModel dm = new AbstractTableModel() {
            public int getRowCount() {
                return map.getLength();
            }

            public int getColumnCount() {
                return 2;
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                if (columnIndex == 0) {
                    return map.item(rowIndex).getNodeName();
                } else {
                    return map.item(rowIndex).getNodeValue();
                }
            }
        };

        JTable tab = new JTable(dm);
        panel.add(tab);
        return panel;
    }

    public String characterString(CharacterData node) {
        StringBuilder builder = new StringBuilder(node.getData());
        for (int i = 0; i < builder.length(); i++) {
            char ic = builder.charAt(i);
            if (ic == '\r') {
                builder.replace(i, i + 1, "\\r");
                i++;
            } else if (ic == '\n') {
                builder.replace(i, i + 1, "\\n");
                i++;
            } else if (ic == '\t') {
                builder.replace(i, i + 1, "\\t");
                i++;
            }
        }
        // builder.insert(0, node.getClass().getName());
        if (node instanceof CDATASection) {
            builder.insert(0, "CDATASection");
        } else if (node instanceof Text) {
            builder.insert(0, "Text: ");
        } else if (node instanceof Comment) {
            builder.insert(0, "Comment: ");
        }
        return builder.toString();
    }
}
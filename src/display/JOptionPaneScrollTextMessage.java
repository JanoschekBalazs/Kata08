package display;

import javax.swing.*;
import java.awt.*;

public class JOptionPaneScrollTextMessage extends JFrame {

    public JOptionPaneScrollTextMessage(String title, String msg) {

        JTextArea jta = new JTextArea(50, 20);
        jta.setText(msg);
        jta.setEditable(false);
        JScrollPane jsp = new JScrollPane(jta);
        setLayout(new BorderLayout());
        add(jsp, BorderLayout.CENTER);

        setTitle(title);
        setSize(1024, 768);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public <E> JOptionPaneScrollTextMessage(String title, E msg) {

        JTextArea jta = new JTextArea(50, 20);
        jta.setText(msg.toString());
        jta.setEditable(false);
        JScrollPane jsp = new JScrollPane(jta);
        setLayout(new BorderLayout());
        add(jsp, BorderLayout.CENTER);

        setTitle(title);
        setSize(1024, 768);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}

package display;

import main.Dictionary;

import javax.swing.*;
import java.awt.*;

public class JOptionPaneScrollTextMessage extends JFrame {

    public JOptionPaneScrollTextMessage(String title, Dictionary msg) {

        JTextArea jta = new JTextArea(50, 20);
        jta.setText(msg.toString());
        jta.setEditable(false);
        JScrollPane jsp = new JScrollPane(jta);
        setLayout(new BorderLayout());
        add(jsp, BorderLayout.CENTER);

        setTitle(title);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

}

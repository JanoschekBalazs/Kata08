package display;

import javax.swing.*;
import java.awt.*;

public class OutputWindow<E> extends JFrame {

    public OutputWindow(String title, E list) {

        JTextArea jta = new JTextArea(50, 20);
        jta.setText(list.toString());
        jta.setEditable(false);
        JScrollPane jsp = new JScrollPane(jta);
        setLayout(new BorderLayout());
        add(jsp, BorderLayout.SOUTH);

        setTitle(title);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

}

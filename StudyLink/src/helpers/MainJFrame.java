package helpers;

import javax.swing.*;

public class MainJFrame extends JFrame {
    public MainJFrame() {
        setVisible(true);
        setTitle("StudyLink");
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setLocationRelativeTo(null);
    }
}

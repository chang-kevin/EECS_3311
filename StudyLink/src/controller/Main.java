package controller;

import java.sql.*;

import javax.swing.*;

public class Main extends JFrame {
    public static void main(String[] args) throws SQLException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
            }
        });

    }

    public static void createGUI() {
        new Login();
    }
}



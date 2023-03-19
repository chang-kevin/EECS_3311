package controller;

import model.Studyresources.StudymaterialDAO;

import javax.swing.*;
import java.sql.SQLException;

public class Main extends JFrame {
    public static void main(String[] args) throws SQLException {
        System.out.println(StudymaterialDAO.getStudymaterial("1").getRating());
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



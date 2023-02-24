package controller;

import java.sql.Connection;

import view.Login;

import java.sql.Driver;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

public class Main extends JFrame {
    
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/studylink";
        String username = "root";
        String password = "kungfuhustle10";
        Connection connection;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
    
            connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement(); //allows SQL to be executed

            ResultSet result = statement.executeQuery("select * from courses");

            while (result.next() != false) {
                System.out.println(result.getString("course_name"));
            }

        }
    
        catch (Exception e) {
            System.out.println(e);
        }

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



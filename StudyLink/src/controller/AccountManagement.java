package controller;

import helpers.MainJFrame;
import view.dashboard.Dashboard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AccountManagement extends MainJFrame {
    private JPanel panel1;
    private JButton cancelButton;
    private JButton saveButton;
    private JTextField emailText;
    private JTextField firstNameText;
    private JTextField lastNameText;
    private JLabel email;
    private JLabel firstName;
    private JLabel lastName;

    public AccountManagement() {
        super();
        setContentPane(panel1);

        setUpCancelBtn();
        setUpSaveBtn();
    }

    private void setUpCancelBtn() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                try {
                    new Dashboard();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void setUpSaveBtn() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                try {
                    new Dashboard();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}

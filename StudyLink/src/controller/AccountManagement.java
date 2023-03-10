package controller;

import helpers.MainJFrame;
import model.User.User;
import model.User.UserDAO;
import model.User.UserRole;
import model.User.UserSession;
import view.dashboard.Dashboard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AccountManagement extends MainJFrame {
    private JPanel panel1;
    private JButton cancelButton;
    private JButton saveButton;
    private JTextField usernameText;
    private JTextField firstNameText;
    private JTextField lastNameText;
    private JLabel username;
    private JLabel firstName;
    private JLabel lastName;
    private JLabel role;
    private JTextField roleTextField;
    private User user;
    private UserDAO userDAO;
    private User sessionUser;

    public AccountManagement() throws SQLException {
        super();

        userDAO = new UserDAO();
        sessionUser = UserSession.getInstance().getCurrentUser();
        user = userDAO.getUser(sessionUser.getUsername());

        setContentPane(panel1);
        setUpCancelBtn();
        setUpSaveBtn();
        setUpFields();
    }

    private void setUpFields() throws SQLException {
        usernameText.setText(user.getUsername());
        firstNameText.setText(user.getFirstName());
        lastNameText.setText(user.getLastName());
        roleTextField.setText(user.getRole());
    }

    private void setUpCancelBtn() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
    }

    private void setUpSaveBtn() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User updatedUser = new User.UserBuilder(user.getUsername(), user.getPassword())
                        .setFirstName(firstNameText.getText())
                        .setLastName(lastNameText.getText())
                        .setRole(UserRole.STUDENT)
                        .build();
                try {
                    userDAO.update(updatedUser);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                setVisible(false);
                dispose();
            }
        });
    }
}

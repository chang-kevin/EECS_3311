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
    private JLabel usernameLabel;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel roleLabel;
    private JTextField roleTextField;
    private User user;
    private UserDAO userDAO;
    private User sessionUser;

    public AccountManagement() throws SQLException {
        super();

        userDAO = new UserDAO();
        sessionUser = UserSession.getInstance().getCurrentUser();
        user = userDAO.getUser(sessionUser.getUsername());

        setUpAnchors();
        setContentPane(panel1);
        setUpCancelBtn();
        setUpSaveBtn();
        setUpFields();
    }

    private void setUpAnchors() {
        usernameText.setName("usernameText");
        firstNameText.setName("firstNameText");
        lastNameText.setName("lastNameText");
        usernameLabel.setName("usernameLabel");
        firstNameLabel.setName("firstNameLabel");
        lastNameLabel.setName("lastNameLabel");
        roleLabel.setName("roleLabel");
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
                try {
                    new Dashboard();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
 package controller;

import helpers.Authenticator.Authenticator;
import helpers.MainJFrame;
import model.User.User;
import model.User.UserDAO;
import model.User.UserSession;
import view.dashboard.Dashboard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Login extends MainJFrame {
    public JPanel panel;
    private JButton Login;
    private JLabel Password;
    private JButton SignUp;
    private JButton ForgotPassword;
    private JTextField username;
    private JPasswordField passwordField1;
    private JLabel EmailAddress;
    private JLabel SignUpText;
    private JButton btnClick;
    private UserSession userSession;

    public Login() {
        super();
        userSession = UserSession.getInstance();
        setContentPane(panel);
        setUpLoginBtn();
        setUpSignUpBtn();
        setUpForgotPasswordBtn();
    }

    private void setUpLoginBtn() {
        Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (username.getText().isBlank() && passwordField1.getText().isBlank()) {
                    JOptionPane.showMessageDialog(btnClick, "Please enter both the username and password.");
                    return;
                }

                try {
                    if (isUserAuthenticated()) {
                        setUserSession();
                        setVisible(false);
                        new Dashboard();
                        return;
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                JOptionPane.showMessageDialog(btnClick, "The username or password is incorrect.");
                clearFields();
            }
        });
    }

    private void setUserSession() throws SQLException {
        User user = UserDAO.getUser(username.getText());
        if (user != null) {
            UserSession.setCurrentUser(user);
        }
    }

    private void setUpSignUpBtn() {
        SignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                new SignUp();
            }
        });
    }

    private void setUpForgotPasswordBtn() {
        ForgotPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                new ForgotPassword();
            }
        });
    }

    private boolean isUserAuthenticated() throws SQLException {
        return Authenticator.authenticateUser(username.getText(), passwordField1.getText());
    }

    private void clearFields() {
        username.setText("");
        passwordField1.setText("");
    }
}

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
    private JButton loginBtn;

    public JButton getLoginBtn() {
        return loginBtn;
    }

    private JLabel passwordLabel;
    private JButton signUpBtn;
    private JButton forgotPasswordBtn;


    public JTextField getUsername() {
        return username;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    private JTextField username;
    private JPasswordField passwordField;
    private JLabel usernameLabel;
    private JLabel loginHeader;
    private JButton btnClick;
    private UserSession userSession;
    private JOptionPane jOptionPane ;

    public JOptionPane getjOptionPane() {
        return jOptionPane;
    }

    public Login() {
        super();
        userSession = UserSession.getInstance();
        setContentPane(panel);
        setUpLoginBtn();
        setUpSignUpBtn();
        setUpForgotPasswordBtn();
        setUpAnchors();
    }

    private void setUpAnchors() {
        username.setName("usernameField");
        usernameLabel.setName("usernameLabel");
        passwordField.setName("passwordField");
        passwordLabel.setName("passwordLabel");
        loginBtn.setName("loginBtn");
        signUpBtn.setName("signUpBtn");
        forgotPasswordBtn.setName("forgotPasswordBtn");
        loginHeader.setName("loginHeader");
    }

    private void setUpLoginBtn() {
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (username.getText().isBlank() && passwordField.getText().isBlank()) {

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
        signUpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                new SignUp();
            }
        });
    }

    private void setUpForgotPasswordBtn() {
        forgotPasswordBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                new ForgotPassword();
            }
        });
    }

    private boolean isUserAuthenticated() throws SQLException {
        return Authenticator.authenticateUser(username.getText(), passwordField.getText());
    }

    private void clearFields() {
        username.setText("");
        passwordField.setText("");
    }

}

package view;

import helpers.Authenticator.Authenticator;
import view.dashboard.Dashboard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Login extends JFrame {
    public JPanel panel1;
    private JButton Login;
    private JLabel Password;
    private JButton SignUp;
    private JButton ForgotPassword;
    private JTextField textField2;
    private JPasswordField passwordField1;
    private JLabel EmailAddress;
    private JLabel SignUpText;
    private JButton btnClick;

    public Login() {
        setVisible(true);
        setTitle("StudyLink");
        setContentPane(panel1);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setLocationRelativeTo(null);

        setUpLoginBtn();
        setUpSignUpBtn();
        setUpForgotPasswordBtn();
    }

    private void setUpLoginBtn() {
        Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField2.getText().isBlank() && passwordField1.getText().isBlank()) {
                    JOptionPane.showMessageDialog(btnClick, "Please enter both the username and password.");
                    return;
                }

                try {
                    if (isUserAuthenticated()) {
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
        return Authenticator.authenticateUser(textField2.getText(), passwordField1.getText());
    }

    private void clearFields() {
        textField2.setText("");
        passwordField1.setText("");
    }
}

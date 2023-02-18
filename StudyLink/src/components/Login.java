package components;

import components.dashboard.Dashboard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);

        setUpLoginBtn();
        setUpSignUpBtn();
        setUpForgotPasswordBtn();
    }

    private void setUpLoginBtn() {
        Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField2.getText().equals("") && passwordField1.getText().equals("")) {
                    JOptionPane.showMessageDialog(btnClick, "Please enter both the username and password.");
                    return;
                }

                // refactor this
                if (authenticateUser()) {
                    if (UserList.instance.x == UserList.instance.y) {
                        setVisible(false);
                        new Dashboard();
                        return;
                    }
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
                new SignUp();
            }
        });
    }

    private void setUpForgotPasswordBtn() {
        ForgotPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new ForgotPassword();
            }
        });
    }

    private boolean authenticateUser() {
        boolean hasUsername = UserList.authemail(textField2.getText());
        boolean hasUserPassword = UserList.authpass(passwordField1.getText());
        return hasUsername && hasUserPassword;
    }

    private void clearFields() {
        textField2.setText("");
        passwordField1.setText("");
    }
}
package view;

import model.User;
import model.UserList;
import model.UserRole;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp extends JFrame {
    private JTextField emailField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JButton cancelBtn;
    private JButton signUpBtn;
    private JButton btnClick;
    private JPasswordField passwordField;
    private JLabel Password;
    private JLabel Email;
    private JLabel lastName;
    private JLabel firstName;
    private JLabel signUpTitle;
    private JPanel signUpPanel;

    public SignUp() {
        setVisible(true);
        setTitle("StudyLink");
        setContentPane(signUpPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setLocationRelativeTo(null);

        setUpSignUpBtn();
        setUpCancelBtn();
    }

    private void setUpSignUpBtn() {
        signUpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hasAllFields()) {
                    User user = new User.UserBuilder(emailField.getText(), passwordField.getText())
                            .setUsername(emailField.getText())
                            .setFirstName(firstNameField.getText())
                            .setLastName(lastNameField.getText())
                            .setRole(UserRole.STUDENT)
                            .build();
                    try {
                        UserList.getInstance().addUser(user);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(btnClick, ex.getMessage());
                        resetFields();
                        return;
                    }

                    redirectToLogin();
                } else {
                    JOptionPane.showMessageDialog(btnClick, "Please fill out the missing fields.");
                }
            }
        });
    }

    private void setUpCancelBtn() {
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redirectToLogin();
            }
        });
    }

    private boolean hasAllFields() {
        return !passwordField.getText().isBlank() &&
                !emailField.getText().isBlank() &&
                !firstNameField.getText().isBlank() &&
                !lastNameField.getText().isBlank();
    }

    private void redirectToLogin() {
        resetFields();
        setVisible(false);
        dispose();
        new Login();
    }

    private void resetFields() {
        emailField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
    }
}

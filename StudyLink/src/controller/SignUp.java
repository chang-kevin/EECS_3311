 

    package controller;

import model.User.User;
import model.User.UserRole;
import model.User.UserDAO;
import model.queries;

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
    private JPanel signUpPanel;
    private JLabel Email;
    private JLabel Password;
    private JLabel lastName;
    private JLabel firstName;
    private JLabel signUpTitle;
    private JCheckBox checkBox1;

    private JComboBox comboBox1;
    private JTextField AnswerField;

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
                            .setFirstName(firstNameField.getText())
                            .setLastName(lastNameField.getText())
                            .setRole(UserRole.STUDENT)
                            .build();
                    try {
                        int n =UserDAO.add(user);
                        String x = String.valueOf(comboBox1.getSelectedIndex()+1);
                        queries.addSecurityQNA(user.getUsername(),x,AnswerField.getText());




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
                !AnswerField.getText().isBlank()&&
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
         String[] questions = {"What is the name of your first pet?","What was your first car?","What is the name of the town where you were born?","What elementary school did you attend?","What is your mother's maiden name?"};
        comboBox1 = new JComboBox(questions);
    }
}


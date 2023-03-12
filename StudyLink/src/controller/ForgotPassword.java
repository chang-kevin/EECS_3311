package controller;

import helpers.Authenticator.Authenticator;
import helpers.MainJFrame;
import model.User.User;
import model.User.UserDAO;
import model.SecurityQuestion.SecurityQuestionDAO;
import model.User.UserSecurityQuestion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ForgotPassword extends MainJFrame {
    private JPanel panel1;
    private JButton resetPasswordBtn;
    private JTextField usernameField;
    private JLabel usernameLabel;
    private JLabel ForgotPasswordTitle;
    private JButton goBackBtn;
    private JTextField Answer;
    private JPasswordField passwordField1;
    private JLabel newPasswordLabel;
    private JLabel securityQuestionLabel;
    private JButton btnClick;

    public ForgotPassword() {
        super();

        setContentPane(panel1);
        setUpResetBtn();
        setUpGoBackBtn();

        this.Answer.setVisible(false);
        this.passwordField1.setVisible(false);
        this.securityQuestionLabel.setVisible(false);
        this.newPasswordLabel.setVisible(false);
    }

    private void setUpResetBtn() {
        resetPasswordBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usernameField.getText().equals("")) {
                    JOptionPane.showMessageDialog(btnClick, "Please enter your username.");
                    return;
                }

                String z = usernameField.getText();
                try {
                    if (Authenticator.hasUser(usernameField.getText())) {
                        UserSecurityQuestion sq = SecurityQuestionDAO.getUserSecurityQuestion(usernameField.getText());
                        String securityQuestion = sq.getSecurityQuestion().getQuestionText();
                        String securityQuestionAnswer = sq.getSecurityQuestionAnswer();

                        securityQuestionLabel.setText(securityQuestion);
                        securityQuestionLabel.setVisible(true);
                        Answer.setVisible(true);

                        if (Answer.getText().equals(securityQuestionAnswer)) {
                            newPasswordLabel.setVisible(true);
                            passwordField1.setVisible(true);

                            User user = UserDAO.getUser(usernameField.getText());
                            user.setPassword(passwordField1.getText());
                            UserDAO.updateUserInfo(user);

                            if (!passwordField1.getText().isBlank()) {
                                JOptionPane.showMessageDialog(btnClick,"Your password was changed successfully!");
                            }
                        }

                        if (!Answer.getText().equals(securityQuestionAnswer) && !Answer.getText().isBlank()) {
                            Answer.setText("");
                            JOptionPane.showMessageDialog(btnClick,"Incorrect answer. Please try again.");
                            return;
                        }
                    } else {
                        resetFields();
                        JOptionPane.showMessageDialog(btnClick,"Please enter a valid email");
                        return;
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                if (hasAllFields()) {
                    cleanUpFrame();
                    new Login();
                }
            }
        });
    }

    private void setUpGoBackBtn() {
        goBackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cleanUpFrame();
                new Login();
            }
        });
    }

    private void cleanUpFrame() {
        dispose();
        setVisible(false);
        usernameField.setText("");
    }
    private boolean hasAllFields() {
        return !passwordField1.getText().isBlank() &&
                !usernameField.getText().isBlank() &&
                !Answer.getText().isBlank() ;
    }
    private void resetFields() {
        usernameField.setText("");
        Answer.setText("");
        Answer.setText("");
    }
}

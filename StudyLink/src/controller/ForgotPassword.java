package controller;

import helpers.Authenticator.Authenticator;
import helpers.MainJFrame;
import model.SecurityQuestion.SecurityQuestionDAO;
import model.User.User;
import model.User.UserDAO;
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

    public JButton getResetPasswordBtn() {
        return resetPasswordBtn;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JTextField getAnswer() {
        return Answer;
    }

    public JPasswordField getPasswordField1() {
        return passwordField1;
    }
    public JLabel getSecurityQuestionLabel() {
        return securityQuestionLabel;
    }

    public JButton getUsernameBtn() {
        return usernameBtn;
    }

    public JButton getSecurityQuestionBtn() {
        return securityQuestionBtn;
    }
    private JButton goBackBtn;
    private JTextField Answer;
    private JPasswordField passwordField1;
    private JLabel newPasswordLabel;
    private JLabel securityQuestionLabel;
    private JButton usernameBtn;
    private JButton securityQuestionBtn;
    private JButton btnClick;
    private String securityQuestionAnswer;

    public ForgotPassword() {
        super();

        setContentPane(panel1);
        setUpUsernameBtn();
        setUpSecurityQuestionBtn();
        setUpResetBtn();
        setUpGoBackBtn();

        this.securityQuestionBtn.setVisible(false);
        this.resetPasswordBtn.setVisible(false);
        this.Answer.setVisible(false);
        this.passwordField1.setVisible(false);
        this.securityQuestionLabel.setVisible(false);
        this.newPasswordLabel.setVisible(false);
    }

    private void setUpUsernameBtn() {
        usernameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usernameField.getText().equals("")) {
                    JOptionPane.showMessageDialog(btnClick, "Please enter your username.");
                    return;
                }

                try {
                    if (Authenticator.hasUser(usernameField.getText())) {
                        renderUserSecurityQuestion();
                        usernameBtn.setVisible(false);
                        securityQuestionBtn.setVisible(true);
                    } else {
                        resetFields();
                        JOptionPane.showMessageDialog(btnClick,"Please enter a valid email address.");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void setUpSecurityQuestionBtn() {
        securityQuestionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Answer.getText().equals(securityQuestionAnswer)) {
                    renderPasswordField();
                    securityQuestionBtn.setVisible(false);
                    resetPasswordBtn.setVisible(true);
                } else {
                    Answer.setText("");
                    JOptionPane.showMessageDialog(btnClick,"Incorrect answer. Please try again.");
                }
            }
        });
    }

    private void setUpResetBtn() {
        resetPasswordBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (passwordField1.getText().isBlank()) {
                        JOptionPane.showMessageDialog(btnClick,"Please enter a new password.");
                        return;
                    }

                    updateUserInfo();
                    JOptionPane.showMessageDialog(btnClick,"Your password was changed successfully!");

                    cleanUpFrame();
                    new Login();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public void renderPasswordField() {
        newPasswordLabel.setVisible(true);
        passwordField1.setVisible(true);
    }

    public void renderUserSecurityQuestion() throws SQLException {
        UserSecurityQuestion sq = SecurityQuestionDAO.getUserSecurityQuestion(usernameField.getText());
        securityQuestionAnswer = sq.getSecurityQuestionAnswer();
        securityQuestionLabel.setText(sq.getSecurityQuestion().getQuestionText());
        securityQuestionLabel.setVisible(true);
        Answer.setVisible(true);
    }

    public void updateUserInfo() throws SQLException {
        User user = UserDAO.getUser(usernameField.getText());
        user.setPassword(passwordField1.getText());
        UserDAO.updateUserInfo(user);
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
        resetFields();
        dispose();
        setVisible(false);
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

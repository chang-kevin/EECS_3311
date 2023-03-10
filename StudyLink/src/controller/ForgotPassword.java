package controller;

import helpers.Authenticator.authusername;
import helpers.MainJFrame;
import model.User.User;
import model.User.UserDAO;
import model.User.UserSession;
import model.queries;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ForgotPassword extends MainJFrame {
    private JPanel panel1;
    private JButton ResetPasswordBtn;
    private JTextField textField2;
    private JLabel EmailAddress;
    private JLabel ForgotPasswordTitle;
    private JButton GoBack;
    private JTextField Answer;
    private JPasswordField passwordField1;
    private JLabel Password;
    private JLabel question;
    private JButton btnClick;

    public ForgotPassword() {
        super();
        setContentPane(panel1);
        setUpResetBtn();
        setUpGoBackBtn();
        this.Answer.setVisible(false);
        this.passwordField1.setVisible(false);
        this.question.setVisible(false);
        this.Password.setVisible(false);


    }

    private void setUpResetBtn() {
        ResetPasswordBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField2.getText().equals("")) {
                    JOptionPane.showMessageDialog(btnClick, "Please enter email address");
                    return;
                }
                String z = textField2.getText();
                try {

                    if(authusername.authuser(textField2.getText())) {
                        List x = queries.securityQuestion(textField2.getText());
                        question.setText(x.get(0).toString());
                        question.setVisible(true);
                        Answer.setVisible(true);
                        if(Answer.getText().equals(x.get(1))){Password.setVisible(true);
                        passwordField1.setVisible(true);
                        User user = UserDAO.getUser(textField2.getText());
                            user.setPassword(passwordField1.getText());
                            UserDAO.update(user);
                            if(!passwordField1.getText().isBlank()){JOptionPane.showMessageDialog(btnClick,"password changed");}


                        }


                            if(!Answer.getText().equals(x.get(1)) && !Answer.getText().isBlank()) {Answer.setText("");
                                JOptionPane.showMessageDialog(btnClick,"Your Answer is wrong try again");

                            return;

                            }







                    }
                    else{resetFields();
                        JOptionPane.showMessageDialog(btnClick,"Please enter valid email");
                    return;}
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                if(hasAllFields()){
                    cleanUpFrame();
                new Login();}



            }
        });
    }

    private void setUpGoBackBtn() {
        GoBack.addActionListener(new ActionListener() {
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
        textField2.setText("");
    }
    private boolean hasAllFields() {
        return !passwordField1.getText().isBlank() &&
                !textField2.getText().isBlank() &&
                !Answer.getText().isBlank() ;
    }
    private void resetFields() {
        textField2.setText("");
        Answer.setText("");
        Answer.setText("");
    }
}

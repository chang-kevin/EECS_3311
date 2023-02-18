package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForgotPassword extends JFrame {
    private JPanel panel1;
    private JButton ResetPasswordBtn;
    private JTextField textField2;
    private JLabel EmailAddress;
    private JLabel ForgotPasswordTitle;
    private JButton btnClick;

    public ForgotPassword() {
        setVisible(true);
        setTitle("StudyLink");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setLocationRelativeTo(null);

        setUpResetBtn();
    }

    private void setUpResetBtn() {
        ResetPasswordBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField2.getText().equals("")) {
                    JOptionPane.showMessageDialog(btnClick, "Please enter a valid email address");
                    return;
                }

                JOptionPane.showMessageDialog(btnClick, "Please check your email for a code");
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
}

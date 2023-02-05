import javax.swing.*;

public class LoginPage {

    private JLabel userLabel;
    private JTextField userText;
    private JLabel passwordLabel;
    private JTextField passwordText;
    public LoginPage() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(500, 500);
        frame.add(panel);

        panel.setLayout(null);

        userLabel = new JLabel("User");
        userLabel.setBounds(10, 30, 80, 25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100, 30, 165, 25);
        panel.add(userText);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 60, 80, 25);
        panel.add(passwordLabel);

        passwordText = new JTextField(20);
        passwordText.setBounds(100, 60, 165, 25);
        panel.add(passwordText);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(120, 100, 80, 25);
        panel.add(loginBtn);

        JLabel success = new JLabel("");
        success.setBounds(10, 110, 300, 25);
        panel.add(success);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
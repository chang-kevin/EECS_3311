package components;

import helpers.Bounds;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Login Page component responsible for authenticating the user and directing them to the
 * components.Dashboard Page.
 */
public class LoginPage implements ActionListener {
    private static final String LOGIN = "Login";
    private static final String FORGOT_PASSWORD = "Forgot Password";
    private JFrame frame;
    private JLabel userLabel;
    private JTextField userText;
    private JLabel passwordLabel;
    private JTextField passwordText;
    private JLabel success;
    private JButton loginBtn;
    private JButton passwordResetBtn;

    JPanel panel;
    public LoginPage() {
        frame = new JFrame();
        panel = new JPanel();
        frame.setSize(500, 500);
        frame.add(panel);

        panel.setLayout(null);

        Bounds userLabelBounds = new Bounds(10, 30, 80, 25);
        userLabel = this.generateLabelComponent("User", userLabelBounds);
        panel.add(userLabel);

        Bounds userTextBounds = new Bounds(100, 30, 165, 25);
        userText = this.generateFieldComponent(userTextBounds);
        panel.add(userText);

        Bounds passwordLabelBounds = new Bounds(10, 60, 80, 25);
        passwordLabel = this.generateLabelComponent("Password", passwordLabelBounds);
        panel.add(passwordLabel);

        Bounds passwordFieldBounds = new Bounds(100, 60, 165, 25);
        passwordText = this.generateFieldComponent(passwordFieldBounds);
        panel.add(passwordText);

        Bounds loginBtnBounds = new Bounds(80, 100, 80, 25);
        loginBtn = this.generateButtonComponent(LOGIN, loginBtnBounds);
        panel.add(loginBtn);

        Bounds passwordResetBounds = new Bounds(180, 100, 150, 25);
        passwordResetBtn = this.generateButtonComponent(FORGOT_PASSWORD, passwordResetBounds);
        panel.add(passwordResetBtn);

        success = new JLabel("");
        success.setBounds(10, 110, 300, 25);
        panel.add(success);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Generate Label component
     * @param componentLabel Name of the label.
     * @param bounds Bounds of the label.
     * @return JLabel
     */
    private JLabel generateLabelComponent(String componentLabel, Bounds bounds) {
        JLabel label = new JLabel(componentLabel);
        label.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        return label;
    }

    /**
     * Generate JField component
     * @param bounds Bounds of the field.
     * @return JField
     */
    private JTextField generateFieldComponent(Bounds bounds) {
        JTextField text = new JTextField(20);
        text.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        return text;
    }

    /**
     * Generate JButton component.
     * @param buttonName Name of the button.
     * @param bounds Bounds of the button.
     * @return JButton
     */
    private JButton generateButtonComponent(String buttonName, Bounds bounds) {
        JButton button = new JButton(buttonName);
        button.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        button.addActionListener(this);
        return button;
    }

    /**
     * Overrides the actionPerformed method from the interface.
     * @param e The action event to be processed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == FORGOT_PASSWORD) {
            // navigate back to the login page
            new ForgotPasswordPage();
        }
        if (e.getActionCommand() == LOGIN) {
            success.setText("Login successful!");
            new Dashboard(); // opens up the dashboard
        }
        frame.setVisible(false); // exits the login page
    }
}
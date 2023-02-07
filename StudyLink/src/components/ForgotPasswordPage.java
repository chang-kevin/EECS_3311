package components;

import helpers.Bounds;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * ForgotPassword Page component responsible for resetting the user's password
 */
public class ForgotPasswordPage implements ActionListener {
    private JFrame frame;
    private JLabel userLabel;
    private JTextField userText;
    private JLabel success;
    private JButton resetBtn;
    private JPanel panel;

    public ForgotPasswordPage() {
        frame = new JFrame();
        panel = new JPanel();
        frame.setSize(500, 500);
        frame.add(panel);

        panel.setLayout(null);

        Bounds userLabelBounds = new Bounds(10, 30, 80, 25);
        userLabel = this.generateLabelComponent("Username", userLabelBounds);
        panel.add(userLabel);

        Bounds userTextBounds = new Bounds(100, 30, 165, 25);
        userText = this.generateFieldComponent(userTextBounds);
        panel.add(userText);

        Bounds resetBtnBounds = new Bounds(100, 100, 160, 25);
        resetBtn = this.generateButtonComponent("Reset Password", resetBtnBounds);
        panel.add(resetBtn);

        success = new JLabel("");
        success.setBounds(10, 110, 300, 25);
        panel.add(success);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Generate Label component
     * @param componentLabel Name of the label.
     * @param bounds Bounds of the button.
     * @return JLabel
     */
    private JLabel generateLabelComponent(String componentLabel, Bounds bounds) {
        JLabel label = new JLabel(componentLabel);
        label.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        return label;
    }

    /**
     * Generate JField component
     * @param bounds Bounds of the button.
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

    @Override
    public void actionPerformed(ActionEvent e) {
        success.setText("An email has been sent with instructions on how to reset your password.");
        new LoginPage();
        frame.setVisible(false);
    }
}
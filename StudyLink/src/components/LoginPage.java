package components;

import helpers.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import components.Dashboard_components.*;;

/**
 * Login Page component responsible for authenticating the user and directing them to the
 * components.Dashboard Page.
 */
public class LoginPage implements ActionListener {
    private static final String LOGIN = "Login";
    private static final String FORGOT_PASSWORD = "Forgot Password";
    private static final String SIGN_UP = "Sign Up";
    private JFrame frame;
    private JLabel userLabel;
    private JTextField userText;
    private JLabel passwordLabel;
    private JTextField passwordText;
    private JLabel success;
    private JButton loginBtn;
    private JButton passwordResetBtn;
    private JButton signUpBtn;

    JPanel panel;
    public LoginPage() {
        frame = new JFrame();
        panel = new JPanel();
        frame.setSize(500, 500);
        frame.add(panel);

        panel.setLayout(null);

        Bounds userLabelBounds = new Bounds(10, 30, 80, 25);
        userLabel = BoundField.generateLabelComponent("User", userLabelBounds);
        panel.add(userLabel);

        Bounds userTextBounds = new Bounds(100, 30, 165, 25);
        userText = BoundField.generateFieldComponent(userTextBounds);
        panel.add(userText);

        Bounds passwordLabelBounds = new Bounds(10, 60, 80, 25);
        passwordLabel = BoundField.generateLabelComponent("Password", passwordLabelBounds);
        panel.add(passwordLabel);

        Bounds passwordFieldBounds = new Bounds(100, 60, 165, 25);
        passwordText = BoundField.generateFieldComponent(passwordFieldBounds);
        panel.add(passwordText);

        Bounds loginBtnBounds = new Bounds(80, 100, 80, 25);
        loginBtn = this.generateButtonComponent(LOGIN, loginBtnBounds);
        panel.add(loginBtn);

        Bounds passwordResetBounds = new Bounds(180, 100, 150, 25);
        passwordResetBtn = this.generateButtonComponent(FORGOT_PASSWORD, passwordResetBounds);
        panel.add(passwordResetBtn);

        Bounds signUpBtnBounds = new Bounds(350, 100, 110, 25);
        signUpBtn = this.generateButtonComponent(SIGN_UP, signUpBtnBounds);
        panel.add(signUpBtn);

        success = new JLabel("");
        success.setBounds(10, 110, 300, 25);
        panel.add(success);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

            if( UserList.instance.authemail(userText.getText()) && UserList.instance.authpass(passwordText.getText())){
                if(UserList.instance.x == UserList.instance.y) {
                    new Dashboard(); // opens up the dashboard
                    frame.setVisible(false);

                }
                else {
                    frame.dispose();
                    new LoginPage();
                    }





            }
            else {frame.dispose();
                new LoginPage();}




    }
        if (e.getActionCommand() == SIGN_UP){
            new SignUp();
            frame.setVisible(false);
        }
}}

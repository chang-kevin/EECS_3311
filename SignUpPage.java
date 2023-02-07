import helpers.Bounds;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SignUpPage implements ActionListener{
    private JFrame frame;
    private JPanel panel;

    private JLabel fNameLabel;
    private JLabel lNameLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JLabel success;
    private JTextField fNameText;
    private JTextField lNameText;
    private JTextField emailText;
    private JPasswordField passwordText;
    private JButton submitBtn;

    public SignUpPage(){
        frame = new JFrame();
        panel = new JPanel();
        frame.setSize(500, 500);
        frame.add(panel);
        panel.setLayout(null);

        Bounds fNameBounds = new Bounds(10, 30, 80, 25);
        fNameLabel = this.labelGenerator("First Name: ", fNameBounds);
        panel.add(fNameLabel);

        Bounds fNameTextBounds = new Bounds(100, 30, 165, 25);
        fNameText = this.fieldGenerator(fNameTextBounds);
        panel.add(fNameText);

        Bounds lNameBounds = new Bounds(10, 60, 80, 25);
        lNameLabel = this.labelGenerator("Last Name: ", lNameBounds);
        panel.add(lNameLabel);

        Bounds lNameTextBounds = new Bounds(100, 60, 165, 25);
        lNameText = this.fieldGenerator(lNameTextBounds);
        panel.add(lNameText);

        Bounds emailBounds = new Bounds(10, 100, 80, 25);
        emailLabel = this.labelGenerator("Email", emailBounds);
        panel.add(emailLabel);

        Bounds emailTextBounds = new Bounds(100, 100, 165, 25);
        emailText= this.fieldGenerator(emailTextBounds);
        panel.add(emailText);

        Bounds passwordLabelBounds = new Bounds(10, 140, 80, 25);
        passwordLabel = this.labelGenerator("Password", passwordLabelBounds);
        panel.add(passwordLabel);

        Bounds passwordTextBounds = new Bounds(100, 140, 165, 25);
        passwordText = this.passwordFieldGenerator(passwordTextBounds);
        panel.add(passwordText);

        Bounds signUpBtnBounds = new Bounds(80, 200, 70, 25);
        submitBtn = this.buttonGenerator("Submit", signUpBtnBounds);
        panel.add(submitBtn);

        success = new JLabel("");
        success.setBounds(10, 110, 300, 25);
        panel.add(success);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "Sign Up"){
            success.setText("Sign-up successful!");
        }
        
    }

    private JLabel labelGenerator(String fieldName, Bounds bounds){
        JLabel label = new JLabel(fieldName);
        label.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        return label;
    }

    private JTextField fieldGenerator(Bounds bounds){
        JTextField text = new JTextField(20);
        text.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        return text;
    } 

    private JPasswordField passwordFieldGenerator(Bounds bounds){
        JPasswordField text = new JPasswordField(20);
        text.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        return text;
    }

    private JButton buttonGenerator(String buttonName, Bounds bounds){
        JButton button = new JButton(buttonName);
        button.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        button.addActionListener(this);
        return button;
    }
}

package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.*;
import helpers.*;

/* 
 * Sign Up page responsbile for creating sign up form.
 */

/* This project makes the use of the userDOB class in the helper folder. The functionality of entering the DOB of the user has been extracted to the
userDOB class since that is an optional field.*/
public class SignUp implements ActionListener{
    private JFrame frame;
    private JTextField fNameText;
    private JTextField lNameText;
    private JTextField emailText;
    private JTextField passwordText;
    private JButton submitBtn;
    private userDOB userDOB = new userDOB();
    private User newUser;

    public SignUp(){
        frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(500, 500);
        frame.add(panel);
        panel.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Bounds signUpBounds = new Bounds(250, 0, 80, 25);
        JLabel signUpLabel = BoundField.generateLabelComponent("Sign-Up", signUpBounds);
        panel.add(signUpLabel);

        //Generate the sign up form
        Bounds fNameBounds = new Bounds(10, 30, 80, 25);
        JLabel fNameLabel = BoundField.generateLabelComponent("First Name: ", fNameBounds);
        panel.add(fNameLabel);

        Bounds fNameTextBounds = new Bounds(100, 30, 165, 25);
        fNameText = BoundField.generateFieldComponent(fNameTextBounds);
        panel.add(fNameText);

        Bounds lNameBounds = new Bounds(10, 60, 80, 25);
        JLabel lNameLabel = BoundField.generateLabelComponent("Last Name: ", lNameBounds);
        panel.add(lNameLabel);

        Bounds lNameTextBounds = new Bounds(100, 60, 165, 25);
        lNameText = BoundField.generateFieldComponent(lNameTextBounds);
        panel.add(lNameText);

        Bounds emailBounds = new Bounds(10, 100, 80, 25);
        JLabel emailLabel = BoundField.generateLabelComponent("Email", emailBounds);
        panel.add(emailLabel);

        Bounds emailTextBounds = new Bounds(100, 100, 165, 25);
        emailText= BoundField.generateFieldComponent(emailTextBounds);
        panel.add(emailText);

        Bounds passwordLabelBounds = new Bounds(10, 140, 80, 25);
        JLabel passwordLabel = BoundField.generateLabelComponent("Password", passwordLabelBounds);
        panel.add(passwordLabel);

        Bounds passwordTextBounds = new Bounds(100, 140, 165, 25);
        passwordText = BoundField.generatePasswordFieldComponent(passwordTextBounds);
        panel.add(passwordText);

        
        panel.add(userDOB.getDobLabel()); //Adding the DOB label
        panel.add(userDOB.getDateBox()); //Adding the Date combo box
        panel.add(userDOB.getMonthBox()); //Adding the month combo box
        panel.add(userDOB.getYearBox()); //Adding the year combo box

        Bounds signUpBtnBounds = new Bounds(80, 300, 70, 25);
        submitBtn = this.generateButtonComponent("Submit", signUpBtnBounds);
        panel.add(submitBtn);
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
        if(e.getSource() == submitBtn){
            //Checking to see if fields are empty and if they are show a pop-up stating to fill first name, last name, email, and passowrd fields
            if(fNameText.getText().isBlank()|| lNameText.getText().isBlank() || emailText.getText().isBlank() || passwordText.getText().isBlank()){
                JOptionPane.showMessageDialog(null, "Please enter your information", "Info Message", JOptionPane.INFORMATION_MESSAGE);
                fNameText.setText("");
                lNameText.setText("");
                emailText.setText("");
                passwordText.setText("");
            }

            //If the DOB field is empty then generate a new user object without the DOB parameter.
            else if(userDOB.getDateItem() == " " || userDOB.getYearItem() == " "){
                newUser = new User(fNameText.getText(), lNameText.getText(), emailText.getText(), String.valueOf(passwordText.getText()));
                UserList.emails.add(emailText.getText());
                UserList.passwords.add(passwordText.getText());
                new Login();
                frame.dispose();
            }
            
            //If the DOB field is filled then generate a new user object with the DOB parameter.
            else{
                //getting the DOB combo box contents
                int date = Integer.parseInt(userDOB.getDateItem());
                int month = userDOB.getMonthItem();
                int year = Integer.parseInt(userDOB.getYearItem());

                DOB birthDate = new DOB(date, month, year);
                newUser = new User(fNameText.getText(), lNameText.getText(), emailText.getText(), passwordText.getText(), birthDate);
                UserList.emails.add(emailText.getText());
                UserList.passwords.add(passwordText.getText());
                new Login(); //go back to the login page
                frame.dispose(); //delete the current frame
                System.out.println("Added to userlist");
            }
        }
    }
}

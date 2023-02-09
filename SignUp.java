package components;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.*;
import domainobjects.*;
import helpers.*;

/* 
 * Sign Up page responsbile for creating sign up form.
 */

public class SignUp implements ActionListener{
    private JFrame frame;
    private JPanel panel;
    private JLabel fNameLabel;
    private JLabel lNameLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JLabel success;
    private JLabel dobLabel;
    private JComboBox <String> dateBox;
    private JComboBox<String> monthBox;
    private JComboBox<String> yearBox;
    private JTextField fNameText;
    private JTextField lNameText;
    private JTextField emailText ;
    private JTextField passwordText;
    private JButton submitBtn;
    private String[] years;
    private String[] datesList;
    private String[] monthsShortList;
    private DOB birthDate;
    private int date;
    private int month;
    private int year;


    public SignUp(){
        frame = new JFrame();
        panel = new JPanel();
        frame.setSize(500, 500);
        frame.add(panel);
        panel.setLayout(null);

        Bounds signUpBounds = new Bounds(250, 0, 80, 25);
        JLabel signUpLabel = BoundField.generateLabelComponent("Sign-Up", signUpBounds);
        panel.add(signUpLabel);

        //Generate the sign up form
        Bounds fNameBounds = new Bounds(10, 30, 80, 25);
        fNameLabel = BoundField.generateLabelComponent("First Name: ", fNameBounds);
        panel.add(fNameLabel);

        Bounds fNameTextBounds = new Bounds(100, 30, 165, 25);
        fNameText = BoundField.generateFieldComponent(fNameTextBounds);
        panel.add(fNameText);

        Bounds lNameBounds = new Bounds(10, 60, 80, 25);
        lNameLabel = BoundField.generateLabelComponent("Last Name: ", lNameBounds);
        panel.add(lNameLabel);

        Bounds lNameTextBounds = new Bounds(100, 60, 165, 25);
        lNameText = BoundField.generateFieldComponent(lNameTextBounds);
        panel.add(lNameText);

        Bounds emailBounds = new Bounds(10, 100, 80, 25);
        emailLabel = BoundField.generateLabelComponent("Email", emailBounds);
        panel.add(emailLabel);

        Bounds emailTextBounds = new Bounds(100, 100, 165, 25);
        emailText= BoundField.generateFieldComponent(emailTextBounds);
        panel.add(emailText);

        Bounds passwordLabelBounds = new Bounds(10, 140, 80, 25);
        passwordLabel = BoundField.generateLabelComponent("Password", passwordLabelBounds);
        panel.add(passwordLabel);

        Bounds passwordTextBounds = new Bounds(100, 140, 165, 25);
        passwordText = BoundField.generateFieldComponent(passwordTextBounds);
        panel.add(passwordText);

        Bounds dobBounds = new Bounds(50, 200, 50, 25);
        dobLabel = BoundField.generateLabelComponent("DOB", dobBounds);
        panel.add(dobLabel);

        this.datesList = toStringArray(generateDates());
        Bounds datesBounds = new Bounds(100, 200, 100, 25);
        dateBox = generateJComboBox(datesList, datesBounds);
        dateBox.addActionListener(this);
        panel.add(dateBox);
        
        this.monthsShortList = toStringArray(generateMonths());
        Bounds monthBounds = new Bounds(200, 200, 100, 25);
        monthBox = generateJComboBox(monthsShortList, monthBounds);
        monthBox.addActionListener(this);
        panel.add(monthBox);

        this.years = toStringArray(generateYears());
        Bounds yearBounds = new Bounds(300, 200, 100, 25);
        yearBox = generateJComboBox(years, yearBounds);
        yearBox.addActionListener(this);
        panel.add(yearBox);

        Bounds signUpBtnBounds = new Bounds(80, 300, 70, 25);
        submitBtn = this.generateButtonComponent("Submit", signUpBtnBounds);
        panel.add(submitBtn);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        String str;
        if (e.getSource() == dateBox){
        	str = (String)dateBox.getSelectedItem();
            date = Integer.parseInt(str);
        	System.out.println(date);
        }

        if (e.getSource() == monthBox){
            month = monthBox.getSelectedIndex();
            month += 1;
            System.out.println(month);
        }

        if (e.getSource() == yearBox){
        	str = (String)yearBox.getSelectedItem();
            year = Integer.parseInt(str);
        	System.out.println(year);
        }

        if(e.getActionCommand() == "Submit"){
            success.setText("Sign-up successful!");
            birthDate = new DOB(date, month, year);
            User newUser = new User(fNameText.getText(), emailText.getText(), String.valueOf(passwordText.getText()), lNameText.getText(), birthDate);
            System.out.println("Generated user with date: " + date + " month: " + month + " year: " + year);
            success.setText("Created User obj");
            UserList.emails.add(emailText.getText());
            UserList.passwords.add(passwordText.getText());











            new LoginPage();
        }


    }

    /**
     * Generate the year list dynamically
     */
    private ArrayList<String> generateYears(){
        ArrayList<String> yearsList = new ArrayList<String>();
        int endYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int year = 1940; year <= endYear; year++){
            yearsList.add(Integer.toString(year));
        }
        return yearsList;
    }

    /*
     * generateDates will generate the dates of the month from 1 - 31
     */
    private ArrayList<String> generateDates(){
        ArrayList<String> datesArrayList = new ArrayList<String>();
       //Getting dates of the month (1 - 31)
        for (int dates = 1; dates <= 31; dates++){
            datesArrayList.add(Integer.toString(dates));
        }
        return datesArrayList;
    }

    /*
     * generateMonths will generate the names of the months Jan - Dec in 3 letter short form
     */
    private ArrayList<String> generateMonths(){
        ArrayList<String> monthsShortArrayList = new ArrayList<String>();
        //Storing the years from 1940 - current year
        String[] shortMonths = new DateFormatSymbols().getShortMonths();
        for(int i = 0; i < shortMonths.length - 1; i++){
            monthsShortArrayList.add(shortMonths[i]);
        }
        return monthsShortArrayList;
    }

    /* Convert an array list to primitive String array
     * @param strArr the array list to be converted
     * @return The converted arraylist to String array
     */
    private String[] toStringArray(ArrayList<String> strArr){
        return strArr.toArray(new String [strArr.size()]);
    }

    /*
     * generate a combo box for the months, days, and years
     * @param arr will take in the list of options
     * @param bounds will take in the size of the combo box
     * @return will return the combo box generated
     */

    public JComboBox <String> generateJComboBox (String[] arr, Bounds bounds){
        JComboBox<String> comboBox = new JComboBox<>(arr);
        comboBox.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        return comboBox;
    }
}

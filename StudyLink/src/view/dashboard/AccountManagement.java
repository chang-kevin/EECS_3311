package view.dashboard;

import helpers.UserRole;
import model.User.User;
import model.User.UserDAO;
import model.User.UserSession;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.border.*;


public class AccountManagement implements ActionListener {



    JPanel settings;
    private JButton save;
    private JLabel approveText;
    private UserDAO userDAO;
    private User sessionUser;
    private User user;
    private JTextField firstNameText;
    private JTextField lastNameText;
    private JLabel userEmail;
    private JLabel userRole;
    JButton backButton;

    public AccountManagement() throws SQLException {
        userDAO = new UserDAO();
        sessionUser = UserSession.getInstance().getCurrentUser();
        user = userDAO.getUser(sessionUser.getUsername());

        accountSettings();
    }

    public void accountSettings() {
        settings = new JPanel();
        settings.setBounds(10, 90, 517, 247);
        settings.setLayout(null);

        labelFields();
        userInformation();
        saveButton();
        backButton();
        approveChanges();

    }

    public void labelFields() {
        JLabel username = new JLabel("Username");
        username = labelLayout(username, 30);
        settings.add(username);

        JLabel firstName = new JLabel("First Name");
        firstName = labelLayout(firstName, 70);
        settings.add(firstName);

        JLabel lastName = new JLabel("Last Name");
        lastName = labelLayout(lastName, 110);
        settings.add(lastName);

        JLabel role = new JLabel("Role");
        role = labelLayout(role, 150);
        settings.add(role);

    }

    public JLabel labelLayout(JLabel label, int y) {
        label.setForeground(new Color(128, 128, 128));
        label.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
        label.setBounds(10, y, 107, 30);
        return label;
    }

    public void userInformation() {
        userEmail = new JLabel(user.getUsername());
        userEmail = (JLabel) editInformation(userEmail, 30);
        settings.add(userEmail);

        firstNameText = new JTextField(user.getFirstName());
        firstNameText = (JTextField) editInformation(firstNameText, 70);
        settings.add(firstNameText);

        lastNameText = new JTextField(user.getLastName());
        lastNameText = (JTextField) editInformation(lastNameText, 110);
        settings.add(lastNameText);

        userRole = new JLabel(user.getRole());
        userRole = (JLabel) editInformation(userRole, 150);
        settings.add(userRole);
    }

    public JComponent editInformation(JComponent info, int y) {
        info.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 13));
        info.setBounds(170, y, 270, 30);
        info.setBorder(new LineBorder(new Color(0, 0, 0)));
        if(info == userEmail || info == userRole){
            info.setBorder(null);
            info.setForeground(new Color(169,169,169));
        }

        return  info;
    }

    public void saveButton() {
        save = new JButton("Save");
        save = createButton(save);
        settings.add(save);

    }

    public JButton createButton(JButton button) {
        button.setBounds(247, 175, 115, 30);
        button.setBackground(new Color(171, 217, 168));
        button.setBorderPainted(false);
        button.setFont(new Font("Segoe UI Light", Font.PLAIN, 13));
        button.setFocusPainted(false);
        button.addActionListener(this);
        return button;

    }

    public void backButton() {
        backButton = new JButton("Back to My Courses");
        backButton.setHorizontalAlignment(SwingConstants.LEFT);
        backButton.setContentAreaFilled(false);
        backButton.setBorder(null);
        backButton.setBounds(10, 0, 131, 30);
        backButton.setForeground(new Color(244, 181, 181));
        backButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
        settings.add(backButton);
    }

    public void approveChanges() {
        approveText = new JLabel("Changes saved");
        approveText.setBounds(250, 213, 115, 22);
        approveText.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
        approveText.setForeground(new Color(128, 128, 128));
        approveText.setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == save) {
            User updatedUser = new User.UserBuilder(user.getUsername(), user.getPassword())
                    .setFirstName(firstNameText.getText())
                    .setLastName(lastNameText.getText())
                    .setRole(UserRole.STUDENT)
                    .build();
            try {
                userDAO.updateUserInfo(updatedUser);
                settings.add(approveText);
                settings.revalidate();
                settings.repaint();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }

    }



}

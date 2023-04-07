package view.dashboard;

import helpers.UserRole;
import model.User.User;
import model.User.UserDAO;
import model.User.UserSession;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AccountManagement extends RoundedPanel implements ActionListener {

    private GridBagConstraints c;
    private UserDAO userDAO;
    private User sessionUser;
    private User user;
    private JTextField firstNameText;
    private JTextField lastNameText;
    private JLabel userEmail;
    private JLabel userRole;
    private JButton save;
    private JLabel approveText;
    private String[] fields = {"Username", "First Name", "Last Name", "Role"};


    public AccountManagement() throws SQLException {
        super(30 ,30);
        userDAO = new UserDAO();
        sessionUser = UserSession.getInstance().getCurrentUser();
        user = userDAO.getUser(sessionUser.getUsername());

        setLayout(new GridBagLayout());
        setBackground(new Color(217, 230, 226));

        userInformation();
        setConstraints();
    }

    public void userInformation() {
        userEmail = new JLabel(user.getUsername());

        firstNameText = new JTextField(user.getFirstName());

        lastNameText = new JTextField(user.getLastName());

        userRole = new JLabel(user.getRole());
    }

    public void setConstraints() {
        c = new GridBagConstraints();


        add(setFieldsLayout(fields[0]), addLeftComponent(0, 0));


        add(userEmail, addRightComponent(1, 0));

        add(setFieldsLayout(fields[1]), addLeftComponent(0, 1));

        add(firstNameText, addRightComponent(1, 1));

        add(setFieldsLayout(fields[2]), addLeftComponent(0, 2));


        add(lastNameText, addRightComponent(1, 2));

        add(setFieldsLayout(fields[3]), addLeftComponent(0, 3));

        add(userRole, addRightComponent(1, 3));


        save = new JButton("Save Changes");
        buttonLayout(save);
        add(save, addLeftComponent(0, 4));


    }

    public GridBagConstraints addLeftComponent(int x, int y) {
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = 1;
        c.insets.left = 20;
        if(y == 1) {
            c.anchor = GridBagConstraints.WEST;
            c.weightx = 0;
        }
        if(y == 4) {
            c.weightx = 0.5;
        }
        return c;
    }

    public GridBagConstraints addRightComponent(int x, int y) {
        c.anchor = GridBagConstraints.EAST;
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = 3;
        c.weightx = 1;
        c.insets.right = 50;

        return c;
    }

    public JLabel setFieldsLayout(String text) {
        JLabel field = new JLabel(text);
        field.setBorder(new EmptyBorder(15, 15, 10, 15));
        field.setForeground(new Color(53, 79, 82));
        field.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 13));
        field.setHorizontalTextPosition(SwingConstants.CENTER);
        field.setHorizontalAlignment(SwingConstants.LEFT);
        return field;
    }

    public void buttonLayout(JButton save) {
        save.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 13));
        save.setBackground(new Color(74, 113, 117));
        save.setForeground(new Color(255, 255, 255));
        save.setPreferredSize(new Dimension(50, 25));
        save.setFocusPainted(false);
        save.addActionListener(this);
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
                add(setFieldsLayout("Saved"), addRightComponent(1, 4));
                revalidate();
                repaint();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }

    }



}

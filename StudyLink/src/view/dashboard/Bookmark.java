package view.dashboard;

import model.Course.Course;
import model.Course.CourseDAO;
import model.User.User;
import model.User.UserDAO;
import model.User.UserSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.border.*;

public class Bookmark implements ActionListener {

    JPanel bookmarkPanel;
    JPanel scrollPanel;
    private JLabel iconLabel;
    private JButton viewCourse;
    private JLabel name;
    private JButton removeCourse;
    List<Course> bookmarkCourses;
    private List<JButton> removeButtons;
    private UserDAO userDAO;
    private User userSession;
    private User user;
    private JButton refresh;
    private JPanel labelPanel;
    List<ViewButtons> viewBookmark;

    public Bookmark() throws SQLException {
        createBookmarks();

    }

    public void createBookmarks() throws SQLException {
        bookmarkPanel = new JPanel();
        bookmarkPanel.setBackground(new Color(255, 255, 255));
        bookmarkPanel.setBounds(10, 90, 517, 247);

        setUpPage();

    }

    public void setUpPage() throws SQLException {
        createContainer();
        addScrollPane();

        labelPanel = new JPanel();
        labelPanel = setPanelLayout(labelPanel);
        labelPanel = setTitleLayout(labelPanel);
        scrollPanel.add(labelPanel);

        viewBookmark = new ArrayList<>();

        getBookmarkedCourses();

        removeButtons = new ArrayList<>();


        if(bookmarkCourses != null) {
            for(Course course : bookmarkCourses) {
                ViewButtons viewButton = new ViewButtons(course.getViewButton(), course);
                addBookmark(course);
                viewBookmark.add(viewButton);
            }
        }
    }

    public void createContainer() {
        scrollPanel = new JPanel();
        scrollPanel.setBackground(new Color(255, 255, 255));
        scrollPanel.setBounds(10, 90, 517, 247);
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));

    }

    public void addScrollPane() {
        JScrollPane scrollpane = new JScrollPane(scrollPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpane.setPreferredSize(new Dimension(517, 247));
        scrollpane.getVerticalScrollBar().setUnitIncrement(5);
        scrollpane.getVerticalScrollBar().setUI(new ScrollBarCustom());
        scrollpane.getVerticalScrollBar().setBackground(new Color(255, 255, 255));
        scrollpane.getVerticalScrollBar().setPreferredSize(new Dimension(8, 8));
        scrollpane.setBorder(null);
        bookmarkPanel.add(scrollpane);
    }

    public JPanel setPanelLayout(JPanel container) {
        container.setBackground(new Color(255, 255, 255));
        container.setBorder(new MatteBorder(0, 0, 1, 0, new Color(192, 192, 192)) );
        container.setPreferredSize(new Dimension(495, 50));
        container.setMaximumSize(new Dimension(495, 50));
        return container;
    }

    public JPanel setTitleLayout(JPanel labelPanel) {
        JLabel title = new JLabel("My Courses");
        title.setForeground(new Color(74, 74, 74));
        title.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
        title.setPreferredSize(new Dimension(360, 40));
        title.setMaximumSize(new Dimension(360, 40));
        labelPanel.add(title);
        refresh = new JButton();
        refresh = buttonStyler(refresh, "Refresh");
        labelPanel.add(refresh);

        return labelPanel;
    }

    public void addBookmark(Course course) {
        JPanel boxPanel = new JPanel();
        boxPanel = setPanelLayout(boxPanel);
        boxPanel = setBookmark(boxPanel, course.getCourseName(), course.getCourseCode());
        scrollPanel.add(boxPanel);
    }

    public void removeBookmark(int index) throws SQLException {
        userDAO = new UserDAO();
        userSession = UserSession.getInstance().getCurrentUser();
        user = userDAO.getUser(userSession.getUsername());

        for (Course course : bookmarkCourses) {
            if (bookmarkCourses.indexOf(course) == index) {
                UserDAO.removeUserCourse(String.valueOf(course.getCourseId()));
            }
        }
    }

    public JPanel setBookmark(JPanel boxPanel, String courseName, String courseCode) {
        boxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        boxPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        iconLabel = new JLabel();
        iconLabel = setIconDimension(iconLabel);

        String path = "/B5BCDA.png";
        ImageIcon icon = setIconLayout(path, 40, 40);
        iconLabel.setIcon(icon);

        boxPanel.add(iconLabel);

        name = new JLabel();
        name = setCourseInfo(name, courseName, courseCode);
        boxPanel.add(name);

        viewCourse = new JButton();
        viewCourse = buttonStyler(viewCourse, "View");
        boxPanel.add(viewCourse);


        removeCourse = new JButton();
        removeCourse = setRemoveButton(removeCourse);
        removeButtons.add(removeCourse);
        boxPanel.add(removeCourse);

        return boxPanel;

    }

    private JLabel setIconDimension(JLabel iconLabel) {
        Dimension size = new Dimension(60, 40);
        iconLabel.setPreferredSize(size);
        iconLabel.setMaximumSize(size);
        iconLabel.setMinimumSize(size);
        return iconLabel;
    }

    public ImageIcon setIconLayout(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image newIcon = icon.getImage();
        newIcon = newIcon.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newIcon);
        return icon;
    }

    public JLabel setCourseInfo(JLabel name, String courseName, String courseCode) {
        name.setMaximumSize(new Dimension(230, 40));
        name.setPreferredSize(new Dimension(230, 40));
        name.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
        name.setForeground(new Color(150, 150, 150));
        name.setText("<html><b><font color='gray'>" + courseName + "</b><br/>" + courseCode + "</font></html>");

        return name;
    }

    public JButton buttonStyler(JButton button, String text) {
        button.setText(text);
        button.setFont(new Font("Dubai", Font.BOLD, 13));
        button.setOpaque(true);
        button.setBackground(new Color(216, 237, 214));
        button.setForeground(new Color(255, 255, 255));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(80, 25));
        button.setMaximumSize(new Dimension(80, 25));
        button.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
        button.addActionListener(this);
        return button;
    }

    public JButton setRemoveButton(JButton remove) {
        remove.setText("Remove");
        remove.setMargin(new Insets(0, 25, 0, 0));
        remove.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 11));
        remove.setHorizontalAlignment(SwingConstants.LEFT);
        remove.setBorderPainted(false);
        remove.setForeground(new Color(128, 128, 128));
        remove.setBackground(new Color(216, 237, 214));
        remove.setFocusPainted(false);
        remove.setOpaque(false);
        remove.setContentAreaFilled(false);
        remove.addActionListener(this);

        String removeIcon = "/remove.png";
        ImageIcon bin = setIconLayout(removeIcon, 15, 15);

        remove.setIcon(bin);
        remove.setIconTextGap(3);

        remove.setHorizontalAlignment(SwingConstants.LEFT);
        remove.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        remove.setHorizontalTextPosition(SwingConstants.RIGHT);

        return remove;
    }

    public void getBookmarkedCourses() throws SQLException {
        userDAO = new UserDAO();
        userSession = UserSession.getInstance().getCurrentUser();
        user = userDAO.getUser(userSession.getUsername());
        bookmarkCourses = UserDAO.getUserCourses(user.getUsername());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(JButton btn : removeButtons) {
            if(e.getSource() == btn) {
                try {
                    removeBookmark(removeButtons.indexOf(btn));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                scrollPanel.remove(btn.getParent());
                scrollPanel.revalidate();
                scrollPanel.repaint();
            }
        }
        if(e.getSource() == refresh) {
            bookmarkPanel.removeAll();
            bookmarkPanel.revalidate();

            try {
                setUpPage();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }



    }


}

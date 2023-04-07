package view.dashboard;

import model.Course.Course;
import model.User.User;
import model.User.UserDAO;
import model.User.UserSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.*;

public class Bookmark extends RoundedPanel implements ActionListener {

    private JLabel iconLabel;
    private JButton viewCourse;
    private JLabel name;
    private JButton removeCourse;
    List<Course> bookmarkCourses;
    private List<JButton> removeButtons;
    private UserDAO userDAO;
    private User userSession;
    private User user;
    List<ViewButtons> viewBookmark;
    private JPanel contentPanel;

    public Bookmark() throws SQLException {
        super(30, 30);

        setBackground(new Color(217, 230, 226));
        userDAO = new UserDAO();
        userSession = UserSession.getInstance().getCurrentUser();
        user = userDAO.getUser(userSession.getUsername());


        viewBookmark = new ArrayList<>();
        removeButtons = new ArrayList<>();
        bookmarkCourses = new ArrayList<>();

        setLayoutPanel();
        setConstraints();
        setUpPage();


    }
    public void setConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(createTitle("My Courses"), gbc);
        createCoursePane();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(addScrollPane(), gbc);

    }

    public JLabel createTitle(String text) {
        JLabel title = new JLabel(text);
        title.setBorder(new EmptyBorder(15, 15, 10, 15));
        title.setForeground(new Color(53, 79, 82));
        title.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 18));
        title.setHorizontalTextPosition(SwingConstants.LEFT);
        return title;
    }

    public void createCoursePane() {
        contentPanel = new JPanel();
        contentPanel.setBackground(new Color(255, 255, 255));
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
    }

    public JScrollPane addScrollPane() {
        JScrollPane scrollpane = new JScrollPane(contentPanel);
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollpane.getVerticalScrollBar().setUnitIncrement(5);
        scrollpane.getVerticalScrollBar().setUI(new ScrollBarCustom());
        scrollpane.getVerticalScrollBar().setBackground(new Color(232, 240, 238));
        scrollpane.getVerticalScrollBar().setPreferredSize(new Dimension(8, 8));
        scrollpane.setBorder(null);
        return scrollpane;
    }

    public void setLayoutPanel() {
        GridBagLayout layout = new GridBagLayout();
        layout.columnWidths = new int[]{0, 0};
        layout.rowHeights = new int[]{0, 0, 0};
        layout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        layout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        setLayout(layout);
    }


    public void setUpPage() throws SQLException {
        bookmarkCourses = UserDAO.getUserCourses(user.getUsername());
        setBookmarkBtn();

        if(bookmarkCourses != null) {
            for(Course course : bookmarkCourses) {
                addBookmark(course);
            }
        }
    }



    public JPanel setPanelLayout(JPanel container) {
        container.setBackground(new Color(255, 255, 255));
        container.setBorder(new MatteBorder(0, 0, 1, 0, new Color(192, 192, 192)) );
        container.setPreferredSize(new Dimension(495, 50));
        container.setMaximumSize(new Dimension(495, 50));
        return container;
    }


    public void addBookmark(Course course) {
        JPanel boxPanel = new JPanel();
        boxPanel = setPanelLayout(boxPanel);
        boxPanel = setBookmark(boxPanel, course.getCourseName(), course.getCourseCode(), course);
        contentPanel.add(boxPanel);
        revalidate();
        repaint();

        if(viewBookmark != null) {
            ViewButtons view = new ViewButtons(course.getViewButton(), course);
            viewBookmark.add(view);
        }

        if(bookmarkCourses != null) {
            if(!bookmarkCourses.contains(course)) {
                bookmarkCourses.add(course);
            }
        }

    }

    public void removeBookmark(int index) throws SQLException {
        if(bookmarkCourses != null){
            for (Course course : bookmarkCourses) {
                if (bookmarkCourses.indexOf(course) == index) {
                    UserDAO.removeUserCourse(String.valueOf(course.getCourseId()));
                }
            }
        }

    }

    public JPanel setBookmark(JPanel boxPanel, String courseName, String courseCode, Course e) {
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

        boxPanel.add(e.getViewButton());


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

    public void setBookmarkBtn() {
        if(bookmarkCourses != null){
            for(Course course : bookmarkCourses) {
                String text = "View";
                viewCourse = new JButton();
                viewCourse = buttonStyler(viewCourse, text);
                course.setViewButton(viewCourse);
            }
        }

    }

    public JButton buttonStyler(JButton button, String text) {
        button.setText(text);
        button.setFont(new Font("Dubai", Font.BOLD, 13));
        button.setOpaque(true);
        button.setBackground(new Color(74, 113, 117));
        button.setForeground(new Color(255, 255, 255));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(80, 25));
        button.setMaximumSize(new Dimension(80, 25));
        button.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
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

    @Override
    public void actionPerformed(ActionEvent e) {
        for(JButton btn : removeButtons) {
            if(e.getSource() == btn) {
                try {
                    removeBookmark(removeButtons.indexOf(btn));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                contentPanel.remove(btn.getParent());
                contentPanel.revalidate();
                contentPanel.repaint();
            }
        }
//        if(e.getSource() == refresh) {
//            bookmarkPanel.removeAll();
//            bookmarkPanel.revalidate();
//
//            try {
//                setUpPage();
//            } catch (SQLException ex) {
//                throw new RuntimeException(ex);
//            }
//
//        }



    }


}

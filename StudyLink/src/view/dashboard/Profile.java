package view.dashboard;

import controller.AccountManagement;
import model.User.User;
import model.User.UserDAO;
import model.User.UserSession;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;

import javax.swing.*;

public class Profile extends JPanel {

    private UserDAO userDAO;
    private User sessionUser;
    private User user;
    JPanel accountInfo;
    private JLabel profileImagePanel;
    JPanel profile;
    JButton logoutBtn;
    private SearchBar searchbar;
    private JLabel role;


    public Profile() throws SQLException {
        profile = new JPanel();
        profile.setBackground(new Color(216, 237, 214));
        profile.setBounds(716, 0, 228, 561);
        profile.setLayout(null);

        userDAO = new UserDAO();
        sessionUser = UserSession.getInstance().getCurrentUser();
        user = userDAO.getUser(sessionUser.getUsername());

        createProfilePanel();

    }


    public JPanel createProfilePanel() {
        JPanel calendar = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension arcs = new Dimension(50, 50);
                int width = getWidth();
                int height = getHeight();
                Graphics2D graphics = (Graphics2D) g;
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics.setColor(new Color(255, 255, 255));
                graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
                graphics.setColor(new Color(255, 255, 255));
                graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
            }
        };

        calendar.setBounds(10, 294, 210, 247);

        calendar.setLayout(null);
        calendar.setOpaque(false);
        calendar.setBorder(null);

        calendar = createCalendar(calendar);
        profile.add(calendar);

        userInfoDisplay();

        createSearchbar();
        createLogout();

        return profile;
    }
    public void userInfoDisplay() {
        accountInfo = new JPanel();
        accountInfo = panelBorder(accountInfo);
        accountInfo.setBounds(10, 40, 208, 194);
        profile.add(accountInfo);

        setName();
        viewRole();
        setProfileImage();

    }
    public void setName() {
        JLabel userName = new JLabel(user.getFirstName() + " " + user.getLastName());
        userName.setForeground(new Color(128, 128, 128));
        userName.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 13));
        userName.setBorder(null);
        userName.setHorizontalAlignment(SwingConstants.CENTER);
        userName.setBounds(43, 124, 120, 25);
        accountInfo.add(userName);

    }


    public void viewRole() {
        role = new JLabel(user.getRole());
        role.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
        role.setForeground(new Color(244, 181, 181));
        role.setBackground(new Color(255, 255, 255, 200));
        role.setBorder(null);
        role.setBounds(85, 150, 90, 23);
        accountInfo.add(role);
    }

    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 2, 1, w, h, null);
        g2.dispose();

        return resizedImg;
    }

    public void setProfileImage() {
        profileImagePanel = new JLabel();
        profileImagePanel.setBounds(45, 11, 115, 110);

        String path = "/profilepicture.png";
        ImageIcon image = new ImageIcon(getClass().getResource(path));
        Image newImage = getScaledImage(image.getImage(), profileImagePanel.getWidth(), profileImagePanel.getHeight());
        ImageIcon icon = new ImageIcon(newImage);

        profileImagePanel.setIcon(icon);
        accountInfo.add(profileImagePanel);
    }

    public JPanel panelBorder(JPanel panel) {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension arcs = new Dimension(50, 50);
                int width = getWidth();
                int height = getHeight();
                Graphics2D graphics = (Graphics2D) g;
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics.setColor(new Color(255, 255, 255, 200));
                graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
                graphics.setColor(new Color(216, 237, 214));
                graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
            }
        };
        panel.setOpaque(false);
        panel.setLayout(null);


        return panel;
    }
    public void createSearchbar() {
        searchbar = new SearchBar();
        profile.add(searchbar.searchIcon);
        profile.add(searchbar.searchbar);
    }


    public JPanel createCalendar(JPanel calendar) {
        LocalDate dates = LocalDate.now();
        JLabel month = new JLabel();
        month = getMonth(calendar, month, dates);

        JTextPane[] weeks = new JTextPane[7];
        weeks = getWeek(weeks, calendar);


        int numOfDays = dates.lengthOfMonth();
        JTextPane[] days = new JTextPane[numOfDays + 1];
        days = getDates(numOfDays, days, dates, calendar);

        return calendar;

    }

    public JLabel getMonth(JPanel calendar, JLabel month, LocalDate dates) {
        String monthName = dates.getMonth().toString();
        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        for(int i = 0; i < months.length; i++) {
            if(monthName.equalsIgnoreCase(months[i])) {
                month.setText(months[i]);
            }
        }

        month.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 17));
        month.setForeground(new Color(239, 127, 127));
        month.setBackground(new Color(187, 223, 183, 25));
        month.setBorder(null);
        month.setBounds(10, 10, 134, 32);

        calendar.add(month);
        return month;
    }

    public JTextPane[] getWeek(JTextPane[] weeks, JPanel calendar) {
        String[] daysOfWeek = {"Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"};

        int x = 10;
        for(int i = 0; i < 7; i++) {
            weeks[i] = new JTextPane();
            weeks[i].setText(daysOfWeek[i]);
            weeks[i].setBounds(x, 45, 30, 30);
            weeks[i] = styler(weeks[i]);
            calendar.add(weeks[i]);
            x+= 30;

        }

        return weeks;

    }
    public JTextPane[] getDates(int num, JTextPane[] days, LocalDate dates, JPanel calendar) {

        DayOfWeek firstOfMonth = dates.withDayOfMonth(1).getDayOfWeek();

        String[] dayOfWeek = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
        int start = 0;
        for(int i = 0; i < 7; i++) {
            if(dayOfWeek[i] == firstOfMonth.toString()) {
                start = i;
            }
        }

        int y = 75;
        start = start * 30 + 10;
        int x = start;

        for(int i = 1; i <= num; i++) {
            days[i] = new JTextPane();
            days[i].setText(String.valueOf(i));
            if(x < 210) {
                days[i].setBounds(x, y, 30, 30);
                x = x + 30;
            }
            else {
                x = 10;
                y = y + 30;
                days[i].setBounds(x, y, 30, 30);
                x = x + 30;
            }
            days[i] = styler(days[i]);
            calendar.add(days[i]);

            calendar.add(days[i]);
        }
        return days;

    }

    public JTextPane styler(JTextPane box) {
        box.setMargin(new Insets(8, 8, 8, 8));
        box.setFont(new Font("Tahoma", Font.PLAIN, 11));
        box.setBackground(new Color(187, 223, 183, 25));
        box.setForeground(new Color(239, 127, 127));
        box.setBorder(null);
        box.getHighlighter().removeAllHighlights();
        box.setEditable(false);
        box.setOpaque(false);
        return box;
    }
    public JButton createLogout() {
        logoutBtn = new JButton("Log out");
        logoutBtn.setForeground(new Color(239, 127, 127));
        logoutBtn.setMargin(new Insets(0, 0, 0, 0));
        logoutBtn.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
        logoutBtn.setHorizontalAlignment(SwingConstants.LEFT);
        logoutBtn.setBorderPainted(false);
        logoutBtn.setBackground(new Color(216, 237, 214));
        logoutBtn.setBounds(135, 0, 86, 30);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setOpaque(false);
        logoutBtn.setContentAreaFilled(false);
        logoutIcon();
        logoutSize();
        profile.add(logoutBtn);
        return logoutBtn;
    }

    public void logoutIcon() {
        String path = "/logout.png";
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image newIcon = icon.getImage();
        newIcon = newIcon.getScaledInstance(30, 25, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newIcon);
        logoutBtn.setIcon(icon);
        logoutBtn.setIconTextGap(3);

        logoutBtn.setHorizontalAlignment(SwingConstants.RIGHT);
        logoutBtn.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        logoutBtn.setHorizontalTextPosition(SwingConstants.LEFT);
    }
    public void logoutSize() {
        Dimension size = new Dimension(40, 30);
        logoutBtn.setPreferredSize(size);
        logoutBtn.setMaximumSize(size);
        logoutBtn.setMinimumSize(size);
    }





}

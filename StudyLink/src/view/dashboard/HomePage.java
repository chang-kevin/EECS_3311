package view.dashboard;


import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class HomePage extends JPanel{


    JPanel dashboard;
    JPanel oneLevel;

    private JTextPane timeWidget;
    private JTextPane widget_1;

    public HomePage() {
        createHomePage();
    }

    public void createHomePage() {
        dashboard = new JPanel();
        dashboard = panelBorder();
        dashboard.setBounds(169, 203, 537, 348);

        createBookmarks(dashboard);

        timeWidget = new JTextPane();
        timeWidget = widgets(timeWidget, 20, dashboard);

        widget_1 = new JTextPane();
        widget_1 = widgets(widget_1, 293, dashboard);


    }

    public void createBookmarks(JPanel dashboard) {
        JPanel bookmark = new JPanel();
        bookmark.setBounds(10, 90, 517, 247);
        dashboard.add(bookmark);
        GridBagLayout gbl_bookmark = new GridBagLayout();
        gbl_bookmark.columnWidths = new int[]{0, 0};
        gbl_bookmark.rowHeights = new int[]{50, 50, 0};
        gbl_bookmark.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_bookmark.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        bookmark.setLayout(gbl_bookmark);
        {
            JLabel myCourses = new JLabel(" My Courses");
            myCourses.setFont(new Font("Microsoft JhengHei", Font.BOLD, 20));
            myCourses.setForeground(new Color(137, 180, 148));
            GridBagConstraints gbc_myCourses = new GridBagConstraints();
            gbc_myCourses.fill = GridBagConstraints.BOTH;
            bookmark.add(myCourses, gbc_myCourses);

            JLabel pinCourse = new JLabel();
            pinCourse = getBookmarks(1, bookmark, pinCourse, "");

        }

    }

    public JLabel getBookmarks(int y, JPanel bookmark, JLabel pinCourse, String text) {
        pinCourse = new JLabel(text);
        pinCourse.setFont(new Font("Microsoft JhengHei", Font.BOLD, 15));
        pinCourse.setForeground(new Color(137, 180, 148));
        GridBagConstraints gbc_pinCourse = new GridBagConstraints();
        gbc_pinCourse.fill = GridBagConstraints.BOTH;
        gbc_pinCourse.gridy = 1;
        bookmark.add(pinCourse, gbc_pinCourse);

        return pinCourse;
    }

    public JTextPane widgets(JTextPane widget, int x, JPanel dashboard) {
        widget = new JTextPane() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension arcs = new Dimension(30, 30);
                int width = getWidth();
                int height = getHeight();
                Graphics2D graphics = (Graphics2D) g;
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics.setColor(new Color(202, 222, 237, 150));
                graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
                graphics.setColor(new Color(202, 222, 237, 150));
                graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
            }

        };
        widget.setEditable(false);
        widget.setBounds(x, 10, 224, 57);
        widget.setBorder(null);
        widget.setOpaque(false);

        if(x == 20) {
            LocalDateTime time = LocalDateTime.now();
            JLabel text = new JLabel();
            if(time.getMinute() < 10) {
                text.setText(String.valueOf(" " + time.getHour() + ":0"+ time.getMinute()));
            }
            else {
                text.setText(String.valueOf(" " + time.getHour() + ":"+ time.getMinute()));
            }
            text.setBounds(x, 10, 135, 57);
            text.setBorder(null);
            text.setBackground(new Color(219,237,255));
            text.setForeground(new Color(255, 255, 255));
            text.setFont(new Font("Arial Narrow", Font.PLAIN, 45));
            dashboard.add(text);
            String week = String.valueOf(time.getDayOfWeek());
            LocalDate day = LocalDate.now();
            String date = String.valueOf(day);
            JLabel dateText = new JLabel();
            dateText.setText("<html>" + week + "<br/>" + date + "<html>");
            dateText.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
            dateText.setBorder(null);
            dateText.setBackground(new Color(219,237,255));
            dateText.setForeground(new Color(255, 255, 255));
            dateText.setBounds(135, 10, 114, 57);
            dashboard.add(dateText);
        }

        dashboard.add(widget);


        return widget;
    }

    public JPanel panelBorder() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension arcs = new Dimension(50, 50);
                int width = getWidth();
                int height = getHeight();
                Graphics2D graphics = (Graphics2D) g;
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics.setColor(getBackground());
                graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
                graphics.setColor(getForeground());
                graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
            }
        };

        panel.setForeground(new Color(239, 239, 239, 75));
        panel.setBackground(new Color(239, 239, 239, 75));

        panel.setBorder(null);
        panel.setOpaque(false);
        panel.setLayout(null);


        return panel;
    }

}

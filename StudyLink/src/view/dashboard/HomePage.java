package view.dashboard;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.*;
import javax.swing.*;

public class HomePage extends JPanel implements ActionListener {


    JPanel dashboard;
    JPanel homePanel;
    private CardLayout cardLayout;
    JButton settings;
    JLabel settingsWidget;
    JLabel userFiles;
    private JLabel timeWidget;
    private static final String bookmarkPanel = "Bookmark";
    private static final String settingsPanel = "Settings";
    private AccountManagement account;


    public HomePage() throws SQLException {
        createHomePage();
    }

    public void createHomePage() throws SQLException {
        dashboard = new JPanel();
        dashboard = panelBorder();
        dashboard.setBounds(169, 203, 537, 348);

        widgets();
        homePageCard();
        backButton();

    }

    public void widgets() {
        timeWidget = new JLabel();
        timeWidget = widgetLayout(timeWidget, 20, 224, new Color(135, 146, 198, 150));
        clockText(timeWidget);

        settingsWidget = new JLabel();
        settingsWidget = widgetLayout(settingsWidget, 259, 124, new Color(173, 214, 209, 150));
        setSettingsWidget();

        userFiles= new JLabel("New button");
        userFiles = widgetLayout(userFiles, 393, 124, new Color(237, 203, 222, 150));
    }


    public void clockText(JLabel clock) {
        LocalDateTime time = LocalDateTime.now();
        JLabel text = new JLabel();
        if(time.getMinute() < 10) {
            text.setText(String.valueOf(" " + time.getHour() + ":0"+ time.getMinute()));
        }
        else {
            text.setText(String.valueOf(" " + time.getHour() + ":"+ time.getMinute()));
        }
        text.setBounds(0, 0, 135, 57);
        text.setBorder(null);
        text.setBackground(new Color(219,237,255));
        text.setForeground(new Color(255, 255, 255));
        text.setFont(new Font("Arial Narrow", Font.PLAIN, 45));
        clock.add(text);
        String week = String.valueOf(time.getDayOfWeek());
        LocalDate day = LocalDate.now();
        String date = String.valueOf(day);

        JLabel dateText = new JLabel();
        dateText.setText("<html>" + week + "<br/>" + date + "<html>");
        dateText.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
        dateText.setBorder(null);
        dateText.setBackground(new Color(219,237,255));
        dateText.setForeground(new Color(255, 255, 255));
        dateText.setBounds(115, 0, 114, 57);
        clock.add(dateText);

    }

    public void setSettingsWidget() {
        settings = new JButton("Settings");
        settings.setBounds(6, 3, 110, 51);
        settings.setMargin(new Insets(0, 0, 0, 0));
        settings.setForeground(new Color(255, 255, 255));
        settings.setBorderPainted(false);
        settings.setFocusPainted(false);
        settings.setOpaque(false);
        settings.setContentAreaFilled(false);
        settingsWidget.add(settings);
        settings.addActionListener(this);
        widgetIcon();
        imageSize();
    }

    public void widgetIcon() {
		String path = "/settings.png";
		ImageIcon icon = new ImageIcon(getClass().getResource(path));
		Image newIcon = icon.getImage();
		newIcon = newIcon.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
		icon = new ImageIcon(newIcon);
		settings.setIcon(icon);
        settings.setIconTextGap(3);

        settings.setHorizontalAlignment(SwingConstants.LEFT);
        settings.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        settings.setHorizontalTextPosition(SwingConstants.RIGHT);
	}
	public void imageSize() {
		Dimension size = new Dimension(110, 51);
        settings.setPreferredSize(size);
        settings.setMaximumSize(size);
        settings.setMinimumSize(size);
        settings.addActionListener(this);
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

    public void homePageCard() throws SQLException {
        homePanel = new JPanel();
        homePanel.setBounds(10, 90, 517, 247);
        homePanel = setCardLayout(homePanel);
        dashboard.add(homePanel);
    }

    public JPanel setCardLayout(JPanel homePanel) throws SQLException {
        cardLayout = new CardLayout(0, 0);
        homePanel.setLayout(cardLayout);

        childCard();

        return homePanel;
    }


    public void childCard() throws SQLException {
        Bookmark bookmark = new Bookmark();
        homePanel.add(bookmark.bookmark, bookmarkPanel);

        account = new AccountManagement();
        homePanel.add(account.settings, settingsPanel);

    }


    public JLabel widgetLayout(JLabel widget, int x, int w, Color color) {
        widget = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension arcs = new Dimension(30, 30);
                int width = getWidth();
                int height = getHeight();
                Graphics2D graphics = (Graphics2D) g;
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics.setColor(color);
                graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
                graphics.setColor(color);
                graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
            }

        };

        widget.setBounds(x, 10, w, 57);
        widget.setBorder(null);
        widget.setOpaque(false);
        dashboard.add(widget);

        return widget;
    }

    public void backButton() {
        account.backButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == settings) {
            cardLayout.show(homePanel, settingsPanel);
        }
        else if(e.getSource() == account.backButton) {
            cardLayout.show(homePanel, bookmarkPanel);
        }
    }

}

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

    private static final String bookmarkPanel = "Bookmark";
    private static final String settingsPanel = "Settings";
    private AccountManagement account;
    private Object settings;


    public HomePage() throws SQLException {
        createHomePage();
    }

    public void createHomePage() throws SQLException {
        dashboard = new JPanel();
        dashboard = panelBorder();
        dashboard.setBounds(169, 203, 537, 348);

        homePageCard();
        backButton();

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
        homePanel.add(bookmark.bookmarkPanel, bookmarkPanel);

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
                graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
                graphics.setColor(color);
                graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
            }

        };
        widget.setBounds(x, 10, w, 57);
        widget.setBorder(null);
        widget.setOpaque(false);

        return widget;
    }


    public void backButton() {
        account.backButton.addActionListener(this);
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

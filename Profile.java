package view.dashboard;

import java.awt.*;
import javax.swing.*;

public class Profile extends JPanel {


    JPanel profile;

    public Profile() {
        profile = new JPanel();
        profile = profileDisplay();


    }

    public JPanel profileDisplay() {
        profile = new JPanel() {
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
                graphics.setColor(new Color(216, 237, 214));
                graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
            }
        };
        profile.setOpaque(false);


        profile.setBounds(10, 11, 208, 194);

        return profile;
    }



}

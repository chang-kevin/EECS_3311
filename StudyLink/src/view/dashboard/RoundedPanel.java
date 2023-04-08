package view.dashboard;

import java.awt.*;
import javax.swing.*;

public class RoundedPanel extends JComponent {
    private int width = 0;
    private int height = 0;

    public RoundedPanel(int width, int height) {
        this.width = width;
        this.height = height;
        setOpaque(false);
    }

    public Dimension getDimensions() {
        return new Dimension(width, height);
    }

    public int getArcWidth() {
        Dimension arc = getDimensions();
        return arc.width;
    }

    public int getArcHeight() {
        Dimension arc = getDimensions();
        return arc.height;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, width - 1, height - 1, getArcWidth(), getArcHeight());
        g2.drawRoundRect(0, 0, width - 1, height - 1, getArcWidth(), getArcHeight());
    }

}

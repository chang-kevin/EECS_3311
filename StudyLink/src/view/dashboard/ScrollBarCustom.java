package view.dashboard;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class ScrollBarCustom extends BasicScrollBarUI {

    private int thumbsize = 40;

    @Override
    protected Dimension getMaximumThumbSize() {
        return new Dimension(0, thumbsize);
    }

    @Override
    protected Dimension getMinimumThumbSize() {
        return new Dimension(0, thumbsize);
    }



    @Override
    protected JButton createIncreaseButton(int orientation) {
        JButton button = new JButton();
        button.setBorder(BorderFactory.createEmptyBorder());
        return button;

    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        JButton button = new JButton();
        button.setBorder(BorderFactory.createEmptyBorder());
        return button;
    }

    @Override
    protected void paintTrack(Graphics g, JComponent component, Rectangle r) {
        super.paintTrack(g, component, r);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = r.width / 2;
        int x = r.x + ((r.width - width) / 2);
        g2.setColor(new Color(232, 240, 238));
        g2.fillRect(x, r.y, width, r.height);

    }

    @Override
    protected void paintThumb(Graphics g, JComponent component, Rectangle r) {
        super.paintTrack(g, component, r);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int y = r.y + 5;
        int height = r.height;

        g2.setColor(new Color(53, 79, 82));
        g2.fillRoundRect(r.x, y, r.width, height, 10, 10);
    }

}

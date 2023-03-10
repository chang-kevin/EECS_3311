package helpers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HyperlinkReg {

    private JLabel hyperlink;

    public HyperlinkReg(final String link) {

        hyperlink = new JLabel(link);
		hyperlink.setForeground(Color.BLUE);
		hyperlink.setCursor(new Cursor(Cursor.HAND_CURSOR));

		hyperlink.addMouseListener(new MouseAdapter () {
			@Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(link));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
 
            @Override
            public void mouseExited(MouseEvent e) {
                hyperlink.setText(link);
            }
 
            @Override
            public void mouseEntered(MouseEvent e) {
                hyperlink.setText("<html><a href=''>" + link + "</a></html>");
            }
			
		});

    }

    public void setBounds(int x, int y, int width, int height) {
        hyperlink.setBounds(x, y, width, height);
    }

    public JLabel getHyperlink() {
        return hyperlink;
    }
    
}

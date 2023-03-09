package view.dashboard;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import javax.swing.*;


public class SearchBar extends JLabel {

    JFrame frame;
    JTextField searchbar;
    JLabel searchIcon;



    public SearchBar() {

        searchIcon = new JLabel();
        searchIcon = createSearchBar(searchIcon);

        searchbar = new JTextField();
        searchbar = textField();

    }

    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 2, 1, w, h, null);
        g2.dispose();

        return resizedImg;
    }

    public JLabel createSearchBar(JLabel searchIcon) {

        searchIcon.setBackground(new Color(255, 255, 255));
        searchIcon.setOpaque(true);
        searchIcon.setBounds(10, 232, 210, 36);
        searchIcon.setIcon(addIcon());

        return searchIcon;
    }


    public ImageIcon addIcon() {
        String path = "/searchIcon.jpg";
        ImageIcon image = new ImageIcon(getClass().getResource(path));
        Image newImage = getScaledImage(image.getImage(), 26, 24);
        ImageIcon img = new ImageIcon(newImage);

        return img;
    }

    public JTextField textField() {

        searchbar.setBorder(null);
        searchbar.setBounds(49, 232, 157, 36);
        searchbar.setColumns(10);

        return searchbar;
    }



}

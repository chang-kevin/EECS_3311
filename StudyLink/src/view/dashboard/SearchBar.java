package view.dashboard;


import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;


public class SearchBar extends JLabel {

    JFrame frame;
    JTextField searchbar;
    JLabel searchIcon;



    public SearchBar() {

        searchIcon = new JLabel();
        searchIcon = createSearchBar(searchIcon);
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
        searchIcon.setBounds(10, 245, 210, 36);
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

    public HintTextField textField() {
        searchbar = new HintTextField("Search for e.g:- EECS 3311");
        searchbar.setBorder(null);
        searchbar.setBounds(49, 245, 157, 36);
        searchbar.setColumns(10);

        return (HintTextField) searchbar;
    }

    /**
     * Adds the input hint for the search bar prompt
     */
    class HintTextField extends JTextField implements FocusListener {

        private final String hint;
        private boolean showingHint;

        public HintTextField(final String hint) {
            super(hint);
            this.hint = hint;
            this.showingHint = true;
            super.addFocusListener(this);
        }

        @Override
        public void focusGained(FocusEvent e) {
            if(this.getText().isEmpty()) {
                super.setText("");
                showingHint = false;
            }
        }
        @Override
        public void focusLost(FocusEvent e) {
            if(this.getText().isEmpty()) {
                super.setText(hint);
                showingHint = true;
            }
        }

        @Override
        public String getText() {
            return showingHint ? "" : super.getText();
        }
    }


}
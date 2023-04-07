package view.dashboard;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class Profile extends RoundedPanel {


    private JLabel profileImagePanel;
    private JButton profileBtn;

    public Profile() {
        super(30, 30);


        setBackground(new Color(232, 240, 238));
        setLayout(new MigLayout("fill", "", "20[30%!]15[10%!]5[10%!]20"));
        setName();
        viewRole();
        setProfileImage();
    }

    public void setProfileImage() {
        profileImagePanel = new JLabel();

        String path = "/profilepicture.png";
        ImageIcon image = new ImageIcon(getClass().getResource(path));
        Image newImage = getScaledImage(image.getImage(), 70, 70);
        ImageIcon icon = new ImageIcon(newImage);

        profileImagePanel.setIcon(icon);
        profileImagePanel.setHorizontalAlignment(SwingConstants.CENTER);
        add(profileImagePanel, "cell 0 0 1 1, grow");
    }

    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 2, 1, w, h, null);
        g2.dispose();

        return resizedImg;
    }

    public void setName() {
        //JLabel userName = new JLabel(user.getFirstName() + " " + user.getLastName());
        JLabel username = new JLabel("angela manalo");
        username.setForeground(new Color(128, 128, 128));
        username.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 13));
        username.setBorder(null);
        username.setHorizontalAlignment(SwingConstants.CENTER);
        add(username, "cell 0 1 1 1, grow");

    }


    public void viewRole() {
        //role = new JLabel(user.getRole());
        JLabel role = new JLabel("role");
        role.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
        role.setForeground(new Color(53, 79, 82));
        role.setBorder(null);
        role.setHorizontalAlignment(SwingConstants.CENTER);
        add(role, "cell 0 2 1 1, grow");
    }








}

package view.dashboard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.SQLException;

import javax.swing.*;

import model.User.User;
import model.User.UserDAO;
import model.User.UserSession;
import net.miginfocom.swing.MigLayout;

public class Profile extends RoundedPanel {


    private User sessionUser;
    private UserDAO userDAO;
    private JLabel profileImagePanel;
    private User user;
    private JButton update;

    public Profile() throws SQLException {
        super(30, 30);
        userDAO = new UserDAO();
        sessionUser = UserSession.getInstance().getCurrentUser();
        user = userDAO.getUser(sessionUser.getUsername());

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
        JLabel username = new JLabel(user.getFirstName() + " " + user.getLastName());
        username.setForeground(new Color(128, 128, 128));
        username.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 13));
        username.setBorder(null);
        username.setHorizontalAlignment(SwingConstants.CENTER);
        add(username, "cell 0 1 1 1, grow");
    }


    public void viewRole() {
        JLabel role = new JLabel(user.getRole());
        role.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
        role.setForeground(new Color(53, 79, 82));
        role.setBorder(null);
        role.setHorizontalAlignment(SwingConstants.CENTER);
        add(role, "cell 0 2 1 1, grow");
    }

    public void update() {
        invalidate();
        setName();
        revalidate();
        repaint();
    }

}

package view.dashboard;

import controller.AccountManagement;
import model.User.User;
import model.User.UserDAO;
import model.User.UserSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.SQLException;

public class Profile extends JPanel implements ActionListener {

    JPanel profile;
    JButton profileBtn;
    private JLabel profileImagePanel;


    public Profile() throws SQLException {
        profile = new JPanel();
        profile = panelBorder(profile);
        profile.setBounds(10, 40, 208, 194);

        setName();
        viewProfileBtn();
        setProfileImage();

    }



    public void setName() throws SQLException {

        UserDAO userDAO = new UserDAO();
        User sessionUser = UserSession.getInstance().getCurrentUser();
        User user = userDAO.getUser(sessionUser.getUsername());


        JLabel userName = new JLabel(user.getFirstName() + " " + user.getLastName());
        userName.setForeground(new Color(128, 128, 128));
        userName.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 13));
        userName.setBorder(null);
        userName.setHorizontalAlignment(SwingConstants.CENTER);
        userName.setBounds(43, 124, 120, 25);
        profile.add(userName);

    }


    public void viewProfileBtn() {
        profileBtn = new JButton("View Profile");
        profileBtn.setFocusPainted(false);
        profileBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
        profileBtn.setForeground(new Color(244, 181, 181));
        profileBtn.setBackground(new Color(255, 255, 255, 200));
        profileBtn.setBorder(null);
        profileBtn.setOpaque(false);
        profileBtn.setBounds(59, 160, 90, 23);
        profileBtn.addActionListener(this);
        profile.add(profileBtn);

    }

    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 2, 1, w, h, null);
        g2.dispose();

        return resizedImg;
    }

    public void setProfileImage() {
        profileImagePanel = new JLabel();
        profileImagePanel.setBounds(45, 11, 115, 110);

        String path = "/profilepicture.png";
        ImageIcon image = new ImageIcon(getClass().getResource(path));
        Image newImage = getScaledImage(image.getImage(), profileImagePanel.getWidth(), profileImagePanel.getHeight());
        ImageIcon icon = new ImageIcon(newImage);

        profileImagePanel.setIcon(icon);
        profile.add(profileImagePanel);
    }

    public JPanel panelBorder(JPanel panel) {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension arcs = new Dimension(50, 50);
                int width = getWidth();
                int height = getHeight();
                Graphics2D graphics = (Graphics2D) g;
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics.setColor(new Color(255, 255, 255, 200));
                graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
                graphics.setColor(new Color(216, 237, 214));
                graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
            }
        };
        panel.setOpaque(false);
        panel.setLayout(null);


        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == profileBtn) {
            try {
                new AccountManagement();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
    }
}

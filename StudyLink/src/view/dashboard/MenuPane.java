package view.dashboard;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.miginfocom.swing.MigLayout;

public class MenuPane extends JPanel {

    private CardLayoutDisplay swap;

    private JLabel name;

    public MenuPane() {
        setBackground(new Color(53, 79, 82));
        setLayout(new GridBagLayout());

        setAppName();
        setConstraints();

    }

    public void setAppName() {
        name = new JLabel();
        name.setVerticalAlignment(SwingConstants.TOP);
        name.setFont(new Font("Microsoft JhengHei", Font.BOLD, 20));
        name.setBorder(new EmptyBorder(0, 5, 0, 0));
        name.setHorizontalAlignment(SwingConstants.CENTER);
        String green = "<font color='rgb(82, 121, 111)'>Study</font>";
        String coral = "<font color='rgb(239, 127, 127)'>Link</font>";
        name.setText("<html>" + green + coral + "</html>");
    }

    public void setConstraints() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridx = 0;
        c.gridy = 0;
        c.insets.bottom = 200;
        add(name, c);

        MenuButtons menu = new MenuButtons();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1;
        c.insets.bottom = 0;
        c.gridx = 0;
        c.gridy = 1;
        add(menu.dashboardButton, c);
        c.gridx = 0;
        c.gridy = 2;
        add(menu.uploadButton, c);
        c.gridx = 0;
        c.gridy = 3;
        add(menu.levels, c);
        c.gridx = 0;
        c.gridy = 4;
        add(menu.one, c);
        c.gridx = 0;
        c.gridy = 5;
        add(menu.two, c);
        c.gridx = 0;
        c.gridy = 6;
        c.insets.bottom = 100;
        add(menu.three, c);
    }



    public void setController(CardLayoutDisplay swap) {
        this.swap = swap;
    }

    class MenuButtons extends JButton implements ActionListener {

        private JButton dashboardButton;
        private JButton uploadButton;
        private JButton one;
        private JButton two;
        private JButton three;
        private JLabel levels;

        public MenuButtons() {

            dashboardButton = new JButton("Dashboard");
            setStyle(dashboardButton);

            uploadButton = new JButton("Upload");
            setStyle(uploadButton);
            levels = new JLabel("Course Levels");
            setLabel(levels);

            one = new JButton("1000 Level");
            setStyle(one);

            two = new JButton("2000 Level");
            setStyle(two);

            three = new JButton("3000 Level");
            setStyle(three);


        }

        public void setStyle(JButton btn) {
            btn.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 18));
            btn.setForeground(Color.black);
            btn.setContentAreaFilled(false);
            btn.addActionListener(this);
            btn.setHorizontalTextPosition(SwingConstants.CENTER);
            btn.setFocusPainted(false);
        }

        public void setLabel(JLabel label) {
            label.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 18));
            label.setForeground(new Color(255, 255, 255));
            label.setHorizontalAlignment(SwingConstants.CENTER);
        }

        public void visibleSubMenu(boolean isVisible) {
            one.setVisible(isVisible);
            two.setVisible(isVisible);
            three.setVisible(isVisible);
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == dashboardButton) {
                swap.swapTo("bookmark");
            }
            else if(e.getSource() == one) {
                swap.swapTo("one");
            }
            else if(e.getSource() == two) {
                swap.swapTo("two");
            }
            else if(e.getSource() == three) {
                swap.swapTo("three");
            }
            else if(e.getSource() == uploadButton) {
                swap.swapTo("upload");
            }

        }
    }
}


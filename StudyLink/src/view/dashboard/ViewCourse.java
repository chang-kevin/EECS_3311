package view.dashboard;

import java.awt.*;
import javax.swing.*;


public class ViewCourse {

    JPanel coursePage;
    private JPanel container;


    public ViewCourse() {

        createPanel();
        createContainer();
        courseTitle();
        createDescription();
        courseInformation();
        getTopics();
        courseContent();


    }

    public void createPanel() {
        coursePage = new JPanel();
        coursePage = panelBorder(coursePage);
    }

    public void courseTitle() {
    }

    public void createContainer() {
        container = new JPanel();
        container.setBackground(new Color(255, 255, 255));
        container.setBounds(10, 60, 517, 277);
        coursePage.add(container);
        container.setLayout(null);
    }

    public void createDescription() {
        JLabel descriptionTitle = new JLabel("Description");
        descriptionTitle.setForeground(new Color(241, 146, 146));
        descriptionTitle.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 13));
        descriptionTitle.setBounds(10, 0, 144, 27);
        container.add(descriptionTitle);
    }

    public void courseInformation() {
        JLabel courseInfo = new JLabel("course description here");
        courseInfo.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        courseInfo.setVerticalAlignment(SwingConstants.TOP);
        courseInfo.setBounds(20, 30, 487, 55);
        container.add(courseInfo);
    }

    public void createTopicPanel() {
        JLabel topicTitle = new JLabel("Topic here");
        topicTitle.setForeground(new Color(241, 146, 146));
        topicTitle.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 13));
        topicTitle.setBounds(10, 84, 497, 27);
        container.add(topicTitle);
    }

    public void getTopics() {
        //get all topics then iterate
        createTopicPanel();

    }

    public void courseContent() {
        JLabel content = new JLabel();
        content.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        content.setText("list all content ");
        content.setBounds(20, 111, 487, 27);
        container.add(content);
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
                graphics.setColor(new Color(213, 237, 209));
                graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
                graphics.setColor(new Color(213, 237, 209));
                graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
            }
        };

        panel.setBounds(169, 203, 537, 348);
        panel.setOpaque(false);
        panel.setLayout(null);
        JLabel courseName = new JLabel("Course Name");
        courseName.setBounds(10, 11, 246, 38);
        panel.add(courseName);
        courseName.setForeground(new Color(241, 146, 146));
        courseName.setBackground(new Color(255, 255, 255));
        courseName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));


        return panel;
    }




}

package helpers;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import components.Dashboard_components.Dashboard;

public class CourseLevelDesign extends Dashboard{

    public JButton createCourseButton(String courseName) {
		JButton course = new JButton(courseName);
		course.setFocusPainted(false);
		course.setHorizontalAlignment(SwingConstants.LEFT);
		course.setFont(new Font("Tahoma", Font.PLAIN, 25));
		course.setBackground(new Color(255, 204, 182));
		course.setForeground(new Color(255, 255, 255));
		course.setBorder(BorderFactory.createLineBorder(new Color(221, 221, 221), 5));
		course.addActionListener(this);
		return course;
	}
    public JLabel createCourseHeader(String title) {
		JLabel level = new JLabel(title);
        level.setForeground( new Color(133, 153, 205));
		level.setVerticalAlignment(SwingConstants.TOP);
		level.setBorder(new LineBorder(new Color(133, 153, 205), 0));
		level.setFont( new Font("Tahoma", Font.PLAIN, 28));	
		return level;
	}
    public JPanel createPanel() {
		JPanel thePanel = new JPanel();
		thePanel.setBackground(new Color(240, 240, 240));
		thePanel.setBounds(0, 175, 834, 336);
		thePanel.setLayout(null);
		return thePanel;
	}

}

package view.dashboard;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import helpers.CourseLevelDesign;

import java.awt.event.*;


/**
 * 
 * Course page that displays the full list of courses by year level. 
 *
 */

public class ThirdYearCourses implements CourseLevelDesign, ActionListener {

	JPanel courses;
	private Dashboard dashboard;
	private JButton eecs_3311;

	private JLabel header;
	
	public ThirdYearCourses(){

		dashboard = new Dashboard();
		
		courses = createPanel();
		dashboard.frame.add(courses);
		
		eecs_3311 = createCourseButton("    \rEECS 3311");
		eecs_3311.setBounds(37, 93, 248, 148);
		courses.add(eecs_3311);
		
		header = createCourseHeader("   3000 Level Courses");
		header.setBounds(37, 25, 772, 53);
		courses.add(header);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(133, 153, 205));
		separator.setBounds(60, 62, 732, 2);
		courses.add(separator);
		
	}
	
	/**
	 * Overrides the actionPerformed method from ActionListener interface. 
	 * @param e event to be processed. 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==eecs_3311) {
			dashboard.frame.dispose();
			new CourseResource();
		}
	}

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
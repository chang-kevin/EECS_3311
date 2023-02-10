package components.Dashboard_components;
import java.awt.*;
import javax.swing.*;

import helpers.CourseLevelDesign;

import java.awt.event.*;


/**
 * 
 * Course page that displays the full list of courses by year level. 
 *
 */

public class ThirdYearCourses extends CourseLevelDesign implements ActionListener {

	JPanel courses;
	private Dashboard dashboard;
	private JButton eecs_3311;

	private JLabel header;

	
	public ThirdYearCourses(){
	
		dashboard = new Dashboard();

		courses = createPanel();
		dashboard.frame.getContentPane().add(courses);
		
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
			new CourseResource();
			dashboard.frame.dispose();
		}
	}


}
package components.Dashboard_components;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import helpers.CourseButton;
import helpers.CourseLevelHeader;

import java.awt.event.*;

/**
 * 
 * Course page that displays the full list of courses by year level. 
 *
 */

public class ThirdYearCourses extends JFrame implements ActionListener {

	private Dashboard dashboard;
	private JTextField border;
	private JButton eecs_3311;
    private final Border courseBorder = new LineBorder(new Color(133, 153, 205), 0);
	private JLabel header;

	
	public ThirdYearCourses() {
	
		dashboard = new Dashboard();

		JPanel courses = new JPanel();
		courses.setBackground(new Color(240, 240, 240));
		courses.setBounds(0, 175, 834, 336);
		dashboard.frame.getContentPane().add(courses);
		courses.setLayout(null);
		
		eecs_3311 = new CourseButton("    \rEECS 3311", new Font("Tahoma", Font.PLAIN, 25), new Color(255, 204, 182), new Color(255, 255, 255));
		eecs_3311.setBounds(37, 93, 248, 148);
		eecs_3311.addActionListener(this);
		courses.add(eecs_3311);
		
		header = new CourseLevelHeader("   3000 Level Courses", new Color(133, 153, 205), courseBorder, new Font("Tahoma", Font.PLAIN, 28));
		header.setBounds(37, 25, 772, 53);
		courses.add(header);
		
		border = new JTextField();
		border.setCaretColor(new Color(192, 192, 192));
		border.setBorder(new LineBorder(new Color(221, 221, 221), 5));
		border.setBounds(31, 87, 260, 160);
		courses.add(border);
		border.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(133, 153, 205));
		separator.setBounds(60, 62, 732, 2);
		courses.add(separator);
		
		JPanel db_panel = new JPanel();
		db_panel.setBounds(0, 0, 834, 511);
		dashboard.frame.getContentPane().add(db_panel);
		db_panel.setLayout(null);
	
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
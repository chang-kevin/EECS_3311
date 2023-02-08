package components;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

/**
 * 
 * Course page that displays the full list of courses by year level. 
 *
 */

public class CourseGUI extends JFrame implements ActionListener {

	private Dashboard dashboard;
	private JTextField border;
	private JButton coursebtn;

	
	public CourseGUI() {
	
		dashboard = new Dashboard();

		JPanel courses = new JPanel();
		courses.setBackground(new Color(240, 240, 240));
		courses.setBounds(0, 175, 834, 336);
		dashboard.frame.getContentPane().add(courses);
		courses.setLayout(null);
		
		coursebtn = new JButton("    \rEECS 3311");
		coursebtn.setFocusPainted(false);
		coursebtn.setHorizontalAlignment(SwingConstants.LEFT);
		coursebtn.setFont(new Font("Tahoma", Font.PLAIN, 25));
		coursebtn.setBackground(new Color(255, 204, 182));
		coursebtn.setForeground(new Color(255, 255, 255));
		coursebtn.setBorder(null);
		coursebtn.setBounds(37, 93, 248, 148);
		coursebtn.addActionListener(this);
		courses.add(coursebtn);
		
		JLabel lblNewLabel = new JLabel("   3000 Level Courses");
		lblNewLabel.setForeground(new Color(133, 153, 205));
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBorder(new LineBorder(new Color(133, 153, 205), 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setBounds(37, 25, 772, 53);
		courses.add(lblNewLabel);
		
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
		if(e.getSource()==coursebtn) {
			new CourseResource();
			dashboard.frame.dispose();
		}
	}
}
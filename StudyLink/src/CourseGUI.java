import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CourseGUI extends JFrame {

	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CourseGUI frame = new CourseGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CourseGUI() {
		
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 850, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(new Color(255, 255, 255));
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		
		JPanel courses = new JPanel();
		courses.setBackground(new Color(240, 240, 240));
		courses.setBounds(0, 175, 834, 336);
		frame.getContentPane().add(courses);
		courses.setLayout(null);
		
		JButton coursebtn = new JButton("    \rEECS 3311");
		coursebtn.setFocusPainted(false);
		coursebtn.setHorizontalAlignment(SwingConstants.LEFT);
		coursebtn.setFont(new Font("Tahoma", Font.PLAIN, 25));
		coursebtn.setBackground(new Color(255, 204, 182));
		coursebtn.setForeground(new Color(255, 255, 255));
		coursebtn.setBorder(null);
		coursebtn.setBounds(34, 111, 247, 148);
		courses.add(coursebtn);
		
		JLabel lblNewLabel = new JLabel("   3000 Level Courses");
		lblNewLabel.setForeground(new Color(133, 153, 205));
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBorder(new LineBorder(new Color(128, 128, 128), 2));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel.setBounds(34, 11, 772, 67);
		courses.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setCaretColor(new Color(192, 192, 192));
		textField.setBorder(new LineBorder(new Color(221, 221, 221), 5));
		textField.setBounds(28, 107, 259, 157);
		courses.add(textField);
		textField.setColumns(10);
		
		JPanel db_panel = new JPanel();
		db_panel.setBounds(0, 0, 834, 511);
		frame.getContentPane().add(db_panel);
		db_panel.setLayout(null);
	
	}
}

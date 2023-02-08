package components;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import helpers.CustomButton;

/**
 * Dashboard component as the home page and container for the taskbar features. 
 */

public class Dashboard implements ActionListener, ChangeListener {

	JFrame frame;
	
	private JPanel name_bg;
    private JPanel taskbar;

	private JButton addCourse;
	private JButton courseList;
	private JButton profile;

	private JButton logoutButton;
	private JButton accountButton;
	private JButton settingsButton;

	private JButton one;
	private JButton two;
	private JButton three;

	private JTextField searchbar;
	
	private final JPanel courseMenu;
	private final JPanel profileMenu; 
	
	/**
	 * Create the application.
	 */
	public Dashboard() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 850, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(new Color(255, 255, 255));
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		
		name_bg = new JPanel();
		name_bg.setBounds(0, 0, 834, 175);
		name_bg.setBackground(new Color(117, 153, 193));
		frame.getContentPane().add(name_bg);
		name_bg.setLayout(null);
		
		taskbar = new JPanel();
		taskbar.setBounds(0, 0, 1921, 55);
		taskbar.setBackground(new Color(70, 99, 172));
		name_bg.add(taskbar);
		taskbar.setLayout(null);
		
		CustomButton btn = new CustomButton();
		
		addCourse = btn.taskBarButton("+");;
		addCourse.setBounds(0, 0, 78, 55);
		addCourse.addActionListener(this);
		taskbar.add(addCourse);
		
		profile = btn.taskBarButton("|     Profile   ");
		profile.getModel().addChangeListener(this);
		profile.addActionListener(this);
		profile.setBounds(614, 3, 220, 55);
		taskbar.add(profile);
		
		searchbar = new JTextField();
		searchbar.setBorder(null);
		searchbar.setBackground(new Color(198, 207, 232, 100));
		searchbar.setSelectedTextColor(new Color(198, 207, 232));
		searchbar.setBounds(329, 11, 285, 33);
		taskbar.add(searchbar);
		searchbar.setColumns(10);
		
		courseMenu = new JPanel();
		courseMenu.setAlignmentX(0.0f);
		courseMenu.setDoubleBuffered(false);
		courseMenu.setBackground(new Color(70, 99, 172, 150));
		courseMenu.setBounds(110, 0, 188, 55);
		name_bg.add(courseMenu);
		courseMenu.setLayout(null);
		
		courseList = btn.taskBarButton("|   Course List   |");
		courseList.addActionListener(this);
		courseList.getModel().addChangeListener(this);
		courseList.setBounds(88, 0, 234, 55);
		taskbar.add(courseList);
		
		one = btn.courseListMenu("1000 Level");
		one.addActionListener(this);
		one.getModel().addChangeListener(this);
		one.setBounds(0, 55, 188, 40);
		courseMenu.add(one);
	
		two = btn.courseListMenu("3000 Level");
		two.setBounds(0, 134, 188, 40);
		two.addActionListener(this);
		two.getModel().addChangeListener(this);
		courseMenu.add(two);
		
		three = btn.courseListMenu("2000 Level");
		three.setBounds(0, 94, 188, 40);
		three.addActionListener(this);
		three.getModel().addChangeListener(this);
		courseMenu.add(three);
		
		
		
		profileMenu = new JPanel();
		profileMenu.setLayout(null);
		profileMenu.setDoubleBuffered(false);
		profileMenu.setBackground(new Color(70, 99, 172));
		profileMenu.setBounds(646, 0, 188, 55);
		name_bg.add(profileMenu);
		
		accountButton = btn.profileListMenu("Account");
		accountButton.getModel().addChangeListener(this);
		accountButton.setBounds(0, 55, 188, 40);
		accountButton.addActionListener(this);
		profileMenu.add(accountButton);
		
		logoutButton = btn.profileListMenu("Log out");	
		logoutButton.getModel().addChangeListener(this);	
		logoutButton.setBounds(0, 134, 188, 40);
		logoutButton.addActionListener(this);
		profileMenu.add(logoutButton);
		
		settingsButton = btn.profileListMenu("Settings");
		settingsButton.getModel().addChangeListener(this);
		settingsButton.setBounds(0, 94, 188, 40);
		settingsButton.addActionListener(this);
		profileMenu.add(settingsButton);
		
	}
	
	/**
	 * Overrides the actionPerformed method from ActionListener interface. 
	 * @param e event to be processed. 
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==logoutButton) {
			
			new LoginPage(); // takes the user back to the login page
			frame.setVisible(false); //exits the dashboard upon clicking the logout button
			frame.dispose();
		}
		
		if(e.getSource() == addCourse){ // displays the pop up window for the upload page 
					new addFile();
		}

		if (e.getSource()==two) { // takes the user to the 3000 level courses page 
			new CourseGUI(); 
			frame.dispose();
		}
	}
	
	/**
	 * Overrides the stateChanged method from the interface ChangeListener. Invoked
	 * when the target has changed its state. 
	 * @param event to be processed. 
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		ButtonModel btn = (ButtonModel) e.getSource(); 
			if(btn == courseList.getModel() || btn == one.getModel()|| btn == two.getModel() ||
					btn == three.getModel()) {
				if(btn.isRollover()) {
					courseMenu.setSize(188, 234);
				}
				else {
					courseMenu.setSize(0, 0);
				}
			}
			else {
				if(btn.isRollover()) {
					profileMenu.setSize(188, 175);
				}
				else {
					profileMenu.setSize(0, 0);
				}
			}
			
	}
		
}



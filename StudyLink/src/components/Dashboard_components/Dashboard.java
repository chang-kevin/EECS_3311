package components.Dashboard_components;

import components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * Dashboard component as the home page and container for the taskbar features. 
 */

public class Dashboard implements ActionListener, ChangeListener, DashboardGUI {

	JFrame frame;

	private JPanel name_bg;
    private JPanel taskbar;
	private JPanel courseMenu;
	private JPanel profileMenu; 

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
	
	/**
	 * Create the application.
	 */
	public Dashboard() {
		
		frame = createFrame("Dashboard");
		
		name_bg = createPanel();
		frame.getContentPane().add(name_bg);

		taskbar = createPanel();
		taskbar.setBounds(0, 0, 1921, 55);
		taskbar.setBackground(new Color(70, 99, 172));
		name_bg.add(taskbar);
		
		addCourse = createTaskbarButton("+");;
		addCourse.setBounds(0, 0, 78, 55);
		taskbar.add(addCourse);
		
		profile = createTaskbarButton("|     Profile   ");
		profile.setBounds(614, 3, 220, 55);
		taskbar.add(profile);
		
		searchbar = new JTextField();
		searchbar.setBorder(null);
		searchbar.setBackground(new Color(198, 207, 232, 100));
		searchbar.setSelectedTextColor(new Color(198, 207, 232));
		searchbar.setBounds(329, 11, 285, 33);
		taskbar.add(searchbar);
		searchbar.setColumns(10);
		
		courseMenu = createMenuPanel();
		courseMenu.setAlignmentX(0.0f);
		courseMenu.setBounds(110, 0, 188, 55);
		name_bg.add(courseMenu);
		
		courseList = createTaskbarButton("|   Course List   |");
		courseList.setBounds(88, 0, 234, 55);
		taskbar.add(courseList);
		
		one = createMenuButton("1000 Level");
		one.setBounds(0, 55, 188, 40);
		courseMenu.add(one);
	
		two = createMenuButton("3000 Level");
		two.setBounds(0, 134, 188, 40);
		courseMenu.add(two);
		
		three = createMenuButton("2000 Level");
		three.setBounds(0, 94, 188, 40);
		courseMenu.add(three);
		
		profileMenu = createMenuPanel();
		profileMenu.setBounds(646, 0, 188, 55);
		name_bg.add(profileMenu);
		
		accountButton = createMenuButton("Account");
		accountButton.setBounds(0, 55, 188, 40);
		profileMenu.add(accountButton);
		
		logoutButton = createMenuButton("Log out");	
		logoutButton.setBounds(0, 134, 188, 40);
		profileMenu.add(logoutButton);
		
		settingsButton = createMenuButton("Settings");
		settingsButton.setBounds(0, 94, 188, 40);
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
			frame.dispose();
		}
		
		if(e.getSource() == addCourse){ // displays the pop up window for the upload page 
					new addFile();
		}

		if (e.getSource()==two) { // takes the user to the 3000 level courses page 
			new ThirdYearCourses();
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

	@Override
	public JButton createTaskbarButton(String name) {
		JButton taskbarButton = new JButton(name);
		taskbarButton.setText(name);
		taskbarButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		taskbarButton.setFocusPainted(false);
		taskbarButton.setBorderPainted(false);
		taskbarButton.setForeground(Color.WHITE);
		taskbarButton.setBackground(new Color(70, 99, 172, 150));
		if (name!="+") {
			taskbarButton.getModel().addChangeListener(this);
		}
		taskbarButton.addActionListener(this);
		return taskbarButton;
	}

	@Override
	public JFrame createFrame(String title) {
		JFrame theFrame = new JFrame(title);
        theFrame.setBounds(100, 100, 850, 550);
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theFrame.setBackground(new Color(255, 255, 255));
		theFrame.setVisible(true);
		theFrame.getContentPane().setLayout(null);
		return theFrame;
	}

	@Override
	public JButton createMenuButton(String name) {
		JButton menuButton = new JButton(name);
		menuButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		menuButton.setFocusPainted(false);
		menuButton.setBorderPainted(false);
		menuButton.setForeground(new Color(198, 207, 232, 150));
		menuButton.setBackground(new Color(70, 99, 172));
		menuButton.getModel().addChangeListener(this);
		menuButton.addActionListener(this);
		return menuButton;
	}

	@Override
	public JPanel createPanel() {
		JPanel thePanel = new JPanel();
		thePanel.setBounds(0, 0, 834, 175);
		thePanel.setBackground(new Color(117, 153, 193));
		thePanel.setLayout(null);
		return thePanel;
	}

	@Override
	public JPanel createMenuPanel() {
		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(null);
		menuPanel.setDoubleBuffered(false);
		menuPanel.setBackground(new Color(70, 99, 172));
		return menuPanel;
	}
	
}


